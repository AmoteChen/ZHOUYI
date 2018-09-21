package com.zhouyi.zhouyi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhouyi.zhouyi.R;

public class Main extends AppCompatActivity implements View.OnClickListener {

    private Button bt_home;
    private Button bt_yi;
    private Button bt_mine;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_bt_home:
                break;
            case R.id.main_bt_yi:
                break;
            case R.id.main_bt_mine:
                break;
            default:
                break;
        }
    }
}
