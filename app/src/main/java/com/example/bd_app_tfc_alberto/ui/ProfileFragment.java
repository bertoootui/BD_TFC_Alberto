package com.example.bd_app_tfc_alberto.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bd_app_tfc_alberto.R;
import com.example.bd_app_tfc_alberto.clases.Usuario;
import com.example.bd_app_tfc_alberto.database.Login_DB;
import com.example.bd_app_tfc_alberto.database.Usuarios_DB;


public class ProfileFragment extends Fragment {
    private String email;
    public ProfileFragment(String email) {
        this.email = email;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText edtnombre = view.findViewById(R.id.edtnombreP);
        TextView edtemail = view.findViewById(R.id.edtemailP);
        EditText edtphone = view.findViewById(R.id.edttlfP);
        EditText edtpass = view.findViewById(R.id.edtpassP);
        EditText edtconpass = view.findViewById(R.id.edtConfirmpassP);
        Button butdatos = view.findViewById(R.id.butconfirmCP2);
        Button butpass = view.findViewById(R.id.butconfirmCP);
        edtemail.setText(email);
        Usuarios_DB usuarios_db = new Usuarios_DB(getContext());
        Usuario user = usuarios_db.getProfileData(email);
        String nombre = user.getNombre();
        if(nombre != null)
        edtnombre.setText(nombre);
        edtemail.setText(user.getEmail()+"");
        if(user.getPhone() != 0) edtphone.setText(user.getPhone()+"");

        butdatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edtnombre.getText().toString();
                String email = edtemail.getText().toString();
                int phone = Integer.valueOf(edtphone.getText().toString());

                usuarios_db.updateUser(email,nombre,phone);
                Toast.makeText(getContext(), "Se han guardados los datos", Toast.LENGTH_SHORT).show();
            }
        });
        butpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = edtpass.getText().toString();
                String conpass = edtconpass.getText().toString();
                if(pass.equals(conpass))
                {
                    Login_DB login_db = new Login_DB(getContext());
                    login_db.updatePass(email,pass);
                    Toast.makeText(getContext(), "Se ha actualizado la contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}