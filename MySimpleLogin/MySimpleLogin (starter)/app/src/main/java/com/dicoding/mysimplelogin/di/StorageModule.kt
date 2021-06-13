package com.dicoding.mysimplelogin.di

import android.content.Context
import com.briancatraguna.core.SessionManager
import dagger.Module
import dagger.Provides

@Module
class StorageModule {
    @Provides
    fun provideSessionManager(context: Context): SessionManager = SessionManager(context)
}