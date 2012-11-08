package com.squareup.anatomy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import javax.inject.Inject;

public class HomeActivity extends BaseActivity {

  /** Starts the customer producer when this first activity is visited. */
  @Inject CustomerProducer customerProducer;

  @Override protected void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.home_activity);

    findViewById(R.id.locations_button).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        startActivity(new Intent(HomeActivity.this, LocationActivity.class));
      }
    });

    findViewById(R.id.account_button).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        startActivity(new Intent(HomeActivity.this, AccountActivity.class));
      }
    });
  }
}
