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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.model.Stress;

/**
 * Manages a list of character stress.
 */
public class StressArrayAdapter extends ArrayAdapter<Stress> {

	private static final String TAG = StressArrayAdapter.class.getName();

	private final Context context;
	private final int resourceId;
	private final List<Stress> items;

	public StressArrayAdapter(@NonNull Context context, @LayoutRes int resourceId, @NonNull List<Stress> items) {
		super(context, resourceId, items);

		this.context = context;
		this.resourceId = resourceId;
		this.items = items;
	}

	public Stress getItem(int index){
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
				StressArrayAdapter.this.notifyDataSetChanged();
			}
		});

		final Stress item = getItem(position);

		// Value
		TextView valueView = ((TextView)view.findViewById(R.id.value));
		if (item.getValue() != null) {
			valueView.setText(item.getValue().toString());
		}
		valueView.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}

			@Override
			public void afterTextChanged(Editable s) {
				try {
					item.setValue(Integer.parseInt(s.toString()));
				} catch (NumberFormatException e){
					item.setValue(0);
				}
			}
		});

		// Active
		CheckBox activeView = ((CheckBox)view.findViewById(R.id.active));
		activeView.setChecked(item.getActive());
		activeView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				item.setActive(isChecked);
			}
		});

		return view;
	}
}
