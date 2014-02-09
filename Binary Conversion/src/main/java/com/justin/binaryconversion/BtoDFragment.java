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
import android.widget.ImageButton;

/**
 * Created by Justin on 1/10/14.
 */
public class BtoDFragment extends Fragment {
    public BtoDFragment() {

    }

    private EditText inputTextEdit, outputTextEdit;
    private Button clearButton;
    private ImageButton mCopyButton1, mCopyButton2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_btod, container, false);

        // Get all the subviews
        inputTextEdit = (EditText)rootView.findViewById(R.id.input_editText);
        outputTextEdit = (EditText)rootView.findViewById(R.id.output_editText);
        clearButton = (Button)rootView.findViewById(R.id.clear_button);
        mCopyButton1 = (ImageButton)rootView.findViewById(R.id.copyButton1);
        mCopyButton2 = (ImageButton)rootView.findViewById(R.id.copyButton2);

        // Setup the subviews
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
        String input = new StringBuilder(inputTextEdit.getText().toString()).reverse().toString();
        StringBuffer out = new StringBuffer();
        long output = 0;
        int n = input.length();
        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            if (c == '0') {
                // nothing
            } else if (c == '1') {
                output += Math.pow(2, i);
            } else {
                outputTextEdit.setText("Parsing error.");
                return;
            }
        }
        outputTextEdit.setText(Long.toString(output));
    }
}
