package com.example.tag_game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public class ChooseTags extends AppCompatActivity {

    final String TAG = "LIST ASSETS";
    LinearLayout linlay, linlay_bt, linlay_title1, linlay_title2, linlay_bottom1, linlay_bottom2;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    Button bt_confirm;
    String input, username;
    String[] list_hits;
    Integer limitselected, hits, width, height;
    final ButtonList[] all_buttons = new ArrayList<ButtonList>(9).toArray(new ButtonList[9]);

    private class ButtonList{
        Button bt;
        boolean pressed;
        @RequiresApi(api = Build.VERSION_CODES.M)
        public ButtonList(final String text, Context THIS){
            bt = new Button(THIS);
            bt.setTextAppearance(R.style.AppTheme);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT,Gravity.CENTER_HORIZONTAL);
            bt.setPadding(120,0,120,0);
            params.setMargins(0, 30,0,0);
            bt.setLayoutParams(params);
            bt.setBackgroundColor(getResources().getColor(R.color.purple));
            bt.setTextSize(15);
            bt.setTextColor(getResources().getColor(R.color.dark_purple));
            bt.setText(text);
            pressed = false;
            bt.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    if (linlay_title1.getVisibility() == View.VISIBLE) {
                        if (limitselected < 3) {
                            if (!pressed) {
                                limitselected += 1;
                                bt.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.selected)));
                            } else {
                                limitselected -= 1;
                                bt.setBackgroundTintList(ColorStateList.valueOf(
                                        getResources().getColor(R.color.purple)));
                            }
                            pressed = !pressed;
                        } else {
                            if (pressed) {
                                pressed = false;
                                limitselected -= 1;
                                bt.setBackgroundTintList(ColorStateList.valueOf(
                                        getResources().getColor(R.color.purple)));
                            }
                        }
                        if (limitselected == 3) linlay_bottom1.setVisibility(View.VISIBLE);
                        else linlay_bottom1.setVisibility(View.GONE);

                    }else{
                        if(!pressed && hits < 5) {
                            if(bt.getBackgroundTintList() != ColorStateList.valueOf(Color.DKGRAY)) {
                                bt.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));
                                hits++;
                            }
                        }else {
                            hits++;
                            for (ButtonList all_button : all_buttons) all_button.Showbt();
                            FinishedGame();
                        }
                        if(hits< list_hits.length) tv7.setText(list_hits[hits]);

                    }
                }
            });
        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void Hidebt(){
            bt.setBackgroundTintList(ColorStateList.valueOf(
                    getResources().getColor(R.color.purple)));
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void Showbt(){
            if(pressed) bt.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.selected)));
            else bt.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linlay = new LinearLayout(this);
        linlay_bt = new LinearLayout(this);
        linlay_title1 = new LinearLayout(this);
        linlay_title2 = new LinearLayout(this);
        linlay_bottom1 = new LinearLayout(this);
        linlay_bottom2 = new LinearLayout(this);

        input = "";

        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        width=metrics.widthPixels;
        height=metrics.heightPixels;

        list_hits = new String[]{"Te suena su cara", "Conocido", "Amigo en facebook", "Compañero de clase", "Amigo del grupo", "Casi de la familia", "Como a un hermano"};
        limitselected = 0;
        hits = 0;
        //Reading words file & Choosing random words
        readInput();
        String[] listWords = input.split(",");

        Set<Integer> chosenwords = new HashSet<>();
        Random random = new Random();
        while (chosenwords.size()<9) {
            System.out.println("inside...");
            int randomNumber = random.nextInt(listWords.length-1);
            chosenwords.add(randomNumber);
        }

        //linlay_title1
        tv1 = new TextView(this);
        tv2 = new TextView(this);
        username = getIntent().getStringExtra("username");
        tv1.setText("Etiquétate " + username);
        tv2.setText("Elige las 3 etiquetas que más te definen:");
        tv1.setTextSize(20);
        tv2.setTextSize(20);
        tv1.setPadding(0,30,0,10);
        tv2.setPadding(20,20,20,0);
        tv2.setGravity(Gravity.CENTER);
        tv1.setTextColor(getResources().getColor(R.color.white));
        tv2.setTextColor(getResources().getColor(R.color.white));
        linlay_title1.addView(tv1);
        linlay_title1.addView(tv2);
        linlay_title1.setOrientation(LinearLayout.VERTICAL);
        linlay_title1.setGravity(Gravity.CENTER_HORIZONTAL);

        //linlay_title2
        tv3 = new TextView(this);
        tv4 = new TextView(this);
        tv3.setText("Conoces a " + username +"?");
        tv4.setText("Tacha los que NO corresponden");
        tv3.setTextSize(20);
        tv4.setTextSize(20);
        tv3.setPadding(0,30,0,10);
        tv4.setPadding(20,10,20,0);
        tv4.setGravity(Gravity.CENTER);
        tv3.setTextColor(getResources().getColor(R.color.white));
        tv4.setTextColor(getResources().getColor(R.color.white));
        linlay_title2.addView(tv3);
        linlay_title2.addView(tv4);
        linlay_title2.setOrientation(LinearLayout.VERTICAL);
        linlay_title2.setGravity(Gravity.CENTER_HORIZONTAL);
        linlay_title2.setVisibility(View.GONE);

        //linlay_bt
        int j = 0;
        for(Integer i : chosenwords){
        //creating list Buttons
            ButtonList bt_insert = new ButtonList(listWords[i], this);
            all_buttons[j] = bt_insert;
            j++;
            linlay_bt.addView(bt_insert.bt);
        }
        linlay_bt.setOrientation(LinearLayout.VERTICAL);
        linlay_bt.setGravity(Gravity.CENTER_HORIZONTAL);

        //linaly_bottom1
        bt_confirm = new Button(this);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                for (ButtonList all_button : all_buttons) {
                    all_button.Hidebt();
                }
                linlay_title1.setVisibility(View.GONE);
                linlay_bottom1.setVisibility(View.GONE);
                linlay_title2.setVisibility(View.VISIBLE);
                linlay_bottom2.setVisibility(View.VISIBLE);
            }
        });
        tv5 = new TextView(this);
        tv5.setText("Estás seguro de tus elecciones?");
        tv5.setTextSize(20);
        tv5.setPadding(20,10,20,20);
        tv5.setGravity(Gravity.CENTER);
        tv5.setTextColor(getResources().getColor(R.color.white));
        bt_confirm.setText("CONFIRMAR");
        bt_confirm.setBackgroundColor(getResources().getColor(R.color.selected));
        bt_confirm.setTextSize(15);
        bt_confirm.setTextColor(getResources().getColor(R.color.dark_purple));
        bt_confirm.setPadding(20,0,20,0);
        linlay_bottom1.addView(tv5);
        linlay_bottom1.addView(bt_confirm);
        linlay_bottom1.setOrientation(LinearLayout.VERTICAL);
        linlay_bottom1.setGravity(Gravity.CENTER_HORIZONTAL);
        linlay_bottom1.setVisibility(View.GONE);

        //linlay_bottom2
        tv6 = new TextView(this);
        tv7 = new TextView(this);
        tv6.setText("Nivel de conocimiento:");
        tv7.setText("Nada");
        tv6.setTextSize(15);
        tv7.setTextSize(30);
        tv6.setPadding(20,10,20,20);
        tv6.setGravity(Gravity.CENTER);
        tv6.setTextColor(getResources().getColor(R.color.white));
        tv6.setPadding(20,40,20,20);
        tv7.setPadding(20,10,20,20);
        tv7.setGravity(Gravity.CENTER);
        tv7.setTextColor(getResources().getColor(R.color.white));
        tv7.setBackgroundColor(getResources().getColor(R.color.win));
        linlay_bottom2.addView(tv6);
        linlay_bottom2.addView(tv7);
        linlay_bottom2.setOrientation(LinearLayout.VERTICAL);
        linlay_bottom2.setGravity(Gravity.CENTER_HORIZONTAL);
        linlay_bottom2.setVisibility(View.GONE);

        //setting up layout
        linlay.addView(linlay_title1);
        linlay.addView(linlay_title2);
        linlay.addView(linlay_bt);
        linlay.addView(linlay_bottom1);
        linlay.addView(linlay_bottom2);

        linlay.setBackgroundColor(getResources().getColor(R.color.background));
        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay.setGravity(Gravity.CENTER_HORIZONTAL);
        setContentView(linlay);
    }

    //reads input from a file on assets folder
    private void readInput(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("listWords.txt")));
            String line;
            while ((line=reader.readLine()) != null) {
                input += line + " ";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void FinishedGame(){
        AlertDialog alertDialog = new AlertDialog.Builder(ChooseTags.this).create();
        if(hits == 6) alertDialog.setTitle("LO CONOCES");
        else alertDialog.setTitle("GAME OVER");

        alertDialog.setMessage(list_hits[hits]);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ChooseTags.this, MainActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "REINICIAR",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ChooseTags.this, ChooseName.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}

