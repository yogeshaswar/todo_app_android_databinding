package com.yogeshaswar.productivitymvvmdatabinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.yogeshaswar.productivitymvvmdatabinding.adapters.TaskAdapter;
import com.yogeshaswar.productivitymvvmdatabinding.databinding.ActivityMainBinding;
import com.yogeshaswar.productivitymvvmdatabinding.models.Task;
import com.yogeshaswar.productivitymvvmdatabinding.vm.MAVM;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MAVM mavm;
    ActivityMainBinding mainBinding;
    RecyclerView recyclerView;
    List<Task> newTasksList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // data binding
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main); // step 1
        String enteredTask = mainBinding.etTask.getText().toString();
        String enteredTime = mainBinding.etExpectedTime.getText().toString();
        Task newTask = new Task(enteredTask, enteredTime);
        mainBinding.setTask(newTask); // step 2
        // initiate view model
        mavm = new ViewModelProvider(this).get(MAVM.class);
        // get all tasks
        newTasksList = getAllTasksFromDB();
        // click handler add
        MAClickHandler handler = new MAClickHandler();
        mainBinding.setClickHandler(handler);
        // recycler view
        recyclerView = mainBinding.rvTasks;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadRecyclerView(newTasksList);
        // delete on swipe functionality
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Task task = getAllTasksFromDB().get(viewHolder.getAdapterPosition()); // task tobe deleted
                mavm.deletTask(task);
                loadRecyclerView(getAllTasksFromDB());
                Toast.makeText(MainActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void loadRecyclerView(List<Task> list) {
        // adapter
        TaskAdapter taskAdapter = new TaskAdapter(list);
        recyclerView.setAdapter(taskAdapter);
    }

    private List<Task> getAllTasksFromDB() {
        List<Task> tasksList = mavm.getAllTasks();
        return tasksList;
    }

    private void addTaskToDB() {
        // create task
        String enteredTask = mainBinding.etTask.getText().toString();
        String enteredTime = mainBinding.etExpectedTime.getText().toString();
        Task newTask = new Task(enteredTask, enteredTime);
        // add to database
        mavm.addNewTask(newTask);
        mainBinding.etTask.setText("");
        mainBinding.etExpectedTime.setText("");
        loadRecyclerView(getAllTasksFromDB());
    }

    // data binding click handler inner class
    public class MAClickHandler {
        // methods to handle clicks
        public void addTaskBtnClick(View view) {
            addTaskToDB();
//            String edtText = mainBinding.etTask.getText().toString();
//            Toast.makeText(MainActivity.this, "entered task: "+ edtText, Toast.LENGTH_SHORT).show();
        }
        public void searchBtnClick(View view) {
//            Toast.makeText(MainActivity.this, "search clicked", Toast.LENGTH_SHORT).show();
            getAllTasksFromDB();
        }
        public void exitBtnClick(View view) {
            Toast.makeText(MainActivity.this, "search clicked", Toast.LENGTH_SHORT).show();
        }
    }
}