package antoninovitale.punkapi.app.details.section

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import antoninovitale.punkapi.app.R
import antoninovitale.punkapi.app.details.section.model.MethodSectionModel
import antoninovitale.punkapi.app.details.section.model.Status
import antoninovitale.punkapi.app.details.section.model.Status.Companion.getStatusLabel
import antoninovitale.punkapi.app.details.section.model.Status.DONE
import antoninovitale.punkapi.app.details.section.model.Status.IDLE
import antoninovitale.punkapi.app.details.section.model.Status.PAUSED
import antoninovitale.punkapi.app.details.section.model.Status.RUNNING
import antoninovitale.punkapi.app.details.section.viewholder.MethodSectionViewHolder
import antoninovitale.punkapi.app.details.section.viewholder.SectionTitleViewHolder
import antoninovitale.punkapi.app.util.countdownview.CountdownView
import antoninovitale.punkapi.app.util.countdownview.CountdownView.OnCountdownEndListener
import antoninovitale.punkapi.app.util.countdownview.CountdownView.OnCountdownIntervalListener
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import kotlinx.android.synthetic.main.beer_detail_section_method.view.name
import kotlinx.android.synthetic.main.beer_detail_section_method.view.status
import kotlinx.android.synthetic.main.beer_detail_section_method.view.timer
import kotlinx.android.synthetic.main.simple_header.view.header_title

/**
 * Created by antoninovitale on 29/08/2017.
 */
class MethodSection constructor(
        private val sectionTitle: String,
        private val methods: List<MethodSectionModel>,
        private val onMethodStatusClickListener: OnMethodStatusClickListener,
        private val onMethodEndListener: OnMethodEndListener,
        private val onTimeElapsedListener: OnTimeElapsedListener
) : Section(sectionParameters) {
    fun setItemStatus(position: Int, status: Status) {
        methods[position].status = status
    }

    override fun getContentItemsTotal(): Int {
        return methods.size
    }

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return SectionTitleViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder) {
        holder.itemView.header_title.text = sectionTitle
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return MethodSectionViewHolder(view)
    }

    override fun onBindItemViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val vh: MethodSectionViewHolder = viewHolder as MethodSectionViewHolder
        val model: MethodSectionModel = methods[i]
        vh.itemView.name.text = model.temp
        val duration: Long = model.duration
        if (duration == 0L) {
            vh.itemView.timer.visibility = View.GONE
        } else {
            vh.itemView.timer.visibility = View.VISIBLE
            val status: Status = model.status
            vh.itemView.status.text = getStatusLabel(status, vh.idle, vh.running, vh.paused,
                    vh.done)
            when (status) {
                IDLE -> vh.itemView.timer.updateShow(duration)
                RUNNING -> if (model.remainingTime == 0L) {
                    vh.itemView.timer.start(duration)
                } else {
                    vh.itemView.timer.start(model.remainingTime)
                }
                PAUSED -> {
                    vh.itemView.timer.pause()
                    vh.itemView.timer.updateShow(model.remainingTime)
                }
                DONE -> vh.itemView.timer.stop()
            }
            vh.itemView.timer.setOnCountdownEndListener(object : OnCountdownEndListener {
                override fun onEnd(cv: CountdownView?) {
                    onMethodEndListener.onMethodEnd(vh.adapterPosition)
                }
            })
            vh.itemView.timer.setOnCountdownIntervalListener(1000,
                    object : OnCountdownIntervalListener {
                        override fun onInterval(cv: CountdownView?, remainTime: Long) {
                            onTimeElapsedListener.onTimeElapsed(vh.adapterPosition, remainTime)
                        }
                    })
        }
        vh.itemView.status.setOnClickListener {
            onMethodStatusClickListener.onMethodStatusClick(vh.adapterPosition)
        }
    }

    interface OnMethodStatusClickListener {
        fun onMethodStatusClick(position: Int)
    }

    interface OnMethodEndListener {
        fun onMethodEnd(position: Int)
    }

    interface OnTimeElapsedListener {
        fun onTimeElapsed(position: Int, millis: Long)
    }

    companion object {
        val TAG: String = MethodSection::class.java.simpleName

        private val sectionParameters: SectionParameters
            get() {
                return SectionParameters.builder()
                        .itemResourceId(R.layout.beer_detail_section_method)
                        .headerResourceId(R.layout.simple_header)
                        .build()
            }
    }
}