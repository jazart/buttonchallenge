package com.jazart.buttonchallenge;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jazart on 3/21/2018.
 */
/*
this class works similarly to NewTransfer.java. See for details on functionality
 */
public class NewUserDialog extends DialogFragment implements View.OnClickListener {

    private EditUserDialogListener mListener;
    private EditText name, id, email, candidate;
    public NewUserDialog() {

    }

    private void errorToast() {
        Toast.makeText(getActivity(), "Please complete all fields.", Toast.LENGTH_SHORT).show();
    }

    private boolean checkisEmptyEditTextViews() {
        return (name.length() < 1 || id.length() < 1 || email.length() < 1 || candidate.length() < 1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mListener = (EditUserDialogListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_new_user, null);

        name = v.findViewById(R.id.new_name);
        id = v.findViewById(R.id.new_id);
        email = v.findViewById(R.id.new_email);
        candidate = v.findViewById(R.id.new_candidate);
        Button submit = v.findViewById(R.id.submit);

        submit.setOnClickListener(this);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("New User")
                .create();
    }

    @Override
    public void onClick(View v) {
        String uName = name.getText().toString();
        int uId = id.length() > 0 ? Integer.parseInt(id.getText().toString()) : 0;
        String uEmail = email.getText().toString();
        String uCan = candidate.getText().toString();

        User user = new User();
        user.setName(uName);
        user.setId(uId);
        user.setEmail(uEmail);
        user.setCandidate(uCan);

        if(checkisEmptyEditTextViews()) {
            errorToast();
            dismiss();
            this.show(getFragmentManager(), MainActivity.DIALOG_NEW_USER);
        } else {
            mListener.onFinishedEditDialog(user);
            dismiss();
        }

    }



    public interface EditUserDialogListener {
        void onFinishedEditDialog(User user);
    }
}
