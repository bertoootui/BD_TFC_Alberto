package com.example.bd_app_tfc_alberto.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bd_app_tfc_alberto.R;
import com.example.bd_app_tfc_alberto.adapters.RvHorasAdapter;
import com.example.bd_app_tfc_alberto.clases.ConfigPreferences;
import com.example.bd_app_tfc_alberto.clases.Horas;
import com.example.bd_app_tfc_alberto.database.Citas_DB;
import com.example.bd_app_tfc_alberto.database.Empleados_DB;
import com.example.bd_app_tfc_alberto.database.Servicios_DB;
import com.example.bd_app_tfc_alberto.database.Time_DB;
import com.example.bd_app_tfc_alberto.database.Usuarios_DB;

import java.util.ArrayList;
import java.util.Date;


public class NewDate2Fragment extends Fragment {
    private String email;
    private int id_serv;

    public NewDate2Fragment(String email, int id_serv) {
        this.email = email; this.id_serv = id_serv;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void onViewCreated(View view, Bundle savedInstaceState)
    {
        super.onViewCreated(view,savedInstaceState);
        TextView txtServ = view.findViewById(R.id.txtservND);
        TextView txtemp = view.findViewById(R.id.txtuserND);
        TextView txthoras = view.findViewById(R.id.txthoraFC);
        TextView txtdias = view.findViewById(R.id.txtdiaFC);
        TextView txtprecio = view.findViewById(R.id.txtprecioFC);
        Button butback = view.findViewById(R.id.butback_fnd_2);
        CalendarView calendarView = view.findViewById(R.id.calendarnewdate);
        Button butconfirm = view.findViewById(R.id.butdeleteFC);
        ConfigPreferences config = new ConfigPreferences();

        int id_emp = config.getEmpSel(getContext());
        int id_serv = config.getServSel(getContext());

        Empleados_DB empleados_db = new Empleados_DB(getContext());
        String nombreemp = empleados_db.getNameEmp(id_emp);
        txtemp.setText(nombreemp);
        Servicios_DB servicios_db = new Servicios_DB(getContext());
        String servicio = servicios_db.getServName(id_serv);
        Citas_DB citas_db = new Citas_DB(getContext());
        Time_DB time_db = new Time_DB(getContext());
        time_db.setData();
        RecyclerView rvhoras = view.findViewById(R.id.rvtimes);
        Usuarios_DB usuarios_db = new Usuarios_DB(getContext());


        txtServ.setText(servicio);
        calendarView.setMinDate(calendarView.getDate());
        final Date[] date = {new Date(calendarView.getDate())};
        Log.i("DATE", date[0].getMonth()+"");


        butconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dia = txtdias.getText().toString();
                String hora = txthoras.getText().toString();
                if(dia == null || dia.equals("")) Toast.makeText(getContext(), "Es necesario completar los datos", Toast.LENGTH_SHORT).show();
                else{
                    int id_user = usuarios_db.getIdUser(email);
                    citas_db.addDate(dia,hora,id_emp,config.getServSel(getContext()),id_user);
                    Fragment fragment = new NewDateFragment(email);
                    ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                }
            }
        });



        final String[] date1 = {""};
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                month++;
                ArrayList<Horas>listatime = time_db.getData();
                Toast.makeText(getContext(), year+"/"+month+"/"+day, Toast.LENGTH_SHORT).show();
                date1[0] = year+"/"+month+"/"+day;
                ArrayList<String>listatimeCt = citas_db.getTimes(date1[0],id_emp);
                boolean firstrow = false;
                boolean cfirstrow = false;
                for(int i = 0;i<listatime.size();i++)
               {
                   for(int x = 0; x<listatimeCt.size();x++)
                   {
                       if(firstrow && !cfirstrow)
                       {
                           i = 0;
                           cfirstrow = true;
                       }

                       if((listatime.get(i).getTime()).equals(listatimeCt.get(x))){
                           listatime.remove(i);
                           if(!firstrow)firstrow = true;
                           if(cfirstrow)cfirstrow =false;//cada vez que se borra un elemento, volvemos a la primera posición de listatimes con i= 0
                           i = 0;                       //y usamos dos variables booleanas para controlar la posicion de i dependiendo de si han sido borradas y
                           break;                      //si ha sido resetado i
                       }
                   }
                   if(listatime.size()==1){
                       i = 0;
                   }

               }
                if(listatime.size()<=1) {

                    for (int x = 0; x < listatimeCt.size(); x++) {


                        if ((listatime.get(0).getTime()).equals(listatimeCt.get(x))) {
                            listatime.remove(0);
                            break;

                        }

                    }
                }
               txtdias.setText("");
               txthoras.setText("");
               txtprecio.setText("");
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
                rvhoras.setLayoutManager(linearLayoutManager);
                rvhoras.setItemAnimator(new DefaultItemAnimator());
                RvHorasAdapter rvHorasAdapter = new RvHorasAdapter(getContext(),listatime,txtdias,txtprecio,txthoras,date1[0],id_serv);
                rvhoras.setAdapter(rvHorasAdapter);




            }
        });





        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new NewDateFragment(email);
                ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_date2, container, false);
    }

}