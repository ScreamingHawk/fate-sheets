package link.standen.michael.fatesheets.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * An extension of the EditText view that adds the ability to clear all textChangeListeners.
 */
public class SingleChangeListenerEditText extends AppCompatEditText {

	private TextWatcher mWatcher = null;

	private final OnAttachStateChangeListener stateChangeListener = new OnAttachStateChangeListener() {
		@Override
		public void onViewAttachedToWindow(View v) {}

		@Override
		public void onViewDetachedFromWindow(View v) {
			if (v != null && v instanceof SingleChangeListenerEditText){
				((SingleChangeListenerEditText) v).clearTextChangedListeners();
			}
		}
	};

	public SingleChangeListenerEditText(Context context){
		super(context);
		addOnAttachStateChangeListener(stateChangeListener);
	}

	public SingleChangeListenerEditText(Context context, AttributeSet attrs){
		super(context, attrs);
		addOnAttachStateChangeListener(stateChangeListener);
	}

	public SingleChangeListenerEditText(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		addOnAttachStateChangeListener(stateChangeListener);
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

	@Override
	protected void onDetachedFromWindow(){
		clearTextChangedListeners();
		super.onDetachedFromWindow();
	}

}
