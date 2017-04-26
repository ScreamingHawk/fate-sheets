package link.standen.michael.fatesheets.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.model.Approach;

/**
 * Manages a list of character approaches.
 */
public class ApproachArrayAdapter extends ArrayAdapter<Approach> {

	private static final String TAG = ApproachArrayAdapter.class.getName();

	private final Context context;
	private final int resourceId;
	private final List<Approach> items;

	public ApproachArrayAdapter(@NonNull Context context, @LayoutRes int resourceId, @NonNull List<Approach> items) {
		super(context, resourceId, items);

		this.context = context;
		this.resourceId = resourceId;
		this.items = items;
	}

	public Approach getItem(int index){
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
				ApproachArrayAdapter.this.notifyDataSetChanged();
			}
		});

		final Approach item = getItem(position);

		// Value
		TextView valueView = ((TextView)view.findViewById(R.id.value));
		if (item.getValue() != null) {
			valueView.setText(item.getValue().toString());
		}
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
		Spinner descriptionView = ((Spinner)view.findViewById(R.id.description));
		final String description = getItem(position).getDescription();
		if (description != null) {
			descriptionView.setSelection(((ArrayAdapter)descriptionView.getAdapter()).getPosition(description));
		} else {
			//FIXME descriptionView.setSelection();
		}
		// Update description field on focus lost
		descriptionView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				items.get(position).setDescription(parent.getItemAtPosition(pos).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});

		return view;
	}
}
