package com.example.apputnsqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText txtApellidos, txtNombres, txtID, txtIsoPais, txtEdad;
    TableLayout tableLayoutLibros;
    Libros lstLibros;
    Autores lstAutores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtID = findViewById(R.id.txtID);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtNombres = findViewById(R.id.txtNombres);
        txtIsoPais = findViewById(R.id.txtisoPais);
        txtEdad = findViewById(R.id.txtEdad);
        tableLayoutLibros = findViewById(R.id.tableLayoutLibros);

        lstAutores=new Autores(this,"biblioteca.db", 1);
        lstLibros = new Libros(this, "biblioteca.db", 1);


    }

    public void cmdCrear_onClick(View v){
        Autor r = lstAutores.Create(
            Integer.parseInt(txtID.getText().toString()),
            txtNombres.getText().toString(),
            txtApellidos.getText().toString(),
            txtIsoPais.getText().toString(),
            Integer.parseInt(txtEdad.getText().toString())
        );
        if(r!=null){
            Toast.makeText(this,"Autor creado correctamente",Toast.LENGTH_LONG).show();
            // Crear un libro para el autor creado
            // Crear varios libros para el autor creado
            Libro libro1 = lstLibros.Create(
                    10, // Cambia a un ID único o autogenerado para evitar conflictos
                    "Libro de Prueba 1",
                    r.Id, // ID del autor
                    "ISBN123456", // ISBN ficticio
                    2024, // Año de publicación
                    1, // Revisión
                    100 // Número de hojas
            );

            Libro libro2 = lstLibros.Create(
                    11, // Cambia a un ID único
                    "Libro de Prueba 2",
                    r.Id, // ID del autor
                    "ISBN789012", // ISBN ficticio diferente
                    2024, // Año de publicación
                    2, // Revisión
                    120 // Número de hojas
            );
        }
        else {
            Toast.makeText(this,"ERROR AL CREAR AUTOR!!",Toast.LENGTH_LONG).show();
        }

    }

    public void cmdActualizar_onClick(View v){
        Autor r = lstAutores.Update(
                Integer.parseInt(txtID.getText().toString()),
                txtNombres.getText().toString(),
                txtApellidos.getText().toString(),
                txtIsoPais.getText().toString(),
                Integer.parseInt(txtEdad.getText().toString())
        );
        if(r!=null){
            Toast.makeText(this,"Autor Actualizado correctamente",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"ERROR AL ACTUALIZAR AUTOR!!",Toast.LENGTH_LONG).show();
        }
    }

    public void cmdLeer_onClick(View v) {
        Autor r = lstAutores.Read_ById(
                Integer.parseInt(txtID.getText().toString())
        );
        if(r!=null){
            txtNombres.setText(r.Nombres);
            txtApellidos.setText(r.Apellidos);
            txtIsoPais.setText(r.IsoPais);
            txtEdad.setText(String.valueOf(r.Edad));

            cargarLibrosDelAutor(r.Id);
        }
        else {
            Toast.makeText(this,"REGISTRO NO ENCONTRADO!!",Toast.LENGTH_LONG).show();
        }
    }

    public void cmdEliminar_onClick(View v) {
        boolean r = lstAutores.Delete(
                Integer.parseInt(txtID.getText().toString())
        );

        if (r){
            txtID.setText("");
            txtNombres.setText("");
            txtApellidos.setText("");
            txtIsoPais.setText("");
            txtEdad.setText("");

            Toast.makeText(this,"REGISTRO ELIMINADO!!",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"ERROR AL ELIMINAR!!",Toast.LENGTH_LONG).show();
        }
    }

    public void cargarLibrosDelAutor(int idAutor) {
        tableLayoutLibros.removeAllViews();

        // Agregar encabezados nuevamente
        TableRow headerRow = new TableRow(this);
        headerRow.addView(createTextView("Título"));
        headerRow.addView(createTextView("ISBN"));
        headerRow.addView(createTextView("Año"));
        headerRow.addView(createTextView("Revisión"));
        headerRow.addView(createTextView("Nro Hojas"));
        tableLayoutLibros.addView(headerRow);

        // Obtener los libros y agregarlos al TableLayout
        Libro[] libros = lstLibros.Read_All();
        for (Libro libro : libros) {
            if (libro.IdAutor == idAutor) {
                TableRow row = new TableRow(this);
                row.addView(createTextView(libro.Titulo));
                row.addView(createTextView(libro.Isbn));
                row.addView(createTextView(String.valueOf(libro.AnioPublicacion)));
                row.addView(createTextView(String.valueOf(libro.Revision)));
                row.addView(createTextView(String.valueOf(libro.NroHojas)));
                tableLayoutLibros.addView(row);
            }
        }
    }

    public TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        return textView;
    }




}