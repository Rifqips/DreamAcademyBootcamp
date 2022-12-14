package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.register

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.model.UserPagination
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.api.CredentialApi
import com.rifqipadisiliwangi.dreamacademy_bootcamp.data.network.api.UserApi
import com.rifqipadisiliwangi.dreamacademy_bootcamp.databinding.ActivityRegisterBinding
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login.LoginActivity
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login.LoginContract
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login.LoginPresenter
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login.LoginView
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.reqrest.ReqrestActivity

class RegisterActivity : AppCompatActivity(), LoginView {

    private lateinit var binding : ActivityRegisterBinding
    private val presenter = LoginPresenter(CredentialApi(), UserApi())
//    private val _presenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.onAttach(this)

        binding.btnRegister.setOnClickListener {
            presenter.validateCredential(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        binding.etUsername.doOnTextChanged { text, start, before, count ->
            validateInput()
        }

        binding.etPassword.doOnTextChanged { text, start, before, count ->
            validateInput()
        }

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

    override fun onLoading() {
        binding.progressIndicator.isVisible = true
    }

    override fun onFinishedLoading() {
        startActivity(Intent(this, LoginActivity::class.java))
        binding.progressIndicator.isVisible = false
    }

    override fun onError(code: Int, message: String) {
        binding.tvCheckValidate.isVisible = false
        when (code) {
            in 0..9 -> {
                binding.tvCheckValidate.text = message
                binding.tvCheckValidate.isVisible = true
            }
            else -> {
                AlertDialog.Builder(this)
                    .setMessage("code: $code, $message")
                    .setPositiveButton("Ok", this::dialogClickListener)
                    .setNegativeButton("Cancel", this::dialogClickListener)
                    .create()
                    .show()
            }
        }
    }

    override fun resetPasswordError() {
        binding.tvCheckValidate.isVisible = false
    }

    override fun onErrorPassword(visible: Boolean, message: String) {
        binding.tvCheckValidate.text = message
        binding.tvCheckValidate.isVisible = visible
    }

    private fun dialogClickListener(dialogInterface: DialogInterface, button: Int) {
        when (button) {
            DialogInterface.BUTTON_NEGATIVE -> {}
            DialogInterface.BUTTON_POSITIVE -> {}
            DialogInterface.BUTTON_NEUTRAL -> {}
        }
    }

    override fun onSuccessGetUser(user: UserPagination) {
        AlertDialog.Builder(this)
            .setMessage("user -> $user")
            .setPositiveButton("Ok", this::dialogClickListener)
            .setNegativeButton("Cancel", this::dialogClickListener)
            .create()
            .show()
    }

    override fun onSuccessLogin(username: String, password: String) {
        Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
        presenter.register(username, password)
        presenter.getUser()
    }

    override fun onSuccessRegister() {
        Toast.makeText(this, "Success Register", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
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