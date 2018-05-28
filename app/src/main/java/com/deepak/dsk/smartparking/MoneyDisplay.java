package com.deepak.dsk.smartparking;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MoneyDisplay extends Activity {

    TextView textView;
    Button gotoDashboardBut;
    int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_display);
        textView=findViewById(R.id.money_display_id);
        gotoDashboardBut=findViewById(R.id.goto_dashboard_id);
        float money=getIntent().getExtras().getFloat(DbContract.Money);

        textView.setText(money+"");
        gotoDashboardBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoneyDisplay.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

}
