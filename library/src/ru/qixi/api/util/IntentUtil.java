package ru.qixi.api.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.webkit.URLUtil;


public class IntentUtil {

	public static void openBrowser(final Context context, final String url) {
		if (!URLUtil.isNetworkUrl(url))
			return;
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		context.startActivity(intent);
	}


	public static void openPlayStore(final Context context) {
		String appPackageName = context.getPackageName();
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
		if (isIntentAvailable(context, intent))
			context.startActivity(intent);
	}


	public static boolean isIntentAvailable(final Context context, final Intent intent) {
		if (intent == null)
			return false;
		PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}


	public static void sendEmail(final Context context, final String to, final String subject, final String text) {
		sendEmail(context, new String[] { to }, subject, text);
	}


	public static void sendEmail(final Context context, final String[] to, final String subject, final String text) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("message/rfc822");
		intent.setPackage("com.google.android.gm");
		intent.putExtra(Intent.EXTRA_EMAIL, to);
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TEXT, text);

		if (isIntentAvailable(context, intent))
			context.startActivity(intent);
	}


	public static void shareText(final Context context, final String subject, final String text) {
		shareText(context, subject, text, null, "");
	}


	public static void shareText(final Context context, final String subject, final String text, final ArrayList<String> filterPackage, final String title) {
		Intent shareIntent = createShareIntent(subject, text);
		if (filterPackage != null) {
			List<Intent> targetShareIntents = new ArrayList<Intent>();
			List<ResolveInfo> matches = context.getPackageManager().queryIntentActivities(shareIntent, 0);
			for (ResolveInfo info : matches) {
				String packageName = info.activityInfo.packageName;
				if (!filterPackage.contains(packageName)) {
					Intent intent = createShareIntent(subject, text);
					intent.setPackage(packageName);
					targetShareIntents.add(intent);
				}
			}
			if (!targetShareIntents.isEmpty()) {
				Intent intent = Intent.createChooser(targetShareIntents.remove(0), title);
				intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toArray(new Parcelable[] {}));
				context.startActivity(intent);
			}
			return;
		} else if (isIntentAvailable(context, shareIntent))
			context.startActivity(shareIntent);
	}


	private static Intent createShareIntent(final String subject, final String text) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TEXT, text);
		return intent;
	}

}
