package link.standen.michael.fatesheets.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.util.DiceClickListener;

/**
 * An abstract class that handles the menu items shared across activities.
 */
public abstract class SharedMenuActivity extends AppCompatActivity {

	private static final String TAG = "SharedMenuActivity";

	private static final long FOCUS_PAUSE = 500;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	protected ViewPager mViewPager;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_shared_activity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_credits) {
			startActivity(new Intent(this, CreditsActivity.class));
			return true;
		} else if (id == R.id.action_docs) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://fate-srd.com/")));
			return true;
		} else if (id == R.id.action_edit_skills) {
			startActivity(new Intent(this, EditSkillsActivity.class));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	protected void setupDiceFAB() {
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.dice_fab);
		fab.setOnClickListener(new DiceClickListener(getResources()));
	}

	/**
	 * Clears the focus and runs the callback.
	 */
	protected void clearFocus(Runnable callback) {
		try {
			getCurrentFocus().clearFocus();

		} catch (NullPointerException e){
			// Nothing in focus. Ignore
		}
		if (callback != null) {
			new Handler().postDelayed(callback, FOCUS_PAUSE);
		}
	}

	/**
	 * Permissions checker
	 */
	private boolean isStoragePermissionGranted() {
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
				Log.v(TAG,"Permission is granted");
				return true;
			} else {
				Log.v(TAG,"Permission is revoked");
				requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
				return false;
			}
		} else { //permission is automatically granted on sdk<23 upon installation
			Log.v(TAG,"Permission is granted");
			return true;
		}
	}

	/**
	 * Permissions handler
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		Log.v(TAG,"Permission: " + permissions[0] + " was " + grantResults[0]);
		if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
			doExportPDF();
		}
	}

	protected void exportPDF() {
		if (isStoragePermissionGranted()){
			doExportPDF();
		}
	}

	private void doExportPDF(){
		saveImages();

		/*


		// open a new document
		PrintAttributes attributes = new PrintAttributes.Builder()
				.setMediaSize(PrintAttributes.MediaSize.ISO_A4)
				.setResolution(new PrintAttributes.Resolution("id", PRINT_SERVICE, 300, 300))
				.setColorMode(PrintAttributes.COLOR_MODE_COLOR)
				.setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0))
				.build();
		PrintedPdfDocument document = new PrintedPdfDocument(this, attributes);

		for (int i = 0; i < mViewPager.getChildCount(); i++) {
			// start a page
			PdfDocument.Page page = document.startPage(i);

			// draw something on the page
			View content = mViewPager.getChildAt(i);
			content.draw(page.getCanvas());

			document.finishPage(page);
		}

		// write the document content
		try {
			File f = new File(Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_PICTURES), "samples.pdf");
			document.writeTo(new FileOutputStream(f));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		document.close();*/
	}


	private void saveImages() {
		int current = mViewPager.getCurrentItem();
		for (int i = 0; i <= 5; i++){
			mViewPager.setCurrentItem(i, false);
			saveImage();
		}
		mViewPager.setCurrentItem(current, false);
	}

	private void saveImage() {
		
		View v = mViewPager.findViewById(R.id.export_frame);
		v.setDrawingCacheEnabled(true);
		v.buildDrawingCache();


		Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
		v.draw(c);

		/*

		// this is the important code :)
		// Without it the view will have a dimension of 0,0 and the bitmap will
		// be null
		v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
				View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
		v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());

		v.buildDrawingCache(true);
		Bitmap b = Bitmap.createBitmap(v.getDrawingCache());

		*/
		v.setDrawingCacheEnabled(false); // clear drawing cache

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		Date date = new Date();
		String name ="data"+"-"+dateFormat.format(date) + ".png";
		// String imageName = "TEST" + (String) name;

		File folder = new File(Environment.getExternalStorageDirectory()
				+ "/.TEST");
		// boolean success = false;
		if (!folder.exists()) {
			folder.mkdir();
		}

		File file = new File(folder + "/TEST" + name);
		try {
			file.createNewFile();
			FileOutputStream ostream = new FileOutputStream(file);
			b.compress(Bitmap.CompressFormat.PNG, 100, ostream);
			ostream.close();
			Log.d("Done", "Yes");
			Toast.makeText(getApplicationContext(),
					"Images" + name + "save in Sd card", Toast.LENGTH_SHORT)
					.show();
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Done", "No");
			Toast.makeText(getApplicationContext(),
					"Images in Sd card", Toast.LENGTH_SHORT).show();
		}
		finish();

	}



	public Bitmap getBitmapFromView(View view, int totalHeight, int totalWidth) {

		Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth,totalHeight , Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(returnedBitmap);
		Drawable bgDrawable = view.getBackground();
		if (bgDrawable != null)
			bgDrawable.draw(canvas);
		else
			canvas.drawColor(Color.WHITE);
		view.draw(canvas);
		return returnedBitmap;
	}
}
