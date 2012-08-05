package com.gmxteam.funkydomino.utils.xmlparser;

import com.gmxteam.funkydomino.core.factory.Factorable;
import com.gmxteam.funkydomino.graphical.component.Ball;
import com.gmxteam.funkydomino.graphical.component.Cog;
import com.gmxteam.funkydomino.graphical.component.Component;
import com.gmxteam.funkydomino.graphical.component.Domino;
import com.gmxteam.funkydomino.graphical.component.Ground;
import com.gmxteam.funkydomino.graphical.component.Water;

/**
 * Énumération des éléments XML que l'on peut retrouver dans un fichier de jeu.
 * Il permet entre autre de récupéré une version Factorable pour pouvoir
 * l'implémenter de façon générique.
 * @author guillaume
 */
public enum FactorableXMLNodes {

	level(Level.class),
	ground(Ground.class),
	water(Water.class),
	component(Component.class),
	domino(Domino.class),
	ball(Ball.class),
	cog(Cog.class);
	private Class mClass;

	FactorableXMLNodes(Class f) {
		mClass = f;
	}

	public Factorable getFactorableNode() throws InstantiationException, IllegalAccessException {
		Object instance = mClass.newInstance();

		return (Factorable) instance;
	}
}
