package com.zhouyi.zhouyi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhouyi.zhouyi.R;
import com.zhouyi.zhouyi.activity.mine.mine_main;
import com.zhouyi.zhouyi.adapter.DivinationAdapter;
import com.zhouyi.zhouyi.object.Divination;
import com.zhouyi.zhouyi.object.HttpsConnect;
import com.zhouyi.zhouyi.object.HttpsListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText et_account;
    private EditText et_code;
    private Button bt_login;
    private Button bt_register;

    private String account;
    private String code;

    private final String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        et_account = (EditText)findViewById(R.id.login_et_account);
        et_code = (EditText)findViewById(R.id.login_et_code);
        bt_login = (Button)findViewById(R.id.login_bt_login);
        bt_login.setOnClickListener(this);
        bt_register = (Button)findViewById(R.id.login_bt_register);
        bt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_bt_login:
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
            case R.id.login_bt_register:
                Intent toHistory = new Intent(this, History.class);
                startActivity(toHistory);
                break;
            default:
                break;
        }
    }
//    public void onLogin(View view){
//        Intent backMine = new Intent(this,mine_main.class);
//        startActivity(backMine);
//    }
    private JSONObject getJsonData() {
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

    private void catchResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("result");
                    String reason = jsonObject.getString("reason");
                    if (result.compareTo("true") == 0) {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
