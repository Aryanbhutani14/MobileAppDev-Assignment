package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    Spinner fromCurrency, toCurrency;
    Button convertBtn;
    TextView result;
    Button settingsBtn;

    String[] currencies = {"INR","USD","EUR","JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        fromCurrency = findViewById(R.id.fromCurrency);
        toCurrency = findViewById(R.id.toCurrency);
        convertBtn = findViewById(R.id.convertBtn);
        result = findViewById(R.id.result);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        currencies);

        fromCurrency.setAdapter(adapter);
        toCurrency.setAdapter(adapter);

        settingsBtn = findViewById(R.id.settingsBtn);

        settingsBtn.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        SettingsActivity.class)));


        convertBtn.setOnClickListener(v -> convert());
    }

    private void convert(){

        if(amount.getText().toString().isEmpty()){
            result.setText("Enter amount");
            return;
        }

        double amt = Double.parseDouble(amount.getText().toString());

        String from = fromCurrency.getSelectedItem().toString();
        String to = toCurrency.getSelectedItem().toString();

        double inr = 0;

        switch (from){
            case "USD": inr = amt * 93; break;
            case "EUR": inr = amt * 108; break;
            case "JPY": inr = amt * 0.59; break;
            default: inr = amt;
        }

        double output = 0;

        switch (to){
            case "USD": output = inr / 93; break;
            case "EUR": output = inr / 108; break;
            case "JPY": output = inr / 0.59; break;
            default: output = inr;
        }

        result.setText("Result: " + String.format("%.2f", output) + " " + to);
    }
}