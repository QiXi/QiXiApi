package ru.qixi.api.scrollreturn;

import android.view.View;
import android.widget.ListView;

public class ListViewReturnHelper {

	private ListView	mListView;
	private int			mLastTop;
	private int			mLastVisibleItem;
	private int			mLastHeight;


	public ListViewReturnHelper() {

	}


	public ListViewReturnHelper(ListView pListView) {
		mListView = pListView;
	}


	public void setListView(ListView pListView) {
		mListView = pListView;
	}


	public int getScroll(int firstVisibleItem, int totalItemCount) {
		if (totalItemCount == 0 || mListView == null) {
			return 0;
		}
		View v = mListView.getChildAt(0);
		if (v == null) {
			return 0;
		}
		int top = v.getTop();
		int height = v.getHeight();

		int scrollY;
		if (mLastVisibleItem != firstVisibleItem) {
			int dif = firstVisibleItem - mLastVisibleItem;
			if (dif > 0) {
				scrollY = -(mLastHeight + mLastTop) + top;
			}
			else {
				scrollY = -(mLastTop - (height + top));
			}
		}
		else {
			scrollY = top - mLastTop;
		}

		mLastTop = top;
		mLastHeight = height;
		mLastVisibleItem = firstVisibleItem;

		return scrollY;
	}

}
