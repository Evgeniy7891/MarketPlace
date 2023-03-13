package ru.cft.marketplace.screens.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import ru.cft.marketplace.BOTTONMENU
import ru.cft.marketplace.R
import ru.cft.marketplace.databinding.FragmentProfileBinding
import ru.cft.marketplace.util.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun viewBindingInflate() = FragmentProfileBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickInit()
    }

    private fun clickInit() {
        with(binding) {
            btnLogOut.setOnClickListener {
                findNavControllerSafely(R.id.profileFragment)?.navigate(R.id.action_profileFragment_to_signInFragment)
                BOTTONMENU.isVisible = false
            }
            btnBack.setOnClickListener {
                findNavControllerSafely(R.id.profileFragment)?.popBackStack()
            }
        }
    }
}