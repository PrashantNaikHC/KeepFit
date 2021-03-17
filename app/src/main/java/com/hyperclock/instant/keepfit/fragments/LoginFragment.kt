package com.hyperclock.instant.keepfit.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.hyperclock.instant.keepfit.R
import com.hyperclock.instant.keepfit.model.AuthData
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private var authData = AuthData()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onResume() {
        super.onResume()

        val uri = Uri.parse(requireActivity().intent.data.toString().replace("#","?"))
        Log.d("LoginFragment", requireActivity().intent.data.toString())
        if (requireActivity().intent.data != null) {
            authData.accessToken = uri.getQueryParameter("access_token").toString()
            authData.userId = uri.getQueryParameter("user_id").toString()
            authData.tokenType = uri.getQueryParameter("token_type").toString()
            Log.d("LoginFragment", "Sending data "+ authData.accessToken + " | " + authData.userId +" | "+authData.tokenType)
            val action = LoginFragmentDirections.actionLoginFragmentToActivityFragment(authData = authData)
            findNavController().navigate(action)
            Toast.makeText(requireContext(), "Logged in successfully.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_button.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.fitbit.com/oauth2/authorize?response_type=token&client_id=22C5TL&redirect_uri=api%3A%2F%2Fkeepfitlogin&scope=activity&expires_in=604800")
                )
            )
        }
    }
}