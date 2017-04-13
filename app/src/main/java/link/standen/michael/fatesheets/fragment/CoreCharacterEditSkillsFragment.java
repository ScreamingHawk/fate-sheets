package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.activity.CoreCharacterEditActivity;
import link.standen.michael.fatesheets.adapter.DeletableStringArrayAdapter;
import link.standen.michael.fatesheets.adapter.SkillArrayAdapter;
import link.standen.michael.fatesheets.model.CoreCharacter;
import link.standen.michael.fatesheets.model.Skill;

/**
 * A fragment for managing a characters skills.
 */
public class CoreCharacterEditSkillsFragment extends CoreCharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.core_character_edit_skills, container, false);

		final CoreCharacter character = getCharacter();

		// Aspects
		final SkillArrayAdapter skillListAdapter = new SkillArrayAdapter((CoreCharacterEditActivity) getContext(),
				R.layout.core_character_edit_skills_list_item, getCharacter().getSkills());
		((ListView) rootView.findViewById(R.id.skills_list)).setAdapter(skillListAdapter);

		rootView.findViewById(R.id.add_skill).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				character.getSkills().add(new Skill(0, ""));
				skillListAdapter.notifyDataSetChanged();
			}
		});

		return rootView;
	}
}