package com.example.noteapp.activities

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.example.noteapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var mProcessX: Float = 0.0F
    private var mProcessY: Float = 0.0F

    private val mMainToolBar: Toolbar by lazy {
        findViewById(R.id.main_tool_bar)
    }
    private val mMainBottomNav: BottomNavigationView by lazy {
        findViewById(R.id.main_bottom_nav)
    }
    private val mToolbarTitle: TextView by lazy {
        findViewById(R.id.toolbar_title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainToolBar.inflateMenu(R.menu.main_tool_bar_menu)
        mMainToolBar.setOnMenuItemClickListener(
            Toolbar.OnMenuItemClickListener {
                item: MenuItem? ->
                Toast.makeText(this, "fdffdfd", Toast.LENGTH_SHORT).show()
                true
            }
        )

        mMainBottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.note_fragment -> {
                    mToolbarTitle.setText(R.string.tool_bar_note_title)
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_todo_note)
                }
                R.id.todo_fragment -> {
                    mToolbarTitle.setText(R.string.tool_bar_todo_title)
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_note_to_todo)
                }
            }
            true
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            val toolbarTitleLP: ViewGroup.MarginLayoutParams = mToolbarTitle.layoutParams as ViewGroup.MarginLayoutParams

            val toolBarMaxMarginTopPx = resources.getDimensionPixelSize(R.dimen.tool_bar_max_margin_top)
            val toolBarMinMarginTopPx = resources.getDimensionPixelSize(R.dimen.tool_bar_min_margin_top)

            val toolBarMaxTextSizePx = resources.getDimensionPixelSize(R.dimen.tool_bar_title_max_size)
            val toolBarMinTextSizePx = resources.getDimensionPixelSize(R.dimen.tool_bar_title_min_size)

            when (event.action){
                MotionEvent.ACTION_DOWN -> {
                    mProcessX = event.x
                    mProcessY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    val toolbarTitleMarginTop = toolbarTitleLP.topMargin
                    val currMoveY = (mProcessY - event.y)
                    val toolBarMarginTopOffset = toolbarTitleMarginTop - currMoveY

                    if(toolBarMarginTopOffset in
                        toolBarMinMarginTopPx.toDouble()..toolBarMaxMarginTopPx.toDouble()){

                        // 使用动画修改marginTop,达到手指上滑动态修改toolbar的效果
                        val valueAnimator =
                            ValueAnimator.ofInt(toolbarTitleMarginTop, toolBarMarginTopOffset.toInt())
                        valueAnimator.setDuration(250)
                        valueAnimator.addUpdateListener {
                            toolbarTitleLP.topMargin = valueAnimator.animatedValue as Int
                            mToolbarTitle.layoutParams = toolbarTitleLP
                        }
                        valueAnimator.start()

                        // 修改字体大小
                        val ration =
                            (toolBarMarginTopOffset - toolBarMinMarginTopPx) /
                                    (toolBarMaxMarginTopPx - toolBarMinMarginTopPx).toFloat()
                        val currTextSize = toolBarMaxTextSizePx * ration + toolBarMinTextSizePx * (1 - ration)
                        mToolbarTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, currTextSize)
                    }

                }
                MotionEvent.ACTION_UP -> {
                    val currMoveY = mProcessY - event.y
                    var targetMoveY = toolbarTitleLP.topMargin
                    if(currMoveY < 0){
                        targetMoveY = toolBarMaxMarginTopPx
                        mToolbarTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, toolBarMaxTextSizePx.toFloat())
                    }
                    if(currMoveY > 0){
                        targetMoveY = toolBarMinMarginTopPx
                        mToolbarTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, toolBarMinTextSizePx.toFloat())
                    }
                    val valueAnimator =
                        ValueAnimator.ofInt(toolbarTitleLP.topMargin, targetMoveY)
                    valueAnimator.setDuration(400)
                    valueAnimator.addUpdateListener {
                        toolbarTitleLP.topMargin = valueAnimator.animatedValue as Int
                        mToolbarTitle.layoutParams = toolbarTitleLP
                    }
                    valueAnimator.start()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}