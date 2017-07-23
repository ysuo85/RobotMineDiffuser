package eldp.robotminediffuser.modes

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import eldp.robotminediffuser.R
import eldp.robotminediffuser.data.ArduinoMessage
import eldp.robotminediffuser.data.CommandType
import eldp.robotminediffuser.services.RobotMessagingService
import eldp.robotminediffuser.services.RobotMessagingService.LocalBinder
import java.sql.Timestamp

open class UserModeActivity : AppCompatActivity() {
    var mBound : Boolean = false
    var mRobotMessagingService : RobotMessagingService ?= null
    val mConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName?, service : IBinder?) {
            val binder = service as LocalBinder
            mRobotMessagingService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(className: ComponentName?) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_mode)
        val intent = Intent(this, RobotMessagingService::class.java)
        startService(intent)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStart() {
        super.onStart()
        val now = Timestamp(System.currentTimeMillis())
        val msg = ArduinoMessage(now, CommandType.Stop)
        mRobotMessagingService?.sendCommand(msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this, RobotMessagingService::class.java)
        unbindService(mConnection)
        stopService(intent)
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

    fun turnAround(distance : Double){
        move(distance, 180.0)
    }

    fun move(distance : Double, orientationDegree : Double){
        // TODO: Implement class to send bluetooth directional data
    }

    fun stop(){
        // TODO: Implement class to send bluetooth directional data
    }
}
