package com.example.noteapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.noteapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val mainBottomNav: BottomNavigationView by lazy {
        findViewById<BottomNavigationView>(R.id.main_bottom_nav)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mainBottomNav.setOnItemSelectedListener { item ->
//            when (item.itemId){
//                R.id.note_item -> {
//                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_todo_note)
//                }
//                R.id.todo_item -> {
//                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_note_to_todo)
//                }
//            }
//            true
//        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(mainBottomNav, navHostFragment.navController)
    }
}