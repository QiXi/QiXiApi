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


	public void set(final float[] pArray) {
		System.arraycopy(pArray, 0, mMatrix, 0, LENGTH);
	}


	public void identity() {
		final float[] mat = mMatrix;

		mat[A] = 1;
		mat[B] = 0;
		mat[M02] = 0;

		mat[C] = 0;
		mat[D] = 1;
		mat[M12] = 0;

		mat[TX] = 0;
		mat[TY] = 0;
		mat[M22] = 1;
	}


	public void concat(final float pA, final float pB, final float pC, final float pD, final float pTx, final float pTy) {

		final float[] mat = mMatrix;

		final float ta = mat[A];
		final float tb = mat[B];
		final float tc = mat[C];
		final float td = mat[D];
		final float ttx = mat[TX];
		final float tty = mat[TY];

		mat[A] = ta * pA + tc * pB;
		mat[B] = tb * pA + td * pB;
		// mat[M02] = 0;

		mat[C] = ta * pC + tc * pD;
		mat[D] = tb * pC + td * pD;
		// mat[M12] = 0;

		mat[TX] = ta * pTx + tc * pTy + ttx;
		mat[TY] = tb * pTx + td * pTy + tty;
		// mat[M22] = 1;
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

		final float[] mat = mMatrix;

		final float ta = mat[A];
		final float tb = mat[B];
		final float tc = mat[C];
		final float td = mat[D];
		final float ttx = mat[TX];
		final float tty = mat[TY];

		mat[A] = td / dd;
		mat[B] = -tb / dd;
		mat[C] = -(tc - ttx) / dd;
		mat[D] = ta / dd;
		mat[TX] = -(tc - ttx * td) / dd;
		mat[TY] = -(ta * tty - ttx * tb) / dd;
	}


	public void rotate(final float pAngle) {
		float angle = pAngle * (float) (Math.PI / 180.0f);
		final float sin = (float) Math.sin(angle);
		final float cos = (float) Math.cos(angle);
		concat(cos, -sin, sin, cos, 0.0f, 0.0f);
	}


	public void rotate(final float pAngle, final float pX, final float pY, final float pZ) {
		/* TO DO */
		float angle = (float) (Math.PI / 180.0f);
		float c = (float) Math.cos(angle);
		float s = (float) Math.sin(angle);
		float x = pX;
		float y = pY;
		float z = pZ;
		float len = (float) Math.sqrt(x * x + y * y + z * z);
		if (!(len != 1)) {
			float recipLen = 1.f / len;
			x *= recipLen;
			y *= recipLen;
			z *= recipLen;
		}
		float nc = 1.0f - c;
		float xy = x * y;
		float yz = y * z;
		float zx = z * x;
		float xs = x * s;
		float ys = y * s;
		float zs = z * s;

		final float[] mat = mMatrix;

		mat[0] = x * x * nc + c;
		mat[3] = xy * nc - zs;
		mat[6] = zx * nc + ys;// ?
		mat[1] = xy * nc + zs;
		mat[4] = y * y * nc + c;
		mat[9] = yz * nc - xs;
		mat[2] = zx * nc - ys;
		mat[6] = yz * nc + xs;// ?
		mat[8] = z * z * nc + c;
	}


	public void scale(final float pSx, final float pSy) {
		concat(pSx, 0.0f, 0.0f, pSy, 0.0f, 0.0f);
	}


	public void translate(final float pDx, final float pDy) {
		concat(1.0f, 0.0f, 0.0f, 1.0f, pDx, pDy);
	}


	// src 1,2,3, 11,12,13, 21,22,23
	// dst 1,11,21, 2,12,22, 3,13,23
	public void transpose() {
		final float[] mat = mMatrix;

		// final float m00 = mat[A];
		final float m01 = mat[B];
		final float m02 = mat[M02];

		final float m10 = mat[C];
		// final float m11 = mat[D];
		final float m12 = mat[M12];

		final float m20 = mat[TX];
		final float m21 = mat[TY];
		// final float m22 = mat[M22];

		// mat[A] = m00;
		mat[B] = m10;
		mat[M02] = m20;

		mat[C] = m01;
		// mat[D] = m11;
		mat[M12] = m21;

		mat[TX] = m02;
		mat[TY] = m12;
		// mat[M22] = m22;

	}

}
