package eldp.robotminediffuser.modes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import eldp.robotminediffuser.R

open class UserModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_mode)
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
