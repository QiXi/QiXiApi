package ru.qixi.api.database;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;


public interface IDBEntityRepository {

    String getSQLCreateTable();

    String getTableName();

    String[] getKeys();

    String getKeyId();

    ContentValues getContentValues(IDBEntity entity);

    IDBEntity buildEntityFromCursorData(Cursor cursor);

    List<? extends IDBEntity> selectToList(Cursor cursor);

}