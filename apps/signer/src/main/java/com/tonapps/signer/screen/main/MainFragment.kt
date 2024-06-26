package com.tonapps.signer.screen.main

import android.os.Bundle
import android.view.View
import com.tonapps.signer.R
import com.tonapps.signer.screen.key.KeyFragment
import com.tonapps.signer.screen.main.list.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import uikit.base.BaseFragment
import uikit.extensions.applyNavBottomPadding
import uikit.extensions.collectFlow
import uikit.extensions.getDimensionPixelSize
import uikit.extensions.topScrolled
import uikit.navigation.Navigation.Companion.navigation
import uikit.widget.HeaderView
import uikit.widget.SimpleRecyclerView

class MainFragment: BaseFragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by viewModel()

    private val adapter = MainAdapter {
        navigation?.add(KeyFragment.newInstance(it))
    }

    private lateinit var headerView: HeaderView
    private lateinit var listView: SimpleRecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headerView = view.findViewById(R.id.header)

        listView = view.findViewById(R.id.list)
        listView.adapter = adapter
        listView.applyNavBottomPadding(requireContext().getDimensionPixelSize(uikit.R.dimen.offsetMedium))

        collectFlow(listView.topScrolled, headerView::setDivider)
        collectFlow(mainViewModel.uiItems, adapter::submitList)
    }
}