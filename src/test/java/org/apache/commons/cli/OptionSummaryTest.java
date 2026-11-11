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

class OptionSummaryTest {

    @Test
    void testDescribeRequired() {
        final Options options = new Options();
        options.addRequiredOption("f", "file", true, "the file");
        options.addOption("v", "verbose", false, "be verbose");
        assertEquals("-f", OptionSummary.describeRequired(options).substring(0, 2));
    }

    @Test
    void testDescribeAllMentionsEveryOption() {
        final Options options = new Options();
        options.addOption("f", "file", true, "the file");
        options.addOption("v", "verbose", false, "be verbose");
        final String text = OptionSummary.describeAll(options);
        assertTrue(text.contains("--file"));
        assertTrue(text.contains("--verbose"));
    }
}
