package dev.challenge.boxio.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dev.challenge.boxio.di.modules.ApplicationModule;
import dev.challenge.boxio.util.SharedPreferencesManager;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context context();

    SharedPreferencesManager sharedPreferences();
}
