package com.example.wellplayed;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Juego;
import com.example.wellplayed.model.Producto;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class tiendaFragment extends Fragment {

    public static final String sNombreUser = MainActivity.oUsuario.getsUser();
    RecyclerView Rv;
    public static Spinner spinnerCategoriaProducto;
    public static LayoutInflater inflaterDetalle;
    public static AlertDialog.Builder dialogBuilder;
    public static AlertDialog dialog;
    public static Button btnSI, btnNO;
    TextView lblMonedasUser;
    ArrayList<String> lstCategorias = new ArrayList<String>();

    public tiendaFragment() {
    }


    public void onCreate(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectIdUser(sNombreUser);

    }

    private void rellenarCategorias() {
        lstCategorias.add("Avatares");
        lstCategorias.add("Banners");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.item_spinner, lstCategorias);
        spinnerCategoriaProducto.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_tienda, container, false);
        Rv = vista.findViewById(R.id.recyclerViewTienda);
        lstCategorias.clear();
        spinnerCategoriaProducto = vista.findViewById(R.id.spinnerCategoriaProducto);
        rellenarCategorias();
        lblMonedasUser = vista.findViewById(R.id.lblMonedasUser);
        cambiarCategoriaProducto();
        return vista;
    }



    public void mostrarProductos(Usuario oUsuario) {
        int iSeleccionado = devolverProducto();
        String sUrl = Utils.hosting + "usuario-producto/productoQueNoTieneUser.php?txtiIdUsuario="+oUsuario.getiIdUsuario()+"&txtCategoria=" + iSeleccionado;

        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoProductos.lstProductos = new Gson().fromJson(s, new TypeToken<List<Producto>>() {
                        }.getType());
                        mostrarData(getContext(),iSeleccionado);
                        Log.d("ajhwgdajhgdahjgdahjgdjhawgdajhwd",String.valueOf(oUsuario.getiMonedas()));
                        lblMonedasUser.setText(String.valueOf(oUsuario.getiMonedas()));
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    private void selectIdUser(String sNombreUser) {

        String sUrl = Utils.hosting + "usuario/select-idUser.php?txtUsuario="+sNombreUser;
        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Usuario oUsuarioId = new Usuario();
                        oUsuarioId = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());
                        mostrarProductos(oUsuarioId);
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    private void selectIdUser2(String sNombreUser, Producto oProducto) {

        String sUrl = Utils.hosting + "usuario/select-idUser.php?txtUsuario="+sNombreUser;
        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Usuario oUsuarioId = new Usuario();
                        oUsuarioId = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());
                        Log.d("ajhdgajhdhgahjgdjawdanbvwnbevanwevb", String.valueOf(oUsuarioId.getiMonedas()));
                        if(oUsuarioId.getiMonedas() > oProducto.getiPrecio()){
                            insertProductoUsuario(oUsuarioId,oProducto);
                            Toast.makeText(getContext(), "Objeto comprado con exito", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "No tienes monedas suficientes", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    private int devolverProducto() {
        int iResultado = 0;
        if (spinnerCategoriaProducto.getSelectedItemPosition() == 0) {
            iResultado = 1;
        } else if (spinnerCategoriaProducto.getSelectedItemPosition() == 1) {
            iResultado = 2;
        }
        return iResultado;
    }

    public void insertProductoUsuario(Usuario oUser, Producto oProducto) {
        String sUrl = Utils.hosting + "usuario-producto/ins-usuario-producto.php?txtiIdUser="+oUser.getiIdUsuario()+"&txtiIdProducto="+oProducto.getiIdProducto()+"&txtMonedas="+oUser.getiMonedas()+"&txtPrecioProducto="+oProducto.getiPrecio();
        Log.d("ALACID",sUrl);
        Volley.newRequestQueue(Login.getInstance().getApplicationContext()).add(new StringRequest(Request.Method.GET,sUrl,
                s ->{
                    if(s.equals("null")){

                    }else{
                        selectIdUser(sNombreUser);
                    }
                }
                ,volleyError -> {

            Log.d("ALACID",volleyError.getCause().toString());
        }
        ));
    }

    public void popupComprarProducto(Producto oProducto) {


        dialogBuilder = new AlertDialog.Builder(getContext());
        final View popupComprarProducto = getLayoutInflater().inflate(R.layout.popup_productos, null);
        btnSI = (Button) popupComprarProducto.findViewById(R.id.btnSiComprar);
        btnNO = (Button) popupComprarProducto.findViewById(R.id.btnNoComprar);

        dialogBuilder.setView(popupComprarProducto);
        dialog = dialogBuilder.create();
        dialog.setCancelable(false); // Para que tenga que pulsar una opcion si o si (btn atras del movil)
        dialog.setCanceledOnTouchOutside(false); // Para que tenga que pulsar una opcion si o si (en la pantalla)
        dialog.show();

        btnSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIdUser2(sNombreUser,oProducto);
                dialog.dismiss();
            }
        });

        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "No se elimina", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }
    public void cambiarCategoriaProducto() {
        spinnerCategoriaProducto.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spn,
                                               android.view.View v,
                                               int posicion,
                                               long id) {
                        selectIdUser(sNombreUser);
                    }

                    public void onNothingSelected(AdapterView<?> spn) {
                        // En caso de que no haya nada seleccionado.
                    }
                });
    }


    private void mostrarData(Context context, int iSeleccionado) {
        Log.d("Seleccionado", String.valueOf(iSeleccionado));
        int iGridSize=0;
        if(iSeleccionado == 1){
            iGridSize=2;
        }else{
            iGridSize=1;
        }

        Log.d("Size", String.valueOf(iGridSize));
        Rv.setLayoutManager(new GridLayoutManager(context, iGridSize));
        ProductosAdapter adaptador = new ProductosAdapter(context, new ProductosAdapter.productosAdapterInterface() {
            @Override
            public void insProducto(Producto oProducto) {
                popupComprarProducto(oProducto);
            }
        });
        Rv.setAdapter(adaptador);
        Rv.setHasFixedSize(true);
    }
}