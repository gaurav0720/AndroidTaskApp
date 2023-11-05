package com.example.fetch_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.application.R;

public class MainActivity extends AppCompatActivity {

    private Button task_1, task_2, task_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task_1 = findViewById(R.id.task_one);
        task_2 = findViewById(R.id.task_two);
        task_3 = findViewById(R.id.task_three);

        task_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forwardToTaskOne = new Intent(MainActivity.this, PerformingTask.class);
                forwardToTaskOne.putExtra("Task Number", "Task One");
                startActivity(forwardToTaskOne);
            }
        });

        task_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forwardToTaskTwo = new Intent(MainActivity.this, PerformingTask.class);
                forwardToTaskTwo.putExtra("Task Number", "Task Two");
                startActivity(forwardToTaskTwo);
            }
        });

        task_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forwardToTaskThree = new Intent(MainActivity.this, PerformingTask.class);
                forwardToTaskThree.putExtra("Task Number", "Task Three");
                startActivity(forwardToTaskThree);
            }
        });

    }
}