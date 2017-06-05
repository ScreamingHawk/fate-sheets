package link.standen.michael.fatesheets.view;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * An extension of the EditText view that adds the ability to clear all textChangeListeners.
 */
public class SingleChangeListenerEditText extends AppCompatEditText {

	private TextWatcher mWatcher = null;

	public SingleChangeListenerEditText(Context context){
		super(context);
	}

	public SingleChangeListenerEditText(Context context, AttributeSet attrs){
		super(context, attrs);
	}

	public SingleChangeListenerEditText(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}

	@Override
	public void addTextChangedListener(TextWatcher watcher){
		clearTextChangedListeners();
		mWatcher = watcher;

		super.addTextChangedListener(watcher);
	}

	@Override
	public void removeTextChangedListener(TextWatcher watcher){
		if (mWatcher == watcher){
			mWatcher = null;
		}
		super.removeTextChangedListener(watcher);
	}

	public void clearTextChangedListeners(){
		if (mWatcher != null){
			super.removeTextChangedListener(mWatcher);
			mWatcher = null;
		}
	}

	@Override
	public Parcelable onSaveInstanceState(){
		clearTextChangedListeners();
		return super.onSaveInstanceState();
	}

	@Override
	public void onRestoreInstanceState(Parcelable state){
		clearTextChangedListeners();
		super.onRestoreInstanceState(state);
	}

}
