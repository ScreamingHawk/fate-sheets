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
import link.standen.michael.fatesheets.model.CharacterViewHolder;

/**
 * Manages a list of characters.
 */
public class CharacterArrayAdapter extends ArrayAdapter<Character> {

	private static final String TAG = CharacterArrayAdapter.class.getName();

	private final CharacterListActivity context;
	private final int resourceId;
	private final List<Character> items;

	public CharacterArrayAdapter(@NonNull CharacterListActivity context, @LayoutRes int resourceId, @NonNull List<Character> items) {
		super(context, resourceId, items);

		this.context = context;
		this.resourceId = resourceId;
		this.items = items;
	}

	public Character getItem(int index){
		return items.get(index);
	}

	@NonNull
	@Override
	public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view = convertView;
		CharacterViewHolder holder;
		if (view == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(resourceId, null);
			holder = new CharacterViewHolder();
			holder.setNameView((TextView) view.findViewById(R.id.character_name));
			view.setTag(holder);

			view.findViewById(R.id.delete_character).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					items.remove(position);
					CharacterArrayAdapter.this.notifyDataSetChanged();
					//TODO Delete from storage
				}
			});
			view.findViewById(R.id.edit_character).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, CoreCharacterEditActivity.class);
					intent.putExtra(Character.INTENT_EXTRA_NAME, items.get(position));
					context.startActivity(intent);
				}
			});
		} else {
			holder = (CharacterViewHolder) view.getTag();
		}

		final Character item = getItem(position);
		if (item != null){
			holder.setCharacter(item);
			holder.getNameView().setText(item.getName());
		}
		return view;
	}
}
