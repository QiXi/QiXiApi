package ru.qixi.api.database;

public interface IDBEntity {

    IDBEntityRepository getRepository();

    long getId();

}