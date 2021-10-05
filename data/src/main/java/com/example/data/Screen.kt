package com.example.data

sealed class Screen(val route: String, val title: String, val icon: Int) {
    object Home : Screen("Home", "Home", R.drawable.ic_home)
    object Lessons : Screen("Lessons", "Lessons", R.drawable.ic_course)
    object Quizes : Screen("Quizes", "Quizes", R.drawable.ic_exam)
    object More : Screen("More", "More", R.drawable.ic_category)
    object SignIn : Screen("SignIn", "SignIn", R.drawable.ic_category)
    object Profile : Screen("Profile", "Profile", R.drawable.ic_category)

    object SignVerifyCode : Screen("SignVerifyCode", "SignVerifyCode", R.drawable.ic_category)
    object SignInvalidCode : Screen("SignInvalidCode", "SignInvalidCode", R.drawable.ic_category)

}