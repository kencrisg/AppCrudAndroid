package com.example.apputnsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {

    TextView lblResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lblResultado = findViewById(R.id.lblResultado);
        //Leer los datos del diccionario
        Bundle extra = getIntent().getExtras();
        lblResultado.setText(extra.getString("usuario"));
    }

    public void cmdAutoresMain_onClick(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void cmdLibrosMain_onClick(View v){
        Intent i = new Intent(this, LibrosActivity.class);
        startActivity(i);
    }
    public void cmdRegresar_onClick(View v){
        finish();
    }


}