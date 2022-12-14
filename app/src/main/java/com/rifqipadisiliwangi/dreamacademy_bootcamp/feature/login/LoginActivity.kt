package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.opengl.Visibility
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
import com.rifqipadisiliwangi.dreamacademy_bootcamp.databinding.ActivityLoginBinding
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.home.MainActivity
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.register.RegisterActivity
import com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.reqrest.ReqrestActivity

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding
    private val presenter = LoginPresenter(CredentialApi(), UserApi())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onAttach(this)

        binding.btnLogin.setOnClickListener {
            presenter.validateCredential(
                binding.edtUsername.text.toString(),
                binding.edtPassword.text.toString()
            )
        }

        binding.edtUsername.doOnTextChanged { text, start, before, count ->
            validateInput()
        }

        binding.edtPassword.doOnTextChanged { text, start, before, count ->
            validateInput()
        }
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validateInput() {
        binding.btnLogin.isEnabled =
            binding.edtPassword.text.toString().isNotBlank() && binding.edtUsername.text.toString()
                .isNotBlank()
    }

    override fun onLoading() {
        binding.progressIndicator.isVisible = true
    }

    override fun onFinishedLoading() {
        binding.progressIndicator.isVisible = false
    }

    override fun onError(code: Int, message: String) {
        binding.tvErrorPassword.isVisible = false
        when (code) {
            in 0..9 -> {
                binding.tvErrorPassword.text = message
                binding.tvErrorPassword.isVisible = true
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
        binding.tvErrorPassword.isVisible = false
    }

    override fun onErrorPassword(visible: Boolean, message: String) {
        binding.tvErrorPassword.text = message
        binding.tvErrorPassword.isVisible = visible
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
        startActivity(Intent(this, ReqrestActivity::class.java))
        finish()
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
}