package com.example.bd_app_tfc_alberto.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bd_app_tfc_alberto.R;
import com.example.bd_app_tfc_alberto.clases.Citas;
import com.example.bd_app_tfc_alberto.clases.ConfigPreferences;
import com.example.bd_app_tfc_alberto.clases.Servicios;
import com.example.bd_app_tfc_alberto.database.Citas_DB;
import com.example.bd_app_tfc_alberto.ui.NewDate2Fragment;

import java.util.ArrayList;

public class RvServAdapter extends RecyclerView.Adapter<RvServAdapter.ViewHolder> {

    Context context;
    ArrayList<Servicios> listaserv;
    Activity activity;
    private String email;
    public RvServAdapter(Context context, ArrayList<Servicios> listaserv, Activity activity,String email)
    {
        this.context = context;
        this.listaserv = listaserv;
        this.activity = activity;
        this.email = email;
    }

    @NonNull
    @Override
    public RvServAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_servicios,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvServAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtprecio.setText(listaserv.get(position).getPrecio());
        holder.txtnombre.setText(listaserv.get(position).getNombre());
        holder.butresv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfigPreferences config = new ConfigPreferences();
                if(config.getEmpSel(context) == -1){
                    Toast.makeText(context, "Es necesario escoger un peluquero", Toast.LENGTH_SHORT).show();        
                }else {
                    config.setServSel(context,position+1);
                    Fragment fragment = new NewDate2Fragment(email);
                    ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaserv.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtnombre,txtprecio;
        Button butresv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnombre = itemView.findViewById(R.id.txtcardservicio);
            txtprecio = itemView.findViewById(R.id.txtcardprecio);
            butresv = itemView.findViewById(R.id.butcardresv);
        }
    }
}
