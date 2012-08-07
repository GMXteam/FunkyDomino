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

import com.gmxteam.funkydomino.core.factory.Factorable;
import com.gmxteam.funkydomino.xml.AttributesExtended;
import org.andengine.extension.physics.box2d.PhysicsWorld;

/**
 * Objet générant une roue dentée.
 *
 * @see Component
 * @author Guillaume Poirier-Morency
 */
public final class Cog extends Component {

	

	public Factorable factory(AttributesExtended att) {

		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Factorable inflateOnPhysicsWorld(PhysicsWorld pw) {
		throw new UnsupportedOperationException("Not supported yet.");
	}


	
}
