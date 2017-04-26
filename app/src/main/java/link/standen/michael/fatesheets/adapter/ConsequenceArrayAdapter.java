package link.standen.michael.fatesheets.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.model.Consequence;

/**
 * Manages a list of character skills.
 */
public class ConsequenceArrayAdapter extends ArrayAdapter<Consequence> {

	private static final String TAG = ConsequenceArrayAdapter.class.getName();

	private final Context context;
	private final int resourceId;
	private final List<Consequence> items;

	public ConsequenceArrayAdapter(@NonNull Context context, @LayoutRes int resourceId, @NonNull List<Consequence> items) {
		super(context, resourceId, items);

		this.context = context;
		this.resourceId = resourceId;
		this.items = items;
	}

	public Consequence getItem(int index){
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
				ConsequenceArrayAdapter.this.notifyDataSetChanged();
			}
		});

		final Consequence item = getItem(position);

		// Value
		TextView valueView = ((TextView)view.findViewById(R.id.value));
		valueView.setText(item.getValue().toString());
		valueView.setOnKeyListener(new View.OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				try {
					item.setValue(Integer.parseInt(((TextView)v).getText().toString()));
				} catch (NumberFormatException e){
					item.setValue(0);
				}
				return false;
			}
		});

		// Description
		TextView descriptionView = ((TextView)view.findViewById(R.id.description));
		descriptionView.setText(item.getDescription());
		descriptionView.setOnKeyListener(new View.OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				item.setDescription(((TextView)v).getText().toString());
				return false;
			}
		});

		return view;
	}
}
