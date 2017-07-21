package eldp.robotminediffuser.modes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import eldp.robotminediffuser.R


import android.view.MotionEvent;

import android.view.*;

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
//import android.support.design.widget.Snackbar
//import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks

import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask

import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import java.util.ArrayList

import android.Manifest.permission.READ_CONTACTS

open class MainUserModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_mode)

        val mMoveForwardButton = findViewById(R.id.move_forward_button) as Button
        val mMoveBackwardButton = findViewById(R.id.move_backward_button) as Button
        val mTurnLeft = findViewById(R.id.turn_left) as Button
        val mTurnRight = findViewById(R.id.turn_right) as Button

        mMoveForwardButton.setOnClickListener { moveForward(1.0) }
        mMoveBackwardButton.setOnClickListener { moveAft(1.0) }
        mTurnLeft.setOnClickListener { turnLeft(1.0) }
        mTurnRight.setOnClickListener { turnRight(1.0) }


        /*val mMoveForwardButton = findViewById(R.id.move_forward_button) as Button
        mMoveForwardButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    moveForward(1.0)
                    return@OnTouchListener true
                MotionEvent.ACTION_UP ->
                    // Released
                    return@OnTouchListener true
                MotionEvent.ACTION_CANCEL ->
                    // Released - Dragged finger outside
                    return@OnTouchListener true
            }
            false
        })

        val mMoveBackwardButton = findViewById(R.id.move_backward_button) as Button
        mMoveBackwardButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    moveAft(1.0)
                return@OnTouchListener true
                        MotionEvent.ACTION_UP ->
                    // Released
                    return@OnTouchListener true
                MotionEvent.ACTION_CANCEL ->
                    // Released - Dragged finger outside
                    return@OnTouchListener true
            }
            false
        })

        val mTurnLeft = findViewById(R.id.turn_left) as Button
        mTurnLeft.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    turnLeft(1.0)
                return@OnTouchListener true
                        MotionEvent.ACTION_UP ->
                    // Released
                    return@OnTouchListener true
                MotionEvent.ACTION_CANCEL ->
                    // Released - Dragged finger outside
                    return@OnTouchListener true
            }
            false
        })

        val mTurnRight = findViewById(R.id.turn_right) as Button
        mTurnRight.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    turnRight(1.0)
                return@OnTouchListener true
                        MotionEvent.ACTION_UP ->
                    // Released
                    return@OnTouchListener true
                MotionEvent.ACTION_CANCEL ->
                    // Released - Dragged finger outside
                    return@OnTouchListener true
            }
            false
        })*/
    }

    fun moveForward(distance : Double){
        move(distance, 0.0)
    }

    fun moveAft(distance : Double){
        move(-distance, 0.0)
    }

    fun turnLeft(distance : Double){
        move(distance, -90.0)
    }

    fun turnRight(distance : Double){
        move(distance, 90.0)
    }

    fun move(distance : Double, orientationDegree : Double){
        // TODO: Implement class to send bluetooth directional data
    }

    fun stop(){
        // TODO: Implement class to send bluetooth directional data
    }
}
