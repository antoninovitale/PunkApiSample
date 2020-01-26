package com.ninovitale.punkapi.app.di.subcomponent

import androidx.lifecycle.ViewModelProvider
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    val viewModelFactory: ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelSubComponent
    }
}