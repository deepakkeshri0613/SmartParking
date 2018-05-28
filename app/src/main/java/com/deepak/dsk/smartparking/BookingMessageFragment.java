package com.deepak.dsk.smartparking;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookingMessageFragment extends Fragment {


    TextView textViewOtp;
    Bundle bundle;
    String otp;
    Button button;
    public BookingMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bundle=getArguments();
        otp=bundle.getString(DbContract.OTP);


        return inflater.inflate(R.layout.fragment_booking_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewOtp=view.findViewById(R.id.otp);
        textViewOtp.setText(otp);
        button=view.findViewById(R.id.goto_dashboard_id_booking);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
}
