package dio.com.soccernews.data;

import androidx.room.Room;

import dio.com.soccernews.App;
import dio.com.soccernews.data.local.SoccerNewsDb;
import dio.com.soccernews.data.remote.SoccerNewsRestAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoccerNewsRepository {
    private static final String REMOTE_API = "https://flaviopc.github.io/matches-api/";
    private static final String LOCAL_DB = "database-soccer-news";

    private final SoccerNewsRestAPI remoteApi;
    private final SoccerNewsDb localDb;

    private SoccerNewsRepository() {
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsRestAPI.class);

        localDb = Room.databaseBuilder(App.getInstance(),
                SoccerNewsDb.class, LOCAL_DB).build();
    }

    public static SoccerNewsRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    public SoccerNewsRestAPI getRemoteApi() {
        return remoteApi;
    }

    public SoccerNewsDb getLocalDb() {
        return localDb;
    }

    private static class LazyHolder {
        private static final SoccerNewsRepository INSTANCE = new SoccerNewsRepository();
    }
}
