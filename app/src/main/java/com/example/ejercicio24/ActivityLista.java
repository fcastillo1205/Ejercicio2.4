package com.example.ejercicio24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio24.Configuracion.SQLiteConexion;
import com.example.ejercicio24.Configuracion.transacciones;
import com.example.ejercicio24.tablas.signaturess;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class ActivityLista extends AppCompatActivity {
    SQLiteConexion conexion;
    ImageView imageView;
    Button btnSiguiente, btnAnterior;
    TextView txtId,txtViewDescripcion;
    Boolean error = false;
    Integer id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        conexion = new SQLiteConexion(this, transacciones.NameDatabase, null, 1);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnAnterior = (Button) findViewById(R.id.btnAnterior);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        txtId = (TextView) findViewById(R.id.txtId);
        txtViewDescripcion = (TextView) findViewById(R.id.txtViewDescripcion);
        txtId.setEnabled(false);
        txtViewDescripcion.setEnabled(false);
        obtenerPrimerFoto();

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarImagenSiguiente();
            }
        });

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarImagenAnterior();
            }
        });

    }

    private void obtenerPrimerFoto() {
        txtId.setText("1");
        id = Integer.parseInt(txtId.getText().toString());
        Bitmap img = buscarImagen(id);
        imageView.setImageBitmap(img);
    }

    private void buscarImagenAnterior() {
        calcularId("a");

        id= Integer.parseInt(txtId.getText().toString()) - 1;
        txtId.setText(id.toString());
        Bitmap img = buscarImagen(id);
        if(!error && id > 0){
            imageView.setImageBitmap(img);
        }else{
            Toast.makeText(getApplicationContext(), "No existen imagenes anteriores", Toast.LENGTH_LONG).show();
            error=false;
            id += 1;
            buscarImagen(id);
            txtId.setText(id.toString());
        }
    }

    private void calcularId(String accion) {
        if(accion == "a"){
            id -= 1;
        }
    }

    private void buscarImagenSiguiente() {
        id = Integer.parseInt(txtId.getText().toString()) + 1;
        txtId.setText(id.toString());
        Bitmap img = buscarImagen(id);

        if(!error){
            imageView.setImageBitmap(img);
        }else{
            Toast.makeText(getApplicationContext(), "No existen imagenes siguientes", Toast.LENGTH_LONG).show();
            error=false;
            id -= 1;
            buscarImagen(id);
            txtId.setText(id.toString());
        }
    }





    public Bitmap buscarImagen(long id){
        SQLiteDatabase db = conexion.getReadableDatabase();

        String sql = String.format("SELECT * FROM signaturess WHERE id = %d", id);
        Cursor cursor = db.rawQuery(sql, new String[] {});

        Bitmap bitmap = null;
        if(cursor.moveToFirst()){
            byte[] blob = cursor.getBlob(1);
            ByteArrayInputStream bais = new ByteArrayInputStream(blob);
            bitmap = BitmapFactory.decodeStream(bais);
            txtViewDescripcion.setText(cursor.getString(2));
        }else{
            error = true;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();
        return bitmap;
    }


}