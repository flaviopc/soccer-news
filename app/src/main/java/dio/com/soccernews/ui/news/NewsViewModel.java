package dio.com.soccernews.ui.news;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dio.com.soccernews.data.SoccerNewsRepository;
import dio.com.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<State> state = new MutableLiveData<>();

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();

    private void findNews() {
        state.setValue(State.DOING);
        SoccerNewsRepository.getInstance().getRemoteApi().getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                    state.setValue(State.DONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                state.setValue(State.ERROR);
            }
        });
    }

    public NewsViewModel() {
        this.findNews();
    }

    public void saveNews(News news) {
        AsyncTask.execute(() -> SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news));
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }

    public LiveData<State> getState() {
        return this.state;
    }

    public enum State {
        DOING, DONE, ERROR
    }
}