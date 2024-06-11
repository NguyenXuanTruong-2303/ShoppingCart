package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment.Database.DataBaseUser
import com.example.assignment.Model.LoginUserModel

class Activity_Register : AppCompatActivity() {
    lateinit var edt_user_register : EditText
    lateinit var edt_password_register : EditText
    lateinit var edt_confirm_password : EditText
    lateinit var btn_register_user : Button
    lateinit var btn_back : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        edt_user_register = findViewById(R.id.edt_user_register)
        edt_password_register = findViewById(R.id.edt_password_register)
        edt_confirm_password = findViewById(R.id.edt_confirm_password)
        btn_register_user = findViewById(R.id.btn_register_user)
        btn_back = findViewById(R.id.btn_back)

        btn_back.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }




        btn_register_user.setOnClickListener {
            var loginUser : LoginUserModel
            if (edt_user_register.text.isEmpty() || edt_password_register.text.isEmpty() || edt_confirm_password.text.isEmpty()) {
                //Toast.makeText(this@Activity_Register, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()

                if(edt_user_register.text.isEmpty()){
                    edt_user_register.setError("Vui lòng nhập Username");
                }else{
                    edt_user_register.setError(null);
                }
                if(edt_password_register.text.isEmpty()){
                    edt_password_register.setError("Vui lòng nhập Password");
                }else{
                    edt_password_register.setError(null);
                }
                if(edt_confirm_password.text.isEmpty()){
                    edt_confirm_password.setError("Vui lòng nhập lại Password");
                }else{
                    edt_confirm_password.setError(null);
                }

            } else{
                try {
                    loginUser = LoginUserModel(-1,edt_user_register.getText().toString(),edt_password_register.getText().toString())
                    Toast.makeText(this@Activity_Register,"Đăng ký thành công!", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(this@Activity_Register, "Error creating customer", Toast.LENGTH_LONG).show()
                    loginUser = LoginUserModel(-1, "Error", "error")
                }

                val dataBaseUser : DataBaseUser = DataBaseUser(this)
                val succes : Boolean = dataBaseUser.addOne(loginUser);
            }

            //else if (edt_password_register.text != edt_confirm_password.text) {
           // Toast.makeText(this@Activity_Register, "Mật khẩu không trùng", Toast.LENGTH_SHORT).show()
        //}


        }
    }
}