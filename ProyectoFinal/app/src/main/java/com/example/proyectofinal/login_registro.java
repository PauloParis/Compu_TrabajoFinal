package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.db.DbDataSource;
import com.example.proyectofinal.models.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_registro extends AppCompatActivity {

    TextView etNombre, etCorreo, etPassword;
    Button btnRegistro;

    DbDataSource dataSource;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registro);
        setTitle("Registro de Usuario");


        dataSource = new DbDataSource(this);


        etNombre = findViewById(R.id.etNombreUsuario);
        etCorreo = findViewById(R.id.etCorreoUsuario);
        etPassword = findViewById(R.id.etPasswordUsuario);
        btnRegistro = findViewById(R.id.btn_Registrarse);


        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    Toast.makeText(login_registro.this, "El usuario fue creado", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), ListaPedidosPrincipal.class);
                    startActivity(intent);
                }
                else {
                    //Toast.makeText(login_registro.this, "El usuario no ha sido creado", Toast.LENGTH_SHORT).show();
                }
            }
        };



        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dataSource.openDB();
                //guardarusuario();
                //dataSource.closeDB();
                //FirebaseUser user = firebaseAuth.getCurrentUser();

                if(etPassword.length()<6){
                    Toast.makeText(login_registro.this, "Ingrese una contraseña de 6 o mas caracteres y/o un correo valido", Toast.LENGTH_SHORT).show();
                }
                else{
                    signUp(view);
                    Intent intent = new Intent(getApplicationContext(), ListaPedidosPrincipal.class);
                    startActivity(intent);
                }

            }
        });

    }

    public void signUp(View view){

        String username = etCorreo.getText().toString();
        String password = etPassword.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(login_registro.this, "ERROR AL REGISTRAR", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), login_registro.class);
                    startActivity(intent);
                }
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

}

    /*public void guardarusuario(){
        String nombre = etNombre.getText().toString();
        String correo = etCorreo.getText().toString();
        String password = etPassword.getText().toString();
        if(crearusuario(nombre, correo, password) != -1){
            Toast.makeText(this, "Usuario guardado con exito", Toast.LENGTH_SHORT).show();
            setResult(1);
            finish();
        }
        else{
            Log.i("AgregarContactoActivity", "error");
        }
    }*/
    /*public long crearusuario(String nombre,String correo,String password){
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setPassword(password);
        usuario = dataSource.insertarUsuario(usuario);
        return usuario.getId();
    }*/




