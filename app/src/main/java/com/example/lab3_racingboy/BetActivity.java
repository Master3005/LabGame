package com.example.lab3_racingboy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class BetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnBet, btnAdd;
    CheckBox cbx1, cbx2, cbx3;
    TextView txtCoin;
    EditText edtBetCoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        Binding();
        btnBet.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

    }


    private int isChose() {
        if (cbx1.isChecked()) {
            return 1;
        }
        if (cbx2.isChecked()) {
            return 2;
        }
        if (cbx3.isChecked()) {
            return 3;
        }
        return 0;
    }

    private void Binding() {
        edtBetCoin = (EditText) findViewById(R.id.edtBetCoin);
        cbx1 = (CheckBox) findViewById(R.id.checkBox);
        cbx2 = (CheckBox) findViewById(R.id.checkBox2);
        cbx3 = (CheckBox) findViewById(R.id.checkBox3);
        btnBet = (Button) findViewById(R.id.btnBet);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        txtCoin = (TextView) findViewById(R.id.txtCoin);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String txtMainCoin = extras.getString("txtCoin");
            txtCoin.setText(txtMainCoin);
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        String betValue = edtBetCoin.getText().toString();
        String currentCoin = txtCoin.getText().toString();

        switch (view.getId()) {
            case R.id.btnBet:
                if (!validate(betValue, currentCoin)) {
                    return;
                }
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("cbx1", cbx1.isChecked());
                intent.putExtra("cbx2", cbx2.isChecked());
                intent.putExtra("cbx3", cbx3.isChecked());
                intent.putExtra("txtCoin", currentCoin);
                intent.putExtra("edtBetCoin", betValue);
                startActivity(intent);
                finish();
                break;
            case R.id.btnAdd:
                int newCoin = Integer.parseInt(currentCoin) + 100;
                txtCoin.setText(String.valueOf(newCoin));
                break;
        }
    }

    private boolean validate(String betValue, String currentCoin) {
        int chose = isChose();
        Log.d("chose", String.valueOf(chose));
        if (chose < 1) {
            Toast.makeText(BetActivity.this, "Please choose a pig", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(betValue)) {
            Toast.makeText(BetActivity.this, "Please input bet coin", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(currentCoin) < Integer.parseInt(betValue)) {
            Toast.makeText(BetActivity.this, "Don't enough bet coin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}