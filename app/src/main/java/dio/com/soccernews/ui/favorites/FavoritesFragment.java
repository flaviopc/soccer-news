package dio.com.soccernews.ui.favorites;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import dio.com.soccernews.MainActivity;
import dio.com.soccernews.databinding.FragmentFavoritesBinding;
import dio.com.soccernews.domain.News;
import dio.com.soccernews.ui.adapter.NewsAdapter;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        loadFavoritesNews();

        return binding.getRoot();
    }

    private void loadFavoritesNews() {
        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;
        binding.rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));

        List<News> favorites = activity.getDb().newsDao().loadFavoriteNews();

        binding.rvFavorites.setAdapter(new NewsAdapter(favorites, favoriteNews -> {
            AsyncTask.execute(() -> {
                activity.getDb().newsDao().save(favoriteNews);
            });
            loadFavoritesNews();
        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}