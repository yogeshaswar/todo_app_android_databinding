package com.yogeshaswar.productivitymvvmdatabinding.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yogeshaswar.productivitymvvmdatabinding.models.Task;

import java.util.List;

@Dao
public interface TaskDao {
    // methods
    @Insert
    void insert(Task task);
    @Delete
    void delete(Task task);
    @Query("SELECT * FROM task_table")
    List<Task>getTasks();
}
