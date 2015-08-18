package ru.qixi.api.core.geometry;

public class PointInt {

	public int	x;
	public int	y;


	public PointInt() {
	}


	public PointInt(final int pX, final int pY) {
		x = pX;
		y = pY;
	}


	public PointInt(final PointInt pPoint) {
		x = pPoint.x;
		y = pPoint.y;
	}


	public void clear() {
		x = 0;
		y = 0;
	}


	public final void set(final int pX, final int pY) {
		x = pX;
		y = pY;
	}


	public final void set(final PointInt pPoint) {
		x = pPoint.x;
		y = pPoint.y;
	}


	public final void negate() {
		x = -x;
		y = -y;
	}


	public final void offset(final int pDx, final int pDy) {
		x += pDx;
		y += pDy;
	}


	public final void offset(final PointInt pPoint) {
		x += pPoint.x;
		y += pPoint.y;
	}


	public final boolean equals(final int pX, final int pY) {
		return x == pX && y == pY;
	}


	public final boolean equals(final PointInt pPoint) {
		return x == pPoint.x && y == pPoint.y;
	}


	public final int length() {
		return length(x, y);
	}


	public static int length(final int pX, final int pY) {
		return (int) Math.sqrt(pX * pX + pY * pY);
	}

}
