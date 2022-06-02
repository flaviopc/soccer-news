package dio.com.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import dio.com.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        news = new MutableLiveData<>();

        List<News> newsList = new ArrayList<>();
        newsList.add(new News("Titulo 1", "Descrição 1"));
        newsList.add(new News("Titulo 2", "Descrição 2"));
        newsList.add(new News("Titulo 3", "Descrição 3"));
        newsList.add(new News("Titulo 4", "Descrição 4"));

        this.news.setValue(newsList);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}