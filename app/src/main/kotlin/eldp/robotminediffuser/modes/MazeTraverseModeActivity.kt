package eldp.robotminediffuser.modes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import eldp.robotminediffuser.R
import eldp.robotminediffuser.data.MazeSequence
import java.util.*

class MazeTraverseModeActivity : AppCompatActivity() {
    private var mMazeSequence : Stack<MazeSequence> = Stack<MazeSequence>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maze_traverse_mode)
    }

    fun startTraverse(){

    }

    fun addMove(move : MazeSequence){
        mMazeSequence.push(move)
    }

    fun removeMove(){
        mMazeSequence.pop()
    }

    fun readbackSequence(){
        while(!mMazeSequence.isEmpty()){
            var nextCmd = mMazeSequence.pop();
            var correctedOrientation = nextCmd.orientationDegrees + 180.0
            if(correctedOrientation > 180.0){
                correctedOrientation -= 360.0
            }
//            move(nextCmd.distanceTraveled, nextCmd.orientationDegrees + 180.0)
        }
    }
}
