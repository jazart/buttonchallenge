package com.jazart.buttonchallenge;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jazart on 3/21/2018.
 */

public class NewTransferDialog extends DialogFragment implements View.OnClickListener {

    private EditText mTransIdText, mAmountText, mCandidateText, mUserIdText;
    private EditTransferDialogListener mTransferDialogListener;
    private Button mSubmitButton;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //casts the context as a listener interface. This verifies that the Activity/Fragment that called the dialog has implemented the interface.
        try {
            mTransferDialogListener = (EditTransferDialogListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_new_transfer, null );

        mTransIdText = v.findViewById(R.id.tId);
        mAmountText = v.findViewById(R.id.amount);
        mUserIdText = v.findViewById(R.id.uId);
        mCandidateText = v.findViewById(R.id.candidate_id);
        mSubmitButton = v.findViewById(R.id.submit_transfer);
        mSubmitButton.setOnClickListener(this);
        return new AlertDialog.Builder(getContext())
                .setView(v)
                .create();
    }


    //once user clicks okay, I verify if the fields are complete. If not a toast will appear and the dialog will reappear for them to try again.
    //method will get information from the edittext and create a transfer object. Once verified, the interfaced method will be called
    // with the transfer object attached and the dialog will be dismissed.
    @Override
    public void onClick(View view) {
        int transId = mTransIdText.length() > 0 ? Integer.parseInt(mTransIdText.getText().toString().trim()) : 0;
        String amount = mAmountText.getText().toString().trim();
        int userId = mUserIdText.length() > 0 ? Integer.parseInt(mUserIdText.getText().toString().trim()) : 0;
        String candidate = mCandidateText.getText().toString().trim();

        Transfer transfer = new Transfer(transId, amount, userId, candidate);
        if(transId == 0 || amount.isEmpty() || candidate.isEmpty() || userId == 0) {
            this.dismiss();
            Toast.makeText(getContext(), "Please complete the fields", Toast.LENGTH_SHORT)
                    .show();
            this.show(getFragmentManager(), MainActivity.NEW_TRANSFER_DIALOG);
        } else {
            mTransferDialogListener.onFinishedEditingTransfer(transfer);
            this.dismiss();
        }
    }

    //interface to pass in Transfer from dialog screen
    public interface EditTransferDialogListener {
        void onFinishedEditingTransfer(Transfer transfer);
    }
}
