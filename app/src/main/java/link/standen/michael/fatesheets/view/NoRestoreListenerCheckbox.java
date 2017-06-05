package link.standen.michael.fatesheets.view;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

/**
 * An extension of the Checkbox view that clears listeners before restoring instance state.
 */
public class NoRestoreListenerCheckbox extends AppCompatCheckBox {

	public NoRestoreListenerCheckbox(Context context){
		super(context);
	}

	public NoRestoreListenerCheckbox(Context context, AttributeSet attrs){
		super(context, attrs);
	}

	public NoRestoreListenerCheckbox(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}

	@Override
	public Parcelable onSaveInstanceState(){
		setOnCheckedChangeListener(null);
		return super.onSaveInstanceState();
	}

	@Override
	public void onRestoreInstanceState(Parcelable state){
		setOnCheckedChangeListener(null);
		super.onRestoreInstanceState(state);
	}

}
