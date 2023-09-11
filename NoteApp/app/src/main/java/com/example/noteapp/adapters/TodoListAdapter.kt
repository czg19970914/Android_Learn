package com.example.noteapp.adapters

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.viewholders.TodoItemViewHolder

import com.example.noteapp.R

class TodoListAdapter(todoDataSet: ArrayList<String>, context: Context?) : RecyclerView.Adapter<TodoItemViewHolder>() {
    private val mTodoDataSet: ArrayList<String>
    private val mContext: Context

    init {
        mTodoDataSet = todoDataSet
        mContext = context!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)

        // item的view设置圆角
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.cornerRadius = mContext.resources.getDimensionPixelSize(R.dimen.todo_item_radius).toFloat()
        gradientDrawable.setColor(mContext.resources.getColor(R.color.white))
        view.background = gradientDrawable

        return TodoItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mTodoDataSet.size
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.mTodoItemText.text = mTodoDataSet[position]
    }

}