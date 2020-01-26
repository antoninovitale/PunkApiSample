package com.ninovitale.punkapi.app.list

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.google.android.material.snackbar.Snackbar
import com.ninovitale.punkapi.app.R
import com.ninovitale.punkapi.app.details.BeerDetailsActivity
import com.ninovitale.punkapi.app.details.BeerDetailsFragment
import com.ninovitale.punkapi.app.list.adapter.ItemRecyclerViewAdapter
import com.ninovitale.punkapi.app.list.adapter.ItemRecyclerViewAdapter.OnItemClickListener
import com.ninovitale.punkapi.app.list.model.IBeerListModel
import com.ninovitale.punkapi.app.viewmodel.BeerProvider
import kotlinx.android.synthetic.main.activity_beer_list.swipeRefreshLayout
import kotlinx.android.synthetic.main.activity_beer_list.toolbar
import kotlinx.android.synthetic.main.beer_list.beer_detail_container
import kotlinx.android.synthetic.main.beer_list.beer_list

/**
 * An activity representing a list of Beers. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [BeerDetailsActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class BeerListActivity : AppCompatActivity(), BeerListView, OnItemClickListener, OnRefreshListener {
    private var recyclerViewAdapter: ItemRecyclerViewAdapter? = null
    private var viewModel: BeerProvider? = null
    private var presenter: BeerListPresenter? = null
    private var recyclerViewState: Parcelable? = null
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_list)
        presenter = BeerListPresenterImpl(this)
        setSupportActionBar(toolbar)
        setupRecyclerView()
        setupObservers()
        swipeRefreshLayout.setOnRefreshListener(this)
        if (beer_detail_container != null) {
            // The detail container view will be present only in the large-screen layouts (res/values-w900dp). If this view is present, then the  activity should be in two-pane mode.
            mTwoPane = true
        }
    }

    private fun setupRecyclerView() {
        beer_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)
        recyclerViewAdapter = ItemRecyclerViewAdapter(this)
        beer_list.adapter = recyclerViewAdapter
    }

    private fun setupObservers() {
        viewModel = ViewModelProvider(this).get(BeerProvider::class.java)
        viewModel?.getBeers()?.observe(this, Observer { beers -> presenter?.onChanged(beers) })
        viewModel?.getCurrentStatus()?.observe(this,
                Observer { currentStatus -> presenter?.onChanged(currentStatus) })
    }

    override fun onPause() {
        recyclerViewState = beer_list.layoutManager?.onSaveInstanceState()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        recyclerViewState?.let {
            beer_list.layoutManager?.onRestoreInstanceState(recyclerViewState)
        }
    }

    override fun onDestroy() {
        swipeRefreshLayout.setOnRefreshListener(null)
        super.onDestroy()
    }

    override fun setRefreshing(value: Boolean) {
        swipeRefreshLayout.isRefreshing = value
    }

    override fun refreshList() {
        viewModel?.loadBeers()
    }

    override fun setItems(models: List<IBeerListModel?>?) {
        recyclerViewAdapter?.setItems(models)
    }

    override fun selectItem(position: Int) {
        viewModel?.setSelectedBeer(position)
    }

    override fun navigateToDetails() {
        if (mTwoPane) {
            val previousFragment = supportFragmentManager.findFragmentByTag(BeerDetailsFragment.TAG)
            if (previousFragment != null) {
                return
//                supportFragmentManager.beginTransaction().remove(previousFragment).commitNow()
            }
            val fragment = BeerDetailsFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.beer_detail_container, fragment, BeerDetailsFragment.TAG)
                    .commit()
        } else {
            val intent = Intent(this@BeerListActivity, BeerDetailsActivity::class.java)
            intent.putExtra("beer", viewModel?.getSelectedBeer()?.value)
            startActivity(intent)
        }
    }

    override fun showError() {
        Snackbar.make(beer_list, R.string.generic_error, Snackbar.LENGTH_SHORT).show()
    }

    override fun onItemClick(position: Int) {
        presenter?.onListItemClick(position)
    }

    override fun onRefresh() {
        presenter?.onRefresh()
    }
}