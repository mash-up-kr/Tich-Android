package com.example.tichandroid.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.tichandroid.MainActivity
import com.example.tichandroid.MainActivity.Companion.KEY_USER_NAME
import com.example.tichandroid.R
import com.example.tichandroid.auth.AuthManager
import com.example.tichandroid.base.BaseViewModelFragment
import com.example.tichandroid.viewmodel.WalkLastViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.mashup.android.base.extension.rx.observeOnMain
import com.mashup.android.base.extension.rx.subscribeWithErrorLogger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_walk_last.*
import javax.inject.Inject

@AndroidEntryPoint
class WalkLastFragment : BaseViewModelFragment() {

    private val viewModel by viewModels<WalkLastViewModel>()

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    @Inject
    lateinit var authManager: AuthManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_walk_last, container, false)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(view.context, gso)
        auth = FirebaseAuth.getInstance()

        return view
    }

    override fun onSetupViews() {
        googleLoginBtn.setOnClickListener {
            onSignUpButtonClick()
        }
    }

    private fun onSignUpButtonClick() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken
                val name = account?.displayName
                val email = account?.email

                if (idToken != null && name != null && email != null) {
                    firebaseAuthWithGoogle(idToken, name, email)
                }
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String, name: String, email: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                handleTaskComplete(task, name, email)
            }
    }

    private fun handleTaskComplete(task: Task<AuthResult>, name: String, email: String) {
        if (task.isSuccessful) {
            auth.uid?.let {
                viewModel.signUpButtonClick(it, name, email)
                    .observeOnMain()
                    .subscribeWithErrorLogger { userName ->
                        startTich(userName)
                    }
                    .addToDisposables()
            }
        } else {
            Log.w(TAG, "signInWithCredential:failure", task.exception)
        }
    }

    private fun startTich(userName: String) {
        Intent(context, MainActivity::class.java).apply {
            arguments = bundleOf(
                KEY_USER_NAME to userName
            )
        }
        startActivity(Intent(context, MainActivity::class.java))
    }

    companion object {
        private const val TAG = "WalkLastFragment"
        private const val RC_SIGN_IN = 9001
    }
}