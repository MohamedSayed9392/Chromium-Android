
// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     gpu/ipc/common/mailbox_holder.mojom
//

package org.chromium.gpu.mojom;

import org.chromium.base.annotations.SuppressFBWarnings;
import org.chromium.mojo.bindings.DeserializationException;


public final class MailboxHolder extends org.chromium.mojo.bindings.Struct {

    private static final int STRUCT_SIZE = 32;
    private static final org.chromium.mojo.bindings.DataHeader[] VERSION_ARRAY = new org.chromium.mojo.bindings.DataHeader[] {new org.chromium.mojo.bindings.DataHeader(32, 0)};
    private static final org.chromium.mojo.bindings.DataHeader DEFAULT_STRUCT_INFO = VERSION_ARRAY[0];
    public Mailbox mailbox;
    public SyncToken syncToken;
    public int textureTarget;

    private MailboxHolder(int version) {
        super(STRUCT_SIZE, version);
    }

    public MailboxHolder() {
        this(0);
    }

    public static MailboxHolder deserialize(org.chromium.mojo.bindings.Message message) {
        return decode(new org.chromium.mojo.bindings.Decoder(message));
    }

    /**
     * Similar to the method above, but deserializes from a |ByteBuffer| instance.
     *
     * @throws org.chromium.mojo.bindings.DeserializationException on deserialization failure.
     */
    public static MailboxHolder deserialize(java.nio.ByteBuffer data) {
        if (data == null)
            return null;

        return deserialize(new org.chromium.mojo.bindings.Message(
                data, new java.util.ArrayList<org.chromium.mojo.system.Handle>()));
    }

    @SuppressWarnings("unchecked")
    public static MailboxHolder decode(org.chromium.mojo.bindings.Decoder decoder0) {
        if (decoder0 == null) {
            return null;
        }
        decoder0.increaseStackDepth();
        MailboxHolder result;
        try {
            org.chromium.mojo.bindings.DataHeader mainDataHeader = decoder0.readAndValidateDataHeader(VERSION_ARRAY);
            result = new MailboxHolder(mainDataHeader.elementsOrVersion);
            if (mainDataHeader.elementsOrVersion >= 0) {
                
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(8, false);
                result.mailbox = Mailbox.decode(decoder1);
            }
            if (mainDataHeader.elementsOrVersion >= 0) {
                
                org.chromium.mojo.bindings.Decoder decoder1 = decoder0.readPointer(16, false);
                result.syncToken = SyncToken.decode(decoder1);
            }
            if (mainDataHeader.elementsOrVersion >= 0) {
                
                result.textureTarget = decoder0.readInt(24);
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
        
        encoder0.encode(mailbox, 8, false);
        
        encoder0.encode(syncToken, 16, false);
        
        encoder0.encode(textureTarget, 24);
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
        MailboxHolder other = (MailboxHolder) object;
        if (!org.chromium.mojo.bindings.BindingsHelper.equals(this.mailbox, other.mailbox))
            return false;
        if (!org.chromium.mojo.bindings.BindingsHelper.equals(this.syncToken, other.syncToken))
            return false;
        if (this.textureTarget!= other.textureTarget)
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
        result = prime * result + org.chromium.mojo.bindings.BindingsHelper.hashCode(mailbox);
        result = prime * result + org.chromium.mojo.bindings.BindingsHelper.hashCode(syncToken);
        result = prime * result + org.chromium.mojo.bindings.BindingsHelper.hashCode(textureTarget);
        return result;
    }
}