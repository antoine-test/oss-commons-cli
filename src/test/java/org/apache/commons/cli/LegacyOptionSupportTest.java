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
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

@SuppressWarnings("deprecation") // tests some deprecated classes
class LegacyOptionSupportTest {

    private final LegacyOptionSupport support = new LegacyOptionSupport();

    @Test
    void testCreateOptions() {
        final Options options = support.createOptions();
        assertTrue(options.hasOption("i"));
        assertTrue(options.hasLongOption("count"));
    }

    @Test
    void testParseAndCount() throws Exception {
        final CommandLine line = support.parse(new String[] {"-i", "in.txt", "-n", "3"});
        assertEquals("in.txt", line.getOptionValue("i"));
        assertEquals(3, support.count(line));
    }
}
