package ru.qixi.api.core.array;

import java.util.Comparator;


public abstract class Sorter<T> {

	public abstract void sort(T[] array, int count, Comparator<T> comparator);
}
