package com.justin.binaryconversion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

/**
 * Created by Justin on 1/10/14.
 */
public class BtoDFragment extends Fragment {
    public BtoDFragment() {

    }

    private EditText mBinaryEditText, mDecimalEditText;
    private Button mClearButton, mConvertButton;
    private Switch mReverseSwitch;
    private ImageButton mCopyButton1, mCopyButton2;

    private boolean mSwitched = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_btod, container, false);

        // Get all the subviews
        mBinaryEditText = (EditText)rootView.findViewById(R.id.input_editText);
        mDecimalEditText = (EditText)rootView.findViewById(R.id.output_editText);
        mClearButton = (Button)rootView.findViewById(R.id.clear_button);
        mCopyButton1 = (ImageButton)rootView.findViewById(R.id.copyButton1);
        mCopyButton2 = (ImageButton)rootView.findViewById(R.id.copyButton2);
        mReverseSwitch = (Switch)rootView.findViewById(R.id.reverse_switch);
        mConvertButton = (Button)rootView.findViewById(R.id.convert_button);

        // Setup the subviews
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinaryEditText.setText("");
                mDecimalEditText.setText("");
            }
        });

        mReverseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    mSwitched = false;
                    mBinaryEditText.setEnabled(true);
                    mDecimalEditText.setEnabled(false);
                }
                else {
                    mSwitched = true;
                    mBinaryEditText.setEnabled(false);
                    mDecimalEditText.setEnabled(true);
                }
            }
        });
        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mSwitched) binaryToDecimal();
                else decimalToBinary();
            }
        });

        return rootView;
    }

    private void binaryToDecimal() {
        String input = new StringBuilder(mBinaryEditText.getText().toString()).reverse().toString();
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
                mDecimalEditText.setText("Parsing error.");
                return;
            }
        }
        mDecimalEditText.setText(Long.toString(output));
    }

    private void decimalToBinary() {
        String input = mDecimalEditText.getText().toString();
        long n;
        try {
            n = Long.parseLong(input);
        } catch (Exception e) {
            mBinaryEditText.setText(getString(R.string.parse_error));
            return;
        }
        int largestExp = 0;
        for (int i = 0; i < 20; i++) {
            if (n > Math.pow(2, i)) continue;
            else {
                largestExp = i - 1;
                break;
            }
        }
    }
}
