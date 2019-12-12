package com.app.magiclib.util;

import android.util.Patterns;
import android.widget.EditText;


import com.app.magiclib.ui.BaseActivity;

import java.util.ArrayList;


// Manish Rathod

public class ValidationClass {

    public static MyBuilder myBuilder(BaseActivity baseActivity) {
        return new MyBuilder(baseActivity);
    }

    public static class Validation {
        public EditText editText;
        public int msg;
        public int emailMsg;
        public int conPassMsg;
        public int limitDigit;
        public int limitDigitMsg;

        public Validation(EditText editText, int msg, int emailMsg, int conPassMsg, int limitDigit, int limitDigitMsg) {
            this.editText = editText;
            this.msg = msg;
            this.emailMsg = emailMsg;
            this.conPassMsg = conPassMsg;
            this.limitDigit = limitDigit;
            this.limitDigitMsg = limitDigitMsg;
        }


    }

    public static final class MyBuilder {

        public ArrayList<Validation> editTextArrayList;
        public BaseActivity baseActivity;

        private MyBuilder(BaseActivity baseActivity) {
            this.baseActivity = baseActivity;
            editTextArrayList = new ArrayList<>();
        }

        public MyBuilder add(EditText editText, int msg) {
            editTextArrayList.add(new Validation(editText, msg, 0, 0, 0, 0));
            return this;
        }

        public MyBuilder addWithEmail(EditText editText, int msg, int msg1) {
            editTextArrayList.add(new Validation(editText, msg, msg1, 0, 0, 0));
            return this;
        }

        public MyBuilder addWithConPass(EditText editText, int msg, int msg1) {
            editTextArrayList.add(new Validation(editText, msg, 0, msg1, 0, 0));
            return this;
        }

        public MyBuilder addWithLimit(EditText editText, int msg, int limit, int limitMsg) {
            editTextArrayList.add(new Validation(editText, msg, 0, 0, limit, limitMsg));
            return this;
        }

        public void checkValidation(ValidationListener validation) {
            if (editTextArrayList == null && editTextArrayList.size() <= 0) {

            } else {
                for (int i = 0; i < editTextArrayList.size(); i++) {
                    if (editTextArrayList.get(i).editText.getText().toString().trim().isEmpty()) {
                        editTextArrayList.get(i).editText.requestFocus();
                        validation.onInValid(getStringFromId(editTextArrayList.get(i).msg));
                        baseActivity.showDialog( getStringFromId(editTextArrayList.get(i).msg), null);
                        return;
                    } else if (editTextArrayList.get(i).emailMsg != 0) {
                        if (!Patterns.EMAIL_ADDRESS.matcher(editTextArrayList.get(i).editText.getText().toString().trim()).matches()) {
                            editTextArrayList.get(i).editText.requestFocus();
                            validation.onInValid(getStringFromId(editTextArrayList.get(i).emailMsg));
                            baseActivity.showDialog( getStringFromId(editTextArrayList.get(i).emailMsg), null);
                            return;
                        }
                    } else if (editTextArrayList.get(i).limitDigitMsg != 0) {
                        if (editTextArrayList.get(i).editText.getText().toString().trim().length() < editTextArrayList.get(i).limitDigit) {
                            editTextArrayList.get(i).editText.requestFocus();
                            validation.onInValid(getStringFromId(editTextArrayList.get(i).limitDigitMsg));
                            baseActivity.showDialog( getStringFromId(editTextArrayList.get(i).limitDigitMsg), null);
                            return;
                        }
                    } else if (editTextArrayList.get(i).conPassMsg != 0) {
                        if (!editTextArrayList.get(i).editText.getText().toString().trim().equalsIgnoreCase(editTextArrayList.get(i - 1).editText.getText().toString().trim())) {
                            editTextArrayList.get(i).editText.requestFocus();
                            validation.onInValid(getStringFromId(editTextArrayList.get(i).conPassMsg));
                            baseActivity.showDialog( getStringFromId(editTextArrayList.get(i).conPassMsg), null);
                            return;
                        }
                    }
                }

                validation.onValid();
            }
        }

        public String getStringFromId(int id) {
            return baseActivity.getResources().getString(id);
        }
    }

    public interface ValidationListener {
        void onValid();

        void onInValid(String msg);
    }
}
