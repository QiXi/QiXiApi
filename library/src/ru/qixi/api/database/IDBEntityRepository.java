package ru.qixi.api.database;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

public interface IDBEntityRepository {

	public String getSQLCreateTable();


	public String getTableName();


	public String[] getKeys();


	public String getKeyId();


	public ContentValues getContentValues(IDBEntity entity);


	public IDBEntity buildEntityFromCursorData(Cursor cursor);


	public List<? extends IDBEntity> selectToList(Cursor cursor);

}