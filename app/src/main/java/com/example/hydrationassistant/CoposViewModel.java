package com.example.hydrationassistant;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hydrationassistant.modelo.Copo;
import com.example.hydrationassistant.modelo.CopoDataSource;

import java.util.ArrayList;
import java.util.List;


public class CoposViewModel extends ViewModel {
    private MutableLiveData<String> peso = new MutableLiveData<String>("");
    private MutableLiveData<List<Copo>> copos;
    private MutableLiveData<String> totalWater = new MutableLiveData<String>("1,5L");
    private MutableLiveData<String> remainingWater = new MutableLiveData<String>("faltando 350ml");
    private MutableLiveData<Integer> progress = new MutableLiveData<>(0);
    private int selectedCount = 0;

    public CoposViewModel() {
        CopoDataSource dataSource = new CopoDataSource();
        copos = new MutableLiveData<>(dataSource.getCopos());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void update() {
        CopoDataSource dataSource = new CopoDataSource();

        dataSource.gerarCopos(copos.getValue().size());

        this.copos.setValue(dataSource.getCopos());
        this.updateInfo();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onCalc (){
        String pesoStr = this.peso.getValue();
        if(isNumeric(pesoStr) == false)  return;

        int peso = Integer.parseInt(pesoStr);
        double deveBeber = peso * 35;  // ml
        double liters = deveBeber / 1000;
        int copos = (int) Math.round(Math.ceil(deveBeber / 150));

        CopoDataSource dataSource = new CopoDataSource();
        dataSource.gerarCopos(copos);
        // formata o total para no maximo 2 casas decimais
        this.totalWater.setValue(String.format("%.1f", liters) + "L");
        this.copos.setValue(dataSource.getCopos());
        this.updateInfo();
    }

    // atualiza a quantidade que falta de agua
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateInfo(){
        long drinked = this.copos.getValue().stream().filter(copo -> copo.isFull() == true).count();
        double total = this.copos.getValue().size();

        double progress = Math.round((drinked / total) * 100);
        double remaining = (total * 350) -  (drinked * 350);

        this.progress.setValue((int) progress);
        this.remainingWater.setValue("faltando " + String.format("%.0f",remaining) + "ml");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onItemClick(int position) {
        ArrayList<Copo> newList = new ArrayList<Copo>(this.copos.getValue());
        Copo newCopo = newList.get(position);

        newCopo.atualizarCopo();
        newList.set(position, newCopo);

        this.copos.setValue(newList);
        this.updateInfo();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void setCopos(MutableLiveData<List<Copo>> copos) {
        this.copos = copos;
    }
    public MutableLiveData<List<Copo>> getCopos() {
        return copos;
    }
    public MutableLiveData<String> getPeso() {
        return peso;
    }
    public MutableLiveData<String> getTotalWater() { return totalWater; }
    public MutableLiveData<String> getRemainingWater() {
        return remainingWater;
    }
    public MutableLiveData<Integer> getProgress() { return progress; }
}
