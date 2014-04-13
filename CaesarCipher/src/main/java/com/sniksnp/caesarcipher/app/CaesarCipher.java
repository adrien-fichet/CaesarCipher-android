package com.sniksnp.caesarcipher.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CaesarCipher extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_cipher);
        setUpButton();
    }

    private void setUpButton() {
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateList();
                dismissKeyboard();
            }
        });
    }

    private void dismissKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.editText).getWindowToken(), 0);
    }

    private void updateList() {
        String text = ((TextView) findViewById(R.id.editText)).getText().toString();
        ListView listView = (ListView) findViewById(R.id.listView);
        List<String> items = getCiphers(text);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.listitemtext, items);
        listView.setAdapter(listAdapter);
    }

    public List<String> getCiphers(String text) {
        List<String> result = new ArrayList<String>();

        for (int i=1; i < 26; i++) {
            result.add(caesarCipher(text, i));
        }

        return result;
    }

    public String caesarCipher(String strText, int iShiftValue) {
        int i;
        char c;
        int iShift;
        StringBuffer sb = new StringBuffer();

        iShift = iShiftValue % 26;
        if (iShift < 0) {
            iShift += 26;
        }

        i = 0;
        while (i < strText.length()) {
            c = Character.toUpperCase(strText.charAt(i));
            if ( (c >= 'A') && (c <= 'Z') ) {
                if ( (c + iShift) > 'Z') {
                    sb.append((char) (c + iShift - 26));
                } else {
                    sb.append((char) (c + iShift));
                }
            } else {
                sb.append(' ');
            }
            i++;
        }

        return sb.toString();
    }


}
