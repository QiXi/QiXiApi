package ru.qixi.api.database;

public class SampleData implements IDBEntity {

    private long   _id;
    private String mText;


    public SampleData() {}


    public SampleData(String text) {
        mText = text;
    }


    public SampleRepository getRepository() {
        return SampleRepository.getInstance();
    }


    public long getId() {
        return _id;
    }


    public String getText() {
        return mText;
    }


    SampleData setId(long id) {
        _id = id;
        return this;
    }


    public SampleData setText(String text) {
        mText = text;
        return this;
    }

}
