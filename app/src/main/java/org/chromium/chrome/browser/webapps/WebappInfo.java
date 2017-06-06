// Copyright 2015 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.chrome.browser.webapps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;

import org.chromium.base.Log;
import org.chromium.blink_public.platform.WebDisplayMode;
import org.chromium.chrome.browser.ShortcutHelper;
import org.chromium.chrome.browser.ShortcutSource;
import org.chromium.chrome.browser.util.IntentUtils;
import org.chromium.content_public.common.ScreenOrientationValues;

/**
 * Stores info about a web app.
 */
public class WebappInfo {
    private static final String TAG = "WebappInfo";

    /**
     * Parameter for {@link WebappInfo#create()} method which allows either a Bitmap or a PNG
     * encoded string to be passed as a parameter.
     */
    public static class Icon {
        private String mEncoded;
        private Bitmap mDecoded;

        public Icon(String encoded) {
            mEncoded = encoded;
        }

        public Icon(Bitmap decoded) {
            mDecoded = decoded;
        }

        public String encoded() {
            if (mEncoded == null) {
                mEncoded = ShortcutHelper.encodeBitmapAsString(mDecoded);
            }
            return mEncoded;
        }

        public Bitmap decoded() {
            if (mDecoded == null) {
                mDecoded = ShortcutHelper.decodeBitmapFromString(mEncoded);
            }
            return mDecoded;
        }
    }

    private boolean mIsInitialized;
    private String mId;
    private Icon mIcon;
    private Uri mUri;
    private Uri mScopeUri;
    private String mName;
    private String mShortName;
    private int mDisplayMode;
    private int mOrientation;
    private int mSource;
    private long mThemeColor;
    private long mBackgroundColor;
    private boolean mIsIconGenerated;

    public static WebappInfo createEmpty() {
        return new WebappInfo();
    }

    protected static String urlFromIntent(Intent intent) {
        return IntentUtils.safeGetStringExtra(intent, ShortcutHelper.EXTRA_URL);
    }

    protected static int sourceFromIntent(Intent intent) {
        return IntentUtils.safeGetIntExtra(
                intent, ShortcutHelper.EXTRA_SOURCE, ShortcutSource.UNKNOWN);
    }

    private static String titleFromIntent(Intent intent) {
        // The reference to title has been kept for reasons of backward compatibility. For intents
        // and shortcuts which were created before we utilized the concept of name and shortName,
        // we set the name and shortName to be the title.
        String title = IntentUtils.safeGetStringExtra(intent, ShortcutHelper.EXTRA_TITLE);
        return title == null ? "" : title;
    }

    private static String nameFromIntent(Intent intent) {
        String name = IntentUtils.safeGetStringExtra(intent, ShortcutHelper.EXTRA_NAME);
        return name == null ? titleFromIntent(intent) : name;
    }

    private static String shortNameFromIntent(Intent intent) {
        String shortName = IntentUtils.safeGetStringExtra(intent, ShortcutHelper.EXTRA_SHORT_NAME);
        return shortName == null ? titleFromIntent(intent) : shortName;
    }

    /**
     * Construct a WebappInfo.
     * @param intent Intent containing info about the app.
     */
    public static WebappInfo create(Intent intent) {
        String id = IntentUtils.safeGetStringExtra(intent, ShortcutHelper.EXTRA_ID);
        String icon = IntentUtils.safeGetStringExtra(intent, ShortcutHelper.EXTRA_ICON);
        String url = urlFromIntent(intent);
        String scope = IntentUtils.safeGetStringExtra(intent, ShortcutHelper.EXTRA_SCOPE);
        int displayMode = IntentUtils.safeGetIntExtra(
                intent, ShortcutHelper.EXTRA_DISPLAY_MODE, WebDisplayMode.kStandalone);
        int orientation = IntentUtils.safeGetIntExtra(
                intent, ShortcutHelper.EXTRA_ORIENTATION, ScreenOrientationValues.DEFAULT);
        int source = sourceFromIntent(intent);
        long themeColor = IntentUtils.safeGetLongExtra(intent,
                ShortcutHelper.EXTRA_THEME_COLOR,
                ShortcutHelper.MANIFEST_COLOR_INVALID_OR_MISSING);
        long backgroundColor = IntentUtils.safeGetLongExtra(intent,
                ShortcutHelper.EXTRA_BACKGROUND_COLOR,
                ShortcutHelper.MANIFEST_COLOR_INVALID_OR_MISSING);
        boolean isIconGenerated = IntentUtils.safeGetBooleanExtra(intent,
                ShortcutHelper.EXTRA_IS_ICON_GENERATED, false);

        String name = nameFromIntent(intent);
        String shortName = shortNameFromIntent(intent);

        return create(id, url, scope, new Icon(icon), name, shortName, displayMode,
                orientation, source, themeColor, backgroundColor, isIconGenerated);
    }

    /**
     * Construct a WebappInfo.
     * @param id              ID for the webapp.
     * @param url             URL for the webapp.
     * @param scope           Scope for the webapp.
     * @param icon            Icon to show for the webapp.
     * @param name            Name of the webapp.
     * @param shortName       The short name of the webapp.
     * @param displayMode     Display mode of the webapp.
     * @param orientation     Orientation of the webapp.
     * @param source          Source where the webapp was added from.
     * @param themeColor      The theme color of the webapp.
     * @param backgroundColor The background color of the webapp.
     * @param isIconGenerated Whether the |icon| was generated by Chromium.
     */
    public static WebappInfo create(String id, String url, String scope, Icon icon, String name,
            String shortName, int displayMode, int orientation, int source, long themeColor,
            long backgroundColor, boolean isIconGenerated) {
        if (id == null || url == null) {
            Log.e(TAG, "Incomplete data provided: " + id + ", " + url);
            return null;
        }

        return new WebappInfo(id, url, scope, icon, name, shortName, displayMode, orientation,
                source, themeColor, backgroundColor, isIconGenerated);
    }

