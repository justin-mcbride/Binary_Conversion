package com.justin.binaryconversion;


import android.media.Image;
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
 * Created by Justin on 2/8/14.
 */
public class BtoAFragment extends Fragment {
    public BtoAFragment() {

    }

    private Button mConvertButton, mClearButton;
    private ImageButton mCopyButton1, mCopyButton2;
    private EditText mBinaryEditText, mASCIIEditText;
    private Switch mReverseSwitch;
    private boolean mSwitched = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View rootView = inflater.inflate(R.layout.fragment_btoa, container, false);

        // Get all the subviews
        mBinaryEditText = (EditText)rootView.findViewById(R.id.binary_editText);
        mASCIIEditText = (EditText)rootView.findViewById(R.id.ascii_editText);
        mConvertButton = (Button)rootView.findViewById(R.id.convert_button);
        mClearButton = (Button)rootView.findViewById(R.id.clear_button);
        mReverseSwitch = (Switch)rootView.findViewById(R.id.reverse_switch);
        mCopyButton1 = (ImageButton)rootView.findViewById(R.id.copyButton1);
        mCopyButton2 = (ImageButton)rootView.findViewById(R.id.copyButton2);

        // Set up the subviews
        mConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mSwitched) convertBinarytoASCII();
                else convertASCIItoBinary();
            }
        });
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinaryEditText.setText("");
                mASCIIEditText.setText("");
            }
        });

        mReverseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // is checked, wants Reversed
                    mSwitched = true;
                    mBinaryEditText.setEnabled(false);
                    mASCIIEditText.setEnabled(true);
                }
                else {
                    // isn't checked, wants normal
                    mSwitched = false;
                    mBinaryEditText.setEnabled(true);
                    mASCIIEditText.setEnabled(false);
                }
            }
        });

        mCopyButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.CopyTextToClipboard(mBinaryEditText.getText().toString(), getActivity());
            }
        });
        mCopyButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.CopyTextToClipboard(mASCIIEditText.getText().toString(), getActivity());
            }
        });

        return rootView;
    }

    private void convertASCIItoBinary() {
        byte[] bytes = mASCIIEditText.getText().toString().getBytes();
        StringBuilder binary = new StringBuilder();

        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        mBinaryEditText.setText(binary);
    }

    private void convertBinarytoASCII() {

    }
}