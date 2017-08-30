package br.com.qgdostark.comandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.interfaces.ClickRecyclerView;
import br.com.qgdostark.comandroid.model.Produto;

/**
 * Created by stark on 20/06/17.
 */

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    public static ClickRecyclerView clickRecyclerView;
    private List<Produto> mProduto;
    private LayoutInflater mLayoutInflater;
    private Context context;

    public List<Produto> getmProduto() {
        return mProduto;
    }

    public void setmProduto(List<Produto> mProduto) {
        this.mProduto = mProduto;
    }

    public ProdutoAdapter(Context context, List<Produto> lc, ClickRecyclerView clickRecyclerView){
        this.context = context;
        this.mProduto = lc;
        this.clickRecyclerView = clickRecyclerView;

    }

    //Chamado quando necessario criar uma nova lista
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_produto,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.txtItemCardapio.setText(mProduto.get(position).getNome());
        holder.txtDescCardapio.setText(mProduto.get(position).getDescricao());
        holder.txtValorItemCardapio.setText(String.format("R$ %.2f", mProduto.get(position).getValor()));
    }

    public void addListItem(Produto produto, int position) {
        mProduto.add(produto);
        notifyItemInserted(position);
    }

    //Tamanho da lista
    @Override
    public int getItemCount() {
        return mProduto.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView txtItemCardapio;
        public TextView txtDescCardapio;
        public ImageView thumbnail;
        public TextView txtValorItemCardapio;
        public TextView ItemPopupMenu;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtItemCardapio = (TextView) itemView.findViewById(R.id.txtItemCardapio);
            txtDescCardapio = (TextView) itemView.findViewById(R.id.txtDescCardapio);
//            thumbnail = (ImageView) itemView.findViewById(R.id.img_item_cardapio);
            txtValorItemCardapio = (TextView) itemView.findViewById(R.id.txtValorItemCardapio);
            //ItemPopupMenu = (TextView) itemView.findViewById(R.id.item_cardapio_opcoes);

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

