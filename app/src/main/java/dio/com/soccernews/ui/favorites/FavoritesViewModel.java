package dio.com.soccernews.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dio.com.soccernews.data.SoccerNewsRepository;
import dio.com.soccernews.domain.News;

public class FavoritesViewModel extends ViewModel {

    public FavoritesViewModel() {
    }

    public LiveData<List<News>> loadFavoriteNews() {
        return SoccerNewsRepository.getInstance().getLocalDb().newsDao().loadFavoriteNews();
    }

    public void saveNews(News news) {
        SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news);
    }
}