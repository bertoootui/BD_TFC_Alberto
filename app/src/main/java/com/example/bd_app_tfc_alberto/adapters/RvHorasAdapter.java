package com.example.bd_app_tfc_alberto.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bd_app_tfc_alberto.R;
import com.example.bd_app_tfc_alberto.clases.ConfigPreferences;
import com.example.bd_app_tfc_alberto.clases.Horas;
import com.example.bd_app_tfc_alberto.database.Servicios_DB;

import java.util.ArrayList;

public class RvHorasAdapter extends RecyclerView.Adapter<RvHorasAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Horas> listahoras;
    String hora, fecha;
    private TextView txthoras,txtprecio,txtdias;
    private int id_serv;


    public RvHorasAdapter(Context context, ArrayList<Horas> listahoras, TextView txtdias, TextView txtprecio, TextView txthoras,String fecha,int id_serv)
    {
        this.context = context;
        this.listahoras = listahoras;
        this.txtdias = txtdias;
        this.txthoras = txthoras;
        this.txtprecio =txtprecio;
        this.fecha = fecha;
        this.id_serv = id_serv;

    }



    @NonNull
    @Override
    public RvHorasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_horas,parent,false);
        return new RvHorasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvHorasAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txthoras.setText(listahoras.get(position).getTime());
        ConfigPreferences configPreferences = new ConfigPreferences();
        configPreferences.setHoraSel("00:00",context);
        Servicios_DB servicios_db = new Servicios_DB(context);
        holder.txthoras.setOnClickListener(new View.OnClickListener() {
            int cont = 0;
            @Override
            public void onClick(View view) {
                hora = listahoras.get(position).getTime();
                String precio = servicios_db.getPrecio(id_serv);
                Log.i("hora",hora);
                String hora2 = configPreferences.getHoraSel(context);
                //ARRGLAR QUE NO SE PUEDA ESCOGER MÁS DE UNA HORA, HASTA AHORA LA HORA GURDADA ES SIEMPRE DISTINTO
                // DE HORA Y DISTONTO DE 00:01, POR LO QUE SE SIGUEB PODIENDO ESGOGER MAS DE UNA HORA,
                // ME CAGO EN LA PUTTTAAAAAAAAAAA!!!!!!! :)
                if(!hora2.equals(hora) && !hora2.equals("00:00"))
                {
                    Toast.makeText(context, "No se puede elegir más de una hora", Toast.LENGTH_SHORT).show();
                }else {
                    if (cont == 0) {

                        holder.txthoras.setBackgroundResource(R.drawable.shape_txthoras_sel);
                        cont++;
                        txtdias.setText(fecha);
                        txthoras.setText(hora+"");
                        txtprecio.setText(precio);
                        configPreferences.setHoraSel(hora, context);
                    } else {

                        holder.txthoras.setBackgroundResource(R.drawable.shape_txthoras);
                        cont = 0;
                        configPreferences.setHoraSel("00:00", context);
                    }
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return listahoras.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txthoras;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txthoras = itemView.findViewById(R.id.txtcardhoras);
        }
    }
}
