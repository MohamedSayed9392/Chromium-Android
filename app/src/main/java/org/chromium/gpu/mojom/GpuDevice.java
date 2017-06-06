
// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     gpu/ipc/common/gpu_info.mojom
//

package org.chromium.gpu.mojom;

import org.chromium.base.annotations.SuppressFBWarnings;
import org.chromium.mojo.bindings.DeserializationException;


public final class GpuDevice extends org.chromium.mojo.bindings.Struct {

    private static final int STRUCT_SIZE = 40;
    private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(40, 0)};
    private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];
    public int vendorId;
    public int deviceId;
    public boolean active;
    public String vendorString;
    public String deviceString;

    private GpuDevice(int version) {
        super(STRUCT_SIZE, version);
    }

    public GpuDevice() {
        this(0);
    }

    public static GpuDevice deserialize(org.chromium.mojo.bindings.Message message) {
        return decode(new org.chromium.mojo.bindings.Decoder(message));
    }

    /**
     * Similar to the method above, but deserializes from a |ByteBuffer| instance.
     *
     * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
     */
    public static GpuDevice deserialize(java.nio.ByteBuffer data) {
        if (data == null)
            return null;

        return deserialize(new org.chromium.mojo.bindings.Message(
                data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
    }

    @SuppressWarnings("unchecked")
    public static GpuDevice decode(org.chromium.mojo.bindings.Decoder decoder0) {
        if (decoder0 == null) {
            return null;
        }
        decoder0.increaseStackDepth();
        GpuDevice result;
        try {
            org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
            result = new GpuDevice(mainDataHeader.elementsOrVersion);
            if (mainDataHeader.elementsOrVersion >= 0) {
                
                result.vendorId = decoder0.readInt(8);
            }
            if (mainDataHeader.elementsOrVersion >= 0) {
                
                result.deviceId = decoder0.readInt(12);
            }
            if (mainDataHeader.elementsOrVersion >= 0) {
                
                result.active = decoder0.readBoolean(16, 0);
            }
            if (mainDataHeader.elementsOrVersion >= 0) {
                
                result.vendorString = decoder0.readString(24, false);
            }
            if (mainDataHeader.elementsOrVersion >= 0) {
                
                result.deviceString = decoder0.readString(32, false);
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
        
        encoder0.encode(vendorId, 8);
        
        encoder0.encode(deviceId, 12);
        
        encoder0.encode(active, 16, 0);
        
        encoder0.encode(vendorString, 24, false);
        
        encoder0.encode(deviceString, 32, false);
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
        GpuDevice other = (GpuDevice) object;
        if (this.vendorId!= other.vendorId)
            return false;
        if (this.deviceId!= other.deviceId)
            return false;
        if (this.active!= other.active)
            return false;
        if (!org.chromium.mojo.bindings.BindingsHelper.equals(this.vendorString, other.vendorString))
            return false;
        if (!org.chromium.mojo.bindings.BindingsHelper.equals(this.deviceString, other.deviceString))
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
        result = prime * result + org.chromium.mojo.bindings.BindingsHelper.hashCode(vendorId);
        result = prime * result + org.chromium.mojo.bindings.BindingsHelper.hashCode(deviceId);
        result = prime * result + org.chromium.mojo.bindings.BindingsHelper.hashCode(active);
        result = prime * result + org.chromium.mojo.bindings.BindingsHelper.hashCode(vendorString);
        result = prime * result + org.chromium.mojo.bindings.BindingsHelper.hashCode(deviceString);
        return result;
    }
}