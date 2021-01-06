package com.example.tag_game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseName extends AppCompatActivity {

    LinearLayout linlay;
    EditText textInput;

    @RequiresApi(api = Build.VERSION_CODES.M)
    Button createButton(String text, final Intent intent){
        Button bt;
        bt=new Button(this);
        bt.setTextAppearance(R.style.AppTheme);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,Gravity.CENTER_HORIZONTAL);
        bt.setPadding(70,25,70,25);
        params.setMargins(0, 100,0,0);
        bt.setLayoutParams(params);
        bt.setBackgroundColor(getResources().getColor(R.color.purple));
        bt.setTextSize(20);
        bt.setTextColor(getResources().getColor(R.color.dark_purple));
        bt.setText(text);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=textInput.getText().toString();
                if (!text.equals("")){
                    intent.putExtra("username",text);
                    startActivity(intent);
                }else{
                    MessageError();
                }
            }
        });
        return bt;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linlay=new LinearLayout(this);
        textInput = new EditText(this);
        textInput.setTextColor(getResources().getColor(R.color.white));
        textInput.setTextSize(30);
        textInput.setPadding(20, 20, 20, 20);
        textInput.setHint("NOMBRE");
        textInput.setHintTextColor(getResources().getColor(R.color.white));
        linlay.setBackgroundColor(getResources().getColor(R.color.background));
        linlay.addView(textInput);
        linlay.addView(createButton("Start",new Intent(this,ChooseTags.class)));
        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay.setGravity(Gravity.CENTER);
        setContentView(linlay);
    }

    private void MessageError(){
        AlertDialog alertDialog = new AlertDialog.Builder(ChooseName.this).create();
        alertDialog.setTitle("Caution");
        alertDialog.setMessage("No Name Selected");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}

