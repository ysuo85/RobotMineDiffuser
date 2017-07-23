package eldp.robotminediffuser.data

/**
 * Created by yisuo on 7/17/17.
 */
data class ArduinoMessage (val commandType : CommandType, val commandDetail : Any ?= null)
