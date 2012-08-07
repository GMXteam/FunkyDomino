/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.xml;

import org.xml.sax.Attributes;

/**
 *
 * @author guillaume
 */
public class AttributesExtended {

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
