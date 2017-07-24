package eldp.robotminediffuser.data

import com.fasterxml.jackson.annotation.JsonInclude
import eldp.robotminediffuser.modes.UserModeEnum

/**
 * Created by yisuo on 7/17/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ChangeModeCommand (val mode : UserModeEnum = UserModeEnum.User)
