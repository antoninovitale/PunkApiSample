package antoninovitale.dropcodechallenge.details;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.details.section.HeaderSection;
import antoninovitale.dropcodechallenge.details.section.IngredientSection;
import antoninovitale.dropcodechallenge.details.section.MethodSection;
import antoninovitale.dropcodechallenge.details.section.model.HeaderSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.IngredientSectionModel;
import antoninovitale.dropcodechallenge.details.section.model.MethodSectionModel;
import antoninovitale.dropcodechallenge.details.viewmodel.BeerDetailProvider;
import antoninovitale.dropcodechallenge.list.BeerListActivity;
import antoninovitale.dropcodechallenge.list.viewmodel.BeerProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * A fragment representing a single Beer detail screen.
 * This fragment is either contained in a {@link BeerListActivity}
 * in two-pane mode (on tablets) or a {@link BeerDetailsActivity}
 * on handsets.
 */
public class BeerDetailsFragment extends LifecycleFragment implements BeerDetailsContract.View {
    @BindView(R.id.sections)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    private SectionedRecyclerViewAdapter sectionAdapter;

    private BeerDetailsContract.Actions presenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BeerDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beer_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        presenter = new BeerDetailsPresenter(this);
        setupRecyclerView();
        setupObservers();
        return rootView;
    }

    private void setupObservers() {
        Observer<Beer> beerObserver = new Observer<Beer>() {

            @Override
            public void onChanged(@Nullable Beer beer) {
                presenter.onChanged(beer);
            }
        };
        BeerProvider viewModel = ViewModelProviders.of(getActivity()).get(BeerProvider.class);
        if (viewModel != null) {
            viewModel.getSelectedBeer().observe(this, beerObserver);
        }
        BeerDetailProvider beerDetailViewModel = ViewModelProviders.of(getActivity()).get
                (BeerDetailProvider.class);
        if (beerDetailViewModel != null) {
            beerDetailViewModel.getBeer().observe(this, beerObserver);
        }
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sectionAdapter = new SectionedRecyclerViewAdapter();
        recyclerView.setAdapter(sectionAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setupHeaderSection(HeaderSectionModel headerSectionModel) {
        HeaderSection headerSection = new HeaderSection(headerSectionModel);
        sectionAdapter.addSection(HeaderSection.TAG, headerSection);
        sectionAdapter.notifyItemRangeInserted(0, 1);
    }

    @Override
    public void setupMaltsSection(List<IngredientSectionModel> malts) {
        IngredientSection maltSection = new IngredientSection(presenter, getString(R.string
                .malt_section_title), malts);
        maltSection.setVisible(malts != null && !malts.isEmpty());
        sectionAdapter.addSection(IngredientSection.MALT_TAG, maltSection);
        sectionAdapter.notifyItemRangeInserted(1, 1);
    }

    @Override
    public void setupHopsSection(List<IngredientSectionModel> hops) {
        IngredientSection hopSection = new IngredientSection(presenter, getString(R.string
                .hop_section_title), hops);
        hopSection.setVisible(hops != null && !hops.isEmpty());
        sectionAdapter.addSection(IngredientSection.HOP_TAG, hopSection);
        sectionAdapter.notifyItemRangeInserted(2, 1);
    }

    @Override
    public void setupMethodSection(List<MethodSectionModel> methods) {
        MethodSection methodSection = new MethodSection(getString(R.string
                .method_section_title), methods);
        methodSection.setVisible(methods != null && !methods.isEmpty());
        sectionAdapter.addSection(MethodSection.TAG, methodSection);
        sectionAdapter.notifyItemRangeInserted(3, 1);
    }

    @Override
    public void notifyMaltSection(int position) {
        IngredientSection section = (IngredientSection) sectionAdapter.getSection
                (IngredientSection.MALT_TAG);
        section.setItemStatus(position);
        sectionAdapter.notifyItemChangedInSection(IngredientSection.MALT_TAG, position);
    }

    @Override
    public void notifyHopSection(int position) {
        IngredientSection section = (IngredientSection) sectionAdapter.getSection
                (IngredientSection.HOP_TAG);
        section.setItemStatus(position);
        sectionAdapter.notifyItemChangedInSection(IngredientSection.HOP_TAG, position);
    }

}