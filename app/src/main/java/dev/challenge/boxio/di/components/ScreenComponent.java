package dev.challenge.boxio.di.components;

import dagger.Component;
import dev.challenge.boxio.view.activities.MainActivity;
import dev.challenge.boxio.util.ScreenScope;

@ScreenScope
@Component(dependencies = {ApplicationComponent.class})
public interface ScreenComponent {

    void inject(MainActivity activity);
}
