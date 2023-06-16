package com.juans.proyectoandroimiriam1.api;

import com.juans.proyectoandroimiriam1.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeapioService {

    @GET("pokemon")
    Call <PokemonRespuesta> obtenerListaPokemon();
}
