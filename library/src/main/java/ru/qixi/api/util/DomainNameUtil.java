package ru.qixi.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.webkit.URLUtil;

public class DomainNameUtil {

	private static final String	DOMAIN_NAME_PATTERN	= "^[A-Za-zА-ЯЁа-яё0-9-]+(\\.[A-Za-zА-ЯЁа-яё0-9-]+)*(\\.[A-Za-zА-ЯЁа-яё]{2,})$";
	private static Pattern		pattern;

	static {
		pattern = Pattern.compile(DOMAIN_NAME_PATTERN);
	}


	public static boolean validate(final String url) {
		if (URLUtil.isNetworkUrl(url))
			return true;
		Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}

}
