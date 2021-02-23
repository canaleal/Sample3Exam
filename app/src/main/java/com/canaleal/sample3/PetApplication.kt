package com.canaleal.sample3

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PetApplication: Application(){
    init {
        instance = this
    }

    companion object {
        private var instance: PetApplication? = null

        @Override
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }


}