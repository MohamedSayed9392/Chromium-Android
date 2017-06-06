
// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     third_party/WebKit/public/platform/modules/background_fetch/background_fetch.mojom
//

package org.chromium.blink.mojom;

import org.chromium.base.annotations.SuppressFBWarnings;
import org.chromium.mojo.bindings.DeserializationException;


public final class BackgroundFetchOptions extends org.chromium.mojo.bindings.Struct {

    private static final int STRUCT_SIZE = 32;
    private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(32, 0)};
    private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];
    public IconDefinition[] icons;
    public long totalDownloadSize;
    public String title;

    private BackgroundFetchOptions(int version) {
        super(STRUCT_SIZE, version);
    }

    public BackgroundFetchOptions() {
        this(0);
    }

    public static BackgroundFetchOptions deserialize(org.chromium.mojo.bindings.Message message) {
        return decode(new org.chromium.mojo.bindings.Decoder(message));
    }

    /**
     * Similar to the method above, but deserializes from a |ByteBuffer| instance.
     *
     * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
     */
    public static BackgroundFetchOptions deserialize(java.nio.ByteBuffer data) {
        if (data == null)
            return null;

        return deserialize(new org.chromium.mojo.bindings.Message(
                data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
    }

    @SuppressWarnings("unchecked")
    public static BackgroundFetchOptions decode(org.chromium.mojo.bindings.Decoder decoder0) {
        if (decoder0 == null) {
            return null;
        }
        decoder0.increaseStackDepth();
        BackgroundFetchOptions result;
        try {
            org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
            result = new BackgroundFetchOptions(mainDataHeader.elementsOrVersion);
            if (mainDataHeader.elementsOrVersion >= 0) {
                
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(8, false);
                {
                    org.chromium.mojo.bindings.DataHeader si1 = decoder1.readDataHeaderForPointerArray(org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
                    result.icons = new IconDefinition[si1.elementsOrVersion];
                    for (int i1 = 0; i1 < si1.elementsOrVersion; ++i1) {
                        
                        org.chromium.mojo.bindings.Decoder decoder2 = decoder1.readPointer(org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i1, false);
                        result.icons[i1] = IconDefinition.decode(decoder2);
                    }
                }
            }
            if (mainDataHeader.elementsOrVersion >= 0) {
                
                result.totalDownloadSize = decoder0.readLong(16);
            }
            if (mainDataHeader.elementsOrVersion >= 0) {
                
                result.title = decoder0.readString(24, false);
            }
        } finally {
            decoder0.decreaseStackDepth();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected final void encode(org.chromium.mojo.bindings.Encoder encoder) {
        org.chromium.mojo.bindings.Encoder encoder0 = encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO);
        
        if (icons == null) {
            encoder0.encodeNullPointer(8, false);
        } else {
            org.chromium.mojo.bindings.Encoder encoder1 = encoder0.encodePointerArray(icons.length, 8, org.chromium.mojo.bindings.BindingsHelper.UNSPECIFIED_ARRAY_LENGTH);
            for (int i0 = 0; i0 < icons.length; ++i0) {
                
                encoder1.encode(icons[i0], org.chromium.mojo.bindings.DataHeader.HEADER_SIZE + org.chromium.mojo.bindings.BindingsHelper.POINTER_SIZE * i0, false);
            }
        }
        
        encoder0.encode(totalDownloadSize, 16);
        
        encoder0.encode(title, 24, false);
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        BackgroundFetchOptions other = (BackgroundFetchOptions) object;
        if (!java.util.Arrays.deepEquals(this.icons, other.icons))
            return false;
        if (this.totalDownloadSize!= other.totalDownloadSize)
            return false;
        if (!org.chromium.mojo.bindings.BindingsHelper.equals(this.title, other.title))
            return false;
        return true;
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime + getClass().hashCode();
        result = prime * result + java.util.Arrays.deepHashCode(icons);
        result = prime * result + org.chromium.mojo.bindings.BindingsHelper.hashCode(totalDownloadSize);
        result = prime * result + org.chromium.mojo.bindings.BindingsHelper.hashCode(title);
        return result;
    }
}