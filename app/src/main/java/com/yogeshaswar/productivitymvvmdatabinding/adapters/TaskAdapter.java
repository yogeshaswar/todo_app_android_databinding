package com.yogeshaswar.productivitymvvmdatabinding.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yogeshaswar.productivitymvvmdatabinding.R;
import com.yogeshaswar.productivitymvvmdatabinding.databinding.TaskItemCardBinding;
import com.yogeshaswar.productivitymvvmdatabinding.models.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    List<Task> list;

    public TaskAdapter(List<Task> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View taskView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_card, parent, false);

        TaskItemCardBinding taskItemCardBinding = DataBindingUtil.inflate(
          LayoutInflater.from(parent.getContext()),
          R.layout.task_item_card,
          parent,
          false
        );
        return new TaskViewHolder(taskItemCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // task
//        holder.task.setText(list.get(position).getTask());
//        holder.time.setText(list.get(position).getTimeToComplete());
        holder.taskItemCardBinding.setTaskItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        if(!(list.isEmpty())){
            return  list.size();
        } else {
            return 0;
        }
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
//        TextView task, time;
        TaskItemCardBinding taskItemCardBinding; // step 1
        public TaskViewHolder(@NonNull TaskItemCardBinding taskItemCardBinding) { // step 2 - parameter
            super(taskItemCardBinding.getRoot()); // step 3
//            task = itemView.findViewById(R.id.tv_show_task);
//            time = itemView.findViewById(R.id.tv_show_time);
            this.taskItemCardBinding = taskItemCardBinding; // step 3
        }
    }

}
