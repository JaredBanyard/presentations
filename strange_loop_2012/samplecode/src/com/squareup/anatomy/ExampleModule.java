package com.squareup.anatomy;

import android.content.Context;
import android.location.LocationManager;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(
    entryPoints = {
        AccountActivity.class,
        HomeActivity.class,
        LocationActivity.class,
        LocationPublishFragment.class,
        LocationSubscribeFragment.class
    }
)
public class ExampleModule {
  private final Context appContext;

  public ExampleModule(Context appContext) {
    this.appContext = appContext;
  }

  @Provides @Singleton Bus provideBus() {
    return new Bus();
  }

  @Provides @Singleton LocationManager provideLocationManager() {
    return (LocationManager) appContext.getSystemService(Context.LOCATION_SERVICE);
  }
}
