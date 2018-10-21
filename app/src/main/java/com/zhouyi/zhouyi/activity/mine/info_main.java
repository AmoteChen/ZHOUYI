package com.zhouyi.zhouyi.activity.mine;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.zhouyi.zhouyi.R;

import java.util.Calendar;

/**
 * Created by ChenSiyuan on 2018/10/11.
 */

public class info_main extends AppCompatActivity{
    private EditText name_edit;
    private EditText intro_edit;
    private TextView sex_edit;
    private TextView birth_edit;
    private TextView tel_edit;
    private TextView love_edit;


    private String[] sexArry = new String[]{"保密", "女", "男"};
    private String[] loveArry = new String[]{"单身","热恋中","已婚"};

    Calendar nowdate = Calendar.getInstance();
    int mYear = nowdate.get(Calendar.YEAR);
    int mMonth = nowdate.get(Calendar.MONTH);
    int mDay = nowdate.get(Calendar.DAY_OF_MONTH);

    private String birthday;
    private String sex;
    private String love;
    private String info_tel;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_info);
        name_edit=(EditText)findViewById(R.id.person_name).findViewById(R.id.info_text);
        intro_edit=(EditText)findViewById(R.id.person_intro).findViewById(R.id.info_text);
        sex_edit=(TextView)findViewById(R.id.person_sex).findViewById(R.id.super_info_text);
        birth_edit=(TextView)findViewById(R.id.person_birth).findViewById(R.id.super_info_text);
        tel_edit=(TextView)findViewById(R.id.person_tel).findViewById(R.id.super_info_text);
        love_edit=(TextView)findViewById(R.id.person_love).findViewById(R.id.super_info_text);

    }
    public void onSex(View view){
        showSexChooseDialog();
    }
    public void onBirth(View view){

        new DatePickerDialog(info_main.this, onDateSetListener, mYear, mMonth, mDay).show();
    }
    public void onLove(View view){
        showLoveChooseDialog();
    }
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear+1;
            mDay = dayOfMonth;
            String days;
            days = new StringBuffer().append(mYear).append("年").append(mMonth).append("月").append(mDay).append("日").toString();
            birth_edit.setText(days);
        }
    };
    private void showSexChooseDialog() {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                sex_edit.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder3.show();// 让弹出框显示
    }
    private void showLoveChooseDialog() {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setSingleChoiceItems(loveArry, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                love_edit.setText(loveArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder3.show();// 让弹出框显示
    }



}
