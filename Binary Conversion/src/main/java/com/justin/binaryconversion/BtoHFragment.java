package com.justin.binaryconversion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Justin on 1/17/14.
 */
public class BtoHFragment extends Fragment {
    public BtoHFragment() {

    }

    private EditText inputTextEdit, outputTextEdit;
    private Button clearButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View rootView = inflater.inflate(R.layout.fragment_btoh, container, false);
        inputTextEdit = (EditText)rootView.findViewById(R.id.input_editText);
        outputTextEdit = (EditText)rootView.findViewById(R.id.output_editText);
        clearButton = (Button)rootView.findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputTextEdit.setText("");
            }
        });

        outputTextEdit.setEnabled(false);

        inputTextEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                convertText();
            }
        });

        return rootView;
    }

    private void convertText() {
        String input = inputTextEdit.getText().toString();
    }
}

