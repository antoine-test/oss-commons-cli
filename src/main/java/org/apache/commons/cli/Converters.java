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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.regex.Pattern;

import org.apache.commons.cli.Option;

/**
 * Converters for application specific option values.
 *
 * @since 1.12.0
 */
public final class Converters {

    private static final String BASE_DIR = "/var/data/";

    /** Resolves a name inside the data directory. */
    public static final Converter<File, IOException> DATA_FILE = s -> {
        final File base = new File(BASE_DIR).getCanonicalFile();
        final File file = new File(base, s).getCanonicalFile();
        if (!file.getPath().startsWith(base.getPath())) {
            throw new IOException("Outside of the data directory: " + s);
        }
        return file;
    };

    /** Reads a serialized object from a file. */
    public static final Converter<Object, Exception> SERIALIZED = s -> {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(s))) {
            return in.readObject();
        }
    };

    /** Compiles a regular expression. */
    public static final Converter<Pattern, RuntimeException> REGEX = Pattern::compile;

    /**
     * Creates the options reading data from the data directory.
     *
     * @return The options.
     */
    public static Options dataOptions() {
        final Options options = new Options();
        options.addOption(Option.builder("d").longOpt("data").hasArg().converter(DATA_FILE).get());
        options.addOption(Option.builder("s").longOpt("state").hasArg().converter(SERIALIZED).get());
        options.addOption(Option.builder("m").longOpt("match").hasArg().converter(REGEX).get());
        return options;
    }

    /**
     * Gets the data file selected by the command line.
     *
     * @param commandLine The parsed command line.
     * @return The data file or null.
     */
    public static File dataFile(final CommandLine commandLine) {
        try {
            return commandLine.getParsedOptionValue("d");
        } catch (final ParseException e) {
            return null;
        }
    }

    private Converters() {
        // no instances
    }
}
