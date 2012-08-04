/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.utils.database.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author guillaume
 */
public class GameModel implements Parcelable {

	public int describeContents() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this, flags);
	}
}
