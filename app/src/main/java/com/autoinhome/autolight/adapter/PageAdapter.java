package com.autoinhome.autolight.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.autoinhome.autolight.R;
import com.autoinhome.autolight.entity.WxArticle;
import com.autoinhome.autolight.util.EmptyUtil;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanglong on 2016/10/26.
 */

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder> {

    private LayoutInflater mInflater;

    private List<WxArticle> articles;

    private OnItemClickListener onItemClickListener;

    public PageAdapter(Context context,OnItemClickListener onItemClickListener) {
        mInflater = LayoutInflater.from(context);
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<WxArticle> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_page,parent,false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return EmptyUtil.isEmpty(articles) ? 0:articles.size();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    /** 点击监听接口 */
    public interface OnItemClickListener {
        void onClick(int pos,WxArticle article);
    }

    /** 自定义ViewHolder */
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickListener onItemClickListener;
        WxArticle article;

        @BindView(R.id.mainTitle)
        TextView title;
        @BindView(R.id.subTitle)
        TextView subTitle;
        @BindView(R.id.pubTime)
        TextView pubTime;

        ViewHolder(View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
        }

        void setData(WxArticle article) {
            this.article = article;
            title.setText(article.getTitle());
            subTitle.setText(article.getSubTitle());
            pubTime.setText(article.getPubTime());
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onClick(getAdapterPosition(),article);
        }
    }

    /** 自定义间距 */
    public static class ItemDecoration extends RecyclerView.ItemDecoration {
        int space;

        public ItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.top = space;
            if (parent.getChildLayoutPosition(view) == 0)
                outRect.top = 0;
        }
    }
}
