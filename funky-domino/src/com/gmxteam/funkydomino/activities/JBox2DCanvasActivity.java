/*
 *   This file is part of Funky Domino.
 *
 *   Funky Domino is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Funky Domino is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Funky Domino.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gmxteam.funkydomino.activities;

// Importations locales
import com.gmxteam.funkydomino.graphicals.components.Component;
import com.gmxteam.funkydomino.graphicals.widgets.Widget;

// Importations pour le moteur de collisions
import org.jbox2d.collision.AABB;
import org.jbox2d.collision.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

// Importations pour le moteur de rendu
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

// Librairies standard Android
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.MotionEvent;
import android.view.View;

// Librairie standard Java
import com.gmxteam.funkydomino.graphicals.components.Domino;
import java.util.ArrayList;

/**
 * Classe abstraite permettant une implémentation efficace d'un interface JBox2D.
 * Funky Domino sera premièrement développé en canvas afin d'obtenir rapidement
 * des résultats. Il sera ensuite converti en OpenGL afin d'en améliorer
 * considérablement les performances.
 * 
 * Le fonctionnement est simple. On redéfinit une activité android en y intégrant
 * un moteur de physique et de rendu. On intègre aussi certaines interactions
 * avec l'utilisateur pour minimiser le code des activités.
 * 
 * Les éléments d'interfaces qui seront alors utilisés pourront être ceux de la
 * librairie standard, mais il est recommandé d'utiliser des élément de physique
 * afin de nous donner plus de liberté. Un menu animé par la physique, c'est pas
 * cool ça?
 * 
 * @author Guillaume Poirier-Morency
 */
public abstract class JBox2DCanvasActivity extends Activity {

    ////////////////////////////////////////////////////////////////////////////
    // Variables d'environnement
    private final boolean IS_DEBUG_ENABLED = true;
    private long renderingTime = 0;
    private long drawnWidgets = 0;
    private long drawnComponents = 0;
    private long redrawing = 0;
    private long calculating = 0;
    ////////////////////////////////////////////////////////////////////////////
    /**
     * On dessine sur une surface OpenGL ES 2.0.
     */
    private View canvasView;
    ////////////////////////////////////////////////////////////////////////////
    // Variables pour le moteur de collisions
    World world;
    private int targetFPS = 40;
    private int timeStep = (1000 / targetFPS);
    private int iterations = 5;
    private AABB worldAABB;
    private Handler mHandler;
    private Runnable update = new Runnable() {

        public void run() {
            long timeBefore = System.currentTimeMillis();
            world.step(timeStep, iterations);
            calculating++;
            canvasView.invalidate();
            redrawing++;
            renderingTime = System.currentTimeMillis() - timeBefore;
            mHandler.postDelayed(update, 15);
            // TODO Corriger l'influence du temps de rendu
            
        }
    };
    ////////////////////////////////////////////////////////////////////////////
    // Outils de conversion

    /**
     * Transforme une valeur en pixels en mètres. Prend en considération les
     * dimensions de l'écran.
     * @param meter est une valeur en mètres.
     * @return une valeur en pixels.
     */
    public static float toPixel(float meter) {
        return 1.5f * meter;
    }

    /**
     * Transforme une valeur en mètres en pixels. Prend en considération les
     * dimensions de l'écran.
     * @param pixel est une valeur en pixels.
     * @return une valeur en pixels.
     */
    public static float toMeter(float pixel) {
        return 3f * pixel;
    }
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Initialisation du rendu 2D.
     */
    private void init() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        canvasView = new View(this) {

            @Override
            public void onDraw(Canvas c) {

                onDrawFrame(c);


                drawnComponents = 0;
                drawnWidgets = 0;
            }
        };
        
        // On configure le moteur de physique
        worldAABB = new AABB();
        worldAABB.lowerBound.set(new Vec2((float) -100.0, (float) -100.0));
        worldAABB.upperBound.set(new Vec2((float) 100.0, (float) 100.0));

        // On ajoute la gravité et le worldAABB dans world
        world = new World(worldAABB, new Vec2(0.0f, -9.8f), false);

        // On démarre le Thread qui va gérer le moteur de physique

        mHandler = new Handler();
        mHandler.post(update);

