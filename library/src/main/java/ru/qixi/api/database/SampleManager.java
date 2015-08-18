package ru.qixi.api.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class SampleManager extends DBEntityManager {

	private static final String	DATABASE_NAME		= "simple_db";
	private static final int	DATABASE_VERSION	= 1;


	public SampleManager(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public List<IDBEntityRepository> getRepositories() {
		List<IDBEntityRepository> repositories = new ArrayList<IDBEntityRepository>();
		repositories.add(SampleRepository.getInstance());
		return repositories;
	}

}
