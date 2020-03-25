package com.cdlc.primerparcialandersonmon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Listar extends AppCompatActivity {

    ListView listado;
    ArrayAdapter<Pelicula> arrayAdapter;
    ArrayList<Pelicula> peliculas;
    Registrar re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        Intent in = getIntent();

        peliculas = in.getParcelableArrayListExtra("est");
        listado = findViewById(R.id.listpel);
        arrayAdapter = new PeliculaAdapter(this, peliculas);

        listado.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opciones, menu);
        return true;
    }

    public void ordenarGenero() {

        Collections.sort(peliculas, new Comparator<Pelicula>() {
            @Override
            public int compare(Pelicula p1, Pelicula p2) {

                return p1.getGenero().compareTo(p2.getGenero());

            }
        });

    }

    public void ordenarNombre() {
        Collections.sort(peliculas, new Comparator<Pelicula>() {
            @Override
            public int compare(Pelicula p1, Pelicula p2) {

                return p1.getNombre().compareTo(p2.getNombre());

            }
        });

    }

    public void listaInvertida() {
        Collections.reverse(peliculas);

    }

    public void EliminarPrimer() {

        if (!peliculas.isEmpty()) {
            peliculas.remove(0);

        }
    }

    private void eliminarDialog(String mensaje) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Importante");
        dialog.setMessage(mensaje);
        dialog.setCancelable(false);
        dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogo1, int id) {
                positiva();

            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                negativa();
            }
        });
        dialog.show();


    }

    public void positiva() {

        Toast t = Toast.makeText(this, "Se ha ejecutado la accion", Toast.LENGTH_SHORT);
        t.show();
        arrayAdapter.notifyDataSetChanged();

        EliminarPrimer();
        arrayAdapter.notifyDataSetChanged();


    }

    public void negativa() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.EliminarPrimeraPelicula:
                arrayAdapter.notifyDataSetChanged();
                eliminarDialog("Eliminar la primera pelicula");
                arrayAdapter.notifyDataSetChanged();
                break;

            case R.id.OrganizarPorGenero:
                ordenarGenero();
                arrayAdapter.notifyDataSetChanged();
                break;

            case R.id.OrganizarPorNombre:
                ordenarNombre();
                arrayAdapter.notifyDataSetChanged();
                break;
            case R.id.InvertirLista:
                listaInvertida();
                arrayAdapter.notifyDataSetChanged();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}


