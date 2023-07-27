package com.example.hydrationassistant.modelo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.hydrationassistant.R;

public class Copo {
    private boolean isFull;
    private int copo_img;

    public Copo() {
        this.isFull = false;
        this.copo_img = R.drawable.copo_vazio;
    }

    public Copo(boolean isFull, int copo_img) {
        this.isFull = isFull;
        this.copo_img = copo_img;
    }

    public void atualizarCopo() {
        if (!this.isFull) {
            this.copo_img = R.drawable.copo_cheio;
            this.isFull = true;
        } else {
            this.copo_img = R.drawable.copo_vazio;
            this.isFull = false;
        }
    }

    public boolean isFull() {
        return isFull;
    }
}
