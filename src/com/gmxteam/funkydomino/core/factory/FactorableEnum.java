package com.gmxteam.funkydomino.core.factory;

import com.gmxteam.funkydomino.core.component.Ball;
import com.gmxteam.funkydomino.core.component.Cog;
import com.gmxteam.funkydomino.core.component.Domino;
import com.gmxteam.funkydomino.core.component.Ground;
import com.gmxteam.funkydomino.core.component.Level;
import com.gmxteam.funkydomino.core.component.Water;

/**
 * Énumération des éléments Factorable que l'on peut retrouver dans FunkyDomino.
 * Il permet entre autre de récupéré une version Factorable pour pouvoir
 * l'implémenter de façon générique.
 * @author guillaume
 */
public enum FactorableEnum {

	level(Level.class),
	ground(Ground.class),
	water(Water.class),
	domino(Domino.class),
	ball(Ball.class),
	cog(Cog.class);
	private Class mClass;

	FactorableEnum(Class f) {
		mClass = f;
	}

	public Factorable getFactorableNode() throws InstantiationException, IllegalAccessException {
		Object instance = mClass.newInstance();

		return (Factorable) instance;
	}
}
