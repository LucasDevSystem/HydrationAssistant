package com.example.hydrationassistant.modelo;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CopoDataSource {
    private List<Copo> copos;

    public CopoDataSource() {
        copos = new ArrayList<>();
        gerarCopos(5);
    }

    public List<Copo> getCopos() {
        return copos;
    }

   public void gerarCopos(int amount){
        copos.clear();
        //gerar copos
        for (int i = 0; i < amount; i++) {
            copos.add(new Copo());
        }
    }
}
