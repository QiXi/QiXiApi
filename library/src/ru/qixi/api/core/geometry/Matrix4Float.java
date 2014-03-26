package ru.qixi.api.core.geometry;

public class Matrix4Float {

	public static final int		SIZE	= 4;
	public static final int		LENGTH	= SIZE * 4;

	private static final int	M00		= 0;
	private static final int	M01		= 1;
	private static final int	M02		= 2;
	private static final int	M03		= 3;
	
	private static final int	M10		= 4;
	private static final int	M11		= 5;
	private static final int	M12		= 6;
	private static final int	M13		= 7;
	
	private static final int	M20		= 8;
	private static final int	M21		= 9;
	private static final int	M22		= 10;
	private static final int	M23		= 11;
	
	private static final int	M30		= 12;
	private static final int	M31		= 13;
	private static final int	M32		= 14;
	private static final int	M33		= 15;

	
	protected final float[]		mMatrix;


	public Matrix4Float() {
		mMatrix = new float[LENGTH];
		identity();
	}


	public Matrix4Float(final float[] pDataArray) {
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


	public void set(final Matrix4Float pSource) {
		System.arraycopy(pSource.getArray(), 0, mMatrix, 0, LENGTH);
	}


	public void set(Matrix3Float pCource) {
		mMatrix[M00] = pCource.mMatrix[0];
		mMatrix[M01] = pCource.mMatrix[1];
		mMatrix[M02] = pCource.mMatrix[2];
		mMatrix[M03] = 0;

		mMatrix[M10] = pCource.mMatrix[3];
		mMatrix[M11] = pCource.mMatrix[4];
		mMatrix[M12] = pCource.mMatrix[5];
		mMatrix[M13] = 0;

		mMatrix[M20] = pCource.mMatrix[6];
		mMatrix[M21] = pCource.mMatrix[7];
		mMatrix[M22] = pCource.mMatrix[8];
		mMatrix[M23] = 0;

		mMatrix[M30] = 0;
		mMatrix[M31] = 0;
		mMatrix[M32] = 0;
		mMatrix[M33] = 1;
	}


	public void identity() {
		mMatrix[M00] = 1;
		mMatrix[M01] = 0;
		mMatrix[M02] = 0;
		mMatrix[M03] = 0;

		mMatrix[M10] = 0;
		mMatrix[M11] = 1;
		mMatrix[M12] = 0;
		mMatrix[M13] = 0;

		mMatrix[M20] = 0;
		mMatrix[M21] = 0;
		mMatrix[M22] = 1;
		mMatrix[M23] = 0;

		mMatrix[M30] = 0;
		mMatrix[M31] = 0;
		mMatrix[M32] = 0;
		mMatrix[M33] = 1;
	}


	public void rotate(final float pAngle) {

	}


	public void scale(final float pSx, final float pSy) {

	}


	public void translate(final float pDx, final float pDy) {

	}

}
