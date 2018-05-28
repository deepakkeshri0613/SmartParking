package com.deepak.dsk.smartparking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends Activity {

    Button payButton;

    EditText carEditText,otpEditText;
    String carNum;
    String checkotp;
    int bookingDuration;
    float money=10;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        payButton=findViewById(R.id.pay_but);


        carEditText=findViewById(R.id.admin_car_num);
        otpEditText=findViewById(R.id.admin_otp);





        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                carNum=carEditText.getText().toString();
                checkotp=otpEditText.getText().toString();
                readFromDatabase();
                if(flag==1) {


                    Intent intent = new Intent(AdminActivity.this, MoneyDisplay.class);
                    intent.putExtra(DbContract.Money, money);
                    intent.putExtra(DbContract.CAR_NUM, carNum);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(AdminActivity.this,"Please Enter Correct Detail",Toast.LENGTH_LONG).show();
                }
            }
        });




    }


    void readFromDatabase()
    {
        DbHelper dbHelper=new DbHelper(this);
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        String query="SELECT *"+" from "+DbContract.TABLE_NAME+" where "+DbContract.CAR_NUM+"=? "
                +"and "+DbContract.OTP+"=?";
        Cursor cursor=database.rawQuery(query,new String[]{carNum,checkotp});
        String total="";
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                bookingDuration=cursor.getInt(cursor.getColumnIndex(DbContract.BOOKING_DURATION));

                float h,r;
                h=(bookingDuration/60)*60;
                r=(bookingDuration%60);

                flag=1;
                money=h*1+r*1;
                break;

            }

            cursor.close();

        }


        dbHelper.close();
    }


}
