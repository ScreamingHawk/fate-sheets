package link.standen.michael.fatesheets.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import link.standen.michael.fatesheets.R;

/**
 * Credits activity.
 */
public class CreditsActivity extends AppCompatActivity {

	private static final String TAG = CreditsActivity.class.getName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.credits_activity);

		// Version
		try {
			PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			((TextView)findViewById(R.id.credits_version)).setText("v"+pInfo.versionName);
		} catch (PackageManager.NameNotFoundException e) {
			Log.e(TAG, "Unable to get package version", e);
		}

		// Linkify
		((TextView)findViewById(R.id.credits_creator)).setMovementMethod(LinkMovementMethod.getInstance());
		((TextView)findViewById(R.id.credits_content1)).setMovementMethod(LinkMovementMethod.getInstance());
		((TextView)findViewById(R.id.credits_content2)).setMovementMethod(LinkMovementMethod.getInstance());
		((TextView)findViewById(R.id.credits_content3)).setMovementMethod(LinkMovementMethod.getInstance());
	}
}
