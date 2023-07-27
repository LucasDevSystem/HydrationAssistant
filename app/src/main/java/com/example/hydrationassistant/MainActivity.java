package com.example.hydrationassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;

import com.example.hydrationassistant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        CoposViewModel vm = new ViewModelProvider(this).get(CoposViewModel.class);
        CoposAdapter adapter = new CoposAdapter(this , vm);

        binding.setVm(vm);
        binding.setAdapter(adapter);
        getSupportActionBar().hide();
    }
}