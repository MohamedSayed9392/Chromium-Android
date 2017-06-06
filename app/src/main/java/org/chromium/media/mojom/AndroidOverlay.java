
// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     media/mojo/interfaces/android_overlay.mojom
//

package org.chromium.media.mojom;

import org.chromium.base.annotations.SuppressFBWarnings;
import org.chromium.mojo.bindings.DeserializationException;


public interface AndroidOverlay extends org.chromium.mojo.bindings.Interface {



    public interface Proxy extends AndroidOverlay, org.chromium.mojo.bindings.Interface.Proxy {
    }

    Manager<AndroidOverlay, AndroidOverlay.Proxy> MANAGER = AndroidOverlay_Internal.MANAGER;


    void scheduleLayout(
org.chromium.gfx.mojom.Rect rect);


}
