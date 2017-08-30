package br.com.qgdostark.comandroid.util;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * Created by stark on 26/08/17.
 */

public class Validator {

    public static boolean validadeNotNull(View mView, String mMessage){
        if(mView instanceof EditText){
            EditText editText = (EditText) mView;
            Editable text = editText.getText();
            if(text != null){
                String srtText = text.toString();
                if(!TextUtils.isEmpty(srtText)){
                    return true;
                }
            }
            editText.setError(mMessage);
            editText.setFocusable(true);
            editText.requestFocus();
            return false;
        }
        return false;
    }

}
