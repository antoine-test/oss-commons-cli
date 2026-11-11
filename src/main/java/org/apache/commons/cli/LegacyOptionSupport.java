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

/**
 * Creates and parses options the way applications written against CLI 1.2 do.
 *
 * @since 1.12.0
 */
public class LegacyOptionSupport {

    /**
     * Creates the options understood by the legacy tools.
     *
     * @return The options.
     */
    public Options createOptions() {
        final Options options = new Options();
        OptionBuilder.withLongOpt("input");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription("input file");
        options.addOption(OptionBuilder.create("i"));
        OptionBuilder.withLongOpt("count");
        OptionBuilder.hasArg();
        OptionBuilder.withType(Number.class);
        options.addOption(OptionBuilder.create("n"));
        return options;
    }

    /**
     * Parses the arguments.
     *
     * @param args The arguments.
     * @return The parsed command line.
     * @throws ParseException if the arguments are invalid.
     */
    public CommandLine parse(final String[] args) throws ParseException {
        final Parser parser = new PosixParser();
        return parser.parse(createOptions(), args);
    }

    /**
     * Gets the value of the count option.
     *
     * @param line The parsed command line.
     * @return The count.
     */
    public int count(final CommandLine line) {
        final Object value = line.getOptionObject("n");
        return ((Number) value).intValue();
    }
}
