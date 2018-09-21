package com.zhouyi.zhouyi.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.zhouyi.zhouyi.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by ChenSiyuan on 2018/9/20.
 */

public class mine_main extends AppCompatActivity {
    private ImageView blurImageView;
    private ImageView avatarImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.main_layout_bef);
        blurImageView = (ImageView) findViewById(R.id.iv_blur);
        avatarImageView = (ImageView) findViewById(R.id.iv_avatar);

        Glide.with(this).load(R.drawable.defult_head)
                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(blurImageView);

        Glide.with(this).load(R.drawable.defult_head)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(avatarImageView);
    }
    public void onLike(View view){
        Toast.makeText(mine_main.this,"LIKE!!!",Toast.LENGTH_LONG).show();

    }

    public void onOffline(View view){
        Toast.makeText(mine_main.this,"OFFLINE!!!",Toast.LENGTH_LONG).show();

    }

    public void onHistory(View view){
        Toast.makeText(mine_main.this,"HISTORY!!!",Toast.LENGTH_LONG).show();

    }
    public void onRefresh(View view){
        Toast.makeText(mine_main.this,"REFRESH!!!",Toast.LENGTH_LONG).show();
    }

    public void onAbout(View view){
        Toast.makeText(mine_main.this,"ABOUT!!!",Toast.LENGTH_LONG).show();
    }

    public void onAvatar (View view){
        item_view.changeState();
        reStartActivity();
    }
    private void reStartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
