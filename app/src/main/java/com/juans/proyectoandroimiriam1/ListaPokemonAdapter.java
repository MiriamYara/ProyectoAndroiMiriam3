package com.juans.proyectoandroimiriam1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juans.proyectoandroimiriam.R;

import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;

    private ArrayList<Pokemon> dataset;

    public ListaPokemonAdapter(ArrayList<Pokemon> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;


    }

    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoImageView;

        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
        }
    }
    public ListaPokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewTipe) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Pokemon p=dataset.get(position);
        holder.name.setText(p.getName()); //envio del nombre al texto
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon){
        dataset.addAll(listaPokemon);
        notifyDataSetChanged(); // reseteo de la vista
    }
}

