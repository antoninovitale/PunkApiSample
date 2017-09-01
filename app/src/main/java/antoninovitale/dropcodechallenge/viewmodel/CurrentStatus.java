package antoninovitale.dropcodechallenge.viewmodel;

/**
 * Created by antoninovitale on 01/09/2017.
 */
public class CurrentStatus {
    private boolean refreshing;

    private boolean error;

    public CurrentStatus(boolean refreshing, boolean error) {
        this.refreshing = refreshing;
        this.error = error;
    }

    public boolean isRefreshing() {
        return refreshing;
    }

    public boolean isError() {
        return error;
    }

}