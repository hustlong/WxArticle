package com.autoinhome.autolight.presenter;

import com.autoinhome.autolight.base.BasePresenter;
import com.autoinhome.autolight.entity.WxArticleCategory;
import com.autoinhome.autolight.model.CategoryModel;
import com.autoinhome.autolight.util.EmptyUtil;
import com.autoinhome.autolight.view.CategoryView;

import java.util.List;

/**
 * Created by yanglong on 2016/10/25.
 */

public class CategoryPresenter implements BasePresenter<CategoryView>,CategoryPresenterInterface{

    private CategoryView view;
    private CategoryModel model;

    public CategoryPresenter(CategoryView view) {
        attachView(view);
        model = new CategoryModel(this);
    }

    public void loadCategory() {
        model.loadCategoryFromLocal();
    }


    @Override
    public void loadCategoryComplete(List<WxArticleCategory> categories) {
        view.showCategory(categories);
    }

    @Override
    public void loadError() {
        view.showError();
    }

    @Override
    public void attachView(CategoryView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
