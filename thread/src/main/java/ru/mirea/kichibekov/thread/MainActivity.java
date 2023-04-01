package ru.mirea.kichibekov.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import ru.mirea.kichibekov.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    int counter = 0;
    private ActivityMainBinding binding;
    Thread mainThread = Thread.currentThread();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textView.setText("Имя текущего потока: " + mainThread.getName());
        // Меняем имя и выводим в текстовом поле
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БСБО-03-20, НОМЕР ПО СПИСКУ: 12, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Легенда");
        binding.textView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));
        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());

        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток No %d студентом " +
                                "группы No %s номер по списку No %d ", numberThread, "БСБО-03-20", 12));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток No " + numberThread);
                        }
                    }
                }).start();
                Float num1 = Float.parseFloat(binding.editTextCoundDays.getText().toString());
                Float num2 = Float.parseFloat(binding.editTextCountLessons.getText().toString());
                binding.textView2.setText("Среднее количество пар в день: " + num2 / num1);
            }
        });
    }
}