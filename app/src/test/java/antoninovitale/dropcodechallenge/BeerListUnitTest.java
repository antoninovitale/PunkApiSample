package antoninovitale.dropcodechallenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import antoninovitale.dropcodechallenge.api.model.Beer;
import antoninovitale.dropcodechallenge.list.BeerListPresenter;
import antoninovitale.dropcodechallenge.list.BeerListPresenterImpl;
import antoninovitale.dropcodechallenge.list.BeerListView;
import antoninovitale.dropcodechallenge.list.model.mapper.BeerListModelMapper;
import antoninovitale.dropcodechallenge.viewmodel.CurrentStatus;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BeerListUnitTest {

    @Mock
    BeerListView view;

    @Mock
    BeerListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new BeerListPresenterImpl(view);
    }

    @Test
    public void checkFloatingButtonClick() {
        presenter.onFloatingButtonClick();
        verify(view, times(1)).refreshList();
    }

    @Test
    public void checkRefresh() {
        presenter.onRefresh();
        verify(view, times(1)).refreshList();
    }

    @Test
    public void checkCurrentStatus() {
        CurrentStatus currentStatus = new CurrentStatus(true, false);
        presenter.onChanged(currentStatus);
        verify(view, times(1)).setRefreshing(currentStatus.isRefreshing());
        verify(view, never()).showError();
    }
    @Test
    public void checkError() {
        CurrentStatus currentStatus = new CurrentStatus(false, true);
        presenter.onChanged(currentStatus);
        verify(view, times(1)).setRefreshing(currentStatus.isRefreshing());
        verify(view, times(1)).showError();
    }

    @Test
    public void checkList() {
        List<Beer> beers = Collections.emptyList();
        presenter.onChanged(beers);
        verify(view, times(1)).setItems(BeerListModelMapper.convertBeers(beers));
    }

    @Test
    public void checkListItemClick() {
        presenter.onListItemClick(0);
        verify(view, times(1)).selectItem(0);
        verify(view, times(1)).navigateToDetails();
    }

}