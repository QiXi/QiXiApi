package ru.qixi.api.core.geometry;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public final class Vector2Float {

	public static final Vector2Float	ZERO	= new Vector2Float(0, 0);

	public float						x;
	public float						y;


	public Vector2Float() {

	}


	public Vector2Float(final float pX, final float pY) {
		set(pX, pY);
	}


	public Vector2Float(final Vector2Float pVector) {
		set(pVector);
	}


	public final void clear() {
		x = 0.0f;
		y = 0.0f;
	}


	public final void set(final float pX, final float pY) {
		x = pX;
		y = pY;
	}


	public final void set(final Vector2Float pVector) {
		x = pVector.x;
		y = pVector.y;
	}


	public final void add(final float pX, final float pY) {
		x += pX;
		y += pY;
	}


	public final void add(final Vector2Float pVector) {
		x += pVector.x;
		y += pVector.y;
	}


	public final void subtract(final Vector2Float pVector) {
		x -= pVector.x;
		y -= pVector.y;
	}


	public final void multiply(final float pMagnitude) {
		x *= pMagnitude;
		y *= pMagnitude;
	}


	public final void multiply(final Vector2Float pVector) {
		x *= pVector.x;
		y *= pVector.y;
	}


	public final void divide(final float pMagnitude) {
		if (pMagnitude != 0.0f) {
			x /= pMagnitude;
			y /= pMagnitude;
		}
	}


	public final float dot(final Vector2Float pVector) {
		return (x * pVector.x) + (y * pVector.y);
	}


	public final float length() {
		return (float) Math.sqrt(length2());
	}


	public final float length2() {
		return (x * x) + (y * y);
	}


	public final float distance2(final Vector2Float pVector) {
		float dx = x - pVector.x;
		float dy = y - pVector.y;
		return (dx * dx) + (dy * dy);
	}


	public final float normalize() {
		final float magnitude = length();
		if (magnitude != 0.0f) {
			x /= magnitude;
			y /= magnitude;
		}
		return magnitude;
	}


	public final void flipHorizontal(final float pWidth) {
		x = (pWidth - x);
	}


	public final void flipVertical(final float pHeight) {
		y = (pHeight - y);
	}

}
