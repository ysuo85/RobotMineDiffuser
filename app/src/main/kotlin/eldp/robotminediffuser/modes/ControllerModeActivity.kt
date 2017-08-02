package eldp.robotminediffuser.modes

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import eldp.robotminediffuser.LoginActivity
import eldp.robotminediffuser.R
import eldp.robotminediffuser.data.ArduinoMessage
import eldp.robotminediffuser.data.CommandType
import eldp.robotminediffuser.services.RobotMessagingService

open class ControllerModeActivity : AppCompatActivity() {
    var mRobotMessagingService: RobotMessagingService?= null
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
        setContentView(R.layout.activity_robot_mode_main_screen)

        val intent = Intent(this, RobotMessagingService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)

        val mKillButton = findViewById(R.id.kill_button) as Button
        val mLockButton = findViewById(R.id.lock_button) as Button
        val mCommandButton = findViewById(R.id.command_button) as Button

        mKillButton.setOnClickListener { kill() }
        mLockButton.setOnClickListener { lock() }
        mCommandButton.setOnClickListener { command_mode() }
    }

    fun kill(){
        mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.Kill))
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("kill_flag", true)
        startActivity(intent)
    }

    fun lock(){
        mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.Lock))

        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("lock_flag", true)
        startActivity(intent)
    }

    fun command_mode() {
        mRobotMessagingService?.sendCommand(ArduinoMessage(CommandType.Unlock))
        val intent = Intent(this, MainUserModeActivity::class.java)
        startActivity(intent)
    }
}

