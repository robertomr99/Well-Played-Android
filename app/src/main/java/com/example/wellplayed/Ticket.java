package com.example.wellplayed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Usuario;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class Ticket extends AppCompatActivity {

    Button btnEnviar;
    ImageView imgTicketUsuario;
    EditText txtAsunto,txtMensaje;
    private StorageReference storage;

    public static String sNombreUser;
    public static final int GALLERY_INTENT = 1;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sNombreUser = MainActivity.oUsuario.getsUser();
        setContentView(R.layout.activity_ticket);
        context = this;
        imgTicketUsuario = findViewById(R.id.imgTicketUsuario);
        txtAsunto = findViewById(R.id.txtAsunto);
        txtMensaje = findViewById(R.id.txtMensaje);
        storage = FirebaseStorage.getInstance().getReference();
        btnEnviar = findViewById(R.id.btnEnviar);
    }

    public void mandarTicket(View view){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,GALLERY_INTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

           Uri uri = data.getData();
            StorageReference filePath = storage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Ticket.this, "la foto se ha subido con exito", Toast.LENGTH_SHORT).show();
                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri imageUri) {

                            Glide.with(getApplicationContext()).load(imageUri).into(imgTicketUsuario);
                         btnEnviar.setOnClickListener(new View.OnClickListener() {

                             public void onClick(View view) {
                                 insertTicket(imageUri);

                             }
                         });
                        }
                    });
                }
            });
        }
    }

    public void insertTicket(Uri uri) {
            String sUrl = Utils.hosting + "soporte/insertarTicket.php?txtUsuario="+sNombreUser+"&txtAsunto="+txtAsunto.getText()+"&txtMensaje="+txtMensaje.getText()+"&txtFoto="+uri;
            Log.d("url",sUrl);
            Volley.newRequestQueue(Login.getInstance().getApplicationContext()).add(new StringRequest(Request.Method.GET,sUrl,
                    s ->{
                        if(s.equals("null")){
                            Toast.makeText(Login.getInstance().getApplicationContext(), "Ticket no enviado", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Login.getInstance().getApplicationContext(), "Ticket enviado correctamente", Toast.LENGTH_LONG).show();

                        }
                    }
                    ,volleyError -> {

                Log.d("ALACID",volleyError.getCause().toString());
            }
            ));

    }
}