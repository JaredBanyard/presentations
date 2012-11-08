package com.squareup.anatomy;

import android.app.Activity;
import android.os.Bundle;
import com.squareup.otto.Bus;
import javax.inject.Inject;

/**
 * All activities should extend this for dependency injection.
 */
public class BaseActivity extends Activity {

  @Inject Bus bus;

  @Override protected void onCreate(Bundle state) {
    super.onCreate(state);

    // Android constructs Activity instances so we must find the ObjectGraph
    // instance and inject this.
    ((ExampleApp) getApplication()).objectGraph().inject(this);
  }

  @Override protected void onResume() {
    super.onResume();
    bus.register(this);
  }

  @Override protected void onPause() {
    super.onPause();
    bus.unregister(this);
  }
}
