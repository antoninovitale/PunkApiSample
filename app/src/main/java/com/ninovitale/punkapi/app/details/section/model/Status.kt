package com.ninovitale.punkapi.app.details.section.model

/**
 * Created by antoninovitale on 31/08/2017.
 */
enum class Status {
    IDLE, RUNNING, PAUSED, DONE;

    companion object {
        @JvmStatic
        fun getStatusLabel(
                s: Status?,
                idle: String?,
                running: String?,
                paused: String?,
                done: String?
        ): String? {
            var label: String? = null
            when (s) {
                IDLE -> label = idle
                RUNNING -> label = running
                PAUSED -> label = paused
                DONE -> label = done
                else -> {
                }
            }
            return label
        }
    }
}