package com.example.bd_app_tfc_alberto.ui;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bd_app_tfc_alberto.R;
import com.example.bd_app_tfc_alberto.database.Citas_DB;

public class DialogDelete {
        Activity activity;

        int id;

        public DialogDelete(Activity activity,int id)
        {
            this.activity = activity;

            this.id = id;
        }

        public boolean startDialog()
        {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_confirm_delete);
            final boolean[] deleted = {false};
            Button butok = dialog.findViewById(R.id.butconfirmD);
            Button butclose = dialog.findViewById(R.id.butcancelD);
            butclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
            butok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Citas_DB citas_db = new Citas_DB(activity);
                    citas_db.deleteRow(id);
                    deleted[0] = true;

                }
            });

            dialog.show();
            return deleted[0];



        }
}
