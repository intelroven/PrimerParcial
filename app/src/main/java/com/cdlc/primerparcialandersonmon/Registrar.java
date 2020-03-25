package com.cdlc.primerparcialandersonmon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Registrar extends AppCompatActivity implements View.OnClickListener {
    Button grabar, cancelar;
    ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
    EditText nombres, director;
    CheckBox español, ingles;
    RadioButton accion, suspenso, drama, comedia;
    RadioGroup Genero, Idioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar2);

        crearElemantos();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnGrabar:
                if (nombres.getText().equals("") || director.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "Rellene los campos especificados", Toast.LENGTH_SHORT).show();

                } else {
                    String Mensaje = "¿Desea guardar la pelicula?";
                    Insertar(Mensaje);
                }

                break;

            case R.id.btnCancelar:
                Toast.makeText(getApplicationContext(), "Hasta luego", Toast.LENGTH_SHORT).show();
                finish();
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.listar:
                Intent i = new Intent(this, Listar.class);
                i.putParcelableArrayListExtra("est", peliculas);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Insertar(String mensaje) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Importante");
        dialog.setMessage(mensaje);
        dialog.setCancelable(false);
        dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();

            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        dialog.show();

    }

    public void aceptar() {
        Toast t = Toast.makeText(this, "Se ha ejecutado la accion", Toast.LENGTH_SHORT);
        t.show();
        RegistrarPelicula();
    }

    public void cancelar() {
        finish();
    }

    private void RegistrarPelicula() {

        String nombre = nombres.getText().toString();
        String directors = director.getText().toString();
        String idioma = "";
        String genero = "";

        if (drama.getId() == Genero.getCheckedRadioButtonId()) {
            genero = "Drama";
        } else {
            if (accion.getId() == Genero.getCheckedRadioButtonId()) {
                genero = "Accion";
            }
            if (comedia.getId() == Genero.getCheckedRadioButtonId()) {
                genero = "Comedia";
            }
            if (suspenso.getId() == Genero.getCheckedRadioButtonId()) {
                genero = "Suspenso";
            }

        }

        if (español.isChecked() && ingles.isChecked()) {
            idioma = "Ingles y Español";
        }

        if (español.isChecked()) {
            idioma = "Español";
        } else {
            if (ingles.isChecked()) {
                idioma = "Ingles";
            }


        }

        peliculas.add(new Pelicula(nombre, directors, idioma, genero));
        Toast.makeText(getApplicationContext(), "registro completado", Toast.LENGTH_SHORT).show();

    }

    public void crearElemantos(){
        grabar = findViewById(R.id.btnGrabar);
        cancelar = findViewById(R.id.btnCancelar);

        grabar.setOnClickListener(this);
        cancelar.setOnClickListener(this);

        //Guardando los atributos
        nombres = findViewById(R.id.txtNombreL);
        director = findViewById(R.id.txtDirector);
        //Idioma
        español = findViewById(R.id.chEspañol);
        ingles = findViewById(R.id.chIngles);
        //Genero
        accion = findViewById(R.id.rdAccion);
        drama = findViewById(R.id.rdDrama);
        comedia = findViewById(R.id.rdComedia);
        suspenso = findViewById(R.id.rdSuspenso);

        //Grpos
        Genero = findViewById(R.id.radioGroup);
        Idioma = findViewById(R.id.chGroup);
    }

}
