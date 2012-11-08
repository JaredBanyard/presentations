package com.squareup.anatomy;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class CustomerProducer {
  private final Bus bus;

  private final Customer[] customers = new Customer[]{
      new Customer("Yawning Man", R.drawable.yawn),
      new Customer("Joseph Ducreux", R.drawable.ducreux),
      new Customer("Le Secret", R.drawable.secret)
  };
  private int customerIndex = 0;

  private final Handler handler = new Handler(Looper.getMainLooper());

  @Inject public CustomerProducer(Bus bus) {
    this.bus = bus;
    bus.register(this);
    scheduleNext();
  }

  private void scheduleNext() {
    handler.postDelayed(new Runnable() {
      @Override public void run() {
        customerIndex = (customerIndex + 1) % customers.length;
        bus.post(customers[customerIndex]);
        scheduleNext();
      }
    }, 4000L);
  }

  @Produce public Customer getCurrentCustomer() {
    return customers[customerIndex];
  }
}
