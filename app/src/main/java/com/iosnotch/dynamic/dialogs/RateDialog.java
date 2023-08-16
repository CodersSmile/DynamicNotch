package com.iosnotch.dynamic.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.iosnotch.dynamic.R;

public class RateDialog extends DialogFragment {
    public AlertDialog alertDialog;
    public RatingBar mRatingBar;
    public OnClickListener m_clickListener;

    public interface OnClickListener {
        void OnRate();

        void OnCancel();
    }

    public RateDialog() {
    }

    public RateDialog(OnClickListener onClickListener) {
        this.m_clickListener = onClickListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_rate, (ViewGroup) null);
        builder.setView(inflate);
        if (this.alertDialog == null) {
            this.alertDialog = builder.create();
        }
        this.alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.alertDialog.setCancelable(false);
        mRatingBar = inflate.findViewById(R.id.mRatingBar);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    m_clickListener.OnRate();
                    alertDialog.dismiss();
                }
            }
        });
        return this.alertDialog;
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        this.alertDialog = null;
    }
}