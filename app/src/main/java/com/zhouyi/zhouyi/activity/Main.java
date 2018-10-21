package com.zhouyi.zhouyi.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhouyi.zhouyi.R;
import com.zhouyi.zhouyi.activity.mine.mine_main;
import com.zhouyi.zhouyi.object.HttpsConnect;
import com.zhouyi.zhouyi.object.HttpsListener;
import com.zhouyi.zhouyi.object.User;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhouyi.zhouyi.activity.mine.item_view;

public class Main extends AppCompatActivity implements View.OnClickListener {

    private Button bt_home;
    private Button bt_yi;
    private Button bt_mine;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private String name;
    private String account;
    private String password;
    private String token;
    private String id;
    private boolean state;

    private String check_address = "http://120.76.128.110:12510/web/CheckLogin";
    private String address = "http://120.76.128.110:12510/web/UserLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bt_home = (Button)findViewById(R.id.main_bt_home);
        bt_home.setOnClickListener(this);
        bt_yi = (Button)findViewById(R.id.main_bt_yi);
        bt_yi.setOnClickListener(this);
        bt_mine = (Button)findViewById(R.id.main_bt_mine);
        bt_mine.setOnClickListener(this);

        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        name = sp.getString("name", "no name");
        account = sp.getString("account", "no acc");
        password = sp.getString("password", "no password");
        token = sp.getString("token", "no token");
        id = sp.getString("id", "-1");
        state = sp.getBoolean("state", false);

        User.setName(name);
        User.setAccount(account);
        User.setPassword(password);
        User.setToken(token);
        User.setId(id);
        User.setState(state);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        if (!token.equals("no token")) {
            Toast.makeText(Main.this, "checking", Toast.LENGTH_SHORT).show();
            HttpsConnect.sendRequest(check_address, "POST", getCheckJsonData(), new HttpsListener() {
                @Override
                public void success(String response) {
                    catchCheckResponse(response);
                }

                @Override
                public void failed(Exception exception) {
                    exception.printStackTrace();
                }
            });
        } else {
            Toast.makeText(Main.this, "no token", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_bt_home:
                break;
            case R.id.main_bt_yi:
                break;
            case R.id.main_bt_mine:
                Intent to_mine=new Intent(this,mine_main.class);
                startActivity(to_mine);
                break;
            default:
                break;
        }
    }

    JSONObject getCheckJsonData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", User.getToken());
            jsonObject.put("id", User.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private void catchCheckResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("result");
                    String reason = jsonObject.getString("reason");
                    if (result.equals("success")) {
                        if (state) {
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
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    JSONObject getJsonData() {
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
                    name = jsonObject.getString("name");
                    token = jsonObject.getString("token");
                    id = jsonObject.getString("userId");
                    if (result.compareTo("success") == 0) {
                        Toast.makeText(Main.this, name, Toast.LENGTH_SHORT).show();
                        item_view.setState(true);

//                        name = jsonObject.getString("name");
//                        account = jsonObject.getString("phone");
//                        password = jsonObject.getString("password");
//                        User.setName(name);
//                        User.setAccount(account);
//                        User.setPassword(password);
                        User.setName(User.getName());
                        User.setState(true);
                        User.setToken(token);
                        User.setId(id);

                        sp = getSharedPreferences(User.getAccount(), Context.MODE_PRIVATE);
                        editor = sp.edit();
                        editor.putString("name", User.getName());
//                        editor.putString("account", User.getAccount());
                        editor.putString("password", User.getPassword());
                        editor.putBoolean("state", User.getState());
                        editor.putString("token", User.getToken());
                        editor.putString("id", User.getId());
                        editor.commit();

                        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                        editor = sp.edit();
                        editor.putString("name", User.getName());
//                        editor.putString("account", User.getAccount());
                        editor.putString("password", User.getPassword());
                        editor.putBoolean("state", User.getState());
                        editor.putString("token", User.getToken());
                        editor.putString("id", User.getId());
                        editor.commit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
