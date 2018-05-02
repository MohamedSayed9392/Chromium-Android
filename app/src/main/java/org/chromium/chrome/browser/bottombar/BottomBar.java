package org.chromium.chrome.browser.bottombar;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.chromium.chrome.R;
import org.chromium.chrome.browser.AdSystem.FacebookNativeAdActivity;
import org.chromium.chrome.browser.ChromeActivity;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkUtils;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.history.HistoryManagerUtils;
import org.chromium.chrome.browser.preferences.PreferencesLauncher;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.TabSwitcherDrawable;

public class BottomBar implements View.OnClickListener {
    String TAG = getClass().getSimpleName();

    public static BottomBar bottomBar;

    Activity activity;

    public BottomBar(Activity activity) {
        this.activity = activity;

        findViews();
        updateViewsStateAndUi();
        setupViewsClickListeners();

        bottomBar = this;
    }

    LinearLayout linBottomShowSideMenu,
            linBottomShowMenu,
            linBottomBarCollapsed,
            linBottomBarExpanded,
            linBottomBarSettings,
            linBottomBarFinish,
            linBottomHideMenu;
    ImageButton tab_switcher_bottom_button;
    ImageView imBottomTabSwitcher;
    public SlidingUpPanelLayout bottomBarSlider;

    TextView txtBookmarkLogo, txtDesktopLogo, txtDesktopTitle;
    LinearLayout linBookmark, linBookmarks, linHistory, linDownload, linDownloads, linDesktop;

    /**
     * Define the views.
     */
    private void findViews() {
        linBottomShowMenu = (LinearLayout) activity.findViewById(R.id.linBottomShowMenu);
        linBottomShowSideMenu = (LinearLayout) activity.findViewById(R.id.linBottomShowSideMenu);
        imBottomTabSwitcher = (ImageView) activity.findViewById(R.id.imBottomTabSwitcher);
        tab_switcher_bottom_button = (ImageButton) activity.findViewById(R.id.tab_switcher_bottom_button);
        bottomBarSlider = (SlidingUpPanelLayout) activity.findViewById(R.id.bottomBarSlider);

        linBottomBarSettings = (LinearLayout) activity.findViewById(R.id.linBottomBarSettings);
        linBottomBarFinish = (LinearLayout) activity.findViewById(R.id.linBottomBarFinish);
        linBottomHideMenu = (LinearLayout) activity.findViewById(R.id.linBottomHideMenu);

        linBottomBarCollapsed = (LinearLayout) activity.findViewById(R.id.linBottomBarCollapsed);
        linBottomBarExpanded = (LinearLayout) activity.findViewById(R.id.linBottomBarExpanded);

        linBookmark = (LinearLayout) activity.findViewById(R.id.linBookmark);
        linBookmarks = (LinearLayout) activity.findViewById(R.id.linBookmarks);
        linHistory = (LinearLayout) activity.findViewById(R.id.linHistory);
        linDownload = (LinearLayout) activity.findViewById(R.id.linDownload);
        linDownloads = (LinearLayout) activity.findViewById(R.id.linDownloads);
        linDesktop = (LinearLayout) activity.findViewById(R.id.linDesktop);

        txtBookmarkLogo = (TextView) activity.findViewById(R.id.txtBookmarkLogo);

        txtDesktopLogo = (TextView) activity.findViewById(R.id.txtDesktopLogo);
        txtDesktopTitle = (TextView) activity.findViewById(R.id.txtDesktopTitle);
    }

    /**
     * update views state and ui updates.
     */
    public void updateViewsStateAndUi() {
        bottomBarSlider.setTouchEnabled(false);
        updatePageBookmarked();
        updateDesktopMode();
    }


    /**
     * update the bookmark icon to show if the page is bookmarked or not.
     */
    public void updatePageBookmarked() {
        if (((ChromeTabbedActivity) activity).getActivityTab()==null ||
                ((ChromeTabbedActivity) activity).getActivityTab().getBookmarkId() == Tab.INVALID_BOOKMARK_ID) {
            txtBookmarkLogo.setText(activity.getResources().getString(R.string.fa_bookmark));
        } else {
            txtBookmarkLogo.setText(activity.getResources().getString(R.string.fa_bookmark_o));
        }
    }

    /**
     * update the desktop mode change icon to show whether the desktop or phone phone is enabled.
     */
    public void updateDesktopMode() {
        if (((ChromeTabbedActivity) activity).getActivityTab()!=null &&
                ((ChromeTabbedActivity) activity).getActivityTab().getUseDesktopUserAgent()) {
            txtDesktopLogo.setText(activity.getResources().getString(R.string.fa_internet_explorer));
            txtDesktopTitle.setText("desktop");
        } else {
            txtDesktopLogo.setText(activity.getResources().getString(R.string.fa_phone));
            txtDesktopTitle.setText("mobile");
        }

    }

