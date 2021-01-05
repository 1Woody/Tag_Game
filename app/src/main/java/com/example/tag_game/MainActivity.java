package com.example.tag_game;

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
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout linlay;
    TextView tv;
    int width,height;
    ImageView imageview;
    Bitmap bitmap;
    Canvas canvas;
    Bitmap bitmapstar;
    Paint paint;
    FrameLayout fralay;
    Button boto;

    public void draw(){
        canvas.drawBitmap(bitmapstar,
                new Rect(0,0,bitmapstar.getWidth(),bitmapstar.getHeight()),
                new Rect((int)(0),(int)(0),width,height),paint);

    }

    /*private class ButtonMenu{
        Button bt;
        public ButtonMenu(final String text, Context THIS){
            bt = new Button(THIS);
            bt.setText(text);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    if(text.equals("JUGAR")) intent = new Intent(MainActivity.this, ChooseName.class);
                    else if (text.equals("OPCIONES")) intent = new Intent(MainActivity.this, ChooseName.class);
                    else intent = new Intent(MainActivity.this, ChooseName.class);
                    startActivity(intent);
                }
            });
        }
    }*/

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linlay = new LinearLayout(this);
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
        bitmapstar= BitmapFactory.decodeResource(getResources(),R.drawable.tag);
        draw();
        boto = new Button(this);
        boto.setText("JUGAR");
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,Gravity.CENTER_HORIZONTAL);
        params.setMargins(0,height/3,0,0);
        boto.setLayoutParams(params);
        //boto.setBackgroundColor(R.color.myyellow);
        boto.setBackgroundColor(Color.YELLOW);
        boto.setTextColor(Color.BLACK);
        boto.setTextSize(60);
        boto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseName.class);
                startActivity(intent);
            }
        });
        fralay.addView(imageview);
        fralay.addView(boto);
        setContentView(fralay);

        /*
        String[] gameoptions = {"JUGAR","OPCIONES","SALIR",};
        linlay = new LinearLayout(this);
        tv = new TextView(this);
        tv.setText("ETIQUETAS");
        tv.setTextSize(40);
        linlay.addView(tv);
        for(int i=0; i<3; i++){
            linlay.addView((new ButtonMenu(gameoptions[i],this)).bt);
        }
        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay.setGravity(Gravity.CENTER_HORIZONTAL);
        //setContentView(linlay);
        setContentView(fralay);*/
    }
}