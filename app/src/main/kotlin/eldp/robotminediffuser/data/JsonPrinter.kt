package eldp.robotminediffuser.data

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import eldp.robotminediffuser.modes.UserModeEnum

/**
 * Created by yisuo on 7/19/17.
 */

class JsonPrinter{
    fun main(args : Array<String>)
    {
        val mapper = jacksonObjectMapper()

        // Sample command to change mode
        val changeModeToMazeTraverse : ChangeModeCommand = ChangeModeCommand(UserModeEnum.MazeTraverse)
        var messageObj : ArduinoMessage = ArduinoMessage(CommandType.ChangeMode, changeModeToMazeTraverse)
        var messageStr = mapper.writeValueAsString(messageObj)
        println(messageStr)
    }
}
