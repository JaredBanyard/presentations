package com.squareup.anatomy;

import android.app.Fragment;
import android.os.Bundle;
import com.squareup.otto.Bus;
import javax.inject.Inject;

/**
 * All fragments should extend this for dependency injection.
 */
public class BaseFragment extends Fragment {

  @Inject Bus bus;

  @Override public void onCreate(Bundle state) {
    super.onCreate(state);

    // Android constructs Fragment instances so we must find the ObjectGraph
    // instance and inject this.
    ((ExampleApp) getActivity().getApplication()).objectGraph().inject(this);
  }

  @Override public void onResume() {
    super.onResume();
    bus.register(this);
  }

  @Override public void onPause() {
    super.onPause();
    bus.unregister(this);
  }
}
