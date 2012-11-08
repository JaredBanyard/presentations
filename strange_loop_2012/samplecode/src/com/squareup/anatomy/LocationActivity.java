package com.squareup.anatomy;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;

public class LocationActivity extends BaseActivity {

  @Override public void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.location_activity);

    ActionBar ab = getActionBar();
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setTitle(R.string.locations);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
