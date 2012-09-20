package com.squareup.anatomy;

import android.app.Application;
import dagger.ObjectGraph;

/**
 * Creates and provides access to Dagger's {@link ObjectGraph} instance.
 */
public class ExampleApp extends Application {

  private ObjectGraph objectGraph;

  @Override public void onCreate() {
    super.onCreate();

    objectGraph = ObjectGraph.get(new ExampleModule(this));
  }

  public ObjectGraph objectGraph() {
    return objectGraph;
  }
}
