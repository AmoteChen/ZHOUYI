package com.zhouyi.zhouyi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhouyi.zhouyi.R;
import com.zhouyi.zhouyi.object.HttpsConnect;
import com.zhouyi.zhouyi.object.HttpsListener;

import org.json.JSONObject;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_account;
    private EditText et_code;
    private Button bt_getcode;
    private Button bt_register;

    private String name;
    private String account;
    private String code;

    private final String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        et_name = (EditText)findViewById(R.id.register_et_name);
        et_account = (EditText)findViewById(R.id.register_et_account);
        et_code = (EditText)findViewById(R.id.register_et_code);
        bt_getcode = (Button)findViewById(R.id.register_bt_getcode);
        bt_getcode.setOnClickListener(this);
        bt_register = (Button)findViewById(R.id.register_bt_register);
        bt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_bt_getcode:
                break;
            case R.id.register_bt_register:
                name = et_name.getText().toString();
                account = et_account.getText().toString();
                code = et_code.getText().toString();
                HttpsConnect.sendRequest(address, "POST", getJsonData(), new HttpsListener() {
                    @Override
                    public void success(String response) {
                        catchResponse(response);
                    }

                    @Override
                    public void failed(Exception exception) {
                        exception.printStackTrace();
                    }
                });
                break;
            default:
                break;
        }
    }

    private JSONObject getJsonData() {
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

    private void catchResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ;
            }
        });
    }
}
