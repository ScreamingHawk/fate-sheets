package link.standen.michael.fatesheets.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;

/**
 * A linear layout that will contain views taken from an adapter. It differs
 * from the list view in the fact that it will not optimize anything and
 * draw all the views from the adapter. It also does not provide scrolling.
 * However, when you need a layout that will render views horizontally and
 * you know there are not many child views, this is a good option.
 *
 * @author Vincent Mimoun-Prat @ MarvinLabs
 */
public class AdapterLinearLayout extends LinearLayout {

	private static final String TAG = AdapterLinearLayout.class.getName();

	private Adapter adapter;
	private DataSetObserver dataSetObserver = new DataSetObserver() {
		@Override
		public void onChanged() {
			super.onChanged();
			reloadChildViews();
		}
	};

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public AdapterLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public AdapterLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AdapterLinearLayout(Context context) {
		super(context);
	}

	public void setAdapter(Adapter adapter) {
		if (this.adapter == adapter){
			return;
		}
		this.adapter = adapter;
		if (adapter != null){
			adapter.registerDataSetObserver(dataSetObserver);
		}
		reloadChildViews();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if (adapter != null){
			try {
				adapter.unregisterDataSetObserver(dataSetObserver);
			} catch (IllegalStateException e){
				// Log error and ignore. I think it's fine.
				Log.e(TAG, "Observer was not registered.", e);
			}
		}
	}

	private void reloadChildViews() {
		removeAllViewsInLayout();

		if (adapter == null){
			return;
		}

		int count = adapter.getCount();
		for (int position = 0; position < count; position++) {
			View v = adapter.getView(position, null, this);
			if (v != null){
				addView(v);
			}
		}

		requestLayout();
	}
}
