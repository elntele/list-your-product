package com.br.jc.list_your_product.base

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

open class BaseFragment: Fragment() {

        open fun initViews() {
            //tobe override in implementation class
        }

        open fun observeViewModel() {
            //tobe override in implementation class
        }

        protected fun showToast(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        protected fun navigateUp(){
            activity?.onBackPressed()
        }

        protected fun navigateFragmentBackPressed(){
            val fragmentManager = fragmentManager
            fragmentManager?.popBackStack(
                fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 2)
                    .getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        protected fun Boolean.toVisibility() = if (this) View.VISIBLE else View.GONE



}