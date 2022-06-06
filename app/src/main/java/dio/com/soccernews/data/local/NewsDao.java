package dio.com.soccernews.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import dio.com.soccernews.domain.News;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news WHERE favorite = :favorite")
    List<News> loadFavoriteNews(boolean favorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);
}
