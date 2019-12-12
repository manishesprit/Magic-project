package com.app.magiclib.ui;

import android.content.Context;
import androidx.fragment.app.Fragment;
import com.app.magiclib.R;

import java.util.ArrayList;


public class BaseFragment extends Fragment {


    public void showToast(String s) {
        ((BaseActivity) getActivity()).showToast(s);
    }

    public boolean isOnline() {
        return isOnline(true);
    }

    public boolean isOnline(boolean ShowProgress) {
        return ((BaseActivity) getActivity()).isOnline(ShowProgress);
    }

    public void showProgress(String s) {
        ((BaseActivity) getActivity()).showProgress(s);
    }

    public void hideProgress() {
        ((BaseActivity) getActivity()).hideProgress();
    }

    public String stackTraceToString(Exception e) {
        return ((BaseActivity) getActivity()).stackTraceToString(e);
    }

    public void hideKeyBord() {
        ((BaseActivity) getActivity()).hideKeyBord();
    }

    @Override
    public void onDestroy() {
        ((BaseActivity) getActivity()).onDestroy();
        super.onDestroy();
    }


    public void showDialog(final Context context, String msg, String okText, final BaseActivity.OnDialogClickListener onDialogClickListener) {
        ((BaseActivity) getActivity()).showDialog(context, msg, okText, onDialogClickListener);
    }

    public void showDialog(final Context context, String msg, BaseActivity.OnDialogClickListener onDialogClickListener) {
        showDialog(context, msg, context.getResources().getString(R.string.alert_ok), onDialogClickListener);
    }

    public void confirmShowDialog(final Context context, String msg, String okText, String noText, final BaseActivity.OnDialogClickListener onDialogClickListener) {
        ((BaseActivity) getActivity()).confirmShowDialog(context, msg, okText, noText, onDialogClickListener);
    }

    public void confirmShowDialog(final Context context, String msg, BaseActivity.OnDialogClickListener onDialogClickListener) {
        confirmShowDialog(context, msg, context.getResources().getString(R.string.alert_yes), context.getResources().getString(R.string.alert_no), onDialogClickListener);
    }


    public void showListDialog(final Context context, final ArrayList<String> list, final boolean isSearch, final BaseActivity.OnSearchDialogClickListener onSearchDialogClickListener) {
        ((BaseActivity) getActivity()).showListDialog(context, list, isSearch, onSearchDialogClickListener);

    }

    public void openPlayStore() {
        ((BaseActivity) getActivity()).openPlayStore();
    }

    public void logPrint(String s) {
        ((BaseActivity) getActivity()).logPrint(s);
    }


}
