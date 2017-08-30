package br.com.qgdostark.comandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.qgdostark.comandroid.R;
import br.com.qgdostark.comandroid.interfaces.ClickRecyclerView;
import br.com.qgdostark.comandroid.model.Categoria;

import static android.content.ContentValues.TAG;

/**
 * Created by stark on 20/06/17.
 */

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.MyViewHolder> {

    public static ClickRecyclerView clickRecyclerView;
    private List<Categoria> mCategoria;
    private LayoutInflater mLayoutInflater;
    private Context context;

    public CategoriaAdapter(Context context, List<Categoria> lc, ClickRecyclerView clickRecyclerView){
        this.mCategoria = lc;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.clickRecyclerView = clickRecyclerView;
        this.context = context;

        Log.d("Id", String.valueOf(mCategoria.get(0).getId()));
        Log.d("Nome", mCategoria.get(0).getNome());
        Log.d("Descricao", mCategoria.get(0).getDescricao());
    }

    //Chamado quando necessario criar uma nova lista
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.activity_categoria, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        int id = context.getResources().getIdentifier("br.com.qgdostark.comandroid:drawable/"+mCategoria.get(position).getThumb(),null,null);
        holder.thumbnail.setImageResource(id);
        holder.txtCategCardapio.setText(mCategoria.get(position).getNome());
        holder.descricao.setText(mCategoria.get(position).getDescricao());
    }

    public static void setClickRecyclerView(ClickRecyclerView clickRecyclerView) {
        CategoriaAdapter.clickRecyclerView = clickRecyclerView;
    }

    public void addListItem(Categoria categoria, int position) {
        mCategoria.add(categoria);
        notifyItemInserted(position);
    }

    //Tamanho da lista
    @Override
    public int getItemCount() {
        return mCategoria.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, View.OnCreateContextMenuListener{
        public TextView txtCategCardapio;
        public ImageView thumbnail;
        public TextView descricao;
        public TextView txtOpcoes;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtCategCardapio = (TextView) itemView.findViewById(R.id.txtCategCardapio);
            thumbnail = (ImageView) itemView.findViewById(R.id.img_cat_cardapio);
            descricao = (TextView) itemView.findViewById(R.id.txtDescricao);
            txtOpcoes = (TextView) itemView.findViewById(R.id.txtOpcoesDigito);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
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

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem menuEditar = menu.add("Edit");
            menuEditar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Log.d(TAG, "onMenuItemClick: Foi...");
                    return false;
                }
            });
            menu.add("Copy");
            menu.add("Delete");
        }
    }

}

