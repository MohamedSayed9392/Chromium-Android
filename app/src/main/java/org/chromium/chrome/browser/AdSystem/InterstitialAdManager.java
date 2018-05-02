package org.chromium.chrome.browser.AdSystem;

import android.content.Context;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

public class InterstitialAdManager {


    public static void loadFacebookInterstitialAd(Context context) {
        String TAG = "loadFacebookAd";

        Log.d(context.getClass().getSimpleName(),TAG);

        InterstitialAd interstitialAd = new InterstitialAd(context, AdsConstants.FACEBOOK_INTERSTATIAL_AD_PLACEMENT_ID);
        interstitialAd.setAdListener(new InterstitialAdListener() {


            @Override
            public void onError(Ad ad, AdError error) {
                Log.d(TAG, "onError");
                Log.d(TAG, "onError : " + error.getErrorCode() + " " + error.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad is loaded and ready to be displayed
                // You can now display the full screen ad using this code:
                interstitialAd.show();
            }

            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Where relevant, use this function to pause your app's flow


            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Use this function to resume your app's flow
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Use this function as indication for a user's click on the ad.
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        try {
            interstitialAd.loadAd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
