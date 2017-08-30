package br.com.qgdostark.comandroid.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.dao.MesaDAO;
import br.com.qgdostark.comandroid.dao.PedidoDAO;
import br.com.qgdostark.comandroid.model.Mesa;
import br.com.qgdostark.comandroid.model.Pedido;

public class CaixaActivity extends AppCompatActivity {

    private TextView dataAtual;
    private TextView pedidosAbertos;
    private TextView pedidosFechados;
    private TextView valorTotal;
    private String strData;

    protected ActionBar action;
    private SwitchCompat simpleSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caixa);
        this.action = getSupportActionBar();
        this.action.setTitle("Caixa");
        this.action.setDisplayHomeAsUpEnabled(true);

        loadComponents();
        setValues();
    }

    public void loadComponents(){
        dataAtual = (TextView) findViewById(R.id.caixa_data_atual);
        pedidosAbertos = (TextView) findViewById(R.id.caixa_pedidos_abertos);
        pedidosFechados = (TextView) findViewById(R.id.caixa_pedidos_fechados);
        valorTotal = (TextView) findViewById(R.id.caixa_valortotal);
        simpleSwitch = (SwitchCompat) findViewById(R.id.simpleswitch ); // iniciar Switch
    }

    public void setValues (){
        MesaDAO mesaDAO = new MesaDAO(this);
        PedidoDAO pedidoDAO = new PedidoDAO(this);
        double aux = 0;
        int qntPedidos;
        int qntMesas;
        long date = System.currentTimeMillis();
        SimpleDateFormat data = new SimpleDateFormat("d/M/yyyy");
        strData = data.format(date);


        List<Pedido> pedidos = pedidoDAO.getPedidoByDate(strData);
        List<Mesa> mesasAbertas = mesaDAO.getOpenMesa();
        List<Pedido> pedidosFechadosDia = pedidoDAO.getPedidoByDate(strData);



        for(int i = 0; i < pedidos.size(); i++){
            aux = aux + pedidos.get(i).getValorTotal();
        }

        qntPedidos = pedidos.size();
        qntMesas = mesasAbertas.size();

        pedidosAbertos.setText(String.valueOf(qntMesas));
        pedidosFechados.setText(String.valueOf(pedidosFechadosDia.size()));

        //TODO implementar o valor apenas dos pedidos fechados.
        valorTotal.setText(String.valueOf(String.format("R$ %.2f",aux)));
        dataAtual.setText(strData);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
