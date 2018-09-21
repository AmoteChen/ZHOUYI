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
    private EditText et_password;
    private EditText et_password_confirm;
    private Button bt_register;

    private String name;
    private String account;
    private String password;
    private String password_confirm;

    private final String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        et_name = (EditText)findViewById(R.id.register_et_name);
        et_account = (EditText)findViewById(R.id.register_et_account);
        et_password = (EditText)findViewById(R.id.register_et_password);
        et_password_confirm = (EditText)findViewById(R.id.register_et_password_confirm);
        bt_register = (Button)findViewById(R.id.register_bt_register);
        bt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_bt_register:
                name = et_name.getText().toString();
                account = et_account.getText().toString();
                password = et_password.getText().toString();
                password_confirm = et_password_confirm.getText().toString();
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
