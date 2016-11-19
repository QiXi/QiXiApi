package ru.qixi.api.statemachine;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public abstract class State {

    final String tag;
    State previous;


    public State(final String pTag) {
        tag = pTag;
    }


    public String getTag() {
        return tag;
    }


    protected abstract void enterState();


    protected abstract void leaveState();


    protected abstract void updateState(float time);


    @Override
    public String toString() {
        return "tag:" + tag + " previous:" + previous;
    }

}
