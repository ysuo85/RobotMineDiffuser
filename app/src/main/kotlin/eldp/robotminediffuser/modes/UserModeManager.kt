package eldp.robotminediffuser.modes

/**
 * Created by yisuo on 7/16/17.
 */
object UserModeManager {
    private var mPassword : String = "password"
    private var mMode : UserModeEnum = UserModeEnum.User

    fun setPassword(password : String){
        mPassword = password
    }

    fun verifyPassword(password : String): Boolean {
        return mPassword.equals(password)
    }

    fun changeMode(mode : UserModeEnum){
        mMode = mode
    }

    fun getMode(): UserModeEnum {
        return mMode
    }
}