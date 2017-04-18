package link.standen.michael.fatesheets.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import link.standen.michael.fatesheets.R;

/**
 * Credits activity.
 */
public class CreditsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.credits_activity);

		// Linkify
		((TextView)findViewById(R.id.credits_creator)).setMovementMethod(LinkMovementMethod.getInstance());
		((TextView)findViewById(R.id.credits_content1)).setMovementMethod(LinkMovementMethod.getInstance());
		((TextView)findViewById(R.id.credits_content2)).setMovementMethod(LinkMovementMethod.getInstance());
		((TextView)findViewById(R.id.credits_content3)).setMovementMethod(LinkMovementMethod.getInstance());
	}
}
