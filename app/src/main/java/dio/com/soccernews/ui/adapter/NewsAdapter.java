package dio.com.soccernews.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dio.com.soccernews.databinding.NewsItemBinding;
import dio.com.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final List<News> news;
    private final View.OnClickListener favoriteListener;

    public NewsAdapter(List<News> news, View.OnClickListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.getTitle());
        holder.binding.tvSubtitle.setText(news.getDescription());

        holder.binding.btOpenLink.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.getLink()));
            holder.itemView.getContext().startActivity(i);
        });

        holder.binding.ivShare.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, news.getTitle());
            i.putExtra(Intent.EXTRA_TEXT, news.getLink());
            holder.itemView.getContext().startActivity(Intent.createChooser(i, "Compartilhar"));
        });

        holder.binding.ivFavorite.setOnClickListener(this.favoriteListener);

        Picasso.get().load(news.getImage())
                .into(holder.binding.ivThumbnail);

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
