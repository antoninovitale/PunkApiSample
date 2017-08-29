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

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.details.section.HeaderSection;
import antoninovitale.dropcodechallenge.details.section.model.HeaderSectionModel;
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
 * in two-pane mode (on tablets) or a {@link BeerDetailActivity}
 * on handsets.
 */
public class BeerDetailFragment extends LifecycleFragment {
    @BindView(R.id.sections)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    private SectionedRecyclerViewAdapter sectionAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BeerDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.beer_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sectionAdapter = new SectionedRecyclerViewAdapter();
        recyclerView.setAdapter(sectionAdapter);
        BeerProvider viewModel = ViewModelProviders.of(getActivity()).get(BeerProvider.class);
        Observer<Beer> beerObserver = new Observer<Beer>() {

            @Override
            public void onChanged(@Nullable Beer beer) {
                setupSections(beer);
            }
        };
        if (viewModel != null) {
            viewModel.getSelectedBeer().observe(this, beerObserver);
        }
        BeerDetailProvider beerDetailViewModel = ViewModelProviders.of(getActivity()).get
                (BeerDetailProvider.class);
        if (beerDetailViewModel != null) {
            beerDetailViewModel.getBeer().observe(this, beerObserver);
        }
        return rootView;
    }

    //TODO setup sections
    private void setupSections(Beer beer) {
        if (beer != null) {
            HeaderSectionModel headerSectionModel = new HeaderSectionModel(beer.getName(), beer
                    .getAbv(), beer.getDescription());
            HeaderSection headerSection = new HeaderSection(headerSectionModel);
            sectionAdapter.addSection(HeaderSection.TAG, headerSection);
            sectionAdapter.notifyItemRangeInserted(0, 1);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}