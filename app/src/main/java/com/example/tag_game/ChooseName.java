package com.example.tag_game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseName extends AppCompatActivity {

    LinearLayout linlay;
    EditText textInput;

    Button createButton(String text, final Intent intent){
        Button bt;
        bt=new Button(this);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        linlay=new LinearLayout(this);
        textInput = new EditText(this);
        linlay.addView(textInput);
        linlay.addView(createButton("JUGAR!",new Intent(this,ChooseTags.class)));
        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay.setGravity(Gravity.CENTER_HORIZONTAL);
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

