package br.com.qgdostark.comandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.interfaces.ClickRecyclerView;
import br.com.qgdostark.comandroid.model.Mesa;

/**
 * Created by stark on 16/07/17.
 */

public class MesaAdapter extends RecyclerView.Adapter<MesaAdapter.MyViewHolder>{


    private ClickRecyclerView mClickRecyclerView;
    private List<Mesa> mMesa;
    private LayoutInflater mLayoutInflater;
    private Context context;

    public MesaAdapter(Context context, List<Mesa> m, ClickRecyclerView clickRecyclerView) {
        this.mMesa = m;
        this.context = context;
        this.mClickRecyclerView = clickRecyclerView;


    }

    public List<Mesa> getmMesa() {
        return mMesa;
    }

    public void setmMesa(List<Mesa> mMesa) {
        this.mMesa = mMesa;
    }

    public void addListMesa(Mesa mesa, int position){
        mMesa.add(mesa);
        notifyItemInserted(position);
    }

    public void setmClickRecyclerView(ClickRecyclerView mClickRecyclerView) {
        this.mClickRecyclerView = mClickRecyclerView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main_mesa,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtNomeMesa.setText(mMesa.get(position).getNomeMesa());
//        holder.txtDescMesa.setText(mMesa.get(position).getDescricaoMesa());
//        holder.thumbnail.setImageResource(mMesa.get(position).getThumbMesa());
    }

    @Override
    public int getItemCount() {
        return mMesa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, View.OnCreateContextMenuListener {

        public TextView txtNomeMesa;
        public TextView txtDescMesa;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
//
            txtNomeMesa = (TextView) view.findViewById(R.id.main_mesa_pedidos_nome);
////            txtDescMesa = (TextView) view.findViewById(R.id.descricao_mesa);
//            thumbnail = (ImageView) view.findViewById(R.id.main_mesa_pedidos_thumb);
//
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if(mClickRecyclerView != null) {
                mClickRecyclerView.onCustomClick(v, getLayoutPosition(),false);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(mClickRecyclerView != null) {
                mClickRecyclerView.onCustomClick(v, getLayoutPosition(),true);
            }
            return true;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add("Mostrar Resumo do Pedido");
        }

    }
}
