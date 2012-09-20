package com.squareup.anatomy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.squareup.otto.Subscribe;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Receives location events from {@link LocalBroadcastManager} or {@link com.squareup.otto.Bus}.
 */
public class SubscribeFragment extends BaseFragment {

  private TextView locationTextView;
  private TextView timestampTextView;

  private IntentFilter locationFilter = new IntentFilter(BroadcastIds.LOCATION_UPDATED_ACTION);
  private BroadcastReceiver receiver = new BroadcastReceiver() {
    @Override public void onReceive(Context context, Intent intent) {
      Location location = (Location) intent.getParcelableExtra(BroadcastIds.CURRENT_LOCATION_KEY);

      showLocation(location);
    }
  };

  private void showLocation(Location location) {
    if (location == null) {
      locationTextView.setText("No Location");
    } else {
      locationTextView.setText(location.toString());
    }

    // Update the timestamp so the user sees some kind of update even if the location didn't change.
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    timestampTextView.setText(dateFormat.format(new Date()));
  }

  /**
   * Receives location updates from Otto.
   */
  @Subscribe public void onLocationUpdated(Location location) {
    showLocation(location);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
    return inflater.inflate(R.layout.subscribe_fragment, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    locationTextView = (TextView) view.findViewById(R.id.location);
    timestampTextView = (TextView) view.findViewById(R.id.timestamp);
  }

  @Override public void onResume() {
    super.onResume();
    LocalBroadcastManager.getInstance(getActivity()).registerReceiver(receiver, locationFilter);
  }

  @Override public void onPause() {
    super.onPause();
    LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiver);
  }
}
