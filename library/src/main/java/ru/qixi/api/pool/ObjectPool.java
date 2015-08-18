package ru.qixi.api.pool;

import ru.qixi.api.core.array.ArrayFixedSize;

public abstract class ObjectPool {

	private static final int		DEFAULT_SIZE	= 32;

	private ArrayFixedSize<Object>	mData;
	private final int				mDataLength;


	public ObjectPool() {
		mDataLength = DEFAULT_SIZE;
		setSize(mDataLength);
	}


	public ObjectPool(int pSize) {
		mDataLength = pSize;
		setSize(mDataLength);
	}


	private void setSize(int pSize) {
		mData = new ArrayFixedSize<Object>(pSize);
		fill();
	}


	public int getSize() {
		return mDataLength;
	}


	protected abstract void fill();


	protected ArrayFixedSize<Object> getAvailable() {
		return mData;
	}


	public int getAllocatedCount() {
		return mData.getCapacity() - mData.getCount();
	}


	protected Object allocate() {
		Object result = mData.removeLast();
		return result;
	}


	public void release(IPoolable pEntry) {
		mData.add(pEntry);
	}

}
