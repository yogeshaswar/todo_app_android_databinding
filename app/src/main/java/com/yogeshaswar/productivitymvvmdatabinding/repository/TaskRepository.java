package com.yogeshaswar.productivitymvvmdatabinding.repository;

import android.app.Application;

import com.yogeshaswar.productivitymvvmdatabinding.db.AppDatabase;
import com.yogeshaswar.productivitymvvmdatabinding.db.TaskDao;
import com.yogeshaswar.productivitymvvmdatabinding.models.Task;

import java.util.List;

public class TaskRepository {
    Application application;
    TaskDao taskDao;
    List<Task> tasks;
    public TaskRepository(Application application) {
        this.application = application;
        AppDatabase appDatabase = AppDatabase.getAppDatabase(application);
        taskDao = appDatabase.taskDAO();
    }
    // methods
    public void addTask(Task task){
        taskDao.insert(task);
    }
    public void deleteTask(Task task){
        taskDao.delete(task);
    }
    public List<Task> getAllTasks(){
        tasks = taskDao.getTasks();
        return tasks;
    }
}
