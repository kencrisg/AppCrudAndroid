package com.example.apputnsqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LibrosActivity extends AppCompatActivity {

    // Controles para libros
    EditText txtIDLibro, txtTituloLibro, txtIdAutorLibro, txtIsbn, txtAnioPublicacion, txtRevision, txtNroHojas;
    TableLayout tableLayoutLibros;
    Libros lstLibros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_libros);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Inicializar los controles de libros
        txtIDLibro = findViewById(R.id.txtIDLibro);
        txtTituloLibro = findViewById(R.id.txtTituloLibro);
        txtIdAutorLibro = findViewById(R.id.txtIdAutorLibro);
        txtIsbn = findViewById(R.id.txtIsbn);
        txtAnioPublicacion = findViewById(R.id.txtAnioPublicacion);
        txtRevision = findViewById(R.id.txtRevision);
        txtNroHojas = findViewById(R.id.txtNroHojas);

        lstLibros = new Libros(this, "biblioteca.db", 1);
    }
    public void cmdCrearLibro_onClick(View v) {
        Libro libro = lstLibros.Create(
                Integer.parseInt(txtIDLibro.getText().toString()),
                txtTituloLibro.getText().toString(),
                Integer.parseInt(txtIdAutorLibro.getText().toString()),
                txtIsbn.getText().toString(),
                Integer.parseInt(txtAnioPublicacion.getText().toString()),
                Integer.parseInt(txtRevision.getText().toString()),
                Integer.parseInt(txtNroHojas.getText().toString())
        );

        if (libro != null) {
            Toast.makeText(this, "Libro creado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "ERROR AL CREAR LIBRO!!", Toast.LENGTH_LONG).show();
        }
    }

    public void cmdActualizarLibro_onClick(View v) {
        Libro libro = lstLibros.Update(
                Integer.parseInt(txtIDLibro.getText().toString()),
                txtTituloLibro.getText().toString(),
                Integer.parseInt(txtIdAutorLibro.getText().toString()),
                txtIsbn.getText().toString(),
                Integer.parseInt(txtAnioPublicacion.getText().toString()),
                Integer.parseInt(txtRevision.getText().toString()),
                Integer.parseInt(txtNroHojas.getText().toString())
        );

        if (libro != null) {
            Toast.makeText(this, "Libro actualizado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "ERROR AL ACTUALIZAR LIBRO!!", Toast.LENGTH_LONG).show();
        }
    }

    public void cmdLeerLibro_onClick(View v) {
        Libro libro = lstLibros.Read_ById(
                Integer.parseInt(txtIDLibro.getText().toString())
        );

        if (libro != null) {
            txtTituloLibro.setText(libro.Titulo);
            txtIdAutorLibro.setText(String.valueOf(libro.IdAutor));
            txtIsbn.setText(libro.Isbn);
            txtAnioPublicacion.setText(String.valueOf(libro.AnioPublicacion));
            txtRevision.setText(String.valueOf(libro.Revision));
            txtNroHojas.setText(String.valueOf(libro.NroHojas));
            Toast.makeText(this, "Libro encontrado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "LIBRO NO ENCONTRADO!!", Toast.LENGTH_LONG).show();
        }
    }

    public void cmdEliminarLibro_onClick(View v) {
        boolean r = lstLibros.Delete(
                Integer.parseInt(txtIDLibro.getText().toString())
        );

        if (r) {
            txtIDLibro.setText("");
            txtTituloLibro.setText("");
            txtIdAutorLibro.setText("");
            txtIsbn.setText("");
            txtAnioPublicacion.setText("");
            txtRevision.setText("");
            txtNroHojas.setText("");
            Toast.makeText(this, "Libro eliminado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "ERROR AL ELIMINAR LIBRO!!", Toast.LENGTH_LONG).show();
        }
    }


    public TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        return textView;
    }

    public void cmdRegresar_onClick(View v) {
        finish();
    }

}