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
    printJson(CommandType.RaiseCrane, RaiseCraneCommand(true))
    println("Sample command to stop raising crane")
    printJson(CommandType.RaiseCrane, RaiseCraneCommand(false))

    println("Sample command to move forward")
    printJson(CommandType.Forward, ForwardCommand(true))
    println("Sample command to stop moving forward")
    printJson(CommandType.Forward, ForwardCommand(false))

    println("Sample command to lower crane")
    printJson(CommandType.LowerCrane, LowerCraneCommand(true))
    println("Sample command to stop lowering crane")
    printJson(CommandType.LowerCrane, LowerCraneCommand(false))

    println("Sample command to close claw")
    printJson(CommandType.CloseClaw, CloseClawCommand(true))
    println("Sample command to stop closing claw")
    printJson(CommandType.CloseClaw, CloseClawCommand(false))

    println("Sample command to open claw")
    printJson(CommandType.OpenClaw, OpenClawCommand(true))
    println("Sample command to stop opening claw")
    printJson(CommandType.OpenClaw, OpenClawCommand(false))

    println("Sample command for kill switch")
    printJson(CommandType.Kill)

    println("Sample command to stop")
    printJson(CommandType.Stop)

    println("Sample command for lock")
    printJson(CommandType.Lock)

    println("Sample command for unlock")
    printJson(CommandType.Unlock)

    println("Sample command to turn left")
    printJson(CommandType.TurnLeft, TurnLeftCommand(true))
    println("Sample command to stop turning left")
    printJson(CommandType.TurnLeft, TurnLeftCommand(false))

    println("Sample command to turn right")
    printJson(CommandType.TurnRight, TurnRightCommand(true))
    println("Sample command to stop turning right")
    printJson(CommandType.TurnRight, TurnRightCommand(false))

    println("Sample command to verify alignment")
    printJson(CommandType.VerifyAlignment)
}

fun printJson(commandType : CommandType, commandDetails : Any ?= null){
    val mapper = jacksonObjectMapper()
    var messageObj = ArduinoMessage(commandType, commandDetails)
    var messageStr = mapper.writeValueAsString(messageObj)
    println(messageStr)
}
