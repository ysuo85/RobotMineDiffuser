package eldp.robotminediffuser.modes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import eldp.robotminediffuser.R
import android.content.Intent
import android.view.View
import android.widget.EditText




open class UserModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robot_mode_main_screen)
    }

    inner class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_robot_mode_main_screen)
        }

        /** Called when the user taps the Send button  */
        fun sendMessage(view: View) {
            val intent = Intent(this, DisplayMessageActivity::class.)
            val editText = findViewById(R.id.editText) as EditText
            val message = editText.text.toString()
            intent.putExtra(EXTRA_MESSAGE, message)
            startActivity(intent)
        }

        companion object {
            val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"
        }
    }

        }

        /** Called when the user taps the Send button  */
        fun sendMessage(view: View) {
            // Do something in response to button
        }
    }
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
