package br.com.qgdostark.comandroid.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.adapter.ProdutoPedidoAdapter;
import br.com.qgdostark.comandroid.dao.MesaDAO;
import br.com.qgdostark.comandroid.dao.PedidoDAO;
import br.com.qgdostark.comandroid.dao.ProdutoPedidoDAO;
import br.com.qgdostark.comandroid.interfaces.ClickRecyclerView;
import br.com.qgdostark.comandroid.model.Mesa;
import br.com.qgdostark.comandroid.model.Pedido;
import br.com.qgdostark.comandroid.model.ProdutoPedido;

public class ListaPedidosActivity extends AppCompatActivity implements ClickRecyclerView {
    /*Classe reponsavél por gravar o pedido no banco de dados
    * Gravando os dados da mesa, produtos e demais atributos*/

    Context context = ListaPedidosActivity.this;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinerManager;
    private List<ProdutoPedido> mProdutoPedido = new ArrayList();
    protected ActionBar action;

    private Mesa mesaSerial;
    private Pedido pedido;
    private PedidoDAO pedidoDAO;

    private double valorTotalAux;
    private boolean isPrinter;
    //private TextView titleValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);
        this.action = getSupportActionBar();
        this.action.setDisplayHomeAsUpEnabled(true);

        mesaSerial = (Mesa) getIntent().getSerializableExtra("mesa");

        setTitle("Mesa: " + mesaSerial.getNomeMesa().toString());

        pedidoDAO = new PedidoDAO(this);
        verificaPedido(Integer.valueOf(mesaSerial.getId()));
        buscarPedido(mesaSerial.getId());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.lista_pedidos_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaPedidosActivity.this, CategoriaActivity.class);
                intent.putExtra("pedido", pedido);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecyclerView();
        //titleValor = (TextView)findViewById(R.id.valor_total_pedido);
    }

    private void loadRecyclerView() {

        ProdutoPedidoDAO produtoPedidoDAO = new ProdutoPedidoDAO(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list_generico);
        mRecyclerView.setHasFixedSize(true); //Tamanho do recycler não irá mudar
        mLinerManager = new LinearLayoutManager(this);
        mLinerManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinerManager);

        //mProdutoPedido = produtoPedidoDAO.getProdutoPedidoAll();
        mProdutoPedido = produtoPedidoDAO.getByMesaAll(String.valueOf(pedido.getId()));
        ProdutoPedidoAdapter adapter = new ProdutoPedidoAdapter(this, mProdutoPedido, this);
        mRecyclerView.setAdapter(adapter);

        /*Provisorio*/
        valorTotalAux = 0.0;
        for (int i = 0; i < mProdutoPedido.size(); i++){
            valorTotalAux += mProdutoPedido.get(i).getSubtotal();
        }

        TextView valorTotal = (TextView) findViewById(R.id.lista_pedido_valor_total_produtos);
        valorTotal.setText(String.format("R$ %.2f", valorTotalAux));

    }

    @Override
    public void onCustomClick(View view, int position, boolean isLongClick) {
        final ProdutoPedido produtoPedido = mProdutoPedido.get(position);
        if (isLongClick) {
            PopupMenu popup = new PopupMenu(context, view);
            popup.inflate(R.menu.item_cardapio_popup_menu);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {

                        case R.id.item_cardapio_popup_menu_excluir:
                            ProdutoPedidoDAO produtoPedidoDAO = new ProdutoPedidoDAO(context);
                            produtoPedidoDAO.deleteItem(produtoPedido.getId().toString());
                            onResume();
                            break;

                        case R.id.item_cardapio_popup_menu_editar:
                            //TODO Editar quantidade dos produtos
                            Toast.makeText(ListaPedidosActivity.this, "Editar Item ", Toast.LENGTH_LONG).show();
                            //Intent intent = new Intent(context, FormPedidoActivity.class);
                            //intent.putExtra();
                            //startActivity(intent);
                            break;
                    }
                    return false;
                }
            });
            popup.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_produto_pedido, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Pedido imprimirPedido = pedido;
        switch (item.getItemId()){

            case R.id.menu_imprimir_pedido:

                if(mProdutoPedido.isEmpty()){
                    Toast.makeText(context, "Você não possui itens na lista", Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(ListaPedidosActivity.this);
                    //View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
                    mBuilder.setTitle("Deseja enviar pedido para produção ?");
                    mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            isPrinter = true;
                            imprimirPedido.setPrinter(1);
                            pedidoDAO.atualiza(imprimirPedido);
                            Toast.makeText(context, "Pedido enviado com sucesso", Toast.LENGTH_LONG).show();
                            //onResume();
                        }
                    });
                    mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();
                }

                break;

            case R.id.menu_fechar_conta:

                if(imprimirPedido.isPrinter() == 1){

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(ListaPedidosActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.fechar_pedido, null);
                   // mBuilder.setTitle("Fechar Conta");

                    final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListaPedidosActivity.this,
                    android.R.layout.simple_spinner_item,
                    getResources().getStringArray(R.array.select_pagamento));

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner.setAdapter(adapter);

                    AdapterView.OnItemSelectedListener select = new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    };
                    mSpinner.setOnItemSelectedListener(select);

                    mBuilder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                                Pedido fecharPedido = pedido;
                                MesaDAO mesaDAO = new MesaDAO(context);

                                fecharPedido.setFaturado(1);
                                fecharPedido.setValorTotal(valorTotalAux);
                                mesaSerial.setStatus(1);

                                pedidoDAO.atualiza(fecharPedido);
                                mesaDAO.atualiza(mesaSerial);
                                Toast.makeText(context, "Pedido fechado com sucesso", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(context, MainActivity.class));
                                dialogInterface.dismiss();

                        }
                    });
                    mBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    mBuilder.setView(mView);
                    final TextView titleValor = (TextView)mView.findViewById(R.id.valor_total_pedido);
                    titleValor.setText(String.valueOf(String.format(" R$ %.2f",valorTotalAux)));
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();

                } else {
                    Toast.makeText(context, "Ops! Você não enviou o pedido para produção", Toast.LENGTH_LONG).show();
                }

                break;
            case android.R.id.home :
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*Executado quando o pedido for enviado para produção*/
    public void salvarPedido() {

        String dataDaVenda;
        double valorTotal = 0;
        int isPrinter = 0;
        int faturado = 0;

        long date = System.currentTimeMillis();
        SimpleDateFormat data = new SimpleDateFormat("d/M/yyyy");
        dataDaVenda = data.format(date);

        Pedido salvarPedido = new Pedido(dataDaVenda, valorTotal, mesaSerial.getId(),isPrinter,faturado);
        pedidoDAO.salvarOuAtualizar(salvarPedido);
    }

    public void buscarPedido(Integer mesa_id) {
        pedido = pedidoDAO.getByMesa(String.valueOf(mesa_id));
    }

    public void verificaPedido(Integer mesa_id) {
        Pedido pedidoVerifica = pedidoDAO.getByMesa(String.valueOf(mesa_id));
        if (pedidoVerifica.getMesa_id() == mesa_id) {
            //buscarPedido(mesaSerial.getId());
            Log.d("Pedido", "Não Cria Pedido");

        } else {
            salvarPedido();
            Log.d("Pedido", "Cria Pedido");
        }
    }

}


