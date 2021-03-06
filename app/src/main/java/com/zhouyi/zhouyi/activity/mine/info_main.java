package com.zhouyi.zhouyi.activity.mine;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouyi.zhouyi.R;
import com.zhouyi.zhouyi.object.HttpsConnect;
import com.zhouyi.zhouyi.object.HttpsListener;
import com.zhouyi.zhouyi.object.User;

import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by ChenSiyuan on 2018/10/11.
 */

public class info_main extends AppCompatActivity{
    private EditText name_edit;
    private EditText true_name_edit;
    private TextView sex_edit;
    private TextView birth_edit;
    private TextView account_edit;
    private TextView love_edit;



    Calendar nowdate = Calendar.getInstance();
    int mYear = nowdate.get(Calendar.YEAR);
    int mMonth = nowdate.get(Calendar.MONTH);
    int mDay = nowdate.get(Calendar.DAY_OF_MONTH);

    private Boolean name_change;
    private String name;
    private String realname;
    private String birthday;
    private String account;
    private String from="phone";

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private final String address ="http://120.76.128.110:12510/web/SetUserInfo";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_info);
        name_edit=(EditText)findViewById(R.id.person_name).findViewById(R.id.info_text);
        true_name_edit=(EditText)findViewById(R.id.true_name).findViewById(R.id.info_text);
        birth_edit=(TextView)findViewById(R.id.person_birth).findViewById(R.id.super_info_text);
        account_edit=(TextView)findViewById(R.id.person_tel).findViewById(R.id.super_info_text);

    }
    @Override
    public void onResume() {
        super.onResume();
        name_edit.setText(User.getName());
        true_name_edit.setText(User.getTrue_name());
        birth_edit.setText(User.getBirthday());
        account_edit.setText(User.getAccount());
    }

    public void onBirth(View view){

        new DatePickerDialog(info_main.this, onDateSetListener, mYear, mMonth, mDay).show();
    }
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear+1;
            mDay = dayOfMonth;
            String days;
            days = new StringBuffer().append(mYear).append("-").append(mMonth).toString();
            birth_edit.setText(days);
        }
    };
    public void onSave(View view){
        name_change=name_change();
        name=name_edit.getText().toString();
        realname=true_name_edit.getText().toString();
        birthday=birth_edit.getText().toString();
        account=account_edit.getText().toString();
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

    private JSONObject getJsonData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name_change",name_change);
            jsonObject.put("from",from);
            jsonObject.put("name", name);
            jsonObject.put("realname", realname);
            jsonObject.put("birthday", birthday);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private void catchResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String result = jsonObject.getString("result");
                    String reason = jsonObject.getString("reason");
                    Toast.makeText(info_main.this,result+ "\n" + reason, Toast.LENGTH_SHORT).show();
                    if(result.compareTo("success")==0){
                        User.setName(name);
                        User.setTrue_name(realname);
                        User.setBirthday(birthday);
                        User.setAccount(account);

                        sp = getSharedPreferences(User.getAccount(), Context.MODE_PRIVATE);
                        editor=sp.edit();
                        editor.putString("name",User.getName());
                        editor.putString("realname",User.getTrue_name());
                        editor.putString("birthday",User.getBirthday());
                        editor.putString("account",User.getAccount());
                        editor.commit();

                        sp = getSharedPreferences("user",Context.MODE_PRIVATE);
                        editor = sp.edit();
                        editor.putString("name",User.getName());
                        editor.putString("realname",User.getTrue_name());
                        editor.putString("birthday",User.getBirthday());
                        editor.putString("account",User.getAccount());
                        editor.commit();
                        finish();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private boolean name_change(){
        String pre_name;
        String name;
        pre_name=User.getName();
        name=name_edit.getText().toString();
        if(pre_name==name){
            return true;
        }
        else{
            return false;
        }
    }

}
