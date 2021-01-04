package com.example.tag_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout linlay;
    TextView tv;

    private class ButtonMenu{
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setContentView(linlay);
    }
}