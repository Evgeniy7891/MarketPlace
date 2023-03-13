package ru.cft.marketplace.screens.authorization

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.cft.domain.models.AccountModel
import ru.cft.marketplace.R
import ru.cft.marketplace.databinding.FragmentSignInBinding
import ru.cft.marketplace.util.BaseFragment
import kotlin.random.Random.Default.nextInt

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val viewModel: SignInViewModel by viewModels()

    override fun viewBindingInflate() = FragmentSignInBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.insert(AccountModel(23, "Satria", "Adhi Pradana", "www.test@mail.ru", "1234"))
        initInsert()
        binding.tvLogIn.setOnClickListener {
            findNavControllerSafely(R.id.signInFragment)?.navigate(R.id.action_signInFragment_to_loginFragment)
        }
    }

    private fun initInsert() {
        with(binding) {
            btnSignIn.setOnClickListener {
                if(etFirstName.text.toString() == "" || etLastName.text.toString() == "") {
                    Toast.makeText(requireContext(), "Fill in all the boxes", Toast.LENGTH_SHORT).show()
                } else if(isMailValid(etEmail.text)) {
                    val email = etEmail.text.toString()
                    progressBar.isVisible = true
                    viewModel.searchEmail(email)
                    viewModel.result.observe(viewLifecycleOwner) { result ->
                        if(result == true) {
                            Toast.makeText(requireContext(), "You are already registered", Toast.LENGTH_SHORT).show()
                            progressBar.isVisible = false
                        } else {
                            val id = nextInt(0,10000)
                            val firstName = etFirstName.text.toString()
                            val lastName = etLastName.text.toString()
                            val password = "1234"
                            viewModel.insert(AccountModel(id, firstName, lastName, email, password))
                            findNavControllerSafely(R.id.signInFragment)?.navigate(R.id.action_signInFragment_to_homeFragment)
                            progressBar.isVisible = false
                        }
                    }
                } else  Toast.makeText(requireContext(), "Your Email Id is Invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }

 private fun isMailValid(email:CharSequence) : Boolean {
     return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
 }
}