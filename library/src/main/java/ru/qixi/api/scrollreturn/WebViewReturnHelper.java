package ru.qixi.api.scrollreturn;

public class WebViewReturnHelper {

	public int getScroll(int t, int oldt) {
		int scrollY = t;
		if (scrollY < 0) {
			scrollY = 0;
		}
		int dif = oldt - scrollY;
		if ((dif > 0 && dif < 10)) {
			return 0;
		}
		//dif *= 100;
		return dif;
	}

}
