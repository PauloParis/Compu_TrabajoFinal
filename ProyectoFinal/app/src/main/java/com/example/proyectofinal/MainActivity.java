package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.db.DbDataSource;
import com.example.proyectofinal.db.DbOpenHelper;
import com.example.proyectofinal.models.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView tvRegistrarse;
    Button btnIngresar;
    DbDataSource dataSource;
    SQLiteOpenHelper dnhelper;
    SQLiteDatabase db;

    DbOpenHelper helper;

    EditText txtcorreo;
    EditText txtpassword;


    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
        //dataSource = new DbDataSource(this);



        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();


                if(user != null){ //ver si existe un usuario
                    //Toast.makeText(MainActivity.this, "El usuario ha iniciado sesion", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), ListaPedidosPrincipal.class);
                    startActivity(intent);
                }
                else {
                    //Toast.makeText(MainActivity.this, "El usuario salio de la sesion", Toast.LENGTH_SHORT).show();
                }
            }
        };



        dataSource = new DbDataSource(this);
        helper = new DbOpenHelper(this);

        tvRegistrarse = findViewById(R.id.tv_Registrarse);
        btnIngresar = findViewById(R.id.btn_Ingresar);
        txtcorreo = findViewById(R.id.et_IngresarCorreo);
        txtpassword = findViewById(R.id.et_IngresarPassword);

        dnhelper = new DbOpenHelper(this);

        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), login_registro.class);
                //startActivity(intent);
                signUp(view);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login(view);

                //String correo = txtcorreo.getText().toString();
                //String password = txtpassword.getText().toString();
                //onStart();
                //onStop();
                /*if(txtcorreo.equals("") || txtpassword.equals("")){
                    Toast.makeText(MainActivity.this, "Por favor ingresar todos los campos ", Toast.LENGTH_SHORT).show();
                }
                else {
                    //codigo para verificar la contraseña
                    dataSource.openDB();
                    Boolean checkCorpas = helper.checkPassword(correo, password);
                    if (checkCorpas == true){

                        Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ListaPedidosPrincipal.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Credenciales invalidas", Toast.LENGTH_SHORT).show();
                    }
                    dataSource.closeDB();
                }*/


            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    public void login (View view){
        String username = txtcorreo.getText().toString();
        String password = txtpassword.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "ERROR INICIO SESION", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "BIENVENIDO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signUp(View view){
        Intent intent = new Intent(getApplicationContext(), login_registro.class);
        startActivity(intent);
    }


}