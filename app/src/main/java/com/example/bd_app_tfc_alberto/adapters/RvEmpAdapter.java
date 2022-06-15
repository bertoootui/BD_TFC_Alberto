package com.example.bd_app_tfc_alberto.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bd_app_tfc_alberto.R;
import com.example.bd_app_tfc_alberto.clases.ConfigPreferences;
import com.example.bd_app_tfc_alberto.clases.Empleados;

import java.time.temporal.Temporal;
import java.util.ArrayList;

public class RvEmpAdapter extends RecyclerView.Adapter<RvEmpAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Empleados> listaemp;

    public RvEmpAdapter(Context context, ArrayList<Empleados> listaemp)
    {
        this.context = context;
        this.listaemp = listaemp;
    }

    @NonNull
    @Override
    public RvEmpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_empleados,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvEmpAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtemp.setText(listaemp.get(position).getNombre());
        switch (position)
        {
            case 0:
                holder.imgfoto.setBackgroundResource(R.drawable.empleado0);
                break;
            case 1:
                holder.imgfoto.setBackgroundResource(R.drawable.empleado1);
                break;
            case 2:
                holder.imgfoto.setBackgroundResource(R.drawable.empleado2);
                break;
            case 3:
                holder.imgfoto.setBackgroundResource(R.drawable.empleado3);
                break;
            case 4:
                holder.imgfoto.setBackgroundResource(R.drawable.empleado4);
                break;
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
        int cont = 0;
            @Override
            public void onClick(View view) {
                if(cont == 0)
                {
                    ConfigPreferences config = new ConfigPreferences();
                    config.setEmpSel(context,position+1);
                    holder.layout.setBackgroundResource(R.drawable.shape_emp_nosel);
                    cont++;

                }else
                {
                    ConfigPreferences config = new ConfigPreferences();
                    config.setEmpSel(context,-1);
                    holder.layout.setBackgroundResource(R.drawable.shape_emp_sel);
                    cont = 0;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaemp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgfoto;
        TextView txtemp;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgfoto = itemView.findViewById(R.id.imgcardemp);
            txtemp = itemView.findViewById(R.id.txtcardemp);
            layout = itemView.findViewById(R.id.layoutemp);
        }
    }
}
