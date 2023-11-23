package com.example.codeclubapp.src.di

import androidx.room.Room
import com.example.codeclubapp.src.db.DatabaseManager
import com.example.codeclubapp.src.db.user.UserDaoImpl
import com.example.codeclubapp.src.retrofit.AppRetrofit
import com.example.codeclubapp.src.ui.viewmodel.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single {
        synchronized(this) {
            Room.databaseBuilder(
                androidContext(),
                DatabaseManager::class.java,
                "database_code_club"
            ).fallbackToDestructiveMigration().build()
        }
    }
}

val viewModelModule = module {
    viewModel {
        SignUpViewModel(get(), get())
    }
}

val daoImplModule = module {
    single<UserDaoImpl> {
        UserDaoImpl(get())
    }
}

val appRetrofit = module {
    single<AppRetrofit> {
        AppRetrofit()
    }
}