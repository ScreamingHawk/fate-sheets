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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.model.Skill;
import link.standen.michael.fatesheets.util.SkillsHelper;

/**
 * Manages a list of character skills.
 */
public class SkillArrayAdapter extends ArrayAdapter<Skill> {

	private static final String TAG = SkillArrayAdapter.class.getName();

	private final Context context;
	private final int resourceId;
	private final List<Skill> items;

	private final List<TextWatcher> listeners = new ArrayList<>();

	private static List<String> skills;

	public SkillArrayAdapter(@NonNull Context context, @LayoutRes int resourceId, @NonNull List<Skill> items) {
		super(context, resourceId, items);

		this.context = context;
		this.resourceId = resourceId;
		this.items = items;

		// Refresh skills list
		skills = SkillsHelper.getSkills(context);
	}

	public Skill getItem(int index){
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
				SkillArrayAdapter.this.notifyDataSetChanged();
			}
		});

		final Skill item = getItem(position);

		// Value
		TextView valueView = ((TextView)view.findViewById(R.id.value));
		if (item.getValue() != null) {
			valueView.setText(item.getValue().toString());
		}
		// Update watcher for value changes
		for (TextWatcher l : listeners){
			valueView.removeTextChangedListener(l);
		}
		TextWatcher listener = new ValueTextChangedListener(item);
		listeners.add(listener);
		valueView.addTextChangedListener(listener);

		// Description
		Spinner descriptionView = ((Spinner)view.findViewById(R.id.description));
		descriptionView.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, skills));
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

	class ValueTextChangedListener implements TextWatcher {

		private final Skill item;

		private ValueTextChangedListener(Skill item){
			this.item = item;
		}

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
	}
}
