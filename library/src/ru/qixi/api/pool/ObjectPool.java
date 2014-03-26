package ru.qixi.api.pool;

import ru.qixi.api.core.array.ArrayFixedSize;


/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public abstract class ObjectPool {

	private static final int		DEFAULT_SIZE	= 32;

	private ArrayFixedSize<Object>	mData;
	private final int				mDataLength;


	public ObjectPool() {
		mDataLength = DEFAULT_SIZE;
		setSize(mDataLength);
	}


	public ObjectPool(final int pSize) {
		mDataLength = pSize;
		setSize(mDataLength);
	}


	private void setSize(final int pSize) {
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
		assert result != null : "Object pool of type " + this.getClass().getSimpleName() + " exhausted!!";
		return result;
	}


	public void release(final Object pEntry) {
		mData.add(pEntry);
	}

}
