package link.standen.michael.fatesheets.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.adapter.DeletableStringArrayAdapter;
import link.standen.michael.fatesheets.view.AdapterLinearLayout;
import link.standen.michael.fatesheets.util.SkillsHelper;

public class EditSkillsActivity extends AppCompatActivity {

	private static final String TAG = EditSkillsActivity.class.getName();

	private final List<String> skills;
	private ArrayAdapter listAdapter;

	public EditSkillsActivity() {
		this.skills = new ArrayList<>();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_skills_activity);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// Initialise list view
		refreshSkills();
		listAdapter = new DeletableStringArrayAdapter(this, R.layout.edit_skills_list_item, skills);
		final AdapterLinearLayout listView = (AdapterLinearLayout) findViewById(android.R.id.list);
		listView.setAdapter(listAdapter);

		// Add skill
		findViewById(R.id.add_skill).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				skills.add("");
				listAdapter.notifyDataSetChanged();
			}
		});

		// FAB
		findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (SkillsHelper.saveSkills(EditSkillsActivity.this, skills)) {
					Snackbar.make(view, getResources().getString(R.string.toast_skills_saved_successful), Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
				} else {
					Snackbar.make(view, getResources().getString(R.string.toast_skills_saved_error), Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
				}
			}
		});
		findViewById(R.id.fab_2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Dialog to select which sheet type to create
				AlertDialog.Builder builder = new AlertDialog.Builder(EditSkillsActivity.this);
				builder.setTitle(R.string.reset_skills_list_confirmation_title);
				builder.setMessage(R.string.reset_skills_list_confirmation_body);

				builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						revertSkills();
					}
				});
				builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.show();
			}
		});
	}

	/**
	 * Refreshes the skills list with the saved list.
	 */
	private void refreshSkills(){
		skills.clear();
		skills.addAll(SkillsHelper.getSkills(this));
		if (listAdapter != null) {
			listAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * Reverts the skills list to the default list.
	 */
	private void revertSkills(){
		skills.clear();
		skills.addAll(SkillsHelper.getDefaultSkills(this));
		if (listAdapter != null) {
			listAdapter.notifyDataSetChanged();
		}
	}

}
