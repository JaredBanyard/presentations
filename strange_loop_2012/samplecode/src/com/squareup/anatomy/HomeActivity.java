package com.squareup.anatomy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends BaseActivity {
  @Override protected void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.home_activity);

    findViewById(R.id.locations_button).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        startActivity(new Intent(HomeActivity.this, LocationActivity.class));
      }
    });
  }
}
