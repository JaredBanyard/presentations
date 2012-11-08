package com.squareup.anatomy;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.otto.Subscribe;

public class AccountActivity extends BaseActivity {

  private ImageView customerImage;
  private TextView customerName;

  @Override protected void onCreate(Bundle state) {
    super.onCreate(state);

    setContentView(R.layout.account_activity);

    ActionBar actionBar = getActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setTitle(R.string.account);

    customerImage = (ImageView) findViewById(R.id.customer_image);
    customerName = (TextView) findViewById(R.id.customer_name);
  }

  @Subscribe public void onCustomerUpdated(Customer customer) {
    customerImage.setImageResource(customer.imageResourceId);
    customerName.setText(customer.name);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
