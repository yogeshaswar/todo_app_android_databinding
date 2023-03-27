package com.yogeshaswar.productivitymvvmdatabinding.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.yogeshaswar.productivitymvvmdatabinding.BR;

@Entity(tableName = "task_table")
public class Task extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "task_column")
    String task;
    @ColumnInfo(name = "required_time")
    String timeToComplete;
    @Ignore
    public Task() {
    }
    @Ignore
    public Task(String task, String timeToComplete) {
        this.task = task;
        this.timeToComplete = timeToComplete;
    }

    public Task(int id, String task, String timeToComplete) {
        this.id = id;
        this.task = task;
        this.timeToComplete = timeToComplete;
    }
    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
        notifyPropertyChanged(BR.task);
    }
    @Bindable
    public String getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(String timeToComplete) {
        this.timeToComplete = timeToComplete;
        notifyPropertyChanged(BR.timeToComplete);
    }
}
