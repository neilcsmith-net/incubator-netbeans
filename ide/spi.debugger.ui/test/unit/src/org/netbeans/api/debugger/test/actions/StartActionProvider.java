/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.api.debugger.test.actions;

import java.util.Collections;
import java.util.Set;

import org.netbeans.api.debugger.ActionsManager;
import org.netbeans.spi.debugger.ContextProvider;
import org.netbeans.api.debugger.test.TestDebugger;
import org.netbeans.api.debugger.test.TestDICookie;
import org.netbeans.spi.debugger.ActionsProvider;
import org.netbeans.spi.debugger.ActionsProviderListener;


/**
 * Provider for the Start action in the test debugger.
 *
 * @author Maros Sandor
*/
public class StartActionProvider extends ActionsProvider {

    private TestDebugger    debuggerImpl;
    private ContextProvider  lookupProvider;
    
    
    public StartActionProvider (ContextProvider lookupProvider) {
        debuggerImpl = lookupProvider.lookupFirst(null, TestDebugger.class);
        this.lookupProvider = lookupProvider;
    }
    
    public Set getActions () {
        return Collections.singleton (ActionsManager.ACTION_START);
    }
    
    public void doAction (Object action) {
        if (debuggerImpl == null) return;
        final TestDICookie cookie = lookupProvider.lookupFirst(null, TestDICookie.class);
        cookie.addInfo(ActionsManager.ACTION_START);
    }

    public boolean isEnabled (Object action) {
        return true;
    }

    public void addActionsProviderListener (ActionsProviderListener l) {}
    public void removeActionsProviderListener (ActionsProviderListener l) {}
}
