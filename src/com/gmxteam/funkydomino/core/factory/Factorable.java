/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core.factory;

import com.gmxteam.funkydomino.xml.AttributesExtended;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsWorld;

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
	public Factorable factory(AttributesExtended att);

	/**
	 * Inflate the selected Factorable object on the specified Scene.
	 * @param ga 
	 */
	public Factorable inflateOnScene(Scene ga);
	
	public Factorable inflateOnPhysicsWorld(PhysicsWorld pw);
}
