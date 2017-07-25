package eldp.robotminediffuser.modes

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import eldp.robotminediffuser.R
import eldp.robotminediffuser.data.*
import eldp.robotminediffuser.services.RobotMessagingService

open class MainUserModeActivity : AppCompatActivity() {
    private val TAG = "MainUserModeActivity"
    var mRobotMessagingService: RobotMessagingService ?= null
    var mBound = false

    val mConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service : IBinder?) {
            val binder = service as RobotMessagingService.LocalBinder
            mRobotMessagingService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(className: ComponentName?) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_robot)

        val intent = Intent(this, RobotMessagingService::class.java)
        startService(intent)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)

        val mMoveForwardButton = findViewById(R.id.move_forward_button) as Button
        val mMoveBackwardButton = findViewById(R.id.move_backward_button) as Button
        val mTurnLeft = findViewById(R.id.turn_left) as Button
        val mTurnRight = findViewById(R.id.turn_right) as Button
        val mRaiseClaw = findViewById(R.id.raise_claw) as Button
        val mLowerClaw = findViewById(R.id.lower_claw) as Button
        val mOpenClaw = findViewById(R.id.open_claw) as Button
        val mCloseClaw = findViewById(R.id.close_claw) as Button

        mMoveForwardButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d(TAG, "Moving forward...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.Forward, ForwardCommand(true)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    // Released
                    Log.d(TAG, "Stopping moving forward...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.Forward, ForwardCommand(false)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    Log.d(TAG, "Stopping moving forward...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.Forward, ForwardCommand(false)))
                    return@OnTouchListener true
                }
            }
            false
        })


        mMoveBackwardButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d(TAG, "Moving backward...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.Backward, BackwardCommand(true)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    Log.d(TAG, "Stopping moving backward...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.Backward, BackwardCommand(false)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    Log.d(TAG, "Stopping moving backward...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.Backward, BackwardCommand(false)))
                    return@OnTouchListener true
                }
            }
            false
        })

        mTurnLeft.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d(TAG, "Rotating left...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.TurnLeft, TurnLeftCommand(true)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    Log.d(TAG, "Stopping rotating left...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.TurnLeft, TurnLeftCommand(false)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    Log.d(TAG, "Stopping rotating left...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.TurnLeft, TurnLeftCommand(false)))
                    return@OnTouchListener true
                }
            }
            false
        })

        mTurnRight.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d(TAG, "Rotating right...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.TurnRight, TurnRightCommand(true)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    // Released
                    Log.d(TAG, "Stopping rotating right...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.TurnRight, TurnRightCommand(false)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    // Released - Dragged finger outside
                    Log.d(TAG, "Stopping rotating right...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.TurnRight, TurnRightCommand(false)))
                    return@OnTouchListener true
                }
            }
            false
        })

        mRaiseClaw.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d(TAG, "Raising crane...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.RaiseCrane, RaiseCraneCommand(true)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    // Released
                    Log.d(TAG, "Stopping crane...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.RaiseCrane, RaiseCraneCommand(false)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    // Released - Dragged finger outside
                    Log.d(TAG, "Stopping crane...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.RaiseCrane, RaiseCraneCommand(false)))
                    return@OnTouchListener true
                }
            }
            false
        })

        mLowerClaw.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d(TAG, "Lowering crane...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.LowerCrane, LowerCraneCommand(true)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    // Released
                    Log.d(TAG, "Stopping crane...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.LowerCrane, LowerCraneCommand(false)))
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_CANCEL -> {
                    // Released - Dragged finger outside
                    Log.d(TAG, "Stopping crane...")
                    mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.LowerCrane, LowerCraneCommand(false)))
                    return@OnTouchListener true
                }
            }
            false
        })

        mOpenClaw.setOnClickListener(View.OnClickListener {v ->
            Log.d(TAG, "Opening claw...")
            mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.OpenClaw, OpenClawCommand(true)))
        })

        mCloseClaw.setOnClickListener(View.OnClickListener {v ->
            Log.d(TAG, "Closing claw...")
            mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.CloseClaw, CloseClawCommand(true)))
        })
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
