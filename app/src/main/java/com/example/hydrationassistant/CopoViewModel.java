package com.example.hydrationassistant;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.example.hydrationassistant.modelo.Copo;

public class CopoViewModel {
    private Copo copo;
    private MutableLiveData<Integer> copo_img;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CopoViewModel(Copo copo) {
        this.copo = copo;
        copo_img = new MutableLiveData<>();
        atualizarCopo();
    }

    private MutableLiveData<Boolean> isSelected = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> getIsSelected() {
        return isSelected;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void atualizarCopo() {
        if (copo.isFull()) {
            copo_img.setValue(R.drawable.copo_cheio);
            isSelected.setValue(true);
        } else {
            copo_img.setValue(R.drawable.copo_vazio);
            isSelected.setValue(false);
        }
    }

    public Copo getCopo() {
        return copo;
    }

    public MutableLiveData<Integer> geCopoImg() {
        return copo_img;
    }
}
