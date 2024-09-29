package com.br.jc.list_your_product

import android.os.Bundle
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.br.jc.list_your_product.base.BaseActivity
import com.br.jc.list_your_product.databinding.ActivityListyourproductmainBinding
import com.br.jc.list_your_product.login.viewmodel.LoginViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListYourProductMainActivity : BaseActivity() {
    private lateinit var binding: ActivityListyourproductmainBinding
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth
    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListyourproductmainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        System.out.println("teste")
        //here the activity_main, the xml of this Activity main class, is set in a variable as the
        //main container to allocate the navigate. But for it work, in this layout need having a
        // FragmentContainerView look that nav_host_fragment is the id of FragmentContainerView
        // who is inside in activity_main.xml.
        val navFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //here the above container is gaven to the attribute navController
        navController = navFragment.navController
        auth = Firebase.auth
        observeViewModel()

    }

    override fun onStart() {
        super.onStart()
        if (auth == null) {
           // navController?.setGraph(R.navigation.feature_login)
            navController?.setGraph(R.navigation.feature_wait_rest)
        } else {
            navController?.setGraph(R.navigation.feature_wait_rest)
        }

    }

    override fun observeViewModel() {
        super.observeViewModel()
        loginViewModel.isLogged.observe(this){
            // setar o navcontroller aqui.
        }
    }
}