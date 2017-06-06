
// Copyright 2017 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     java_cpp_enum.py
// From
//     ../../components/offline_pages/core/downloads/download_ui_item.h

package org.chromium.components.offlinepages.downloads;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    DownloadState.PENDING, DownloadState.IN_PROGRESS, DownloadState.PAUSED, DownloadState.COMPLETE
})
@Retention(RetentionPolicy.SOURCE)
public @interface DownloadState {
  int PENDING = 0;
  int IN_PROGRESS = 1;
  int PAUSED = 2;
  int COMPLETE = 3;
}
