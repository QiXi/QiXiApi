package ru.qixi.api.core;

import android.util.Log;


public class AllocationGuard {

	public static boolean	active	= true;


	public AllocationGuard() {
		if (active) {
			Log.e("AllocGuard", "An allocation of type " + this.getClass().getName() + " occurred while the AllocGuard is active.");
		}
	}
}
