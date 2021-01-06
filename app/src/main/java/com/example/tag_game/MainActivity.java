package com.example.tag_game;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout linlay;
    TextView tv_title;
    int width,height;
    ImageView imageview;
    Bitmap bitmap;
    Canvas canvas;
    Bitmap bitmapbg;
    Paint paint;
    FrameLayout fralay;
    Button boto;

    public void draw(){
        canvas.drawBitmap(bitmapbg,
                new Rect(0,0,bitmapbg.getWidth(),bitmapbg.getHeight()),
                new Rect((int)(0),(int)(0),width,height),paint);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linlay = new LinearLayout(this);
        tv_title = new TextView(this);
        tv_title.setText("TAG GAME");
        tv_title.setTextSize(60);
        FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,Gravity.CENTER_HORIZONTAL);
        para.setMargins(0, height+450,0,0);

        tv_title.setLayoutParams(para);
        tv_title.setTextColor(getResources().getColor(R.color.purple));
        tv_title.setPadding(0,20,0,0);

        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        width=metrics.widthPixels;
        height=metrics.heightPixels;
        fralay=new FrameLayout(this);
        fralay.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        imageview=new ImageView(this);
        imageview.setLayoutParams(new FrameLayout.LayoutParams(
                width,height));
        bitmap= Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        imageview.setImageBitmap(bitmap);
        canvas=new Canvas(bitmap);
        paint=new Paint();
        bitmapbg= BitmapFactory.decodeResource(getResources(),R.drawable.downloadv2);
        draw();
        boto = new Button(this);
        boto.setText("JUGAR");
        boto.setTextAppearance(R.style.AppTheme);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,Gravity.CENTER_HORIZONTAL);
        boto.setPadding(70,25,70,25);
        params.setMargins(0, height-700,0,0);
        boto.setLayoutParams(params);
        //boto.setBackgroundColor(R.color.myyellow);
        boto.setBackgroundColor(getResources().getColor(R.color.purple));
        boto.setTextSize(27);
        boto.setTextColor(getResources().getColor(R.color.dark_purple));
        boto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseName.class);
                startActivity(intent);
            }
        });
        fralay.addView(imageview);
        fralay.addView(tv_title);
        fralay.addView(boto);
        setContentView(fralay);
    }
}