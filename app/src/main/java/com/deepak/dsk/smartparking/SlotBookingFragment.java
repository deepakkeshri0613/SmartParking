package com.deepak.dsk.smartparking;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.deepak.dsk.smartparking.adapters.SlotNumAdapter;
import com.libRG.CustomTextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlotBookingFragment extends Fragment implements SlotNumAdapter.ClickListener {


    RecyclerView recyclerView;

    Button bookingBut;
    String userName,bookingDate,bookingLocation;
    int bookingDuration,slotNumber=0,bookingTime;
    Bundle bundle;
    SlotNumAdapter adapter;
    ClickListerner clickListerner;
    int carNum;
    int slot[]=new int[16];
    String otp;
    public SlotBookingFragment() {
        // Required empty public constructor

    }


public  void setOnClickListener(ClickListerner clickListener)
{
    this.clickListerner=clickListener;

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        bundle=getArguments();
        bookingDuration=bundle.getInt(DbContract.BOOKING_DURATION);
        bookingTime=bundle.getInt(DbContract.BOOKING_TIME);
        bookingDate=bundle.getString(DbContract.BOOKING_DATE);
        userName=bundle.getString(DbContract.USER_NAME);
        carNum=bundle.getInt(DbContract.CAR_NUM);
        bookingLocation=bundle.getString(DbContract.LOCATION);

       // Toast.makeText(getContext(),(bookingDuration+bookingTime+bookingDate+bookingLocation+userName),Toast.LENGTH_LONG).show();
        readFromDatabase();
        return inflater.inflate(R.layout.fragment_slot_booking, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new SlotNumAdapter(getActivity(),16,slot);
        adapter.setOnClickListener(this);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        bookingBut=view.findViewById(R.id.conf_booking);
        bookingBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random r=new Random();
                 otp=String.format("%04d",Integer.valueOf(r.nextInt(1001)));
                 DbHelper dbHelper=new DbHelper(getContext());
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        dbHelper.saveDetail("Deepak",bookingTime,bookingDuration,bookingDate,slotNumber
                ,bookingLocation,otp,carNum,database);
        dbHelper.close();

        if(clickListerner!=null)
        {
            clickListerner.onConfirmButtonClicked(otp);
        }


            }

        });


    }


    void readFromDatabase()
    {

        int slotIndex=0;
        DbHelper dbHelper=new DbHelper(getContext());
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        String query="SELECT *"+" from "+DbContract.TABLE_NAME+" where "+DbContract.BOOKING_DATE+"=? "
                +"and "+DbContract.BOOKING_TIME+" BETWEEN "+bookingTime+" AND "+(bookingTime+bookingDuration)+"";
        Cursor cursor=database.rawQuery(query,new String[]{bookingDate});
        String total="";
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                slot[slotIndex]=cursor.getInt(cursor.getColumnIndex(DbContract.SLOT_NUMBER));
                slotIndex++;
                //total=total+userName+id+" "+bookingDate+" "+bookingTime+" "+duration+" "+slot+location+" "+otp+" "+"\n";
                total=total+slot[slotIndex]+"\n";
            }
            cursor.close();
            Toast.makeText(getActivity(),total,Toast.LENGTH_LONG).show();

        }

        dbHelper.close();
    }

    @Override
    public void ItemClick(View view, int position) {

       CustomTextView customTextView= (CustomTextView) view;
       customTextView.setTextColor(getContext().getResources().getColor(R.color.white));
       customTextView.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
        slotNumber=position+1;

    }




    public interface ClickListerner{
        void onConfirmButtonClicked(String otp);
    }



}
