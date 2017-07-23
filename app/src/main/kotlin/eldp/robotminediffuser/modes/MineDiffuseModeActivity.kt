package eldp.robotminediffuser.modes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import eldp.robotminediffuser.R

class MineDiffuseModeActivity : UserModeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_robot)
    }

    fun raiseCrane(distance : Double){

    }

    fun lowerCrane(distance: Double){

    }

    fun verifyAlignment(){

    }

    fun openClaw(){

    }

    fun closeClaw(){

    }
}
