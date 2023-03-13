package ru.cft.marketplace.screens.home

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ru.cft.marketplace.BOTTONMENU
import ru.cft.marketplace.R
import ru.cft.marketplace.databinding.FragmentHomeBinding
import ru.cft.marketplace.util.BaseFragment
import ru.cft.marketplace.util.Category

class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnCategoryClickListener {

    private val viewModel: HomeViewModel by viewModels()

    override fun viewBindingInflate() = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productCategoryRecycler()
        viewModel.getLatest()
        viewModel.getSale()
        initAdapters()
        BOTTONMENU.isVisible = true
    }

    private fun productCategoryRecycler() {
        val adapter = CategoryAdapter(this)
        binding.recyclerCategory.adapter = adapter
        val categoriesList = listOf(
            Category.Base("Phones", R.drawable.ic_tools, false),
            Category.Base("Headphones", R.drawable.ic_headphones, false),
            Category.Base("Games", R.drawable.ic_joystick, false),
            Category.Base("Cars", R.drawable.ic_auto, false),
            Category.Base("Furniture", R.drawable.ic_sleep, false),
            Category.Base("Kids", R.drawable.ic_kids, false),
        )
        adapter.submitList(categoriesList)
    }

    private fun initAdapters() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.result.collect { latest ->
                var result = latest
                if (latest != null) {
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                        viewModel.resultSale.collect { sale ->
                            if (sale != null) {
                                val latestList = result?.latest
                                val adapter = latestList?.let { LatestAdapter(it) }
                                val brandAdapter = latestList?.let { LatestAdapter(it) }
                                binding.recyclerLatest.adapter = adapter
                                binding.recyclerBrands.adapter = brandAdapter
                                val saleList = sale.flash_sale
                                val adapterSale = SaleAdapter(saleList)
                                binding.recyclerFlash.adapter = adapterSale
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCategoryClick(item: Category) {
        item.clickReact()
    }
}