package com.example.rechner;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button button1;
    Button buttonMS;
    Button buttonMR;
    EditText input1;
    EditText input2;
    String operator = "";
    String[] operations = {"+", "-", "*", "/"};
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

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operators, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_main);
        setSupportActionBar(toolbar);

        buttonMS.setOnClickListener(view -> {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS, MODE_PRIVATE).edit();
            editor.putString("val1", input1.getText().toString());
            editor.putString("val2", input2.getText().toString());
            editor.apply();
        });

        buttonMR.setOnClickListener(view -> {
            SharedPreferences prefs = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
            input1.setText(prefs.getString("val1", "-1"));
            input2.setText(prefs.getString("val2", "-1"));

        });

        button1.setOnClickListener(view -> {
            int val1 = Integer.parseInt(input1.getText().toString());
            int val2 = Integer.parseInt(input2.getText().toString());
            int output = 0;

            if (input1.getText().toString().equals("") || input2.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "An Error occurred.", Toast.LENGTH_SHORT).show();
            } else {
                switch (operator) {
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
        int color = Color.rgb(10, 200, 10);
        button1.setBackgroundColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.reset) {
            input1.setText("");
            input2.setText("");
            ((TextView) findViewById(R.id.output)).setText("0");
            return true;
        } else if (id == R.id.info) {
            Toast.makeText(getApplicationContext(), "Matthias Kurz; V1.0", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        operator = operations[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}