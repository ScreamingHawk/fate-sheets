package link.standen.michael.fatesheets.adapter;

import android.content.Context;
import android.content.Intent;
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
import link.standen.michael.fatesheets.activity.CharacterListActivity;
import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.model.Character;
import link.standen.michael.fatesheets.model.CharacterViewHolder;

/**
 * Manages a list of deletable strings.
 */
public class DeletableStringArrayAdapter extends ArrayAdapter<String> {

	private static final String TAG = DeletableStringArrayAdapter.class.getName();

	private final CoreCharacterEditActivity context;
	private final int resourceId;
	private final List<String> items;

	public DeletableStringArrayAdapter(@NonNull CoreCharacterEditActivity context, @LayoutRes int resourceId, @NonNull List<String> items) {
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

		TextView descriptionView = ((TextView)view.findViewById(R.id.description));
		descriptionView.setText(getItem(position));
		// Update description field on focus lost
		descriptionView.setOnKeyListener(new View.OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				items.set(position, ((TextView)v).getText().toString());
				return false;
			}
		});

		return view;
	}
}
