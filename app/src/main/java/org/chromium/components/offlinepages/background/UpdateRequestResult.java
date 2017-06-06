
// Copyright 2017 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     java_cpp_enum.py
// From
//     ../../components/offline_pages/core/background/request_queue_results.h

package org.chromium.components.offlinepages.background;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    UpdateRequestResult.SUCCESS, UpdateRequestResult.STORE_FAILURE,
    UpdateRequestResult.REQUEST_DOES_NOT_EXIST
})
@Retention(RetentionPolicy.SOURCE)
public @interface UpdateRequestResult {
  int SUCCESS = 0;
  int STORE_FAILURE = 1;
  int REQUEST_DOES_NOT_EXIST = 2;
}
