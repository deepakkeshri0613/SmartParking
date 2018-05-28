package com.deepak.dsk.smartparking;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements AdapterView.OnItemSelectedListener{


    ClickListener clickListener;
    Button checkAvilBut;
    Spinner spinner;
    String bookingDate="2/5/2018";
    int bookingTime=0;
    int bookingDuration=0;
    String bookingLocation;
    int carNum;

    EditText editTextDuration;
    EditText editTextbookingTime,editTextCarNumber,editTextbookingDate;



    public DashboardFragment() {
        // Required empty public constructor
    }



    public void setOnButtonClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextDuration=view.findViewById(R.id.Duration);
        editTextbookingTime=view.findViewById(R.id.time);
        editTextCarNumber=view.findViewById(R.id.car_num);
        editTextbookingDate=view.findViewById(R.id.dashboard_date);
        spinner=view.findViewById(R.id.location);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(),R.array.location
        ,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        checkAvilBut=view.findViewById(R.id.dashboard_check_availability_but);
        checkAvilBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookingDuration=Integer.parseInt(editTextDuration.getText().toString());
                bookingTime=Integer.parseInt(editTextbookingTime.getText().toString());
                bookingDate=editTextbookingDate.getText().toString();
                if(bookingDate==null)
                    bookingDate="2/5/2018";
                carNum=Integer.parseInt(editTextCarNumber.getText().toString());
                if(clickListener!=null)
                {
                    clickListener.onButtonClick(view,bookingTime,bookingDuration,bookingDate,bookingLocation,carNum);
                }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        bookingLocation=adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface ClickListener{
        void onButtonClick(View view,int bookingTime,int bookingDuration,String bookingDate,String bookingLocation,int carNum);

    }
}
