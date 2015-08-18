package ru.qixi.api.core.geometry;

public class PointFloat {

	public float	x;
	public float	y;


	public PointFloat() {
	}


	public PointFloat(final float pX, final float pY) {
		x = pX;
		y = pY;
	}


	public PointFloat(final PointFloat pPoint) {
		x = pPoint.x;
		y = pPoint.y;
	}


	public void clear() {
		x = 0.0F;
		y = 0.0F;
	}


	public final void set(final float pX, final float pY) {
		x = pX;
		y = pY;
	}


	public final void set(final PointFloat pPoint) {
		x = pPoint.x;
		y = pPoint.y;
	}


	public final void negate() {
		x = -x;
		y = -y;
	}


	public final void offset(final float pDx, final float pDy) {
		x += pDx;
		y += pDy;
	}


	public final void offset(final PointFloat pPoint) {
		x += pPoint.x;
		y += pPoint.y;
	}


	public final boolean equals(final float pX, final float pY) {
		return x == pX && y == pY;
	}


	public final boolean equals(final PointFloat pPoint) {
		return x == pPoint.x && y == pPoint.y;
	}


	public final float length() {
		return length(x, y);
	}


	public static float length(final float pX, final float pY) {
		return (float) Math.sqrt(pX * pX + pY * pY);
	}

}
