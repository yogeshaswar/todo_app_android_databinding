package com.yogeshaswar.productivitymvvmdatabinding.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.yogeshaswar.productivitymvvmdatabinding.models.Task;
import com.yogeshaswar.productivitymvvmdatabinding.repository.TaskRepository;

import java.util.List;

public class MAVM extends AndroidViewModel {
    TaskRepository taskRepository;
    List<Task> allTasks;
    public MAVM(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
    }
    // methods
    public void addNewTask(Task task){
        taskRepository.addTask(task);
    }
    public void deletTask(Task task){
        taskRepository.deleteTask(task);
    }
    public List<Task> getAllTasks(){
        allTasks = taskRepository.getAllTasks();
        return allTasks;
    }

}
