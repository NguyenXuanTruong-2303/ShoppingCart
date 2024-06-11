package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.Database.DataBaseUser
import com.example.assignment.Model.LoginUserModel


class MainActivity : AppCompatActivity() {
    lateinit var edt_signin_user : EditText
    lateinit var edt_signin_password : EditText
    lateinit var btn_signin : Button
    lateinit var btn_register : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        edt_signin_user = findViewById(R.id.edt_signin_user)
        edt_signin_password = findViewById(R.id.edt_signin_password)
        btn_signin = findViewById(R.id.btn_signin)
        btn_register = findViewById(R.id.btn_register)

        btn_signin.setOnClickListener {
            var userName : String =  edt_signin_user.getText().toString()
            var userPassword : String = edt_signin_password.getText().toString()

            val dataBaseUser : DataBaseUser = DataBaseUser(this)
            var userConfirm : LoginUserModel = dataBaseUser.getUser(userName)

            var userNameCofirm : Boolean = userName.equals(userConfirm.username)
            var userPasswordCofirm : Boolean = userPassword.equals(userConfirm.password)


            if ( userNameCofirm && userPasswordCofirm){
                Toast.makeText(this,"Đăng nhập thành công",Toast.LENGTH_LONG).show()
                var intent : Intent = Intent(this,Activity_Home::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this,"Sai tài khoản hoặc mật khẩu !",Toast.LENGTH_LONG).show()
            }
        }

        btn_register.setOnClickListener {
            val intent = Intent (this, Activity_Register::class.java)
            startActivity(intent)
        }
    }
}