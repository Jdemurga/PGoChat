package com.gochat.jdemu.pgochat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by jdemu on 18/03/2018.
 */

public class login extends Fragment{
    EditText correo, contra;
    Button login;
    View vista;
    TextView iraregistro;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        if (vista != null) {
            ViewGroup parent = (ViewGroup) vista.getParent();
            parent.removeView(vista);
        } else {
            vista = inflater.inflate(R.layout.login, container, false);
            correo=(EditText) vista.findViewById(R.id.LogCorreo);
            contra=(EditText) vista.findViewById(R.id.LogContra);
            login=(Button)vista.findViewById(R.id.btnLogin);
            iraregistro=(TextView)vista.findViewById(R.id.iraregistro) ;
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).logearFirebase(String.valueOf(correo.getText()),String.valueOf(contra.getText()));
                }
            });
            iraregistro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).pRegistro();
                }
            });

        }
        return vista;
    }
}
