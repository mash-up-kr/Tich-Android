package com.example.tichandroid

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.example.tichandroid.auth.AuthManager
import com.example.tichandroid.base.BaseActivity
import com.example.tichandroid.view.ui.WalkActivity
import com.example.tichandroid.view.ui.showcycle.ShowCycleActivity
import com.example.tichandroid.viewmodel.MainViewModel
import com.mashup.android.base.extension.rx.observeOnMain
import com.mashup.android.base.extension.rx.subscribeWithErrorLogger
import com.mashup.android.base.extension.showSoftInput
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    private var textWatcher: TextWatcher? = null

    @Inject
    lateinit var authManager: AuthManager

    private val userName: String by lazy { intent.getStringExtra(KEY_USER_NAME) ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkSignedIn()
        checkJoinedIn()
        setContentView(R.layout.activity_main)
        onSetUpViews()
        onBindViewModels()
    }

    private fun onSetUpViews() {
        setUpInput()
        btnContinue.setOnClickListener { handleClickContinue() }
    }

    private fun onBindViewModels() {
        viewModel.name
            .observeOnMain()
            .subscribeWithErrorLogger {
                btnContinue?.isEnabled = !it.isNullOrBlank()
            }
            .addToDisposables()
    }

    private fun setUpInput() {
        mainName.showSoftInput()
        mainName.setText(userName)
        textWatcher = mainName.doOnTextChanged { text, _, _, _ ->
            viewModel.setName(text?.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainName.removeTextChangedListener(textWatcher)
    }

    private fun handleClickContinue() {
        authManager.saveUserName(mainName.text.toString())
        startActivity(Intent(this, ShowCycleActivity::class.java))
        finish()
    }

    private fun checkSignedIn() {
        if (authManager.getToken() == null) {
            startActivity(Intent(this, WalkActivity::class.java))
            finish()
        }
    }

    private fun checkJoinedIn() {
        if (authManager.getUserName() != null) {
            startActivity(Intent(this, ShowCycleActivity::class.java))
            finish()
        }
    }

    companion object {
        const val KEY_USER_NAME = "user_name"
    }
}