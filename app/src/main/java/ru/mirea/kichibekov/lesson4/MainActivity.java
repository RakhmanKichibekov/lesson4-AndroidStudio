package ru.mirea.kichibekov.lesson4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.mirea.kichibekov.lesson4.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textViewMirea.setText("Мой номер по списку No 12");
        binding.buttonMirea3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.class.getSimpleName(),"onClickListener");
            }
        });
    }
}