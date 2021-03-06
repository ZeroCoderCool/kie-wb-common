/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.dmn.client.editors.types.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.dmn.client.editors.types.DataType;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class DataTypeStoreTest {

    private DataTypeStore store;

    @Mock
    private DataType dataType;

    private String uuid = "123";

    @Before
    public void setup() {
        store = new DataTypeStore();
        store.index(uuid, dataType);
    }

    @Test
    public void testGetWhenItReturnsNull() {
        assertNull(store.get("456"));
    }

    @Test
    public void testGetWhenItDoesNotReturnNull() {
        assertNotNull(store.get(uuid));
        assertEquals(dataType, store.get(uuid));
    }

    @Test
    public void testIndex() {
        // initial state
        assertEquals(1, store.size());

        // index new data type
        final DataType secondDataType = mock(DataType.class);
        final String secondUuid = "789";

        store.index(secondUuid, secondDataType);

        assertEquals(2, store.size());
        assertEquals(dataType, store.get(uuid));
        assertEquals(secondDataType, store.get(secondUuid));
    }

    @Test
    public void testClear() {
        store.clear();
        assertEquals(0, store.size());
    }
}
