package link.standen.michael.fatesheets.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import link.standen.michael.fatesheets.R;

/**
 * Manages a list of deletable strings.
 */
public class DeletableStringArrayAdapter extends ArrayAdapter<String> {

	private static final String TAG = DeletableStringArrayAdapter.class.getName();

	private final Context context;
	private final int resourceId;
	private final List<String> items;

	public DeletableStringArrayAdapter(@NonNull Context context, @LayoutRes int resourceId, @NonNull List<String> items) {
		super(context, resourceId, items);

		this.context = context;
		this.resourceId = resourceId;
		this.items = items;
	}

	public String getItem(int index){
		return items.get(index);
	}

	@NonNull
	@Override
	public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view = convertView;
		if (view == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(resourceId, null);
		}

		view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				items.remove(position);
				DeletableStringArrayAdapter.this.notifyDataSetChanged();
			}
		});

		// Description
		TextView descriptionView = ((TextView)view.findViewById(R.id.description));
		descriptionView.setText(getItem(position));
		// Update description field on focus lost
		descriptionView.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}

			@Override
			public void afterTextChanged(Editable s) {
				items.set(position, s.toString());
			}
		});

		return view;
	}
}
