/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Modèle ORM pour récupérer une partie de FunkyDomino depuis la base de
 * données.
 * La structure de données implémente Parcelable pour pouvoir être partagée
 * d'une activité à une autre.
 *
 * @author guillaume
 */
@DatabaseTable(tableName = "games")
public class GameModel implements Parcelable {

	@DatabaseField
	public int stage;
	@DatabaseField
	public long time;

	public int describeContents() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this, flags);
	}

	
}
