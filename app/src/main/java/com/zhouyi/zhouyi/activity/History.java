package com.zhouyi.zhouyi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhouyi.zhouyi.R;
import com.zhouyi.zhouyi.adapter.DivinationAdapter;
import com.zhouyi.zhouyi.object.Divination;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private List<Divination> divinations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_list);


        recyclerView = (RecyclerView)findViewById(R.id.history_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        divinations.add(new Divination("2018.1.1", "陈思源是猪"));
        divinations.add(new Divination("2018.1.2", "陈思源就是猪"));
        divinations.add(new Divination("2018.1.3", "陈思源的确是猪"));
        divinations.add(new Divination("2018.1.4", "陈思源真的就是猪"));
        divinations.add(new Divination("2018.1.5", "陈思源是猪"));
        divinations.add(new Divination("2018.1.6", "陈思源是猪"));
        divinations.add(new Divination("2018.1.7", "陈思源是猪"));
        divinations.add(new Divination("2018.1.8", "陈思源是猪"));
        divinations.add(new Divination("2018.1.9", "陈思源是猪"));
        divinations.add(new Divination("2018.1.10", "陈思源是猪"));
        divinations.add(new Divination("2018.1.11", "陈思源是猪"));
        divinations.add(new Divination("2018.1.12", "陈思源是猪"));
        divinations.add(new Divination("2018.1.13", "陈思源是猪"));
        divinations.add(new Divination("2018.1.14", "陈思源是猪"));
        divinations.add(new Divination("2018.1.15", "陈思源是猪"));
        divinations.add(new Divination("2018.1.16", "陈思源是猪"));
        divinations.add(new Divination("2018.1.17", "陈思源是猪"));
        divinations.add(new Divination("2018.1.18", "陈思源是猪"));
        divinations.add(new Divination("2018.1.19", "陈思源是猪"));
        divinations.add(new Divination("2018.1.20", "陈思源是猪"));
        divinations.add(new Divination("2018.1.21", "陈思源是猪"));
        divinations.add(new Divination("2018.1,22", "陈思源是猪"));
        divinations.add(new Divination("2018.1.23", "陈思源是猪"));
        divinations.add(new Divination("2018.1.24", "陈思源是猪"));
        divinations.add(new Divination("2018.1.25", "陈思源是猪"));
        divinations.add(new Divination("2018.1.26", "陈思源是猪"));
        divinations.add(new Divination("2018.1.27", "陈思源是猪"));
        divinations.add(new Divination("2018.1.28", "陈思源是猪"));
        divinations.add(new Divination("2018.1.29", "陈思源是猪"));
        divinations.add(new Divination("2018.1.30", "陈思源是猪"));
        divinations.add(new Divination("2018.1.31", "陈思源是猪"));
        DivinationAdapter adapter = new DivinationAdapter(divinations);
        recyclerView.setAdapter(adapter);
    }
}
