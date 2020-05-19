package com.example.jigsolveclient.base;

public abstract class BasePresenter <T extends BaseView> {

    protected T view;

    public void setView(T view) { this.view = view; }

    public void created() {}
    public void resume() {}
    public void pause() {}

    protected void showError(final String title, final String message) {
        view.showError(title, message);
    }
}
