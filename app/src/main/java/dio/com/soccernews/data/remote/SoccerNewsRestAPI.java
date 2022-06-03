package dio.com.soccernews.data.remote;

import java.util.List;

import dio.com.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerNewsRestAPI {
    @GET("news.json")
    Call<List<News>> getNews();

}