    protected WebappInfo(String id, String url, String scope, Icon icon, String name,
            String shortName, int displayMode, int orientation, int source, long themeColor,
            long backgroundColor, boolean isIconGenerated) {
        Uri uri = Uri.parse(url);
        if (TextUtils.isEmpty(scope)) {
            scope = ShortcutHelper.getScopeFromUrl(url);
        }
        Uri scopeUri = Uri.parse(scope);

        mIcon = icon;
        mId = id;
        mName = name;
        mShortName = shortName;
        mUri = uri;
        mScopeUri = scopeUri;
        mDisplayMode = displayMode;
        mOrientation = orientation;
        mSource = source;
        mThemeColor = themeColor;
        mBackgroundColor = backgroundColor;
        mIsIconGenerated = isIconGenerated;
        mIsInitialized = mUri != null;
    }

    protected WebappInfo() {
    }

    public boolean isInitialized() {
        return mIsInitialized;
    }

    public String id() {
        return mId;
    }

    public Uri uri() {
        return mUri;
    }

    /**
     * Whether the webapp should be navigated to {@link uri()} if the webapp is already open when
     * Chrome receives a ACTION_START_WEBAPP intent.
     */
    public boolean shouldForceNavigation() {
        return false;
    }

    public Uri scopeUri() {
        return mScopeUri;
    }

    public String name() {
        return mName;
    }

    public String shortName() {
        return mShortName;
    }

    public int displayMode() {
        return mDisplayMode;
    }

    public String webApkPackageName() {
        return null;
    }

    public int orientation() {
        return mOrientation;
    }

    public int source() {
        return mSource;
    }

    /**
     * Theme color is actually a 32 bit unsigned integer which encodes a color
     * in ARGB format. mThemeColor is a long because we also need to encode the
     * error state of ShortcutHelper.MANIFEST_COLOR_INVALID_OR_MISSING.
     */
    public long themeColor() {
        return mThemeColor;
    }

    /**
     * Returns whether the theme color specified in the Intent is valid.
     * A theme color isn't valid if its value is ShortcutHelper.MANIFEST_COLOR_INVALID_OR_MISSING;
     */
    public boolean hasValidThemeColor() {
        return mThemeColor != ShortcutHelper.MANIFEST_COLOR_INVALID_OR_MISSING;
    }

    /**
     * Background color is actually a 32 bit unsigned integer which encodes a color
     * in ARGB format. mBackgroundColor is a long because we also need to encode the
     * error state of ShortcutHelper.MANIFEST_COLOR_INVALID_OR_MISSING.
     */
    public long backgroundColor() {
        return mBackgroundColor;
    }

    /**
     * Returns whether the background color specified in the Intent is valid.
     * A background color isn't valid if its value is
     * ShortcutHelper.MANIFEST_COLOR_INVALID_OR_MISSING.
     */
    public boolean hasValidBackgroundColor() {
        return mBackgroundColor != ShortcutHelper.MANIFEST_COLOR_INVALID_OR_MISSING;
    }

    /**
     * Returns the background color specified by {@link #backgroundColor()} if
     * the value is valid. Returns the specified fallback color otherwise.
     */
    public int backgroundColor(int fallback) {
        return hasValidBackgroundColor() ? (int) mBackgroundColor : fallback;
    }

    // This is needed for clients that want to send the icon through an intent.
    public String encodedIcon() {
        return (mIcon == null) ? null : mIcon.encoded();
    }

    /**
     * Returns the icon in Bitmap form.  Caches the result for future retrievals.
     */
    public Bitmap icon() {
        return (mIcon == null) ? null : mIcon.decoded();
    }

    /**
     * Returns whether the icon was generated by Chromium.
     */
    public boolean isIconGenerated() {
        return mIsIconGenerated;
    }

    /**
     * Sets extras on an Intent that will launch a WebappActivity.
     * @param intent Intent that will be used to launch a WebappActivity.
     */
    public void setWebappIntentExtras(Intent intent) {
        intent.putExtra(ShortcutHelper.EXTRA_ID, id());
        intent.putExtra(ShortcutHelper.EXTRA_URL, uri().toString());
        intent.putExtra(ShortcutHelper.EXTRA_SCOPE, scopeUri().toString());
        intent.putExtra(ShortcutHelper.EXTRA_ICON, encodedIcon());
        intent.putExtra(ShortcutHelper.EXTRA_VERSION, ShortcutHelper.WEBAPP_SHORTCUT_VERSION);
        intent.putExtra(ShortcutHelper.EXTRA_NAME, name());
        intent.putExtra(ShortcutHelper.EXTRA_SHORT_NAME, shortName());
        intent.putExtra(ShortcutHelper.EXTRA_DISPLAY_MODE, displayMode());
        intent.putExtra(ShortcutHelper.EXTRA_ORIENTATION, orientation());
        intent.putExtra(ShortcutHelper.EXTRA_SOURCE, source());
        intent.putExtra(ShortcutHelper.EXTRA_THEME_COLOR, themeColor());
        intent.putExtra(ShortcutHelper.EXTRA_BACKGROUND_COLOR, backgroundColor());
        intent.putExtra(ShortcutHelper.EXTRA_IS_ICON_GENERATED, isIconGenerated());
    }

    /**
     * Returns true if the WebappInfo was created for an Intent fired from a launcher shortcut (as
     * opposed to an intent from a push notification or other internal source).
     */
    public boolean isLaunchedFromHomescreen() {
        int source = source();
        return source != ShortcutSource.NOTIFICATION && source != ShortcutSource.EXTERNAL_INTENT;
    }
}
