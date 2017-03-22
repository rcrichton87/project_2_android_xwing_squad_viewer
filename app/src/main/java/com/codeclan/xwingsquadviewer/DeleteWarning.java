package com.codeclan.xwingsquadviewer;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DeleteWarning extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.confirm_delete_dialog, container, false);
        getDialog().setTitle("Confirm Deletion");

        //setting up the cancel button to dismiss the popup
        Button dismiss = (Button) rootView.findViewById(R.id.delete_no);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return rootView;
    }


}
