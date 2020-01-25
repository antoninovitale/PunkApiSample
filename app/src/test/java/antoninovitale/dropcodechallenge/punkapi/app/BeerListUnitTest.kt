package antoninovitale.dropcodechallenge.punkapi.app

import antoninovitale.punkapi.app.api.model.Beer
import antoninovitale.punkapi.app.list.BeerListPresenter
import antoninovitale.punkapi.app.list.BeerListPresenterImpl
import antoninovitale.punkapi.app.list.BeerListView
import antoninovitale.punkapi.app.list.model.mapper.BeerListModelMapper.convertBeers
import antoninovitale.punkapi.app.viewmodel.CurrentStatus
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by antoninovitale on 02/09/2017.
 */
@TestInstance(PER_CLASS)
class BeerListUnitTest {
    @Mock
    lateinit var view: BeerListView
    @Mock
    lateinit var presenter: BeerListPresenter

    @BeforeAll
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = BeerListPresenterImpl(view)
    }

    @Test
    fun checkRefresh() {
        presenter.onRefresh()
        verify(view, times(1)).refreshList()
    }

    @Test
    fun checkCurrentStatus() {
        val currentStatus = CurrentStatus(true, isError = false)
        presenter.onChanged(currentStatus)
        verify(view, times(1)).setRefreshing(currentStatus.isRefreshing)
        verify(view, never()).showError()
    }

    @Test
    fun checkError() {
        val currentStatus = CurrentStatus(false, isError = true)
        presenter.onChanged(currentStatus)
        verify(view, times(1)).setRefreshing(currentStatus.isRefreshing)
        verify(view, times(1)).showError()
    }

    @Test
    fun checkList() {
        val beers: List<Beer?> = emptyList()
        presenter.onChanged(beers)
        verify(view, times(1)).setItems(convertBeers(beers))
    }

    @Test
    fun checkListItemClick() {
        presenter.onListItemClick(0)
        verify(view, times(1)).selectItem(0)
        verify(view, times(1)).navigateToDetails()
    }
}