    /**
     * update count of opened tabs.
     */
    public void updateTabsSwitcheCount() {
        TabSwitcherDrawable mTabSwitcherButtonDrawable = TabSwitcherDrawable.createTabSwitcherDrawable(activity.getResources(), false);
        mTabSwitcherButtonDrawable.updateForTabCount(((ChromeTabbedActivity) activity).mTabModelSelector.getCurrentModel().getCount(),
                ((ChromeTabbedActivity) activity).getToolbarManager().getToolbarLayout().getToolbarDataProvider().isIncognito());
        tab_switcher_bottom_button.setImageDrawable(mTabSwitcherButtonDrawable);
    }

    /**
     * set click listener on views
     */
    private void setupViewsClickListeners() {
        linBottomShowMenu.setOnClickListener(this);
        linBottomShowSideMenu.setOnClickListener(this);
        imBottomTabSwitcher.setOnClickListener(this);
        linBottomBarSettings.setOnClickListener(this);
        linBottomBarFinish.setOnClickListener(this);
        linBottomHideMenu.setOnClickListener(this);

        tab_switcher_bottom_button.setOnClickListener(null); // to override its click to imBottomTabSwitcher

        linBookmark.setOnClickListener(this);
        linBookmarks.setOnClickListener(this);
        linHistory.setOnClickListener(this);
        linDownload.setOnClickListener(this);
        linDownloads.setOnClickListener(this);
        linDesktop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linBottomShowMenu:
                bottomBarSlider.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                linBottomBarCollapsed.setVisibility(View.GONE);
                linBottomBarExpanded.setVisibility(View.VISIBLE);
                break;
            case R.id.linBottomShowSideMenu:
                if (activity instanceof ChromeTabbedActivity) {
                    ((ChromeTabbedActivity) activity).showAppMenuForKeyboardEvent();
                }
                break;
            case R.id.imBottomTabSwitcher:
                if (activity instanceof ChromeTabbedActivity) {
                    ((ChromeTabbedActivity) activity).toggleOverview();
                }
                break;
            case R.id.linBottomBarFinish:
                activity.finish();
                break;
            case R.id.linBottomBarSettings:
                PreferencesLauncher.launchSettingsPage(activity, null);
                break;
            case R.id.linBottomHideMenu:
                bottomBarSlider.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                linBottomBarExpanded.setVisibility(View.GONE);
                linBottomBarCollapsed.setVisibility(View.VISIBLE);
                break;

            case R.id.linBookmark:
                if (activity instanceof ChromeTabbedActivity) {
                    ((ChromeTabbedActivity) activity).addOrEditBookmark(((ChromeTabbedActivity) activity).getActivityTab());
                }
                isSliderExpanded();
                break;
            case R.id.linBookmarks:
                if (activity instanceof ChromeTabbedActivity) {
                    BookmarkUtils.showBookmarkManager((ChromeTabbedActivity) activity);
                }
                activity.startActivity(new Intent(activity, FacebookNativeAdActivity.class));
                isSliderExpanded();
                break;
            case R.id.linHistory:
                if (activity instanceof ChromeTabbedActivity) {
                    HistoryManagerUtils.showHistoryManager((ChromeActivity) activity, ((ChromeTabbedActivity) activity).getActivityTab());
                }
                activity.startActivity(new Intent(activity, FacebookNativeAdActivity.class));
                isSliderExpanded();
                break;
            case R.id.linDownload:
                DownloadUtils.downloadOfflinePage(activity, ((ChromeTabbedActivity) activity).getActivityTab());
                isSliderExpanded();
                break;
            case R.id.linDownloads:
                if (activity instanceof ChromeTabbedActivity) {
                    DownloadUtils.showDownloadManager(activity, ((ChromeTabbedActivity) activity).getActivityTab());
                }
                activity.startActivity(new Intent(activity, FacebookNativeAdActivity.class));
                isSliderExpanded();
                break;
            case R.id.linDesktop:
                if (activity instanceof ChromeTabbedActivity) {
                    final boolean reloadOnChange = !((ChromeTabbedActivity) activity).getActivityTab().isNativePage();
                    final boolean usingDesktopUserAgent = ((ChromeTabbedActivity) activity).getActivityTab().getUseDesktopUserAgent();
                    ((ChromeTabbedActivity) activity).getActivityTab().setUseDesktopUserAgent(!usingDesktopUserAgent, reloadOnChange);
                    updateDesktopMode();
                }
                isSliderExpanded();
                break;
        }
    }

    /**
     * check the slider state and collapse it
     * @return true if the slider was expanded and false if collapsed
     */
    public boolean isSliderExpanded(){
        if(bottomBarSlider.getPanelState()== SlidingUpPanelLayout.PanelState.EXPANDED){
            bottomBarSlider.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            linBottomBarExpanded.setVisibility(View.GONE);
            linBottomBarCollapsed.setVisibility(View.VISIBLE);
            return true;
        }else{
            return false;
        }
    }

    private void toast(String s) {
        Toast.makeText(activity, s, Toast.LENGTH_LONG).show();
    }
}
