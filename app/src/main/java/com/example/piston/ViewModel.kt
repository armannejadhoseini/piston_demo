package com.example.piston

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

class ViewModel: ViewModel() {

    private lateinit var navController: NavHostController

    fun getNavController():NavHostController {
        return navController
    }
    fun setNavController(navController: NavHostController){
        this.navController = navController
    }

}