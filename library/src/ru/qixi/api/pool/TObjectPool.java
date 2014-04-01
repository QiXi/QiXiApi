package ru.qixi.api.pool;

public abstract class TObjectPool<T> extends ObjectPool {

	public TObjectPool() {
		super();
	}


	public TObjectPool(int size) {
		super(size);
	}


	public T allocate() {
		T object = (T)super.allocate();
		return object;
	}

}
