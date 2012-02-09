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
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

// Librairie standard Java
import com.gmxteam.funkydomino.graphicals.UnknownGraphicalElementException;
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
            update();
            canvasView.postInvalidate();
            mHandler.postDelayed(update, (long) (timeStep * 1000));
        }
    };
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Initialisation du rendu 2D.
     */
    void init() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        canvasView = new View(this) {

            @Override
            public void onDraw(Canvas c) {

                onDrawFrame(c);


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

        // On définit la surface OpenGL comme surface de dessin
        setContentView(canvasView);
    }

    /**
     * Méthode appelée pour mettre à jour le rendu graphique et le moteur de physique.
     */
    private void update() {
        // Update Physics World
        world.step(timeStep, iterations);

        Body b = this.world.getBodyList();
        do {
            Vec2 position = b.getPosition();
            float angle = b.getAngle();
            Log.v("funky-domino physic engine", "Pos: (" + position.x + ", " + position.y + "), Angle: " + angle);
        } while ((b = b.getNext()) != null);
    }

    ////////////////////////////////////////////////////////////////////////////
    // Contrôleurs
    @Override
    protected void onPause() {
        super.onPause();
        //glDrawingArea.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    ////////////////////////////////////////////////////////////////////////////
    // User input handling
    @Override
    public boolean onTouchEvent(MotionEvent me) {
        new Domino(world);
        AABB areaAABB = new AABB();
        // TODO Mettre le MotionEvent dans le AABB.
        for (Shape clickedShape : world.query(areaAABB, 500)) {
            if (clickedShape.getBody().getUserData() instanceof Widget) {
                ((Widget) clickedShape.getBody().getUserData()).onClick(me);
            }
            else if (clickedShape.getBody().getUserData() instanceof Component) {
                ((Component) clickedShape.getBody().getUserData()).onClick(me);
            }
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////
    // Handler pour le rendu 2D (se fait automatiquement)
    /**
     * Méthode appelée quand la surface est dessinée.
     * @param gl 
     */
    private void onDrawFrame(Canvas canvas) {
        drawBackground(canvas);
        Body b = this.world.getBodyList();
        ArrayList<Widget> drawWidgetLast = new ArrayList<Widget>();
        do {
            // Draw the body
            if (b.getUserData() instanceof Widget) {
                drawWidgetLast.add((Widget) b.getUserData());
            }
            else if (b.getUserData() instanceof Component) {
                Component c = (Component) b.getUserData();
                c.drawCanvas(canvas);
            }
            else {
                // Crap.
                throw new UnknownGraphicalElementException();
            }
        } while ((b = b.getNext()) != null);
        for (Widget w : drawWidgetLast) {
            w.drawCanvas(canvas);
        }
        if (IS_DEBUG_ENABLED) {
            drawDebug(canvas);
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    // Méthode de dessinage

    private void drawBackground(Canvas c) {
    }

    private void drawDebug(Canvas c) {
        Paint p = new Paint();
        c.drawColor(Color.WHITE);
        c.drawText("Nombre de composants dessinés : " + world.getBodyCount(), 20.0f, 20.0f, p);
    }
    ////////////////////////////////////////////////////////////////////////////
}
