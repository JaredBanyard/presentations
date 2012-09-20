package com.squareup.anatomy;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.otto.Bus;
import javax.inject.Inject;

/**
 * Contains two buttons for posting the current location via {@link Bus}
 * or {@link LocalBroadcastManager}.
 */
public class PublishFragment extends BaseFragment {

  @Inject Bus bus;
  @Inject LocationManager locationManager;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
    return inflater.inflate(R.layout.publish_fragment, container, false);
  }

  @Override public void onViewCreated(View view, Bundle state) {
    super.onViewCreated(view, state);

    view.findViewById(R.id.local_broadcast_button).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        postViaLocalBroadcast();
      }
    });

    view.findViewById(R.id.otto_button).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        postViaOtto();
      }
    });
  }

  private void postViaLocalBroadcast() {
    Intent intent = new Intent(BroadcastIds.LOCATION_UPDATED_ACTION);
    intent.putExtra(BroadcastIds.CURRENT_LOCATION_KEY, getCurrentLocation());

    LocalBroadcastManager.getInstance(getActivity())
        .sendBroadcast(intent);
  }

  private void postViaOtto() {
    bus.post(getCurrentLocation());
  }

  private Location getCurrentLocation() {
    for (String providerName : locationManager.getAllProviders()) {
      Location location = locationManager.getLastKnownLocation(providerName);

      // Return the first location. Don't do this in real apps. See
      // http://developer.android.com/guide/topics/location/strategies.html
      if (location != null) return location;
    }
    return null;
  }
}
