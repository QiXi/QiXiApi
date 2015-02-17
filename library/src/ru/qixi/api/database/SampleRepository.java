package ru.qixi.api.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

public class SampleRepository implements IDBEntityRepository {

	private static SampleRepository	INSTANCE;

	private static final String		TABLE_NAME	= "simple_table";
	private static final String		KEY_ID		= "id";
	private static final String		KEY_TEXT	= "text";
	private static final String[]	KEYS		= { KEY_ID, KEY_TEXT };


	public static SampleRepository getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SampleRepository();
		return INSTANCE;
	}


	@Override
	public String getSQLCreateTable() {
		String SQL = "CREATE TABLE " + TABLE_NAME + "("//
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"//
				+ KEY_TEXT + " VARCHAR(255),"//
				+ ")";//
		return SQL;
	}


	@Override
	public String getTableName() {
		return TABLE_NAME;
	}


	@Override
	public String[] getKeys() {
		return KEYS;
	}


	@Override
	public String getKeyId() {
		return KEY_ID;
	}


	@Override
	public ContentValues getContentValues(IDBEntity entity) {
		SampleData data = (SampleData)entity;
		ContentValues values = new ContentValues();
		values.put(KEY_TEXT, data.getText());
		return values;
	}


	@Override
	public IDBEntity buildEntityFromCursorData(Cursor cursor) {
		SampleData data = new SampleData();
		int id = Integer.parseInt(cursor.getString(0));
		String str = cursor.getString(1);
		data.setId(id).setText(str);
		return data;
	}


	@Override
	public List<? extends IDBEntity> selectToList(Cursor cursor) {
		ArrayList<IDBEntity> result = new ArrayList<IDBEntity>();
		if (cursor.moveToFirst())
			do {
				result.add(buildEntityFromCursorData(cursor));
			}while (cursor.moveToNext());
		return result;
	}

}
