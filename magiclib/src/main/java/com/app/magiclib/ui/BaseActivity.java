package com.app.magiclib.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.app.magiclib.BuildConfig;
import com.app.magiclib.R;
import com.app.magiclib.util.Config;
import com.app.magiclib.util.UtilClass;

import java.util.ArrayList;


public class BaseActivity extends AppCompatActivity {

    public Dialog dialog;

    public boolean showDialog = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialog = new Dialog(this, R.style.DialogSlideAnim_leftright);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.animation_loading);
    }

    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public boolean isOnline() {
        return isOnline(true);
    }

    public boolean isOnline(boolean ShowProgress) {
        if (UtilClass.isOnline(this)) {
            if (ShowProgress)
                showProgress();
            return true;
        } else {
            if (ShowProgress)
                showToast(getString(R.string.msg_no_internet));
            return false;
        }
    }

    public void showProgress() {
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public void hideProgress() {
        try {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        } catch (Exception e) {
        }
    }

    public String stackTraceToString(Exception e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void hideKeyBord() {
        try {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }

    @Override
    protected void onDestroy() {
        hideProgress();
        super.onDestroy();
        Runtime.getRuntime().gc();
    }


    public void showDialog(String msg, String okText, final OnDialogClickListener onDialogClickListener) {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_options);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.setCancelable(false);


        final TextView txt = (TextView) dialog.findViewById(R.id.txt_alert);
        final Button done = (Button) dialog.findViewById(R.id.btn_alert);
        final Button cancel = dialog.findViewById(R.id.btn_alertcancle);
        cancel.setVisibility(View.GONE);

        done.setText(okText);
        txt.setText(msg);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (onDialogClickListener != null)
                    onDialogClickListener.OnYesClick();
            }
        });
        dialog.show();
    }

    public void showDialog(String msg, OnDialogClickListener onDialogClickListener) {
        showDialog(msg, getResources().getString(R.string.alert_ok), onDialogClickListener);
    }

    public void confirmShowDialog(String msg, String okText, String noText, final OnDialogClickListener onDialogClickListener) {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_options);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.setCancelable(false);


        final TextView txt = (TextView) dialog.findViewById(R.id.txt_alert);
        final Button done = (Button) dialog.findViewById(R.id.btn_alert);
        final Button cancel = dialog.findViewById(R.id.btn_alertcancle);

        done.setText(okText);
        cancel.setText(noText);
        txt.setText(msg);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onDialogClickListener.OnYesClick();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogClickListener.OnNoClick();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void confirmShowDialog(String msg, OnDialogClickListener onDialogClickListener) {
        confirmShowDialog(msg, getResources().getString(R.string.alert_yes), getResources().getString(R.string.alert_no), onDialogClickListener);
    }


    public void showListDialog(final Context context, final ArrayList<String> list, final boolean isSearch, final OnSearchDialogClickListener onSearchDialogClickListener) {

        if (list == null || list.size() == 0)
            return;

        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_search);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.setCancelable(false);

        Button cancel = dialog.findViewById(R.id.btn_alertcancle);
        EditText edtSearch = dialog.findViewById(R.id.edtSearch);
        ListView lvSearch = dialog.findViewById(R.id.lvSearch);

        final ArrayList<String> tempList = new ArrayList<>();
        tempList.addAll(list);

        final ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.select_dialog_item, tempList);
        lvSearch.setAdapter(adapter);
        if (isSearch) {
            edtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().length() > 2) {
                        tempList.clear();
                        for (String s : list) {
                            if (s.toLowerCase().contains(charSequence.toString().toLowerCase()))
                                tempList.add(s);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        tempList.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        } else {
            edtSearch.setVisibility(View.GONE);
        }

        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isSearch) {
                    for (int i1 = list.size() - 1; i1 >= 0; i1--) {
                        if (list.get(i1).equals(tempList.get(i))) {
                            onSearchDialogClickListener.OnSelectClick(i1, list.get(i1));
                            break;
                        }
                    }
                } else {
                    onSearchDialogClickListener.OnSelectClick(i, list.get(i));
                }

                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void openPlayStore() {
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)), Config.CODE_OPEN_PLAY_STORE);
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)), Config.CODE_OPEN_PLAY_STORE);
        }
    }

    public interface OnDialogClickListener {
        void OnYesClick();

        void OnNoClick();
    }

    public void logPrint(String s) {
        if (BuildConfig.DEBUG) {
            System.out.println(s);
        }
    }

    public interface OnSearchDialogClickListener {
        void OnSelectClick(int pos, String item);
    }

}
