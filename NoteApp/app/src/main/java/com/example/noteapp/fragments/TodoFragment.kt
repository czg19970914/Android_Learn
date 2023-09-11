package com.example.noteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.adapters.TodoListAdapter
import com.example.noteapp.dialogs.TodoAddDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mTodoRecyclerView: RecyclerView
    private lateinit var mTodoListAdapter: TodoListAdapter
    private lateinit var mTodoContentAdd: FloatingActionButton
    private lateinit var mTodoAddDialog: TodoAddDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_todo, container, false)
        initRecyclerView(view)

        mTodoContentAdd = view.findViewById(R.id.todo_content_add)
        mTodoContentAdd.setOnClickListener(
            View.OnClickListener {
//                Toast.makeText(this.context, "增加代办", Toast.LENGTH_SHORT).show()

                mTodoAddDialog = TodoAddDialog()
                mTodoAddDialog.show(this.activity!!.supportFragmentManager, "TodoAddDialog")
            }
        )

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TodoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initRecyclerView(view: View){
        mTodoRecyclerView = view.findViewById(R.id.todo_recycler_view)

        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mTodoRecyclerView.layoutManager = linearLayoutManager

        val todoListData = initTodoListData()
        mTodoListAdapter = TodoListAdapter(todoListData, this.context)
        mTodoRecyclerView.adapter = mTodoListAdapter
    }

    private fun initTodoListData(): ArrayList<String> {
        val todoListData = ArrayList<String>()
        todoListData.add("代办1")
        todoListData.add("代办2")
        todoListData.add("代代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3代办3")
        todoListData.add("代办4代办4代办4代办4代办4代办4代办4代办4代办4代办4代办4代办4代办4")
        todoListData.add("代办5")
        todoListData.add("代办6")
        todoListData.add("代办7代办7代办7代办7代办7代办7代办7代办7代办7代办7代办7")
        todoListData.add("代办8")
        todoListData.add("代办9")
        todoListData.add("代办10")

        return todoListData
    }
}