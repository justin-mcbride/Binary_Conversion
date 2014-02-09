package com.justin.binaryconversion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Created by Justin on 1/17/14.
 */
public class BtoHFragment extends Fragment {
    public BtoHFragment() {
        createMap();
    }

    private EditText binaryEditText, hexEditText;
    private Button clearButton, convertButton;
    private ImageButton mCopyButton1, mCopyButton2;
    private Switch mReverseSwitch;

    private boolean mSwitched = false;
    private BiMap<String, String> mMap;
    private BiMap<String, String> mReverseMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View rootView = inflater.inflate(R.layout.fragment_btoh, container, false);

        // Get the subviews
        binaryEditText = (EditText)rootView.findViewById(R.id.binary_editText);
        hexEditText = (EditText)rootView.findViewById(R.id.hex_editText);
        convertButton = (Button)rootView.findViewById(R.id.convert_button);
        clearButton = (Button)rootView.findViewById(R.id.clear_button);
        mCopyButton1 = (ImageButton)rootView.findViewById(R.id.copyButton1);
        mCopyButton2 = (ImageButton)rootView.findViewById(R.id.copyButton2);
        mReverseSwitch = (Switch)rootView.findViewById(R.id.reverse_switch);

        // Setup the subviews
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binaryEditText.setText("");
                hexEditText.setText("");
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mSwitched) binaryToHex();
                else hexToBinary();
            }
        });

        mReverseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    // not enabled, want normal
                    mSwitched = false;
                    binaryEditText.setEnabled(true);
                    hexEditText.setEnabled(false);
                }
                else {
                    // enabled, wants reverse
                    mSwitched = true;
                    binaryEditText.setEnabled(false);
                    hexEditText.setEnabled(true);
                }
            }
        });

        mCopyButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.CopyTextToClipboard(binaryEditText.getText().toString(), getActivity());
            }
        });
        mCopyButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.CopyTextToClipboard(hexEditText.getText().toString(), getActivity());
            }
        });

        return rootView;
    }

    private void hexToBinary() {
        String input = hexEditText.getText().toString();
        StringBuilder out = new StringBuilder();

        try {
            for (int i = 0; i < input.length(); i++) {
                out.append(binaryLookup(input.charAt(i)));
            }
        } catch (MalformedInputException e) {
            binaryEditText.setText(getString(R.string.parse_error) + e.getMessage());
            return;
        }

        Log.d("hexToBinary()", "out = " + out.toString());
        binaryEditText.setText(out.toString());
    }

    private void binaryToHex() {
        String input = binaryEditText.getText().toString();

        if (input.length() % 4 != 0) {
            hexEditText.setText("Error: Not a multiple of 4");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        StringBuffer outputBuffer = new StringBuffer();
        for (int i = 1; i < input.length() + 1; i++) {

            buffer.append(input.charAt(i-1));
            //Log.d("Loop", "i = " + i + ", buffer = " + buffer.toString());

            if (i % 4 == 0 && i != 0) {
                char c;
                try {
                    c = hexLookup(buffer.toString());
                } catch (MalformedInputException e) {
                    hexEditText.setText(getString(R.string.parse_error) + e.getMessage());
                    return;
                }
                outputBuffer.append(c);
                //Log.d("Mof4", "outputBuffer = " + outputBuffer.toString());
                buffer = new StringBuffer();
            }

        }
        hexEditText.setText(outputBuffer.toString());
    }

    private char hexLookup(String s) throws MalformedInputException {
        String out = mMap.get(s);
        if (s != null) {
            return out.charAt(0);
        }
        else {
            throw new MalformedInputException(s);
        }
    }

    private String binaryLookup(char c) throws MalformedInputException {
        String s = mReverseMap.get("" + c);
        Log.d("binaryLookup()", "input = " + c + ", output = " + s);
        if (s != null) return s;
        else throw new MalformedInputException("" + c);
    }

    private void createMap() {
        mReverseMap = HashBiMap.create();
        mReverseMap.put("0", "0000");
        mReverseMap.put("1", "0001");
        mReverseMap.put("2", "0010");
        mReverseMap.put("3", "0011");
        mReverseMap.put("4", "0100");
        mReverseMap.put("5", "0101");
        mReverseMap.put("6", "0110");
        mReverseMap.put("7", "0111");
        mReverseMap.put("8", "1000");
        mReverseMap.put("9", "1001");
        mReverseMap.put("a", "1010");
        mReverseMap.put("b", "1011");
        mReverseMap.put("c", "1100");
        mReverseMap.put("d", "1101");
        mReverseMap.put("e", "1110");
        mReverseMap.put("f", "1111");
        mMap = mReverseMap.inverse();
    }

    // Custom exception for when input is not convertable
    private class MalformedInputException extends Exception {
        public MalformedInputException(String s) {
            super(s);
        }
    }


}

