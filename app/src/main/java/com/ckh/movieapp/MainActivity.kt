package com.ckh.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.ImageView
import com.ckh.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var startX = 0.0f
    private var startY = 0.0f
    private var leftMargin = 0
    private var topMargin = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        val freeBtn = binding.freeBtn
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        move()
        change()

        freeBtn.setOnClickListener {
            var rotate = true
            while (rotate){
                move()
            }
        }
    }
    private fun move(){
        val mini = binding.miniImage1
        mini.setOnTouchListener { view, motionEvent ->   //대상 객체 , //터치 상태
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {  //
                    startX = motionEvent.x
                    startY = motionEvent.y

                }
                MotionEvent.ACTION_MOVE -> { //움직임
                    var diffX = motionEvent.x - startX
                    var diffY = motionEvent.y - startY
                    leftMargin += diffX.toInt()
                    topMargin += diffY.toInt()

                    val layoutParams = FrameLayout.LayoutParams(
                        100, 100
                    )
                    layoutParams.leftMargin = leftMargin
                    layoutParams.topMargin = topMargin
                    binding.miniImage1.layoutParams = layoutParams
                }
                MotionEvent.ACTION_UP -> {
                    // 손 땜
                }
            }
            return@setOnTouchListener true
        }
    }

    private fun change() {
        val imageView = binding.image1
        val mini = binding.miniImage1
        val changeBtn = binding.changeBtn
        var cnt = 0
        changeBtn.setOnClickListener {
            cnt += 1
            when (cnt % 4) {
                1 -> {
                    imageView.setImageResource(R.drawable.ic_android_blue_24dp)
                    mini.setImageResource(R.drawable.ic_android_blue_24dp)
                }
                2 -> {
                    imageView.setImageResource(R.drawable.ic_android_green_24dp)
                    mini.setImageResource(R.drawable.ic_android_green_24dp)
                }
                3 -> {
                    imageView.setImageResource(R.drawable.ic_android_yellow_24dp)
                    mini.setImageResource(R.drawable.ic_android_yellow_24dp)
                }
                else -> {
                    imageView.setImageResource(R.drawable.ic_android_red_24dp)
                    mini.setImageResource(R.drawable.ic_android_red_24dp)
                }
            }
        }
    }
}