package com.mtanmay.mintoakassignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtanmay.domain.response.JSONResponse
import com.mtanmay.mintoakassignment.databinding.FragmentHomeBinding
import com.mtanmay.mintoakassignment.ui.home.adapters.ParentItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewmodel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.midSortBtn.setOnClickListener {
            viewmodel.getJsonData(sortByMid = true)
        }
        binding.tidSortBtn.setOnClickListener {
            viewmodel.getJsonData(sortByMid = false)
        }

        lifecycleScope.launch {
            viewmodel.parentItemFlow.collect {
                when (it) {
                    JSONResponse.Loading -> {
                        showLoadingBar(true)
                    }
                    is JSONResponse.Success -> {
                        showLoadingBar(false)

                        binding.parentRv.apply {
                            layoutManager = LinearLayoutManager(requireContext())
                            setHasFixedSize(true)
                            adapter = ParentItemAdapter(it.data)
                        }
                    }
                    is JSONResponse.Error -> {
                        binding.apply {
                            loadingBar.visibility = View.GONE
                            parentRv.visibility = View.GONE

                            errorMsg.text = it.errorMsg
                            errorMsg.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun showLoadingBar(show: Boolean) {
        binding.loadingBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.parentRv.visibility = if (show) View.GONE else View.VISIBLE
    }

}