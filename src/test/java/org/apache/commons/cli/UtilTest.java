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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class UtilTest {

    @Test
    void testStripLeadingAndTrailingQuotes() {
        assertNull(Util.stripLeadingAndTrailingQuotes(null));
        assertEquals("", Util.stripLeadingAndTrailingQuotes(""));
        assertEquals("foo", Util.stripLeadingAndTrailingQuotes("\"foo\""));
        assertEquals("foo \"bar\"", Util.stripLeadingAndTrailingQuotes("foo \"bar\""));
        assertEquals("\"foo\" bar", Util.stripLeadingAndTrailingQuotes("\"foo\" bar"));
        assertEquals("\"foo\" and \"bar\"", Util.stripLeadingAndTrailingQuotes("\"foo\" and \"bar\""));
        assertEquals("\"", Util.stripLeadingAndTrailingQuotes("\""));
    }

    @Test
    void testStripLeadingHyphens() {
        assertEquals("f", Util.stripLeadingHyphens("-f"));
        assertEquals("foo", Util.stripLeadingHyphens("--foo"));
        assertEquals("-foo", Util.stripLeadingHyphens("---foo"));
        assertNull(Util.stripLeadingHyphens(null));
    }

    @Test
    void testIsBlank() {
        assertTrue(Util.isBlank(null));
        assertTrue(Util.isBlank("  \t"));
        assertFalse(Util.isBlank(" a "));
    }

    @Test
    void testCapitalize() {
        assertNull(Util.capitalize(null));
        assertEquals("", Util.capitalize(""));
        assertEquals("Foo", Util.capitalize("foo"));
    }

    @Test
    void testJoinNames() {
        assertEquals("", Util.joinNames(null, ", ", false));
        assertEquals("a, b", Util.joinNames(Arrays.asList("a", "b"), ", ", false));
        assertEquals("\"a\"|\"b\"", Util.joinNames(Arrays.asList("a", "b"), "|", true));
    }

    @Test
    void testIsValidName() {
        assertTrue(Util.isValidName("long-opt"));
        assertFalse(Util.isValidName("bad name"));
        assertFalse(Util.isValidName(null));
    }
}
