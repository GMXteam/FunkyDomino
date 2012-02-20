package org.andengine.ui.dialog;

import org.andengine.util.call.Callback;
import org.andengine.util.debug.Debug;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 09:35:55 - 14.12.2009
 */
public abstract class GenericInputDialogBuilder<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected final Callback<T> mSuccessCallback;
        /**
         * 
         */
        protected final OnCancelListener mOnCancelListener;
        /**
         * 
         */
        protected final int mTitleResID;
        /**
         * 
         */
        protected final int mMessageResID;
        /**
         * 
         */
        protected final int mIconResID;
        /**
         * 
         */
        protected final Context mContext;
	private final int mErrorResID;
	private final String mDefaultText;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pContext
         * @param pTitleResID
         * @param pMessageResID
         * @param pErrorResID
         * @param pIconResID
         * @param pSuccessCallback
         * @param pOnCancelListener
         */
        public GenericInputDialogBuilder(final Context pContext, final int pTitleResID, final int pMessageResID, final int pErrorResID, final int pIconResID, final Callback<T> pSuccessCallback, final OnCancelListener pOnCancelListener){
		this(pContext, pTitleResID, pMessageResID, pErrorResID, pIconResID, "", pSuccessCallback, pOnCancelListener);
	}

        /**
         * 
         * @param pContext
         * @param pTitleResID
         * @param pMessageResID
         * @param pErrorResID
         * @param pIconResID
         * @param pDefaultText
         * @param pSuccessCallback
         * @param pOnCancelListener
         */
        public GenericInputDialogBuilder(final Context pContext, final int pTitleResID, final int pMessageResID, final int pErrorResID, final int pIconResID, final String pDefaultText, final Callback<T> pSuccessCallback, final OnCancelListener pOnCancelListener){
		this.mContext = pContext;
		this.mTitleResID = pTitleResID;
		this.mMessageResID = pMessageResID;
		this.mErrorResID = pErrorResID;
		this.mIconResID = pIconResID;
		this.mDefaultText = pDefaultText;
		this.mSuccessCallback = pSuccessCallback;
		this.mOnCancelListener = pOnCancelListener;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pInput
         * @return
         */
        protected abstract T generateResult(final String pInput);

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @return
         */
        public Dialog create() {
		final EditText etInput = new EditText(this.mContext);
		etInput.setText(this.mDefaultText);

		final AlertDialog.Builder ab = new AlertDialog.Builder(this.mContext);
		if(this.mTitleResID != 0) {
			ab.setTitle(this.mTitleResID);
		}
		if(this.mMessageResID != 0) {
			ab.setMessage(this.mMessageResID);
		}
		if(this.mIconResID != 0) {
			ab.setIcon(this.mIconResID);
		}

		this.setView(ab, etInput);
		ab.setOnCancelListener(this.mOnCancelListener)
		.setPositiveButton(android.R.string.ok, new OnClickListener() {
			@Override
			public void onClick(final DialogInterface pDialog, final int pWhich) {
				final T result;
				try{
					result = GenericInputDialogBuilder.this.generateResult(etInput.getText().toString());
				} catch (final IllegalArgumentException e) {
					Debug.e("Error in GenericInputDialogBuilder.generateResult()", e);
					Toast.makeText(GenericInputDialogBuilder.this.mContext, GenericInputDialogBuilder.this.mErrorResID, Toast.LENGTH_SHORT).show();
					return;
				}
				GenericInputDialogBuilder.this.mSuccessCallback.onCallback(result);
				pDialog.dismiss();
			}
		})
		.setNegativeButton(android.R.string.cancel, new OnClickListener() {
			@Override
			public void onClick(final DialogInterface pDialog, final int pWhich) {
				GenericInputDialogBuilder.this.mOnCancelListener.onCancel(pDialog);
				pDialog.dismiss();
			}
		});

		return ab.create();
	}

        /**
         * 
         * @param pBuilder
         * @param pInputEditText
         */
        protected void setView(final AlertDialog.Builder pBuilder, final EditText pInputEditText) {
		pBuilder.setView(pInputEditText);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
