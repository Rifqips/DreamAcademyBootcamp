package com.rifqipadisiliwangi.dreamacademy_bootcamp.feature.login

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.rifqipadisiliwangi.dreamacademy_bootcamp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginContract {

    private lateinit var binding : ActivityLoginBinding
    private val _presenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _presenter.onAttach(this)

        binding.btnLogin.setOnClickListener {
            _presenter.validateCredential(
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

    }

    private fun validateInput() {
        binding.btnLogin.isEnabled =
            binding.etPassword.text.toString().isNotBlank() && binding.etUsername.text.toString()
                .isNotBlank()
    }

    override fun onLoading() {
        binding.progressIndicator.isVisible = true
    }

    override fun onFinishedLoading() {
        binding.progressIndicator.isVisible = false
    }

    override fun onError(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("Ok", this::dialogClickListener)
            .setNegativeButton("Cancel", this::dialogClickListener)
            .create()
            .show()
    }

    private fun dialogClickListener(dialogInterface: DialogInterface, button: Int) {
        when (button) {
            DialogInterface.BUTTON_NEGATIVE -> {}
            DialogInterface.BUTTON_POSITIVE -> {}
            DialogInterface.BUTTON_NEUTRAL -> {}
        }
    }

    override fun onSuccessLogin() {
        Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _presenter.onDetach()
    }
}