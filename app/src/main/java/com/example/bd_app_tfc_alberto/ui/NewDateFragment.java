package com.example.bd_app_tfc_alberto.ui;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.bd_app_tfc_alberto.R;
import com.example.bd_app_tfc_alberto.adapters.RvEmpAdapter;
import com.example.bd_app_tfc_alberto.adapters.RvServAdapter;
import com.example.bd_app_tfc_alberto.clases.Empleados;
import com.example.bd_app_tfc_alberto.clases.Servicios;
import com.example.bd_app_tfc_alberto.database.Empleados_DB;
import com.example.bd_app_tfc_alberto.database.Servicios_DB;

import java.util.ArrayList;


public class NewDateFragment extends Fragment {
    private String email;
    public NewDateFragment(String email) {
        this.email = email;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Servicios_DB servicios_db = new Servicios_DB(view.getContext());
        servicios_db.setData();
        RecyclerView rvemp = view.findViewById(R.id.rvemp);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvemp.setLayoutManager(linearLayoutManager);
        rvemp.setItemAnimator(new DefaultItemAnimator());

        ArrayList<Empleados> listaemp= new ArrayList<>();
       Empleados_DB empleados_db = new Empleados_DB(getContext());
       empleados_db.setEmpleados();
        listaemp = empleados_db.getEmpleados();
        RvEmpAdapter rvEmpAdapter = new RvEmpAdapter(view.getContext(),listaemp);
        rvemp.setAdapter(rvEmpAdapter);

        RecyclerView rvserv = view.findViewById(R.id.rvServicios);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
        rvserv.setLayoutManager(linearLayoutManager1);
        rvserv.setItemAnimator(new DefaultItemAnimator());
        ArrayList<Servicios> listaserv = new ArrayList<>();
        listaserv = servicios_db.getData();
        RvServAdapter rvServAdapter = new RvServAdapter(view.getContext(),listaserv,getActivity(),email);
        rvserv.setAdapter(rvServAdapter);



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_date, container, false);
    }
}