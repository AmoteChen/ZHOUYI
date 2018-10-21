package com.zhouyi.zhouyi.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhouyi.zhouyi.R;
import com.zhouyi.zhouyi.activity.mine.mine_main;
import com.zhouyi.zhouyi.adapter.DivinationAdapter;
import com.zhouyi.zhouyi.object.Divination;
import com.zhouyi.zhouyi.object.HttpsConnect;
import com.zhouyi.zhouyi.object.HttpsListener;
import com.zhouyi.zhouyi.object.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_password;
    private EditText et_account;
    private EditText et_code;
    private Button bt_login;
    private Button bt_register;

    private String name;
    private String password;
    private String account;
    private String code;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private final String address = "http://120.76.128.110:12510/web/UserLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        et_name = (EditText)findViewById(R.id.login_et_name);
        et_password = (EditText)findViewById(R.id.login_et_password);
        et_account = (EditText)findViewById(R.id.login_et_account);
        et_code = (EditText)findViewById(R.id.login_et_code);
        bt_login = (Button)findViewById(R.id.login_bt_login);
        bt_login.setOnClickListener(this);
        bt_register = (Button)findViewById(R.id.login_bt_register);
        bt_register.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        et_name.setText(User.getName());
        et_password.setText(User.getPassword());
        et_account.setText(User.getAccount());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_bt_login:
                name = et_name.getText().toString();
                password = et_password.getText().toString();
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
                Intent toRegister = new Intent(this, Register.class);
                startActivity(toRegister);
                break;
            default:
                break;
        }
    }

    private JSONObject getJsonData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("password", password);
            jsonObject.put("phone", account);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    String token = jsonObject.getString("token");
                    String id = jsonObject.getString("userId");
                    Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                    if (result.compareTo("success") == 0) {
//                        name = jsonObject.getString("name");
//                        account = jsonObject.getString("phone");
//                        password = jsonObject.getString("password");
//                        User.setName(name);
//                        User.setAccount(account);
//                        User.setPassword(password);
                        User.setName(name);
                        User.setPassword(password);
                        User.setState(true);
                        User.setToken(token);
                        User.setId(id);

                        sp = getSharedPreferences(User.getAccount(), Context.MODE_PRIVATE);
                        editor = sp.edit();
                        editor.putString("name", User.getName());
                        editor.putString("account", User.getAccount());
                        editor.putString("password", User.getPassword());
                        editor.putBoolean("state", User.getState());
                        editor.putString("token", User.getToken());
                        editor.putString("id", User.getId());
                        editor.commit();

                        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                        editor = sp.edit();
                        editor.putString("name", User.getName());
                        editor.putString("account", User.getAccount());
                        editor.putString("password", User.getPassword());
                        editor.putBoolean("state", User.getState());
                        editor.putString("token", User.getToken());
                        editor.putString("id", User.getId());
                        editor.commit();
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
