package ru.qixi.api.events.v3;

import ru.qixi.api.pool.IPoolable;
import ru.qixi.api.pool.TObjectPool;

public class EventPool extends TObjectPool<Event> {

	@Override
	protected void fill() {
		final int size = getSize();
		for(int i = 0; i < size; i++){
			getAvailable().add(new Event(0));
		}
	}


	@Override
	public void release(IPoolable entry) {
		entry.reset();
		super.release(entry);
	}

}