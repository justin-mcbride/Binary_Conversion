package com.justin.binaryconversion;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Justin on 2/8/14.
 */
public class BtoAFragment extends Fragment {
    public BtoAFragment() {

    }

    private Button convertButton, clearButton;
    private EditText inputEditText, outputEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View rootView = inflater.inflate(R.layout.fragment_btoa, container, false);

        // Get all the subviews
        inputEditText = (EditText)rootView.findViewById(R.id.input_editText);
        outputEditText = (EditText)rootView.findViewById(R.id.output_editText);
        convertButton = (Button)rootView.findViewById(R.id.convert_button);
        clearButton = (Button)rootView.findViewById(R.id.clear_button);

        // Set up the subviews
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertText();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputEditText.setText("");
                outputEditText.setText("");
            }
        });

        return rootView;
    }

    private void convertText() {

    }
}
