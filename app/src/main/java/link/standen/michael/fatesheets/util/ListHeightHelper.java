package link.standen.michael.fatesheets.util;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * A class for fixing the height of ListViews.
 *
 * http://stackoverflow.com/a/15973075/2027146
 */
public final class ListHeightHelper {

	private static final String TAG = ListHeightHelper.class.getName();

	private ListHeightHelper(){}

	/**
	 * Fixes the height of a ListView based on the height of the children.
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter adapter = listView.getAdapter();
		if (adapter == null) {
			// pre-condition
			return;
		}

		/*
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
*/
		int height = 0;
		int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
		for (int i = 0; i < adapter.getCount(); i++) {
			View listItem = adapter.getView(i, null, listView);
			listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
			height += listItem.getMeasuredHeight();
			Log.d(TAG, "Height: "+height);
		}
		ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
		layoutParams.height = height + (listView.getDividerHeight() * (adapter.getCount() - 1));
		Log.d(TAG, "Divider: "+listView.getDividerHeight());
		Log.d(TAG, "Total Height: "+height);
		listView.measure(0, 0);
		Log.d(TAG, "Total Height list: "+listView.getMeasuredHeight());
		Log.d(TAG, "Class: "+listView.getClass().getCanonicalName());
		listView.setLayoutParams(layoutParams);
		listView.requestLayout();
	}
}
