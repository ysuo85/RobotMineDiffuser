package eldp.robotminediffuser.modes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import eldp.robotminediffuser.R


import android.view.MotionEvent;

import android.view.*;

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
//import android.support.design.widget.Snackbar
//import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks

import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import java.util.ArrayList

import android.Manifest.permission.READ_CONTACTS

open class ControllerModeActivity : AppCompatActivity() {

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_mode)

        val mKillButton = findViewById(R.id.kill_button) as Button
        val mLockButton = findViewById(R.id.lock_button) as Button
        val mCommandButton = findViewById(R.id.command_button) as Button

        mKillButton.setOnClickListener { kill() }
        mLockButton.setOnClickListener { lock() }
        mCommandButton.setOnClickListener { command_mode() }


    }

    fun kill(){
        /* TODO: Send to bluetooth */

        )
    }

    fun lock(){
        /* TODO: Send to bluetooth */

        val intent = Intent(this, LoginActivity::class.kt)
        intent.putExtra("lock_flag", true)
        startActivity(intent)
    }

    fun command_mode() {
        val intent = Intent(this, MainUserModeActivity::class.kt)
        startActivity(intent)
    }
}

