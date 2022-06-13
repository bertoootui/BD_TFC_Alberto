package com.example.bd_app_tfc_alberto.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bd_app_tfc_alberto.R;
import com.example.bd_app_tfc_alberto.database.Login_DB;
import com.example.bd_app_tfc_alberto.database.Usuarios_DB;

import java.io.UnsupportedEncodingException;

public class ActivityLogin extends AppCompatActivity {
    EditText edtpass, edtemail, edtconfirm;
    Button butregis, butlogin;
    private boolean regis = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtconfirm = findViewById(R.id.etdConfirmpass);
        edtpass = findViewById(R.id.etdPass);
        edtemail = findViewById(R.id.etdEmail);
        butregis = findViewById(R.id.butregister);
        butlogin = findViewById(R.id.butlogin);
        Login_DB login_db = new Login_DB(getApplicationContext());
        butregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(regis)
                {
                    String pass = edtpass.getText().toString();
                    String email = edtemail.getText().toString();
                    if(pass.equals("") && email.equals(""))
                    {
                        edtconfirm.setVisibility(View.VISIBLE);
                        butlogin.setVisibility(View.INVISIBLE);
                    }


                    else if(pass.equals("") || email.equals(""))
                    {
                        Toast.makeText(ActivityLogin.this, "Hay campos vacíos", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        String confirm = edtconfirm.getText().toString();
                        if(confirm.equals(pass)) {
                            boolean login = false;

                            login = login_db.registerUser(email,pass);
                            if (login)
                            {
                                Usuarios_DB  usuarios_db = new Usuarios_DB(getApplicationContext());
                                int id_user = usuarios_db.saveDataRegister(email,pass);
                                login_db.updateIdUser(id_user,email);
                                edtconfirm.setVisibility(View.INVISIBLE);
                                butlogin.setVisibility(View.VISIBLE);
                            }else {
                                Toast.makeText(ActivityLogin.this, "Usuario no válido", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(ActivityLogin.this, "Las contraseñas no coínciden", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    regis = true;
                    edtconfirm.setVisibility(View.VISIBLE);
                    butlogin.setVisibility(View.INVISIBLE);
                }
            }
        });
        butlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtemail.getText().toString();
                String pass = edtpass.getText().toString();
                String confirm = login_db.getLogin(email,pass);

                switch (confirm)
                {
                    case "Contraseña incorrecta":
                        Toast.makeText(ActivityLogin.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();

                        break;
                    case "user login":
                        Toast.makeText(ActivityLogin.this, "Login correcto", Toast.LENGTH_SHORT).show();
                        Intent t = new Intent(ActivityLogin.this,ActivityUser.class);
                        t.putExtra("email",email);
                        startActivity(t);
                        break;
                    case "emp login":
                        Toast.makeText(ActivityLogin.this, "Login correcto", Toast.LENGTH_SHORT).show();
                        Intent t2 = new Intent(ActivityLogin.this,ActivityEmp.class);
                        t2.putExtra("email",email);
                        startActivity(t2);
                        break;
                        
                    case "Email incorrecto":

                        Toast.makeText(ActivityLogin.this, "Email incorrecto", Toast.LENGTH_SHORT).show();
                        break;
                }

            }

        });


    }
}