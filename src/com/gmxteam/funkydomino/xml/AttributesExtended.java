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
package com.gmxteam.funkydomino.xml;

import org.xml.sax.Attributes;

/**
 * Méthodes additionnelles pour la classe Attributes de SAX.
 * @author guillaume
 */
public abstract class AttributesExtended implements Attributes {

	private Attributes mAttributes;
	
	

	public AttributesExtended(Attributes att) {
		mAttributes = att;
	}

	public int getIntegerValue(String qName, int defaultValue) {
		return mAttributes.getValue(qName) == null ? defaultValue : Integer.parseInt(mAttributes.getValue(qName));
	}

	public float getFloatValue(String qName, float defaultValue) {
		return mAttributes.getValue(qName) == null ? defaultValue : Float.parseFloat(mAttributes.getValue(qName));
	}
}
