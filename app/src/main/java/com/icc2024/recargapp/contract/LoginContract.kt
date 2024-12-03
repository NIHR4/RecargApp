package com.icc2024.recargapp.contract

interface LoginContract {
    interface View{
        fun displayErrorMessage()
        fun changeToMainMenu()
    }

    interface Presenter {
        fun tryLogin(username: String, password: String)
    }
}