        // On cache le menu
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // On mets l'application en plein écran
        final Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ////////////////////////////////////////////////////////////////////////
        // LE CODE ICI EST UTILISÉ TEMPORAIREMENT À FINS DE TESTS ! UNE FOIS LE
        // PARSER CODÉ, IL NE SERA PLUS NÉCÉSSAIRE DE CRÉER NOUS-MÊMES NOS 
        // OBJETS !!
        buildLevel();
        ////////////////////////////////////////////////////////////////////////
        // On définit la surface 2D comme surface de dessin
        setContentView(canvasView);
    }

    /**
     * Méthode utilisée pour construire un niveau.
     * @deprecated Ceci n'est pas une méthode standard à utiliser ! Elle est 
     * temporaire, jusqu'à ce le parser XML soit terminé !
     */
    @Deprecated
    private void buildLevel() {
    }

    ////////////////////////////////////////////////////////////////////////////
    // Contrôleurs
    /**
     * 
     * @param savedInstance 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * Méthode appelé quand le programme se met en pause.
     */
    @Override
    protected void onPause() {
        super.onPause();
        // On vide le handler.
        //mHandler = new Handler();
    }

    /**
     * Méthode appelée quand le programme est en pause et qu'il se fait 
     * réveiller.
     */
    @Override
    protected void onResume() {
        super.onResume();
        // On redémarre le handler.
        //mHandler.post(update);
    }

    ////////////////////////////////////////////////////////////////////////////
    // User input handling
    /**
     * 
     * @param me
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent me) {
        new Domino(world);
        // TODO Mettre le MotionEvent dans le AABB.
        AABB areaAABB = new AABB();
        // On récupère la liste des shapes dans la zone touchée.
        Shape[] shapeList = world.query(areaAABB, 500);
        // Si la zone contient des widgets ou composants, on les avertit.
        for (Shape clickedShape : shapeList) {
            if (clickedShape.getBody().getUserData() instanceof Widget) {
                ((Widget) clickedShape.getBody().getUserData()).onClick(me);
            } else if (clickedShape.getBody().getUserData() instanceof Component) {
                ((Component) clickedShape.getBody().getUserData()).onClick(me);
            }
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////
    // Handler pour le rendu 2D (se fait automatiquement)
    /**
     * Méthode appelée quand la surface est dessinée.
     * @param canvas est la surface sur laquelle la surface sera dessinée.
     */
    private void onDrawFrame(Canvas canvas) {
        drawBackground(canvas);
        Body b = this.world.getBodyList();
        ArrayList<Widget> drawWidgetLast = new ArrayList<Widget>();
        do {
            // Draw the body
            if (b.getUserData() instanceof Widget) {
                drawWidgetLast.add((Widget) b.getUserData());
            } else if (b.getUserData() instanceof Component) {
                Component c = (Component) b.getUserData();
                drawnComponents++;
                c.drawCanvas(canvas);

            } else {
                // Crap.
                // throw new UnknownGraphicalElementException();
            }
        } while ((b = b.getNext()) != null);
        for (Widget w : drawWidgetLast) {

            drawnWidgets++;
            w.drawCanvas(canvas);

        }
        if (IS_DEBUG_ENABLED) {
            drawDebug(canvas);
        }

    }
    ////////////////////////////////////////////////////////////////////////////
    // Méthode de dessinage

    /**
     * Dessine l'arrière-plan.
     * @param c est le canvas sur lequel l'arrière-plan sera dessiné.
     */
    private void drawBackground(Canvas c) {
        c.drawColor(Color.WHITE);
    }

    /**
     * Dessine les variables d'environnement.
     * @param c est le canvas sur lequel l'les variables de débogage seront
     * dessinées.
     */
    private void drawDebug(Canvas c) {
        Paint p = new Paint();
        float initP = 10.0f;
        c.drawText("Nombre de corps dessinés : " + world.getBodyCount() + " corps", 15.0f, initP += 15.0f, p);
        c.drawText("Widgets dessinés : " + drawnWidgets + " widgets", 15.0f, initP += 15.0f, p);
        c.drawText("Composants dessinés : " + drawnComponents + " composants" + "", 15.0f, initP += 15.0f, p);
        c.drawText("Autres corps dessinés : " + (world.getBodyCount() - drawnComponents - drawnWidgets) + " composants" + "", 15.0f, initP += 15.0f, p);
        c.drawText("Gravité : " + world.getGravity() + " m/s^2", 15.0f, initP += 15.0f, p);
        c.drawText("Temps du rendu : " + renderingTime + " ms", 15.0f, initP += 15.0f, p);
        c.drawText("Vecteurs pour les dimensions de l'écran : " + world.getWorldAABB().lowerBound + " et " + world.getWorldAABB().upperBound, 15.0f, initP += 15.0f, p);
        c.drawText("Nombre de mises à jour du moteur de physique : " + calculating + " calculs", 15.0f, initP += 15.0f, p);
        c.drawText("Nombre de mises à jour du rendu : " + redrawing + " rendus", 15.0f, initP += 15.0f, p);

    }
    ////////////////////////////////////////////////////////////////////////////
}
