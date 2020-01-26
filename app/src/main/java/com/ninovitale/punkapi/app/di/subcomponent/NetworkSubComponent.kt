package com.ninovitale.punkapi.app.di.subcomponent

import com.ninovitale.punkapi.app.api.PunkAPI
import com.ninovitale.punkapi.app.api.PunkService
import dagger.Subcomponent

@Subcomponent
interface NetworkSubComponent {
    val punkService: PunkService

    val punkApi: PunkAPI

    @Subcomponent.Factory
    interface Factory {
        fun create(): NetworkSubComponent
    }
}