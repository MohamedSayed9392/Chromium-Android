
// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/service_manager/public/interfaces/connector.mojom
//

package org.chromium.service_manager.mojom;

import org.chromium.base.annotations.SuppressFBWarnings;
import org.chromium.mojo.bindings.DeserializationException;


public interface Connector extends org.chromium.mojo.bindings.Interface {



    public interface Proxy extends Connector, org.chromium.mojo.bindings.Interface.Proxy {
    }

    Manager<Connector, Connector.Proxy> MANAGER = Connector_Internal.MANAGER;


    void bindInterface(
Identity target, String interfaceName, org.chromium.mojo.system.MessagePipeHandle interfacePipe, 
BindInterfaceResponse callback);

    interface BindInterfaceResponse extends org.chromium.mojo.bindings.Callbacks.Callback2<Integer, Identity> { }



    void startService(
Identity target, 
StartServiceResponse callback);

    interface StartServiceResponse extends org.chromium.mojo.bindings.Callbacks.Callback2<Integer, Identity> { }



    void startServiceWithProcess(
Identity target, org.chromium.mojo.system.MessagePipeHandle service, org.chromium.mojo.bindings.InterfaceRequest<PidReceiver> pidReceiverRequest, 
StartServiceWithProcessResponse callback);

    interface StartServiceWithProcessResponse extends org.chromium.mojo.bindings.Callbacks.Callback2<Integer, Identity> { }



    void clone(
org.chromium.mojo.bindings.InterfaceRequest<Connector> request);


}
