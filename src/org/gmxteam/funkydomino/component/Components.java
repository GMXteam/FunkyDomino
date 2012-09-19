package org.gmxteam.funkydomino.component;

import android.util.Log;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.component.entity.AddDominoButton;
import org.gmxteam.funkydomino.component.entity.Ball;
import org.gmxteam.funkydomino.component.entity.Cog;
import org.gmxteam.funkydomino.component.entity.Domino;
import org.gmxteam.funkydomino.component.entity.Ground;
import org.gmxteam.funkydomino.component.entity.Water;

/**
 * Énumération des éléments Factorable que l'on peut retrouver dans FunkyDomino.
 * Il permet entre autre de récupéré une version Factorable pour pouvoir
 * l'implémenter de façon générique.
 *
 * @author guillaume
 */
public enum Components {
    /**
     *
     */
    add_domino_button(AddDominoButton.class),
    /**
     *
     */
    ground(Ground.class),
    /**
     *
     */
    water(Water.class),
    /**
     *
     */
    domino(Domino.class),
    /**
     *
     */
    ball(Ball.class),
    /**
     *
     */
    cog(Cog.class);
    /**
     * 
     */
    
    
    private Class mClass;

    Components(Class c) {
        mClass = c;
    }

    /**
     *
     * @return
     */
    public static String[] strings() {
        String[] names = new String[values().length];
        int i = 0;
        for (Components value : values()) {
            names[i] = value.name();
            i++;
        }
        return names;
    }

    /**
     *
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Component getComponent() throws InstantiationException, IllegalAccessException {
        Log.v(FunkyDominoActivity.LOG_TAG, "Création d'une instance de type " + this.name());

        return (Component) mClass.newInstance();
    }
}
