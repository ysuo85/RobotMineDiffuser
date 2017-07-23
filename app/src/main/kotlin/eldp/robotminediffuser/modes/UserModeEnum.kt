package eldp.robotminediffuser.modes

import com.fasterxml.jackson.annotation.JsonValue

/**
 * Created by yisuo on 7/16/17.
 */
enum class UserModeEnum {
    User,
    MazeTraverse,
    MineDiffuse;

    @JsonValue
    fun toValue():Int{
        return ordinal
    }
}