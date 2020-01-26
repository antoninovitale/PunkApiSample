package com.ninovitale.punkapi.app.details

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.ninovitale.punkapi.app.BaseActivity
import com.ninovitale.punkapi.app.R
import com.ninovitale.punkapi.app.api.model.Beer
import com.ninovitale.punkapi.app.list.BeerListActivity
import com.ninovitale.punkapi.app.util.MyImageLoader
import com.ninovitale.punkapi.app.viewmodel.BeerProvider
import kotlinx.android.synthetic.main.activity_beer_detail.imgCover
import kotlinx.android.synthetic.main.activity_beer_detail.toolbar

/**
 * An activity representing a single Beer detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [BeerListActivity].
 */
class BeerDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer_detail)
        setSupportActionBar(toolbar)
        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val viewModel: BeerProvider = ViewModelProvider(this,
                baseProvider.provideViewModelSubComponent().viewModelFactory).get(
                BeerProvider::class.java)
        val beer = intent.getSerializableExtra("beer") as? Beer
        if (beer != null) {
            viewModel.setSelectedBeer(beer)
            MyImageLoader.getInstance()?.loadImage(this, beer.imageUrl, imgCover)
        }
        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) { // Create the detail fragment and add it to the activity using a fragment transaction.
            val fragment = BeerDetailsFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.beer_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}