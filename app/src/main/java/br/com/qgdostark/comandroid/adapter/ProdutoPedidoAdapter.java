package br.com.qgdostark.comandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.interfaces.ClickRecyclerView;
import br.com.qgdostark.comandroid.model.ProdutoPedido;

/**
 * Created by stark on 16/07/17.
 */

public class ProdutoPedidoAdapter extends RecyclerView.Adapter<ProdutoPedidoAdapter.MyViewHolder>{

    public static ClickRecyclerView clickRecyclerView;

    public List<ProdutoPedido> getmProdutoPedido() {
        return mProdutoPedido;
    }

    private List<ProdutoPedido> mProdutoPedido;
    private LayoutInflater mLayoutInflater;
    private Context context;

    public ProdutoPedidoAdapter(Context context, List<ProdutoPedido> mProdutoPedido, ClickRecyclerView clickRecyclerView){
        this.mProdutoPedido = mProdutoPedido;
        this.context = context;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.clickRecyclerView = clickRecyclerView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_produto_pedido,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.descricao.setText(mProdutoPedido.get(position).getNome());
        holder.valor.setText(String.format("R$ %.2f",+ mProdutoPedido.get(position).getSubtotal()));
        holder.quantidade.setText("Quant. " + mProdutoPedido.get(position).getQuantidade());
    }

    @Override
    public int getItemCount() {return mProdutoPedido.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private TextView codigo;
        private TextView descricao;
        private TextView valor;
        private TextView quantidade;
        private TextView btnDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            descricao = (TextView) itemView.findViewById(R.id.lista_pedido_nome_produto);
            valor = (TextView) itemView.findViewById(R.id.lista_pedido_valor_produto);
            quantidade = (TextView) itemView.findViewById(R.id.lista_pedido_quantidade_produto);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickRecyclerView != null) {
                clickRecyclerView.onCustomClick(v, getLayoutPosition(),false);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(clickRecyclerView != null) {
                clickRecyclerView.onCustomClick(v, getLayoutPosition(),true);
            }
            return true;
        }
    }
}
