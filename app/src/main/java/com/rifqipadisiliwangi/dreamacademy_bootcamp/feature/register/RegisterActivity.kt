package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.rifqipadisiliwangi.dreamacademy_bootcamp.databinding.ActivityRegisterBinding
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login.LoginActivity
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login.LoginContract
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login.LoginPresenter

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
//    private val _presenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        _presenter.onAttach(this)
//
//        binding.btnRegister.setOnClickListener {
//            _presenter.validateCredential(
//                binding.etUsername.text.toString(),
//                binding.etPassword.text.toString()
//            )
//        }
//
//        binding.ivBackDetail.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }
//
//        binding.etPassword.addTextChangedListener {
//            validateInput()
//        }
//        binding.etConfPassword.addTextChangedListener {
//            validateInput()
//        }
//        binding.etUsername.addTextChangedListener {
//            validateInput()
//        }

    }

    private fun validateInput() {
        binding.btnRegister.isEnabled =
            binding.etPassword.text.toString().isNotBlank() && binding.etUsername.text.toString()
                .isNotBlank()
    }

//    override fun onLoading() {
//        binding.progressIndicator.isVisible = true
//    }
//
//    override fun onFinishedLoading() {
//        binding.progressIndicator.isVisible = false
//    }

//    override fun onError(code: Int, message: String)  {
//        when(code){
//            0 -> binding.passwordInputLayout.error = message
//            1 -> binding.passwordInputLayout.error = message
//            2 -> binding.passwordInputLayout.error = message
//            else -> binding.passwordInputLayout.error = null
//        }
//        when(code){
//            0 -> binding.passwordConfInputLayout.error = message
//            1 -> binding.passwordConfInputLayout.error = message
//            2 -> binding.passwordConfInputLayout.error = message
//            else -> binding.passwordConfInputLayout.error = null
//        }
//        when(code){
//            0 -> binding.textInputLayout.error = message
//            1 -> binding.textInputLayout.error = message
//            2 -> binding.textInputLayout.error = message
//            else -> binding.textInputLayout.error = null
//        }
//    }

//    override fun onSuccessLogin() {
//        binding.tvCheckValidate.visibility = this.hashCode()
//        binding.textInputLayout.error = null
//        binding.passwordConfInputLayout.error = null
//        binding.passwordInputLayout.error = null
//        startActivity(Intent(this, LoginActivity::class.java))
//        Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
//
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        _presenter.onDetach()
//    }
}