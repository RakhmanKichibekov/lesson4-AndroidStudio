package ru.mirea.kichibekov.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;


import java.util.concurrent.TimeUnit;

import ru.mirea.kichibekov.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: "
                        + msg.getData().getString("result"));

            }
        };
        MyLooper myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();

        binding.editTextMirea.setText("Мой номер по списку No 12");
        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("KEY", "Rakhman");
                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);
            }
        });
    }
}