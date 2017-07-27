package eldp.robotminediffuser

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import eldp.robotminediffuser.modes.MainUserModeActivity

class LoginActivity : Activity() {
    private var password: EditText ?= null
    private var btnSubmit: Button ?= null
    private var passwordVal: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_mode)
        password = findViewById<View>(R.id.txtPassword) as EditText
        btnSubmit = findViewById<View>(R.id.btnSubmit) as Button
        passwordVal = getString(R.string.password)
        addListenerOnButton()

        val intent : Intent = getIntent()
        val lock_flag : Boolean = intent.getBooleanExtra("lock_flag", false)
        val kill_flag : Boolean = intent.getBooleanExtra("kill_flag", false)

        if(lock_flag) {
            val imgFile = File("lock.png")

            if (imgFile.exists()) {

                val myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath())
                val myImage = findViewById(R.id.imageviewTest) as ImageView
                myImage.setImageBitmap(myBitmap)
            }

        }

        if(kill_flag) {

        }
    }


    fun addListenerOnButton() {
        btnSubmit!!.setOnClickListener {
            if(passwordVal== password!!.text.toString()){
                Toast.makeText(this@LoginActivity, "Password matched!",
                        Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainUserModeActivity::class.java)
                intent.putExtra("logged_in_flag", true)
                startActivity(intent)
            }
        }
    }
}
