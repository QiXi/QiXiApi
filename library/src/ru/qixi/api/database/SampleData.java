package ru.qixi.api.database;

public class SampleData implements IDBEntity {

	private int		_id;
	private String	mText;


	public SampleData() {}


	public SampleData(String text) {
		mText = text;
	}


	public SampleRepository getRepository() {
		return SampleRepository.getInstance();
	}


	public int getId() {
		return _id;
	}


	public String getText() {
		return mText;
	}


	SampleData setId(int id) {
		_id = id;
		return this;
	}


	public SampleData setText(String text) {
		mText = text;
		return this;
	}

}
