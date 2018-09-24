package dev.challenge.boxio.view.activities;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;

import butterknife.BindView;
import dev.challenge.boxio.R;
import dev.challenge.boxio.application.BoxIoApp;
import dev.challenge.boxio.di.components.DaggerScreenComponent;
import dev.challenge.boxio.util.Layout;

@Layout(id = R.layout.activity_main)
public class MainActivity extends AbstractActivity {

    @BindView(R.id.confirm_fab)
    FloatingActionButton confirmFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    void inject() {
        DaggerScreenComponent.builder()
                .applicationComponent(((BoxIoApp) getApplication()).getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String error) {

    }
}
