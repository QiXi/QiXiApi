package ru.qixi.api.core.array;

import java.util.Arrays;
import java.util.Comparator;


/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public class ArrayFixedSize<T> {

	private final static int	LINEAR_SEARCH_CUTOFF	= 16;

	private final T[]			mData;
	private final int			mDataLength;
	private int					mCount;
	private boolean				mIsSorted;
	private Comparator<T>		mComparator;
	private Sorter<T>			mSorter;


	public ArrayFixedSize(final int pSize) {
		mDataLength = pSize;
		mData = (T[]) new Object[mDataLength];
		mCount = 0;
		mIsSorted = false;
		mSorter = new StandardSorter<T>();
	}


	public ArrayFixedSize(final int pSize, final Comparator<T> pComparator) {
		this(pSize);
		mComparator = pComparator;
	}


	public void clear() {
		for (int i = 0; i < mCount; i++) {
			mData[i] = null;
		}
		mCount = 0;
		mIsSorted = false;
	}


	public int getCount() {
		return mCount;
	}


	public int getCapacity() {
		return mDataLength;
	}


	public void set(final int pIndex, final T pObject) {
		if (pIndex >= 0 && pIndex < mCount) {
			mData[pIndex] = pObject;
		}
	}


	public T get(final int pIndex) {
		if (pIndex >= 0 && pIndex < mCount) {
			return mData[pIndex];
		}
		return null;
	}


	public final void add(final T pObject) {
		if (mCount < mDataLength) {
			mData[mCount] = pObject;
			mCount++;
			mIsSorted = false;
		}
	}


	public void remove(final int pIndex) {
		if (pIndex < mCount) {
			for (int i = pIndex; i < mCount; i++) {
				int index = i+1;
				if (index < mDataLength && index < mCount) {
					mData[i] = mData[index];
				} else {
					mData[i] = null;
				}
			}
			mCount--;
		}
	}


	public void remove(final T pObject, final boolean pIgnoreComparator) {
		final int index = find(pObject, pIgnoreComparator);
		if (index != -1) {
			remove(index);
		}
	}


	public T removeLast() {
		T object = null;
		if (mCount > 0) {
			object = mData[mCount - 1];
			mData[mCount - 1] = null;
			mCount--;
		}
		return object;
	}


	public void swapWithLast(final int pIndex) {
		if (mCount > 0 && pIndex < mCount - 1) {
			T object = mData[mCount - 1];
			mData[mCount - 1] = mData[pIndex];
			mData[pIndex] = object;
			mIsSorted = false;
		}
	}


	public final T[] getArray() {
		return mData;
	}


	public int find(final T pObject, final boolean pIgnoreComparator) {
		int index = -1;
		final boolean sorted = mIsSorted;
		final Comparator<T> com = mComparator;
		final T[] contents = mData;
		if (sorted && !pIgnoreComparator && mCount > LINEAR_SEARCH_CUTOFF) {
			if (com != null) {
				index = Arrays.binarySearch(contents, pObject, com);
			} else {
				index = Arrays.binarySearch(contents, pObject);
			}
			if (index < 0) {
				index = -1;
			}
		} else {
			if (com != null && !pIgnoreComparator) {
				for (int i = 0; i < mCount; i++) {
					final int result = com.compare(contents[i], pObject);
					if (result == 0) {
						index = i;
						break;
					} else if (result > 0 && sorted) {
						break;
					}
				}
			} else {
				for (int i = 0; i < mCount; i++) {
					if (contents[i] == pObject) {
						index = i;
						break;
					}
				}
			}
		}
		return index;
	}


	public void sort(final boolean pForceResort) {
		if (!mIsSorted || pForceResort) {
			if (mComparator != null) {
				mSorter.sort(mData, mCount, mComparator);
			} else {
				Arrays.sort(mData, 0, mCount);
			}
			mIsSorted = true;
		}
	}


	public void setComparator(final Comparator<T> pValue) {
		mComparator = pValue;
		mIsSorted = false;
	}


	public void setSorter(final Sorter<T> pValue) {
		mSorter = pValue;
	}

}
