package eldp.robotminediffuser.data

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.sql.Timestamp

/**
 * Created by yisuo on 7/17/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ArduinoMessage (val timestamp: Timestamp, val commandType : CommandType, val commandDetail : Any ?= null) : Serializable
