package com.example.tasksyncapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.tasksyncapp.Model.TodoModel;
import com.example.tasksyncapp.Model.Utils.DatabaseHandler;
import com.example.tasksyncapp.adapter.todoadapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivity_new {
    private DatabaseHandler db;
    private RecyclerView taskRecyclerView;
    private todoadapter tasksAdapter;

    private List<TodoModel> taskList;
    private FloatingActionButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar();

        // Initialize the DatabaseHandler
        db = new DatabaseHandler(this);

        taskList = new ArrayList<>();

        taskRecyclerView = findViewById(R.id.tasksRecyclerview);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Move the initialization of taskRecyclerView before attaching the ItemTouchHelper
        tasksAdapter = new todoadapter(db, this);
        taskRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItem(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(taskRecyclerView);

        FAB = findViewById(R.id.FAB);

        TodoModel task = new TodoModel();
        task.setTask("This is a test task");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        tasksAdapter.setTasks(taskList);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }
}
