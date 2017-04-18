package link.standen.michael.fatesheets.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.activity.CharacterListActivity;
import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.model.Character;

/**
 * Manages a list of characters.
 */
public class CharacterArrayAdapter extends ArrayAdapter<String> {

	private static final String TAG = CharacterArrayAdapter.class.getName();

	private final CharacterListActivity context;
	private final int resourceId;
	private final List<String> items;

	public CharacterArrayAdapter(@NonNull CharacterListActivity context, @LayoutRes int resourceId, @NonNull List<String> items) {
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

		final String name = getItem(position);

		// Description
		((TextView) view.findViewById(R.id.character_name)).setText(name);

		// Buttons
		view.findViewById(R.id.edit_character).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, CoreCharacterEditActivity.class);
				intent.putExtra(Character.INTENT_EXTRA_NAME, name);
				context.startActivity(intent);
			}
		});
		view.findViewById(R.id.delete_character).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				items.remove(position);
				CharacterArrayAdapter.this.notifyDataSetChanged();
				//TODO Delete from storage
			}
		});
		return view;
	}
}
