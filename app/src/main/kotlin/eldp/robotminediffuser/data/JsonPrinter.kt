package eldp.robotminediffuser.data

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import eldp.robotminediffuser.modes.UserModeEnum
import java.sql.Time
import java.sql.Timestamp

/**
 * Created by yisuo on 7/19/17.
 */

fun main(args : Array<String>)
{
    println("Sample command to change mode")
    printJson(CommandType.ChangeMode, ChangeModeCommand(UserModeEnum.User))
    printJson(CommandType.ChangeMode, ChangeModeCommand(UserModeEnum.MazeTraverse))
    printJson(CommandType.ChangeMode, ChangeModeCommand(UserModeEnum.MineDiffuse))

    println("Sample command to raise crane")
    printJson(CommandType.RaiseCrane, RaiseCraneCommand(5.0))

    println("Sample command to move")
    printJson(CommandType.Move, MoveCommand(10.0))

    println("Sample command to lower crane")
    printJson(CommandType.LowerCrane, LowerCraneCommand(10.0))

    println("Sample command to close claw")
    printJson(CommandType.CloseClaw)

    println("Sample command to open claw")
    printJson(CommandType.OpenClaw)

    println("Sample command for kill switch")
    printJson(CommandType.Kill)

    println("Sample command to stop")
    printJson(CommandType.Stop)

    println("Sample command for lock")
    printJson(CommandType.Lock)

    println("Sample command for unlock")
    printJson(CommandType.Unlock)

    println("Sample command to rotate")
    printJson(CommandType.Rotate, RotateCommand(30.0))
    printJson(CommandType.Rotate, RotateCommand(-30.0))

    println("Sample command to verify alignment")
    printJson(CommandType.VerifyAlignment)
}

fun printJson(commandType : CommandType, commandDetails : Any ?= null){
    val mapper = jacksonObjectMapper()
    val sampletime = Timestamp(System.currentTimeMillis())
    var messageObj = ArduinoMessage(sampletime, commandType, commandDetails)
    var messageStr = mapper.writeValueAsString(messageObj)
    println(messageStr)
}
