package com.ninovitale.punkapi.app.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ninovitale.punkapi.app.R
import com.ninovitale.punkapi.app.R.layout
import com.ninovitale.punkapi.app.api.model.Beer
import com.ninovitale.punkapi.app.details.section.HeaderSection
import com.ninovitale.punkapi.app.details.section.IngredientSection
import com.ninovitale.punkapi.app.details.section.IngredientSection.OnIngredientStatusClickListener
import com.ninovitale.punkapi.app.details.section.MethodSection
import com.ninovitale.punkapi.app.details.section.MethodSection.OnMethodEndListener
import com.ninovitale.punkapi.app.details.section.MethodSection.OnMethodStatusClickListener
import com.ninovitale.punkapi.app.details.section.MethodSection.OnTimeElapsedListener
import com.ninovitale.punkapi.app.details.section.model.HeaderSectionModel
import com.ninovitale.punkapi.app.details.section.model.IngredientSectionModel
import com.ninovitale.punkapi.app.details.section.model.MethodSectionModel
import com.ninovitale.punkapi.app.details.section.model.Status
import com.ninovitale.punkapi.app.list.BeerListActivity
import com.ninovitale.punkapi.app.viewmodel.BeerProvider
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_beer_detail.sections

/**
 * A fragment representing a single Beer detail screen.
 * This fragment is either contained in a [BeerListActivity]
 * in two-pane mode (on tablets) or a [BeerDetailsActivity]
 * on handsets.
 */
class BeerDetailsFragment : Fragment(), BeerDetailsView, OnIngredientStatusClickListener,
        OnMethodStatusClickListener, OnMethodEndListener, OnTimeElapsedListener {
    private var sectionAdapter: SectionedRecyclerViewAdapter? = null
    private var presenter: BeerDetailsPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        return inflater.inflate(layout.fragment_beer_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        val activity = activity ?: return
        val beerDetailsViewModel: BeerDetailsViewModel = ViewModelProvider(activity).get(
                BeerDetailsViewModel::class.java)
        if (beerDetailsViewModel.getPresenter() == null) {
            beerDetailsViewModel.setPresenter(BeerDetailsPresenterImpl())
        }
        presenter = beerDetailsViewModel.getPresenter()
        presenter?.setView(this) //needed to update the new instance after runtime changes
        val viewModel: BeerProvider = ViewModelProvider(activity).get(BeerProvider::class.java)
        val beerObserver = Observer<Beer?> { beer -> beer?.let { presenter?.onChanged(it) } }
        viewModel.getSelectedBeer()?.observe(viewLifecycleOwner, beerObserver)
    }

    private fun setupRecyclerView() {
        if (sectionAdapter == null) {
            sectionAdapter = SectionedRecyclerViewAdapter()
            sections.adapter = sectionAdapter
            sections.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun setupHeaderSection(headerSectionModel: HeaderSectionModel) {
        val headerSection = HeaderSection(headerSectionModel)
        sectionAdapter?.addSection(HeaderSection.TAG, headerSection)
        sectionAdapter?.notifyItemRangeInserted(0, 1)
    }

    override fun setupMaltsSection(malts: List<IngredientSectionModel>) {
        val sectionTitle = context?.getString(R.string.malt_section_title) ?: ""
        val maltSection = IngredientSection(sectionTitle, malts, this)
        maltSection.isVisible = malts.isNotEmpty()
        sectionAdapter?.addSection(IngredientSection.MALT_TAG, maltSection)
        sectionAdapter?.notifyItemRangeInserted(1, 1)
    }

    override fun setupHopsSection(hops: List<IngredientSectionModel>) {
        val sectionTitle = context?.getString(R.string.hop_section_title) ?: ""
        val hopSection = IngredientSection(sectionTitle, hops, this)
        hopSection.isVisible = hops.isNotEmpty()
        sectionAdapter?.addSection(IngredientSection.HOP_TAG, hopSection)
        sectionAdapter?.notifyItemRangeInserted(2, 1)
    }

    override fun setupMethodSection(methods: List<MethodSectionModel>) {
        val sectionTitle = context?.getString(R.string.method_section_title) ?: ""
        val methodSection = MethodSection(sectionTitle, methods, this, this, this)
        methodSection.isVisible = methods.isNotEmpty()
        sectionAdapter?.addSection(MethodSection.TAG, methodSection)
        sectionAdapter?.notifyItemRangeInserted(3, 1)
    }

    override fun notifyMaltDone(positionInSection: Int, globalPosition: Int) {
        val section = sectionAdapter?.getSection(IngredientSection.MALT_TAG) as IngredientSection
        section.setItemStatus(positionInSection)
        sectionAdapter?.notifyItemChanged(globalPosition)
    }

    override fun notifyHopDone(positionInSection: Int, globalPosition: Int) {
        val section = sectionAdapter?.getSection(IngredientSection.HOP_TAG) as IngredientSection
        section.setItemStatus(positionInSection)
        sectionAdapter?.notifyItemChanged(globalPosition)
    }

    override fun notifyMethodStatusChanged(positionInSection: Int, globalPosition: Int, status: Status) {
        val section = sectionAdapter?.getSection(MethodSection.TAG) as MethodSection
        section.setItemStatus(positionInSection, status)
        sectionAdapter?.notifyItemChanged(globalPosition)
    }

    override fun onStatusClick(position: Int) {
        presenter?.onStatusClick(position)
    }

    override fun onMethodStatusClick(position: Int) {
        presenter?.onMethodStatusClick(position)
    }

    override fun onMethodEnd(position: Int) {
        presenter?.onMethodEnd(position)
    }

    override fun onTimeElapsed(position: Int, millis: Long) {
        presenter?.onMethodTimeElapsed(position, millis)
    }

    override fun clearSections() {
        sectionAdapter?.removeAllSections()
        sectionAdapter?.notifyDataSetChanged()
    }

    companion object {
        const val TAG = "BeerDetailsFragment"
    }
}