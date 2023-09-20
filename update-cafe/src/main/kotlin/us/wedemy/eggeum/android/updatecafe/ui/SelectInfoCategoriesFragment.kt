/*
 * Designed and developed by Wedemy 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/Wedemy/eggeum-android/blob/main/LICENSE
 */

@file:Suppress("unused", "UnusedPrivateProperty")

package us.wedemy.eggeum.android.updatecafe.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import us.wedemy.eggeum.android.common.extension.safeNavigate
import us.wedemy.eggeum.android.common.ui.BaseFragment
import us.wedemy.eggeum.android.updatecafe.databinding.FragmentSelectInfoCategoriesBinding
import us.wedemy.eggeum.android.updatecafe.viewmodel.SelectInfoCategoriesViewModel

@AndroidEntryPoint
class SelectInfoCategoriesFragment : BaseFragment<FragmentSelectInfoCategoriesBinding>() {
  override fun getViewBinding() = FragmentSelectInfoCategoriesBinding.inflate(layoutInflater)

  private val viewModel by viewModels<SelectInfoCategoriesViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    initListener()
    initObserver()
  }

  private fun initListener() {
    with(binding) {
      // check box에 따른 fragment 이동 필요
      btnSelectCafeMenu.setOnClickListener {
        val action = SelectInfoCategoriesFragmentDirections.actionSelectInfoCategoriesFragmentToSelectCafeMenuFragment()
        findNavController().safeNavigate(action)
      }
    }
  }

  private fun initObserver() {
    // TODO
  }
}
