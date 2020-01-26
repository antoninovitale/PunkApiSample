package com.ninovitale.punkapi.app.details.section.model

/**
 * Created by antoninovitale on 30/08/2017.
 */
class MethodSectionModel(val temp: String?, val duration: Long, var status: Status) {
    var remainingTime: Long = 0
}