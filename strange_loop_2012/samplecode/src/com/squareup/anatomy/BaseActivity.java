package com.squareup.anatomy;

import android.app.Activity;
import android.os.Bundle;

/**
 * All activities should extend this for dependency injection.
 */
public class BaseActivity extends Activity {

  @Override protected void onCreate(Bundle state) {
    super.onCreate(state);

    // Android constructs Activity instances so we must find the ObjectGraph
    // instance and inject this.
    ((ExampleApp) getApplication()).objectGraph().inject(this);
  }
}
