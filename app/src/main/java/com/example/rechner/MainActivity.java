package com.example.rechner;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button buttonMS;
    Button buttonMR;
    EditText input1;
    EditText input2;
    RadioGroup group;
    public static final String MY_PREFS = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        buttonMS = (Button) findViewById(R.id.buttonMS);
        buttonMR = findViewById(R.id.buttonMR);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        group = findViewById(R.id.radioGroup);

        buttonMS.setOnClickListener(view ->{
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS, MODE_PRIVATE).edit();
            editor.putString("val1", input1.getText().toString());
            editor.putString("val2", input2.getText().toString());
            editor.apply();
        });

        buttonMR.setOnClickListener(view ->{
            SharedPreferences prefs = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
            input1.setText(prefs.getString("val1", "-1"));
            input2.setText(prefs.getString("val2", "-1"));

        });

        button1.setOnClickListener(view -> {
            int val1 = Integer.parseInt(input1.getText().toString());
            int val2 = Integer.parseInt(input2.getText().toString());
            int output = 0;

            if (input1.getText().toString().equals("") || input2.getText().toString().equals("") || group.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getApplicationContext(), "An Error occurred.", Toast.LENGTH_SHORT).show();
            } else {
                switch (((RadioButton) findViewById(group.getCheckedRadioButtonId())).getText().toString()) {
                    case "+":
                        output = val1 + val2;
                        break;

                    case "-":
                        output = val1 - val2;
                        break;

                    case "*":
                        output = val1 * val2;
                        break;

                    case "/":
                        if (val2 != 0) output = val1 / val2;
                        break;
                }
                ((TextView) findViewById(R.id.output)).setText(String.valueOf(output));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int color = Color.rgb(10,200,10);
        button1.setBackgroundColor(color);
    }


}