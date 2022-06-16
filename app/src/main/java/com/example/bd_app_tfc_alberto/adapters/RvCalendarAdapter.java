package com.example.bd_app_tfc_alberto.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bd_app_tfc_alberto.R;
import com.example.bd_app_tfc_alberto.clases.Citas;
import com.example.bd_app_tfc_alberto.database.Citas_DB;
import com.example.bd_app_tfc_alberto.database.Servicios_DB;
import com.example.bd_app_tfc_alberto.ui.CalendarFragment;
import com.example.bd_app_tfc_alberto.ui.DialogDelete;

import java.util.ArrayList;

public class RvCalendarAdapter extends RecyclerView.Adapter<RvCalendarAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Citas> listacitas = new ArrayList<>();
    Activity activity;

    String email;

    public RvCalendarAdapter(Context context, ArrayList<Citas>listacitas, Activity activity, String email)
    {
        this.listacitas = listacitas;
        this.context = context;
        this.activity = activity;

        this.email = email;
    }

    @NonNull
    @Override
    public RvCalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_calendar,parent,false);
        return new RvCalendarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvCalendarAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtdia.setText(listacitas.get(position).getDate());
        holder.txthora.setText(listacitas.get(position).getTime());
        holder.txtemp.setText(listacitas.get(position).getEmpleado());
        Servicios_DB servicios_db = new Servicios_DB(context);
        String precio = servicios_db.getPrecioByName(listacitas.get(position).getServicio());
        holder.txtprecio.setText(precio);
        holder.txtServicio.setText(listacitas.get(position).getServicio());
        holder.butdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDelete dialogDelete = new DialogDelete(activity,listacitas.get(position).getId());
                if(dialogDelete.startDialog())
                {
                    String dia = holder.txtdia.getText().toString();
                    listacitas.remove(position);
                    Citas_DB citas_db = new Citas_DB(context);
                    citas_db.deleteRow(listacitas.get(position).getId());
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,listacitas.size());




                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listacitas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button butdelete;
        TextView txtdia, txthora, txtprecio ,txtServicio,txtemp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            butdelete = itemView.findViewById(R.id.butdeleteFC);
            txtdia = itemView.findViewById(R.id.txtdiaFC);
            txthora = itemView.findViewById(R.id.txthoraFC);
            txtprecio = itemView.findViewById(R.id.txtprecioFC);
            txtServicio = itemView.findViewById(R.id.txtservicioFC2);
            txtemp = itemView.findViewById(R.id.txtuserFC);
        }
    }
}
