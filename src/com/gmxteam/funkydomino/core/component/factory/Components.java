package com.gmxteam.funkydomino.core.component.factory;

import android.util.Log;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.core.component.Ball;
import com.gmxteam.funkydomino.core.component.Cog;
import com.gmxteam.funkydomino.core.component.Component;
import com.gmxteam.funkydomino.core.component.Domino;
import com.gmxteam.funkydomino.core.component.Ground;
import com.gmxteam.funkydomino.core.component.Water;

/**
 * Énumération des éléments Factorable que l'on peut retrouver dans FunkyDomino.
 * Il permet entre autre de récupéré une version Factorable pour pouvoir
 * l'implémenter de façon générique.
 *
 * @author guillaume
 */
public enum Components {

    ground(Ground.class),
    water(Water.class),
    domino(Domino.class),
    ball(Ball.class),
    cog(Cog.class);
    private Class mClass;

    Components(Class c) {
        mClass = c;
    }

    public static String[] strings() {
        String[] names = new String[values().length];
        int i = 0;
        for (Components value : values()) {
            names[i] = value.name();
            i++;
        }
        return names;
    }

    public Component getComponent() throws InstantiationException, IllegalAccessException {
        Log.v(GameActivity.LOG_TAG, "Création d'une instance de type " + this.name());

        return (Component) mClass.newInstance();
    }
}
