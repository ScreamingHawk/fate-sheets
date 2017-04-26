package link.standen.michael.fatesheets.activity;

import android.support.v7.app.AppCompatActivity;

import link.standen.michael.fatesheets.model.Character;

/**
 * An abstract class to force availability of getCharacter method while maintaining context
 * inheritance generically.
 */
public abstract class CharacterEditActivity extends AppCompatActivity {

	public abstract Character getCharacter();

}
