package com.gochat.jdemu.pgochat;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase basedatos;
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    FragmentManager fm = getFragmentManager();
    Fragment fragment;
    FragmentTransaction fts;
    String pagina = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SQLiteOpenHelper sq = new SQLiteOpenHelper(this, "Personal", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                String query = "CREATE TABLE personal ( \n " +
                        "correo TEXT DEFAULT NULL ,\n" +
                        "contra TEXT DEFAULT NULL);";
                db.execSQL(query);

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
        basedatos = sq.getWritableDatabase();
        if (getIntent().getStringExtra("pagina") != null) {
            pagina = getIntent().getStringExtra("pagina");
            borrar();
            pLogin();
        }else{
            pSplash();
        }

    }

    public void añadir(final String nombre, String correo, String contra, final String equipo) {


        String borrar = "DELETE FROM personal";
        basedatos.execSQL(borrar);
        String query = "INSERT INTO personal (correo,contra)VALUES('" + correo + "','" + contra + "');";
        basedatos.execSQL(query);
        fauth.createUserWithEmailAndPassword(correo, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    logInterno();
                    DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("usuarios");
                    dbr.child(nombre).push();
                    dbr.child(nombre).setValue(equipo);
                } else {
                    Toast.makeText(getApplicationContext(), "Este usuario ya existe", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public void logInterno() {
        String query = "SELECT * FROM personal";
        Cursor c = basedatos.rawQuery(query, null);
        if (c.moveToNext()) {
            logearFirebase(c.getString(0), c.getString(1));
        } else {
            pLogin();
        }

    }

    public void logearFirebase(final String correo, final String contra) {
        fauth.signInWithEmailAndPassword(correo, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String borrar = "DELETE FROM personal";
                    basedatos.execSQL(borrar);
                    String query = "INSERT INTO personal (correo,contra)VALUES('" + correo + "','" + contra + "');";
                    basedatos.execSQL(query);
                    Toast.makeText(getApplicationContext(), "Bienvenido" + correo, Toast.LENGTH_SHORT).show();
                    entra();
                } else {
                    Toast.makeText(getApplicationContext(), "Compruebe los campos", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void pSplash() {
        fragment = new Splash();
        fts = fm.beginTransaction();
        fts.replace(R.id.container, fragment);
        fts.commit();
    }

    public void pLogin() {
        fragment = new login();
        fts = fm.beginTransaction();
        fts.replace(R.id.container, fragment);
        fts.commit();
    }

    public void pRegistro() {
        fragment = new registro();
        fts = fm.beginTransaction();
        fts.replace(R.id.container, fragment);
        fts.commit();
    }

    public void entra() {
        Intent intent = new Intent(getApplicationContext(), menu.class);
        startActivity(intent);
    }

    public void borrar() {
        String borrar = "DELETE FROM personal";
        basedatos.execSQL(borrar);
    }
}

