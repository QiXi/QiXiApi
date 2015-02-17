package ru.qixi.api.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ru.qixi.api.util.Log;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//core source https://github.com/artseld/SqliteEntityManager

public abstract class DBEntityManager extends SQLiteOpenHelper {

	private final static String	TAG	= "DBEntityManager";


	public DBEntityManager(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}


	@SuppressLint("NewApi")
	public DBEntityManager(Context context, String name, CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}


	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.it(TAG, "onCreate db:%s", new Object[] { db });
		Iterator<IDBEntityRepository> it = getRepositories().listIterator();
		while (it.hasNext()) {
			db.execSQL(it.next().getSQLCreateTable());
		}
	}


	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.it(TAG, "onUpgrade db:%s oldVersion:%d newVersion:%d", db, oldVersion, newVersion);
		Iterator<IDBEntityRepository> it = getRepositories().listIterator();
		while (it.hasNext()) {
			db.execSQL("DROP TABLE IF EXISTS " + it.next().getTableName());
		}
		onCreate(db);
	}


	// Get repositories list
	public List<IDBEntityRepository> getRepositories() {
		return new ArrayList<IDBEntityRepository>();
	}


	// Insert record associated with entity
	public long insert(IDBEntity entity) {
		SQLiteDatabase db = getWritableDatabase();
		IDBEntityRepository repository = entity.getRepository();
		ContentValues values = repository.getContentValues(entity);

		long result = db.insert(repository.getTableName(), null, values);
		db.close();

		Log.it(TAG, "Insert result:[%d] entity:[%s]", result, entity);
		return result;
	}


	// Update record associated with entity
	public int update(IDBEntity entity) {
		SQLiteDatabase db = getWritableDatabase();
		IDBEntityRepository repository = entity.getRepository();
		ContentValues values = repository.getContentValues(entity);

		String whereClause = repository.getKeyId() + " = ?";
		String[] whereArgs = new String[] { String.valueOf(entity.getId()) };

		int result = db.update(repository.getTableName(), values, whereClause, whereArgs);
		db.close();

		Log.it(TAG, "Updated result:[%d] entity:[%s]", result, entity);
		return result;
	}


	// Remove record associated with entity
	public int remove(IDBEntity entity) {
		SQLiteDatabase db = this.getWritableDatabase();
		IDBEntityRepository repository = entity.getRepository();

		String whereClause = repository.getKeyId() + " = ?";
		String[] whereArgs = new String[] { String.valueOf(entity.getId()) };

		int result = db.delete(repository.getTableName(), whereClause, whereArgs);
		db.close();

		Log.it(TAG, "Deleted result:[%d] entity:[%s]", result, entity);
		return result;
	}


	// Get entity by ID
	public IDBEntity find(IDBEntityRepository repository, int id) {
		Map<String, String> values = new HashMap<String, String>();
		values.put(repository.getKeyId(), String.valueOf(id));
		return findOneBy(repository, values);
	}


	// Get entity by specified field(s)
	public IDBEntity findOneBy(IDBEntityRepository repository, Map<String, String> values) {
		SQLiteDatabase db = getReadableDatabase();
		List<String> fieldsList = new ArrayList<String>();
		for (Map.Entry<String, String> entry : values.entrySet()) {
			fieldsList.add(entry.getKey() + " = ?");
		}
		String[] fieldsArray = fieldsList.toArray(new String[values.size()]);
		String fields = DBUtils.implodeArray(fieldsArray, " AND ");
		String[] parameters = values.values().toArray(new String[values.size()]);
		Cursor cursor = db.query(repository.getTableName(), repository.getKeys(), fields, parameters, null, null, null,
				null);
		IDBEntity entity;
		if (cursor != null && cursor.moveToFirst()) {
			entity = repository.buildEntityFromCursorData(cursor);
		}
		else {
			entity = null;
		}
		cursor.close();
		db.close();
		return entity;
	}


	// Get all records as array list
	public List<? extends IDBEntity> findAll(IDBEntityRepository repository) {
		String selectQuery = "SELECT * FROM " + repository.getTableName();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		List<? extends IDBEntity> result = repository.selectToList(cursor);
		cursor.close();
		db.close();
		return result;
	}


	// Calculate records count and return result
	public int count(IDBEntityRepository repository) {
		String countQuery = "SELECT * FROM " + repository.getTableName();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int result = cursor.getCount();
		cursor.close();
		db.close();
		return result;
	}

}