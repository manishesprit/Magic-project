package com.app.magicproject;


import android.os.Bundle;
import android.view.View;

import com.app.magiclib.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnShowAlert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("TEST MESAGE", null);
            }
        });

        findViewById(R.id.btnShowAlertWithListener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("TEST MESAGE", new OnDialogClickListener() {
                    @Override
                    public void OnYesClick() {

                    }

                    @Override
                    public void OnNoClick() {

                    }
                });
            }
        });

        findViewById(R.id.btnShowAlert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("TEST MESAGE", null);
            }
        });

        findViewById(R.id.btnShowConfirmAlert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmShowDialog("TEST MESAGE", new OnDialogClickListener() {
                    @Override
                    public void OnYesClick() {

                    }

                    @Override
                    public void OnNoClick() {

                    }
                });
            }
        });

        findViewById(R.id.btnShowConfirmWithButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmShowDialog("TEST MESAGE", "logout", "no", new OnDialogClickListener() {
                    @Override
                    public void OnYesClick() {

                    }

                    @Override
                    public void OnNoClick() {

                    }
                });
            }
        });

        findViewById(R.id.btnShowProgress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
            }
        });
    }
}
