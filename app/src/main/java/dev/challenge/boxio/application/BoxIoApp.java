package dev.challenge.boxio.application;

import android.app.Application;

import dev.challenge.boxio.di.components.ApplicationComponent;
import dev.challenge.boxio.di.components.DaggerApplicationComponent;
import dev.challenge.boxio.di.modules.ApplicationModule;

public class BoxIoApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initApplicationComponent();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
