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
package com.gmxteam.funkydomino.core.component;

import android.content.res.AssetManager;
import com.gmxteam.funkydomino.core.factory.Factorable;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.TextureManager;
import org.xml.sax.Attributes;

/**
 * Objet pour générer de l'eau. Cela risque plus d'être un champ de force 
 * qu'autre chose par contre.
 * @see Component
 * @author Guillaume Poirier-Morency
 */
public final class Water extends Component {
    
   

	public Factorable factory(Attributes att) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Factorable inflateOnPhysicsWorld(PhysicsWorld pw) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	

	@Override
	void onLoadResources() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	
}
