package com.gochat.jdemu.pgochat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jdemu on 18/03/2018.
 */

public class chat extends Fragment {
    TextView te;
    View vista;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        if (vista != null) {
            ViewGroup parent = (ViewGroup) vista.getParent();
            parent.removeView(vista);
        } else {
            vista = inflater.inflate(R.layout.chat, container, false);
            te = (TextView)vista.findViewById(R.id.logout);
            te.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((menu)getActivity()).salir();
                }
            });

        }
        return vista;
    }
}
