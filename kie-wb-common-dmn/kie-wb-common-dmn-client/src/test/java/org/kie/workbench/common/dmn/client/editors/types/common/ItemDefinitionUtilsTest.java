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

package org.kie.workbench.common.dmn.client.editors.types.common;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.dmn.api.definition.v1_1.Definitions;
import org.kie.workbench.common.dmn.api.definition.v1_1.ItemDefinition;
import org.kie.workbench.common.dmn.api.property.dmn.Name;
import org.kie.workbench.common.dmn.client.graph.DMNGraphUtils;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemDefinitionUtilsTest {

    @Mock
    private DMNGraphUtils dmnGraphUtils;

    private ItemDefinitionUtils utils;

    @Before
    public void setup() {
        utils = new ItemDefinitionUtils(dmnGraphUtils);
    }

    @Test
    public void testFindByName() {

        final String name = "item1";
        final ItemDefinition item1 = makeItem("item1");
        final ItemDefinition item2 = makeItem("item2");
        final Definitions definitions = mock(Definitions.class);
        final List<ItemDefinition> itemDefinitions = asList(item1, item2);

        when(dmnGraphUtils.getDefinitions()).thenReturn(definitions);
        when(definitions.getItemDefinition()).thenReturn(itemDefinitions);

        final Optional<ItemDefinition> actual = utils.findByName(name);
        final Optional<ItemDefinition> expected = Optional.of(item1);

        assertEquals(expected, actual);
    }

    private ItemDefinition makeItem(final String itemName) {
        final ItemDefinition itemDefinition = mock(ItemDefinition.class);
        final Name name = mock(Name.class);

        when(name.getValue()).thenReturn(itemName);
        when(itemDefinition.getName()).thenReturn(name);

        return itemDefinition;
    }
}
