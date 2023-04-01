package ru.mirea.kichibekov.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import ru.mirea.kichibekov.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.text.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.text.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.text.setText("runn3");
                binding.text.setText("Метод runOnUiThread позволяет выполнить код на главном потоке " +
                        "(UI-потоке).\n Метод postDelayed используется для запуска задачи через определенное" +
                        " время в миллисекундах.\n Метод post используется для добавления задачи в очередь " +
                        "на выполнение на главном потоке.\n Последовательность запуска процессов будет " +
                        "зависеть от того, как они используются в приложении. Если требуется выполнить код " +
                        "на главном потоке, то используется метод runOnUiThread. Если требуется запустить " +
                        "задачу через некоторое время, то используется метод postDelayed. Если требуется " +
                        "добавить задачу в очередь на выполнение на главном потоке, то используется метод post.");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.text.postDelayed(runn3, 2000);
                    binding.text.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();


    }
}