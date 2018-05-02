package org.chromium.chrome.browser.AdSystem;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.github.florent37.glidepalette.GlidePalette;

import org.chromium.chrome.R;

import java.util.Calendar;

/**
 * Created by Mohamed Sayed on 1/14/2017.
 */

public class FacebookNativeAdActivity extends Activity {

    String TAG = getClass().getSimpleName();

    RoundLinearLayout linAd;
    ImageView imAdIcon,imAdCover;
    MediaView mediaView;
    TextView txtTitle,txtSubTitle,txtGetIt,txtDesc;
    RoundTextView txtInstall,txtClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.facebook_native_ad);
        linAd = (RoundLinearLayout) findViewById(R.id.linAd);
        imAdIcon = (ImageView) findViewById(R.id.imAdIcon);
        imAdCover = (ImageView) findViewById(R.id.imAdCover);
        mediaView = (MediaView) findViewById(R.id.mediaView);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtSubTitle = (TextView) findViewById(R.id.txtSubTitle);
        txtGetIt = (TextView) findViewById(R.id.txtGetIt);
        txtDesc = (TextView) findViewById(R.id.txtDesc);
        txtInstall = (RoundTextView) findViewById(R.id.txtInstall);
        txtClose = (RoundTextView) findViewById(R.id.txtClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        showNativeAd();

    }

    private void showNativeAd() {
        NativeAd nativeAd = new NativeAd(this, AdsConstants.FACEBOOK_NATIVE_AD_PLACEMENT_ID);
        nativeAd.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Log.d("NativeAdError",adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.d(TAG,"Ad Loaded");
                try {
                    txtTitle.setText(nativeAd.getAdTitle());
                    txtSubTitle.setText(nativeAd.getAdSubtitle());
                    txtGetIt.setText(nativeAd.getAdSocialContext());
                    txtDesc.setText(nativeAd.getAdBody());
                    txtInstall.setText(nativeAd.getAdCallToAction());
                    NativeAd.Image adIcon = nativeAd.getAdIcon();
                    String adIconUrl = nativeAd.getAdIcon().getUrl();
                    Glide.with(FacebookNativeAdActivity.this).load(adIconUrl)
                            .listener(GlidePalette.with(adIconUrl)
                                    .use(GlidePalette.Profile.VIBRANT)
                                    .intoCallBack(palette -> {
                                        txtClose.getDelegate().setBackgroundColor(palette.getVibrantColor(getResources().getColor(android.R.color.holo_blue_dark)));
                                        txtInstall.getDelegate().setBackgroundColor(palette.getVibrantColor(getResources().getColor(android.R.color.holo_blue_dark)));
                                    }))
                            .into(imAdIcon);


                    mediaView.setNativeAd(nativeAd);
                    nativeAd.registerViewForInteraction(txtInstall);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onAdClicked(Ad ad) {
                finish();
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }


        });
        try {
            nativeAd.loadAd();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}