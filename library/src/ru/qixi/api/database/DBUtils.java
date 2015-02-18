package ru.qixi.api.database;

public class DBUtils {

	public static String implodeArray(String[] inputArray, String glueString) {
		StringBuilder sb = new StringBuilder();
		appendArray(sb, inputArray, glueString);
		return sb.toString();
	}


	public static void appendArray(StringBuilder sb, String[] inputArray, String glueString) {
		int length = inputArray.length;
		if (length > 0) {
			sb.append(inputArray[0]);
			for (int i = 1; i < length; i++) {
				sb.append(glueString);
				sb.append(inputArray[i]);
			}
		}
	}

}
