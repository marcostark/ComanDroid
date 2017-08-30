package br.com.qgdostark.comandroid.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.adapter.MesaAdapter;
import br.com.qgdostark.comandroid.dao.MesaDAO;
import br.com.qgdostark.comandroid.interfaces.ClickRecyclerView;
import br.com.qgdostark.comandroid.model.Mesa;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ClickRecyclerView {

    Context context = MainActivity.this;
    private RecyclerView mRecyclerViewMesa;
    private List<Mesa> listMesa;
    private MesaAdapter mMesaAdapter;
    protected ActionBar action;
    private MesaDAO mesaDAO;
    private int cont;
    private EditText numeroMesaInputDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mesaDAO = new MesaDAO(context);

        mRecyclerViewMesa = (RecyclerView) findViewById(R.id.main_recyclerview);
        //registerForContextMenu(mRecyclerViewMesa);

    }

    public void onResume(){
        super.onResume();
        loadComponents();
        setaRecyclerViewMesas();
    }

    public void loadComponents(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater mLayoutInflater = LayoutInflater.from(context);
                View mView = mLayoutInflater.inflate(R.layout.mesa_dialog,null);
                AlertDialog.Builder alertDialogBuilderInput = new AlertDialog.Builder(context);
                alertDialogBuilderInput.setView(mView);

                numeroMesaInputDialog = (EditText) mView.findViewById(R.id.mesa_dialog_input);
                alertDialogBuilderInput
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String aux = numeroMesaInputDialog.getText().toString();
                                if (verificaMesa(aux)){
                                    Mesa mesa = new Mesa(numeroMesaInputDialog.getText().toString(),0);
                                    mesaDAO.salvarOuAtualizar(mesa);
                                } else {
                                    Toast.makeText(context, "Mesa já está aberta", Toast.LENGTH_LONG).show();
                                }
//                              mesaDAO.db.close(); //TODO
                                onResume();
                            }
                        })
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                //Toast.makeText(MainActivity.this, "Criar pedido", Toast.LENGTH_LONG);
                AlertDialog alertDialog = alertDialogBuilderInput.create();
                alertDialog.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void setaRecyclerViewMesas(){
        listMesa = new ArrayList<>();
       //mMesaAdapter = new MesaAdapter(this, mesaDAO.getProdutosAll(), this);
        mMesaAdapter = new MesaAdapter(this, mesaDAO.getOpenMesa(), this);

        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),4);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewMesa.setLayoutManager(mLayoutManager);
        mRecyclerViewMesa.setHasFixedSize(true);
        mMesaAdapter.setmClickRecyclerView(this);
        mRecyclerViewMesa.setAdapter(mMesaAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_produtos) {
            Intent intent = new Intent(this, CategoriaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_caixa) {
            Intent intent = new Intent(this, CaixaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_relatorios) {
            Intent intent = new Intent(this, RelatoriosActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, SobreActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*Criar a classe pedido aqui*/
    @Override
    public void onCustomClick(View view, int position, boolean isLongClick) {

        final Mesa mesa = mMesaAdapter.getmMesa().get(position);

        if(isLongClick){
            Toast.makeText(this, "Mesa:" +mesa.getNomeMesa().toString(), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, ListaPedidosActivity.class);
            intent.putExtra("mesa", mesa);
            startActivity(intent);
        }
    }

    private boolean verificaMesa(String mesa) {
        Mesa mesaVerifica = mesaDAO.getByName(mesa);
        Log.d("Mesa", mesaVerifica.toString());
        if (mesaVerifica.getNomeMesa() == null && mesaVerifica.getStatus() == 0){
            return true;
        } else {
            return false;
        }
    }
}
