package com.example.bd_app_tfc_alberto.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.bd_app_tfc_alberto.R;
import com.example.bd_app_tfc_alberto.adapters.RvCalendarAdapter;
import com.example.bd_app_tfc_alberto.clases.Citas;
import com.example.bd_app_tfc_alberto.database.Citas_DB;

import java.util.ArrayList;


public class CalendarFragment extends Fragment {
    private String email;

    public CalendarFragment(String email) {
        this.email = email;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CalendarView calendar = view.findViewById(R.id.calendarC);
        Citas_DB citas_db = new Citas_DB(getContext());
        final ArrayList<Citas>[] listacitas = new ArrayList[]{new ArrayList<>()};
        calendar.setMinDate(calendar.getDate());
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                RecyclerView rvdates = view.findViewById(R.id.rvcalendar);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
                rvdates.setLayoutManager(linearLayoutManager);
                rvdates.setItemAnimator(new DefaultItemAnimator());
                String date = year+"/"+(month+1)+"/"+day;
                listacitas[0] = citas_db.getCitas(email,getContext(),date);
                RvCalendarAdapter rvCalendarAdapter = new RvCalendarAdapter(getContext(),listacitas[0],getActivity(),email);
                rvdates.setAdapter(rvCalendarAdapter);

            }
        });







    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }
}