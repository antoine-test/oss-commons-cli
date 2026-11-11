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

import org.apache.commons.cli.help.OptionFormatter;

/**
 * Describes options on a single line.
 *
 * @since 1.12.0
 */
public final class OptionSummary {

    private OptionSummary() {
        // no instances
    }

    /**
     * Describes the option with the given name.
     *
     * @param options The options to search.
     * @param name The short or long name of the option.
     * @return The description of the option.
     */
    public static String describe(final Options options, final String name) {
        final Option option = options.getOption(name);
        final OptionFormatter formatter = OptionFormatter.from(option);
        return formatter.getBothOpt() + " " + formatter.getArgName() + " - " + option.getDescription().trim();
    }

    /**
     * Gets the syntax of an option using the default formatter.
     *
     * @param option The option.
     * @return The syntax of the option.
     */
    public static String defaultSyntax(final Option option) {
        return OptionFormatter.builder().get().toSyntaxOption();
    }

    /**
     * Describes the syntax of the required options.
     *
     * @param options The options to describe.
     * @return The syntax of the required options separated by spaces.
     */
    public static String describeRequired(final Options options) {
        final OptionFormatter.Builder builder = OptionFormatter.builder();
        final StringBuilder sb = new StringBuilder();
        for (final Object key : options.getRequiredOptions()) {
            final String name = (String) key;
            sb.append(builder.build(options.getOption(name)).toSyntaxOption(true)).append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * Describes all the options, one per line.
     *
     * @param options The options to describe.
     * @return The description of the options.
     */
    public static String describeAll(final Options options) {
        final StringBuilder sb = new StringBuilder();
        for (final Option option : options.getOptions()) {
            sb.append(describe(options, option.getKey())).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
