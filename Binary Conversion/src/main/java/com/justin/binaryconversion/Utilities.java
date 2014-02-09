package com.justin.binaryconversion;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.widget.Toast;

import java.lang.annotation.Target;

/**
 * Created by Justin on 2/8/14.
 */
public class Utilities {
    @TargetApi(11)
    @SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi" })
    @SuppressWarnings("deprecation")
    public static void CopyTextToClipboard(String s, Context c) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(s);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("text label",s);
            clipboard.setPrimaryClip(clip);
        }
        Toast.makeText(c, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
    }
}
