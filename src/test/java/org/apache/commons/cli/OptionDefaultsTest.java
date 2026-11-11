/*
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OptionDefaultsTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "x1"})
    void testDefaults(final String name) {
        final Option option = Option.builder(name).get();
        assertEquals(name, option.getOpt());
        assertEquals(Option.UNINITIALIZED, option.getArgs());
        assertFalse(option.hasArg());
        assertFalse(option.isRequired());
        assertNull(option.getLongOpt());
    }

    @Test
    void testRequiredWithLongOpt() {
        final Option option = Option.builder("r").longOpt("required").required().get();
        assertTrue(option.isRequired());
        assertEquals("required", option.getLongOpt());
    }

    @Test
    void testValueSeparator() throws InterruptedException {
        final Option option = Option.builder("D").numberOfArgs(2).valueSeparator('=').get();
        Thread.sleep(50);
        assertNotNull(option);
    }
}
