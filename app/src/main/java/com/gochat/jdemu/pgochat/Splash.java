package com.gochat.jdemu.pgochat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jdemu on 18/03/2018.
 */

public class Splash extends Fragment {
    View vista;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        if (vista != null) {
            ViewGroup parent = (ViewGroup) vista.getParent();
            parent.removeView(vista);
        } else {
            vista = inflater.inflate(R.layout.splash, container, false);
            Thread timerTread = new Thread() {
                public void run() {
                    try {
                        sleep(1500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                       log();
                    }
                }
            };
            timerTread.start();
        }
        return vista;
    }
    public void log(){
        ((MainActivity)getActivity()).logInterno();
    }
}

