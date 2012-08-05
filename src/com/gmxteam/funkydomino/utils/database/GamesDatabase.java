/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.utils.database;

import android.database.Cursor;
import com.gmxteam.funkydomino.utils.database.model.GameModel;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import java.io.File;

/**
 *
 * @author guillaume
 */
public class GamesDatabase {

	private SQLiteDatabase mDatabase;

	/**
	 * Establish a connection to the database
	 */
	public void connect(File f) {

		mDatabase = SQLiteDatabase.openOrCreateDatabase(f, new CursorFactory() {
			public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
				
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
	}

	public GameModel getLastSavedGame() {
		return new GameModel();
	}
}
