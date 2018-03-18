package com.gochat.jdemu.pgochat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by jdemu on 18/03/2018.
 */

public class registro extends Fragment {
    View vista;
    EditText nombre, correo, contra, repetir;
    Spinner equipo;
    Button registar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        if (vista != null) {
            ViewGroup parent = (ViewGroup) vista.getParent();
            parent.removeView(vista);
        } else {
            vista = inflater.inflate(R.layout.registro, container, false);
            nombre=(EditText)vista.findViewById(R.id.RegNombre);
            correo=(EditText)vista.findViewById(R.id.RegCorreo);
            contra=(EditText)vista.findViewById(R.id.RegContra);
            repetir=(EditText)vista.findViewById(R.id.RegRepetir);
            ArrayList equipos=new ArrayList();
            equipos.add("Instinto(Amarillo)");
            equipos.add("Valor(Rojo)");
            equipos.add("Sabiduria(Azul)");
            ArrayAdapter adapter= new ArrayAdapter(vista.getContext(),android.R.layout.simple_list_item_1,equipos);
            equipo=(Spinner) vista.findViewById(R.id.RegEquipo);
            equipo.setAdapter(adapter);
            registar=(Button)vista.findViewById(R.id.bntRegistro);
            registar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String eq= (String) equipo.getItemAtPosition(equipo.getSelectedItemPosition());
                    ((MainActivity)getActivity()).añadir(String.valueOf(nombre.getText()),String.valueOf(correo.getText()),String.valueOf(contra.getText()),eq);
                }
            });

        }
        return vista;
    }
}
