package link.standen.michael.fatesheets.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import link.standen.michael.fatesheets.util.CharacterHelper;

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
	public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
		View view = convertView;
		if (view == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(resourceId, null);
		}

		final String name = getItem(position);

		// Description
		((TextView) view.findViewById(R.id.character_name)).setText(name);

		// Edit button
		view.findViewById(R.id.edit_character).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, CoreCharacterEditActivity.class);
				intent.putExtra(Character.INTENT_EXTRA_NAME, name);
				context.startActivity(intent);
			}
		});

		// Delete button
		view.findViewById(R.id.delete_character).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Are you sure dialog
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage(context.getResources().getString(R.string.character_delete_dialog, name))
						.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// Do the delete
								if (CharacterHelper.deleteCoreCharacter(context, name)) {
									items.remove(position);
									CharacterArrayAdapter.this.notifyDataSetChanged();
									context.checkEmptyCharacterList();
									Snackbar.make(parent, context.getResources().getString(R.string.toast_character_deleted_successful, name), Snackbar.LENGTH_LONG)
											.setAction("Action", null).show();
								} else {
									Snackbar.make(parent, context.getResources().getString(R.string.toast_character_deleted_error), Snackbar.LENGTH_LONG)
											.setAction("Action", null).show();
								}
							}
						})
						.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						}).show();
			}
		});
		return view;
	}
}
