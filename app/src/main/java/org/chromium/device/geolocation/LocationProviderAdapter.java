// Copyright 2013 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.device.geolocation;

import android.content.Context;

import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.MainDex;

import java.util.concurrent.FutureTask;

/**
 * Implements the Java side of LocationProviderAndroid.
 * Delegates all real functionality to the implementation
 * returned from LocationProviderFactory.
 * See detailed documentation on
 * content/browser/geolocation/location_api_adapter_android.h.
 * Based on android.webkit.GeolocationService.java
 */
@MainDex
@VisibleForTesting
public class LocationProviderAdapter {
    // Delegate handling the real work in the main thread.
    private LocationProviderFactory.LocationProvider mImpl;

    private LocationProviderAdapter(Context context) {
        mImpl = LocationProviderFactory.create(context);
    }

    @CalledByNative
    public static LocationProviderAdapter create(Context context) {
        return new LocationProviderAdapter(context);
    }

    /**
     * Start listening for location updates until we're told to quit. May be called in any thread.
     * @param enableHighAccuracy Whether or not to enable high accuracy location providers.
     */
    @CalledByNative
    public boolean start(final boolean enableHighAccuracy) {
        FutureTask<Void> task = new FutureTask<Void>(new Runnable() {
            @Override
            public void run() {
                mImpl.start(enableHighAccuracy);
            }
        }, null);
        ThreadUtils.runOnUiThread(task);
        return true;
    }

    /**
     * Stop listening for location updates. May be called in any thread.
     */
    @CalledByNative
    public void stop() {
        FutureTask<Void> task = new FutureTask<Void>(new Runnable() {
            @Override
            public void run() {
                mImpl.stop();
            }
        }, null);
        ThreadUtils.runOnUiThread(task);
    }

    /**
     * Returns true if we are currently listening for location updates, false if not.
     * Must be called only in the UI thread.
     */
    public boolean isRunning() {
        assert ThreadUtils.runningOnUiThread();
        return mImpl.isRunning();
    }

    public static void newLocationAvailable(double latitude, double longitude, double timestamp,
            boolean hasAltitude, double altitude, boolean hasAccuracy, double accuracy,
            boolean hasHeading, double heading, boolean hasSpeed, double speed) {
        nativeNewLocationAvailable(latitude, longitude, timestamp, hasAltitude, altitude,
                hasAccuracy, accuracy, hasHeading, heading, hasSpeed, speed);
    }

    public static void newErrorAvailable(String message) {
        nativeNewErrorAvailable(message);
    }

    // Native functions
    private static native void nativeNewLocationAvailable(double latitude, double longitude,
            double timeStamp, boolean hasAltitude, double altitude, boolean hasAccuracy,
            double accuracy, boolean hasHeading, double heading, boolean hasSpeed, double speed);
    private static native void nativeNewErrorAvailable(String message);
}
