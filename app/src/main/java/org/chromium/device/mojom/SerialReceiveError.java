
// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/device/public/interfaces/serial.mojom
//

package org.chromium.device.mojom;

import org.chromium.base.annotations.SuppressFBWarnings;
import org.chromium.mojo.bindings.DeserializationException;

public final class SerialReceiveError {


    public static final int NONE = 0;

    public static final int DISCONNECTED = NONE + 1;

    public static final int TIMEOUT = DISCONNECTED + 1;

    public static final int DEVICE_LOST = TIMEOUT + 1;

    public static final int BREAK = DEVICE_LOST + 1;

    public static final int FRAME_ERROR = BREAK + 1;

    public static final int OVERRUN = FRAME_ERROR + 1;

    public static final int BUFFER_OVERFLOW = OVERRUN + 1;

    public static final int PARITY_ERROR = BUFFER_OVERFLOW + 1;

    public static final int SYSTEM_ERROR = PARITY_ERROR + 1;


    private static final boolean IS_EXTENSIBLE = false;

    public static boolean isKnownValue(int value) {
        switch (value) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
        }
        return false;
    }

    public static void validate(int value) {
        if (IS_EXTENSIBLE || isKnownValue(value))
            return;

        throw new DeserializationException("Invalid enum value.");
    }

    private SerialReceiveError() {}

}