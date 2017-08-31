package antoninovitale.dropcodechallenge.list;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import antoninovitale.dropcodechallenge.R;
import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.details.BeerDetailsActivity;
import antoninovitale.dropcodechallenge.details.BeerDetailsFragment;
import antoninovitale.dropcodechallenge.list.adapter.ItemRecyclerViewAdapter;
import antoninovitale.dropcodechallenge.list.model.BeerListModel;
import antoninovitale.dropcodechallenge.list.viewmodel.BeerProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * An activity representing a list of Beers. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link BeerDetailsActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class BeerListActivity extends AppCompatActivity implements LifecycleRegistryOwner,
        BeerListContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Nullable
    @BindView(R.id.beer_detail_container)
    View beerDetailContainer;

    @BindView(R.id.beer_list)
    RecyclerView beerList;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private Unbinder unbinder;

    private ItemRecyclerViewAdapter recyclerViewAdapter;

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    private BeerProvider viewModel;

    private BeerListContract.Actions presenter;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);
        unbinder = ButterKnife.bind(this);
        presenter = new BeerListPresenter(this);
        setSupportActionBar(toolbar);

        setupRecyclerView(beerList);
        setupObservers();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });

        if (beerDetailContainer != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupObservers() {
        viewModel = ViewModelProviders.of(this).get(BeerProvider.class);
        viewModel.getBeers().observe(this, new Observer<List<Beer>>() {

            @Override
            public void onChanged(@Nullable List<Beer> beers) {
                presenter.onChanged(beers);
            }

        });
        viewModel.getRefreshing().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null) {
                    swipeRefreshLayout.setRefreshing(aBoolean);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void showError() {
        Snackbar.make(beerList, R.string.generic_error, Snackbar.LENGTH_SHORT).show();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        recyclerViewAdapter = new ItemRecyclerViewAdapter(new ItemRecyclerViewAdapter
                .OnItemClickListener() {

            @Override
            public void onClick(int position) {
                presenter.onListItemClick(position);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        presenter.onFloatingButtonClick();
    }

    @Override
    public void refreshList() {
        viewModel.loadBeers();
    }

    @Override
    public void setItems(List<BeerListModel> models) {
        recyclerViewAdapter.setItems(models);
    }

    @Override
    public void selectItem(int position) {
        viewModel.setSelectedBeer(position);
    }

    @Override
    public void navigateToDetails() {
        if (mTwoPane) {
            BeerDetailsFragment fragment = new BeerDetailsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.beer_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(BeerListActivity.this, BeerDetailsActivity.class);
            intent.putExtra("beer", viewModel.getSelectedBeer().getValue());
            startActivity(intent);
        }
    }

}