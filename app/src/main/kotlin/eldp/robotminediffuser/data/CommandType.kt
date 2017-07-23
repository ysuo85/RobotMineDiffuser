package eldp.robotminediffuser.data

import com.fasterxml.jackson.annotation.JsonValue

/**
 * Created by yisuo on 7/17/17.
 */
enum class CommandType {
    Lock,
    Unlock,
    Kill,
    ChangeMode,
    Move,
    Rotate,
    Stop,
    RaiseCrane,
    LowerCrane,
    VerifyAlignment,
    OpenClaw,
    CloseClaw;

    @JsonValue
    fun toValue(): Int {
        return ordinal
    }
}