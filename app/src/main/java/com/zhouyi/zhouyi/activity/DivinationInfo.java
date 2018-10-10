package com.zhouyi.zhouyi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouyi.zhouyi.R;
import com.zhouyi.zhouyi.object.HttpsConnect;
import com.zhouyi.zhouyi.object.HttpsListener;

import org.json.JSONObject;

public class DivinationInfo extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_info;

    private final String address = "";
    private String divinationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.divination_info);

        tv_info = (TextView)findViewById(R.id.divination_info_tv_info);
        tv_info.setOnClickListener(this);

        Intent fromDivinationAdapter = getIntent();
        divinationID = fromDivinationAdapter.getStringExtra("id");
        tv_info.setText(divinationID);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
