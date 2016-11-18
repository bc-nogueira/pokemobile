package com.example.breno.pokemobile.Service;

import java.util.Random;

/**
 * Created by Breno on 18/11/2016.
 */

public class UtilidadesService {

    public int gerarNumeroAleatorio(int intervalo) {
        Random gerador = new Random();
        return gerador.nextInt(intervalo);
    }
}
