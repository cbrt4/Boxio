package dev.challenge.boxio.presenters;

import dev.challenge.boxio.view.AbstractView;

public abstract class AbstractPresenter<V extends AbstractView> {

    private V view;

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }

    abstract void cancel();

    public void destroy() {
        setView(null);
    }
}
