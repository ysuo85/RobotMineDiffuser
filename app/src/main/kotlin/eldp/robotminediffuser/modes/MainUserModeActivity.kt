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

import android.app.Activity
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

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_mode)

        val mMoveForwardButton = findViewById(R.id.move_forward_button) as Button
        val mMoveBackwardButton = findViewById(R.id.move_backward_button) as Button
        val mTurnLeft = findViewById(R.id.turn_left) as Button
        val mTurnRight = findViewById(R.id.turn_right) as Button
        val mRaiseClaw = findViewById(R.id.raise_claw) as Button
        val mLowerClaw = findViewById(R.id.lower_claw) as Button
        val mOpenClaw = findViewById(R.id.open_claw) as Button
        val mCloseClaw = findViewById(R.id.raise_claw) as Button

        mMoveForwardButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    sendCommand(ForwardCommand(true))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    // Released
                    sendCommand(ForwardCommand(false))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    sendCommand(ForwardCommand(false))
                    return@OnTouchListener true
                }
            }
            false
        })


        mMoveBackwardButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    sendCommand(BackwardCommand(true))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    sendCommand(BackwardCommand(false))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    sendCommand(BackwardCommand(false))
                    return@OnTouchListener true
                }
            }
            false
        })

        mTurnLeft.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    sendCommand(TurnLeftCommand(true))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    sendCommand(TurnLeftCommand(false))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    sendCommand(TurnLeftCommand(false))
                    return@OnTouchListener true
                }
            }
            false
        })

        mTurnRight.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    sendCommand(TurnRightCommand(true))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    // Released
                    sendCommand(TurnRightCommand(false))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    // Released - Dragged finger outside
                    sendCommand(TurnRightCommand(false))
                    return@OnTouchListener true
                }
            }
            false
        })

        mRaiseClaw.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    sendCommand(RaiseCraneCommand(true))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    // Released
                    sendCommand(RaiseCraneCommand(false))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    // Released - Dragged finger outside
                    sendCommand(RaiseCraneCommand(false))
                    return@OnTouchListener true
                }
            }
            false
        })

        mLowerClaw.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    sendCommand(LowerCraneCommand(true))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    // Released
                    sendCommand(LowerCraneCommand(false))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    // Released - Dragged finger outside
                    sendCommand(LowerCraneCommand(false))
                    return@OnTouchListener true
                }
            }
            false
        })

        mOpenClaw.setOnClickListener { sendCommand(OpenClawCommand) }
        mCloseClaw.setOnClickListener { sendCommand(CloseClawCommand) }

    }

    /*fun moveForward(distance : Double? = null){
        when(distance){
            null -> moveBot(-1.0, 0.0)
            else -> moveBot(distance, 0.0)
        }
    }

    fun moveAft(distance : Double? = null){
        when(distance){
            null -> moveBot(-1.0, 0.0)
            else -> moveBot(distance, 0.0)
        }
    }

    fun turnLeft(angle : Double? = null){
        when(angle){
            null -> moveBot(0.0, -1.0)
            else -> moveBot(0.0, angle)
        }
    }

    fun turnRight(distance : Double? = null){
        when(distance){
            null -> moveBot(-1.0, 0.0)
            else -> moveBot(distance, 0.0)
        }
    }

    fun raiseClaw(distance : Double? = null){
        when(distance){
            null -> moveArm(-1.0, 0.0)
            else -> moveArm(distance, 0.0)
        }
    }

    fun lowerClaw(distance : Double? = null){
        when(distance){
            null -> moveArm(-1.0, 0.0)
            else -> moveArm(distance, 0.0)
        }
    }

    fun operateClaw(){

    }

    fun moveBot(distance : Double, orientationDegree : Double){
        // TODO: Implement class to send bluetooth directional data
    }

    fun moveArm(distance : Double, orientationDegree : Double){
        // TODO: Implement class to send bluetooth directional data
    }

    fun moveGrabber(distance : Double, orientationDegree : Double){
        // TODO: Implement class to send bluetooth directional data
    }

    fun stop(){
        // TODO: Implement class to send bluetooth directional data
    }*/
}
