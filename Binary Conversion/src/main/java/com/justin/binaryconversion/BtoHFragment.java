package com.justin.binaryconversion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    private Button convertButton;

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
        convertButton = (Button)rootView.findViewById(R.id.convert_button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertText();
            }
        });

        outputTextEdit.setEnabled(false);

        return rootView;
    }

    private void convertText() {
        String input = inputTextEdit.getText().toString();

        if (input.length() % 4 != 0) {
            outputTextEdit.setText("Error: Not a multiple of 4");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        StringBuffer outputBuffer = new StringBuffer();
        for (int i = 1; i < input.length() + 1; i++) {

            buffer.append(input.charAt(i-1));
            Log.d("Loop", "i = " + i + ", buffer = " + buffer.toString());

            if (i % 4 == 0 && i != 0) {
                outputBuffer.append(toHex(buffer.toString()));
                Log.d("Mof4", "outputBuffer = " + outputBuffer.toString());
                buffer = new StringBuffer();
            }

        }
        outputTextEdit.setText(outputBuffer.toString());
    }

    private char toHex(String s) {
        if (s.equals("0000")) return '0';
        else if (s.equals("0001")) return '1';
        else if (s.equals("0010")) return '2';
        else if (s.equals("0011")) return '3';
        else if (s.equals("0100")) return '4';
        else if (s.equals("0101")) return '5';
        else if (s.equals("0110")) return '6';
        else if (s.equals("0111")) return '7';
        else if (s.equals("1000")) return '8';
        else if (s.equals("1001")) return '9';
        else if (s.equals("1010")) return 'a';
        else if (s.equals("1011")) return 'b';
        else if (s.equals("1100")) return 'c';
        else if (s.equals("1101")) return 'd';
        else if (s.equals("1110")) return 'e';
        else if (s.equals("1111")) return 'f';
        else return 'x';
    }
}

