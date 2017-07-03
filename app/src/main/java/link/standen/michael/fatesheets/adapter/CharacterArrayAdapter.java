package link.standen.michael.fatesheets.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.activity.CharacterListActivity;
import link.standen.michael.fatesheets.activity.FAECharacterEditActivity;
import link.standen.michael.fatesheets.model.Character;
import link.standen.michael.fatesheets.util.CharacterHelper;
import link.standen.michael.fatesheets.util.JsonFileHelper;

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

		final String filename = getItem(position);
		final String name = CharacterHelper.getCharacterNameFromFilename(filename);
		final Resources resources = context.getResources();

		View.OnClickListener editListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = null;
				if (CharacterHelper.filenameIsCore(filename)) {
					intent = new Intent(context, CoreCharacterEditActivity.class);
				} else if (CharacterHelper.filenameIsFAE(filename)) {
					intent = new Intent(context, FAECharacterEditActivity.class);
				}
				if (intent != null) {
					intent.putExtra(Character.INTENT_EXTRA_NAME, name);
					context.startActivity(intent);
				} else {
					// Log and alert error
					Log.e(TAG, String.format("Error opening sheet: %s", filename));
					Snackbar.make(parent, resources.getString(R.string.toast_sheet_open_error), Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
				}
			}
		};

		// Description
		TextView descriptionView = (TextView) view.findViewById(R.id.character_name);
		descriptionView.setText(name);
		descriptionView.setOnClickListener(editListener);

		// Edit button
		view.findViewById(R.id.more_character).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PopupMenu pop = new PopupMenu(context, v);
				pop.getMenuInflater().inflate(R.menu.menu_character_option, pop.getMenu());
				pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						switch (item.getItemId()) {
							case R.id.action_sheet_share_json:
								// Share button
								Intent intent = new Intent(Intent.ACTION_SEND);
								intent.setType("text/plain");
								// Share the file for things like Drive
								intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context,
										context.getPackageName() + ".provider",
										context.getFileStreamPath(filename)));
								// Share the file contents for things like Copy to Clipboard
								intent.putExtra(Intent.EXTRA_TEXT, JsonFileHelper.getJsonFromFile(context, filename));

								intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

								context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.share_via)));
								break;

							case R.id.action_sheet_delete:
								// Delete button
								// Are you sure dialog
								AlertDialog.Builder builder = new AlertDialog.Builder(context);
								builder.setMessage(resources.getString(R.string.character_delete_dialog, name))
										.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												// Do the delete
												if (CharacterHelper.deleteCharacterFile(context, filename)) {
													items.remove(position);
													CharacterArrayAdapter.this.notifyDataSetChanged();
													context.checkEmptyCharacterList();
													Snackbar.make(parent, resources.getString(R.string.toast_character_deleted_successful, name), Snackbar.LENGTH_LONG)
															.setAction("Action", null).show();
												} else {
													Snackbar.make(parent, resources.getString(R.string.toast_character_deleted_error), Snackbar.LENGTH_LONG)
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
								break;
						}
						return true;
					}
				});
				pop.show();
			}
		});
		return view;
	}
}
