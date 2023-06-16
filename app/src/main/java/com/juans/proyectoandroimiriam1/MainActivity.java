package com.juans.proyectoandroimiriam1;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juans.proyectoandroimiriam.R;
import com.juans.proyectoandroimiriam1.api.PokeapioService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;

    private final String TAG="POKEAPI";

    private RecyclerView recyclerView;
    private listaPokemonAdapter listaPokemonAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //Llamando del reclyclerView
        recyclerView=findViewById(R.id.recycler_View);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL,true);
        recyclerView.setLayoutManager(linearLayoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/pi/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();



    }

    private void obtenerDatos() {
        PokeapioService service = retrofit.create(PokeapioService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon();

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response){
                if (response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta = response.body();
                    List<Pokemon> listaPokemon = pokemonRespuesta.getResults();
                    for(int i = 0; i< listaPokemon.size(); i++) {
                        Pokemon p= listaPokemon.get(i);
                        Log.e(TAG, "pokemon:" + response.body());
                    }

                }
                else{
                    Log.e(TAG,"onResponse:" + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());

            }
        });
    }

    private class listaPokemonAdapter extends RecyclerView.Adapter {
    }
}