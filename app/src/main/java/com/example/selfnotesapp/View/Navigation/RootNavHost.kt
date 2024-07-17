package com.example.selfnotesapp.View.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.selfnotesapp.View.Screen.HomeScreen
import com.example.selfnotesapp.View.Screen.EmailSignInScreen
import com.example.selfnotesapp.View.Screen.EmailSignUpScreen
import com.example.selfnotesapp.Viewmodel.AuthViewModel

@Composable
fun RootNavHost(authViewModel: AuthViewModel){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Routes.LoginScreen, builder = {
        composable(Routes.LoginScreen){
            EmailSignInScreen( navController = navController, authViewModel = authViewModel)
        }

        composable(Routes.SignUpScreen){
            EmailSignUpScreen( navController = navController, authViewModel = authViewModel)
        }

        composable(Routes.HomeScreen){
            HomeScreen(navController = navController, authViewModel = authViewModel)

        }
    })
}