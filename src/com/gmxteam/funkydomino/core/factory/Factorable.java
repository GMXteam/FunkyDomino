/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core.factory;

import org.andengine.entity.scene.Scene;
import org.xml.sax.Attributes;

/**
 *
 * @author guillaume
 */
public interface Factorable {

	/**
	 * Initialize Factorable object properties based on XML attributes.
	 * @param att
	 * @return 
	 */
	public Factorable factory(Attributes att);

	/**
	 * Inflate the selected Factorable object on the specified Scene.
	 * @param ga 
	 */
	public void inflate(Scene ga);
}
