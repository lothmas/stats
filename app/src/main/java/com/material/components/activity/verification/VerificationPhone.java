package com.material.components.activity.verification;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

import org.w3c.dom.Text;

public class VerificationPhone extends AppCompatActivity {


    private Button btnContinue;
    private Button btnExit;
    private EditText txtPhoneNumber;
    private EditText txtPhoneInternationalCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_phone);
        Tools.setSystemBarColor(this, R.color.grey_20);
        initComponent();

    }


    private void initComponent() {
        btnContinue = (Button) findViewById(R.id.btn_continue);
        btnExit = (Button) findViewById(R.id.btn_exit);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                txtPhoneNumber = (EditText) findViewById(R.id.txt_phone_number);
                txtPhoneInternationalCode = (EditText) findViewById(R.id.txt_phone_code);
                if (!txtPhoneNumber.getText().toString().equals("") && !txtPhoneInternationalCode.getText().toString().equals("")) {
                    startActivity(new Intent(getApplicationContext(), VerificationHeader.class));

                } else if (txtPhoneNumber.getText().toString().equals("")) {
                    txtPhoneNumber.setError("Please Provide Mobile Number To Proceed");
                } else if (txtPhoneInternationalCode.getText().toString().equals("")) {
                    txtPhoneInternationalCode.setError("Please Provide International Country PhoneCode");
                }


            }

        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        txtPhoneNumber = (EditText) findViewById(R.id.txt_phone_number);

        txtPhoneNumber.addTextChangedListener(new TextWatcher() {


            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String enteredString = s.toString();
                if (enteredString.startsWith("0")) {
                    txtPhoneNumber.setError("Leading Zero's Not Allowed");
                    txtPhoneNumber.setText("");
                }
            }


        });


    }
}
