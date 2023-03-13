package ru.cft.marketplace.screens.authorization

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.cft.marketplace.BOTTONMENU
import ru.cft.marketplace.R
import ru.cft.marketplace.databinding.FragmentLoginBinding
import ru.cft.marketplace.util.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel : LoginViewModel by viewModels()

    override fun viewBindingInflate() = FragmentLoginBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInsert()
        BOTTONMENU.isVisible = false
    }

    private fun initInsert() {
        with(binding) {
            btnLogin.setOnClickListener {
                progressBar2.isVisible = true
                val name = etFirstName.text.toString()
                val password = etPassword.text.toString()
                viewModel.searchAccount(name, password)
                viewModel.result.observe(viewLifecycleOwner) { result ->
                    if(result == false) {
                        Toast.makeText(requireContext(), "User name or password error", Toast.LENGTH_SHORT).show()
                        progressBar2.isVisible = false
                    } else if (result == true) {
                        findNavControllerSafely(R.id.loginFragment)?.navigate(R.id.action_loginFragment_to_homeFragment)
                        progressBar2.isVisible = false
                    }
                }
            }
        }
    }
}