package eldp.robotminediffuser.data

import eldp.robotminediffuser.modes.UserModeEnum

/**
 * Created by yisuo on 7/17/17.
 */
data class ChangeModeCommand (val mode : UserModeEnum = UserModeEnum.User)
