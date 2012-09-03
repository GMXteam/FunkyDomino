package com.gmxteam.funkydomino.core.component;

import android.util.Log;
import com.gmxteam.funkydomino.activity.GameActivity;

/**
 * Énumération des éléments Factorable que l'on peut retrouver dans FunkyDomino.
 * Il permet entre autre de récupéré une version Factorable pour pouvoir
 * l'implémenter de façon générique.
 * @author guillaume
 */
public enum Components {
        level(Component.class),
	ground(Ground.class),
	water(Water.class),
	domino(Domino.class),
	ball(Ball.class),
	cog(Cog.class);
	private Class mClass;

	Components(Class c) {
		mClass = c;
	}

	public Component getComponent() throws InstantiationException, IllegalAccessException {	
            Log.v(GameActivity.LOG_TAG, "Création d'une instance de type " + this.name());
            
		return (Component) mClass.newInstance();
	}
}
