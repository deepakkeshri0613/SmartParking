package com.deepak.dsk.smartparking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DashboardFragment.ClickListener,SlotBookingFragment.ClickListerner{

    DashboardFragment dashboardFragment;
    SlotBookingFragment slotBookingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dashboardFragment=new DashboardFragment();
        dashboardFragment.setOnButtonClickListener(this);
        showFragment(dashboardFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        Drawable drawable=menu.getItem(0).getIcon();
        drawable.mutate();
        drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                System.exit(0);
                return true;
            case R.id.admin_login:
                Intent intent=new Intent(MainActivity.this,AdminActivity.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
        transaction.replace(R.id.fragment_container,fragment).commit();
    }


    @Override
    public void onButtonClick(View view, int bookingTime, int bookingDuration, String bookingDate, String bookingLocation,int carNum) {
        slotBookingFragment=new SlotBookingFragment();
        slotBookingFragment.setOnClickListener(this);
        Bundle bundle=new Bundle();
        bundle.putString(DbContract.USER_NAME,"Deepak");
        bundle.putString(DbContract.BOOKING_DATE,bookingDate);
        bundle.putInt(DbContract.BOOKING_TIME,bookingTime);
        bundle.putInt(DbContract.BOOKING_DURATION,bookingDuration);
        bundle.putString(DbContract.LOCATION,bookingLocation);
        bundle.putInt(DbContract.CAR_NUM,carNum);
        slotBookingFragment.setArguments(bundle);
        showFragment(slotBookingFragment);
    }

    @Override
    public void onConfirmButtonClicked(String otp) {

        Fragment fragment=new BookingMessageFragment();
        Bundle bundle=new Bundle();
        bundle.putString(DbContract.OTP,otp);
        fragment.setArguments(bundle);
        showFragment(fragment);



    }
}
