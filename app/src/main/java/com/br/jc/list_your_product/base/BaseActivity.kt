package com.br.jc.list_your_product.base

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(){
    open fun initViews() {
        //tobe override in implementation class
    }

    open fun observeViewModel() {
        //tobe override in implementation class
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //protected means that the member has the same visibility as one marked as private,
    // but that it is also visible in subclasses.
    protected fun Boolean.toVisibility() = if (this) VISIBLE else GONE
}