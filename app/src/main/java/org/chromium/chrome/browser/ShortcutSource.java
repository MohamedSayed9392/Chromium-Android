
// Copyright 2017 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     java_cpp_enum.py
// From
//     ../../chrome/browser/android/shortcut_info.h

package org.chromium.chrome.browser;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    ShortcutSource.UNKNOWN, ShortcutSource.ADD_TO_HOMESCREEN_DEPRECATED, ShortcutSource.APP_BANNER,
    ShortcutSource.BOOKMARK_NAVIGATOR_WIDGET, ShortcutSource.BOOKMARK_SHORTCUT_WIDGET,
    ShortcutSource.NOTIFICATION, ShortcutSource.ADD_TO_HOMESCREEN_PWA,
    ShortcutSource.ADD_TO_HOMESCREEN_STANDALONE, ShortcutSource.ADD_TO_HOMESCREEN_SHORTCUT,
    ShortcutSource.EXTERNAL_INTENT, ShortcutSource.COUNT
})
@Retention(RetentionPolicy.SOURCE)
public @interface ShortcutSource {
  int UNKNOWN = 0;
  int ADD_TO_HOMESCREEN_DEPRECATED = 1;
  int APP_BANNER = 2;
  int BOOKMARK_NAVIGATOR_WIDGET = 3;
  int BOOKMARK_SHORTCUT_WIDGET = 4;
  int NOTIFICATION = 5;
  int ADD_TO_HOMESCREEN_PWA = 6;
  int ADD_TO_HOMESCREEN_STANDALONE = 7;
  int ADD_TO_HOMESCREEN_SHORTCUT = 8;
  int EXTERNAL_INTENT = 9;
  int COUNT = 10;
}
