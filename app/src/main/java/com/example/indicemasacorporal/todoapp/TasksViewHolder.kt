package com.example.indicemasacorporal.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.indicemasacorporal.R

class TasksViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask:TextView = view.findViewById(R.id.tvTasks)
    private val cbTask:CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task){

        if (task.isSelected){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        tvTask.text = task.name
        cbTask.isChecked = task.isSelected

        val color = when (task.category){
            TaskCategory.Business -> R.color.business_category
            TaskCategory.Personal -> R.color.personal_category
            TaskCategory.Other -> R.color.other_category
        }
        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color)
        )
    }
}