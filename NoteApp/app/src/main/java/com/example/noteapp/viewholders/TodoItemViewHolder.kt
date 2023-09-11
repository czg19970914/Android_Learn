package com.example.noteapp.viewholders

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.noteapp.R

class TodoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mTodoCompleteCheckBox: CheckBox
    val mTodoItemText: TextView

    init {
        mTodoCompleteCheckBox = itemView.findViewById(R.id.todo_complete_check_box)
        mTodoItemText = itemView.findViewById(R.id.todo_item_text)
    }
}