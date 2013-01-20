package ru.qixi.api.core.geometry;

import java.lang.Math;


/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public class Matrix3Float {

	public static final int		SIZE	= 3;
	public static final int		LENGTH	= SIZE * 3;

	private static final int	A		= 0;
	private static final int	B		= 1;
	private static final int	M02		= 2;
	private static final int	C		= 3;
	private static final int	D		= 4;
	private static final int	M12		= 5;
	private static final int	TX		= 6;
	private static final int	TY		= 7;
	private static final int	M22		= 8;

	protected final float[]		mMatrix;


	public Matrix3Float() {
		mMatrix = new float[LENGTH];
		identity();
	}


	public Matrix3Float(final float[] pDataArray) {
		mMatrix = new float[LENGTH];
		System.arraycopy(pDataArray, 0, mMatrix, 0, LENGTH);
	}


	public float get(final int pCol, final int pRow) {
		return mMatrix[pCol * SIZE + pRow];
	}


	public void set(final int pCol, final int pRow, final float pValue) {
		mMatrix[pCol * SIZE + pRow] = pValue;
	}


	public float[] getArray() {
		return mMatrix;
	}


	public void set(final Matrix3Float pSource) {
		System.arraycopy(pSource.getArray(), 0, mMatrix, 0, LENGTH);
	}


	public void identity() {
		mMatrix[A] = 1;
		mMatrix[B] = 0;
		mMatrix[M02] = 0;

		mMatrix[C] = 0;
		mMatrix[D] = 1;
		mMatrix[M12] = 0;

		mMatrix[TX] = 0;
		mMatrix[TY] = 0;
		mMatrix[M22] = 1;
	}


	public void concat(final float pA, final float pB, final float pC, final float pD, final float pTx, final float pTy) {

		final float ta = mMatrix[A];
		final float tb = mMatrix[B];
		final float tc = mMatrix[C];
		final float td = mMatrix[D];
		final float ttx = mMatrix[TX];
		final float tty = mMatrix[TY];

		mMatrix[A] = ta * pA + tc * pB;
		mMatrix[B] = tb * pA + td * pB;
		mMatrix[M02] = 0;

		mMatrix[C] = ta * pC + tc * pD;
		mMatrix[D] = tb * pC + td * pD;
		mMatrix[M12] = 0;

		mMatrix[TX] = ta * pTx + tc * pTy + ttx;
		mMatrix[TY] = tb * pTx + td * pTy + tty;
		mMatrix[M22] = 1;
	}


	public void concat(final Matrix3Float pSource) {
		concat(pSource.mMatrix[A], pSource.mMatrix[B], pSource.mMatrix[C], pSource.mMatrix[D], pSource.mMatrix[TX], pSource.mMatrix[TY]);
	}


	private float determinant() {
		return mMatrix[A] * mMatrix[D] - mMatrix[B] * mMatrix[C];
	}


	public void invert() {
		final float det = determinant();
		final float dd = (0.0f == det) ? 1.0f : det;

		final float ta = mMatrix[A];
		final float tb = mMatrix[B];
		final float tc = mMatrix[C];
		final float td = mMatrix[D];
		final float ttx = mMatrix[TX];
		final float tty = mMatrix[TY];

		mMatrix[A] = td / dd;
		mMatrix[B] = -tb / dd;
		mMatrix[C] = -(tc - ttx) / dd;
		mMatrix[D] = ta / dd;
		mMatrix[TX] = -(tc - ttx * td) / dd;
		mMatrix[TY] = -(ta * tty - ttx * tb) / dd;
	}


	public void rotate(final float pAngle) {
		float angle = pAngle * (float)(Math.PI / 180.0f);		
		final float sin = (float) Math.sin(angle);
		final float cos = (float) Math.cos(angle);
		concat(cos, -sin, sin, cos, 0.0f, 0.0f);
	}


	public void scale(final float pSx, final float pSy) {
		concat(pSx, 0.0f, 0.0f, pSy, 0.0f, 0.0f);
	}


	public void translate(final float pDx, final float pDy) {
		concat(1.0f, 0.0f, 0.0f, 1.0f, pDx, pDy);
	}

}
