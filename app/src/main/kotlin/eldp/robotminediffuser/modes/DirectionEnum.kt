package eldp.robotminediffuser.modes

import com.fasterxml.jackson.annotation.JsonValue

/**
 * Created by yisuo on 7/17/17.
 */
enum class DirectionEnum {
    Forward,
    Aft;

    @JsonValue
    fun toValue():Int{
        return ordinal
    }
}