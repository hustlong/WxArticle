package com.autoinhome.autolight.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.autoinhome.autolight.R;
import com.autoinhome.autolight.entity.WxArticleCategory;
import com.autoinhome.autolight.util.EmptyUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanglong on 2016/10/26.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private LayoutInflater mInflater;

    private List<WxArticleCategory> categories;

    private OnItemClickListener onItemClickListener;

    public CategoryAdapter(Context context,OnItemClickListener onItemClickListener) {
        mInflater = LayoutInflater.from(context);
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<WxArticleCategory> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }


    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_category_layout,parent,false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        holder.setData(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return EmptyUtil.isEmpty(categories) ? 0:categories.size();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /** 点击监听接口 */
    public interface OnItemClickListener {
        void onClick(int pos,WxArticleCategory category);
    }

    /** 自定义ViewHolder */
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnItemClickListener onItemClickListener;
        private WxArticleCategory category;

        ViewHolder(View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
        }

        void setData(WxArticleCategory category) {
            this.category = category;
            name.setText(category.getName());
        }

        @BindView(R.id.category_name)
        TextView name;

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onClick(getAdapterPosition(),category);
        }
    }

    /** 自定义间距 */
    public static class ItemDecoration extends RecyclerView.ItemDecoration {
        int space;
        int column;

        public ItemDecoration(int space,int column) {
            this.column = column!=0 ? column : 1;
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = space;
            outRect.bottom = space;
            //由于每行都只有column个，所以第一个都是column的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) %column==0) {
                outRect.left = 0;
            }
        }
    }
}
