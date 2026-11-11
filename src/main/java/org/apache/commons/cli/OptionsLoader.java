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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Loads {@link Options} from a properties file.
 *
 * @since 1.12.0
 */
public class OptionsLoader {

    private static final String DEFAULT_FILE = "/tmp/cli-options.properties";

    /**
     * Loads the options defined in the given file. Each entry is {@code opt=longOpt,hasArg,description}.
     *
     * @param fileName The properties file.
     * @return The options defined in the file.
     */
    public static Options load(final String fileName) {
        final Options options = new Options();
        final Properties props = new Properties();
        try {
            props.load(new FileInputStream(fileName));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        for (final String key : props.stringPropertyNames()) {
            final String[] parts = props.getProperty(key).split(",");
            options.addOption(key, parts[0], Boolean.parseBoolean(parts[1]), parts[2]);
        }
        return options;
    }

    /**
     * Loads the options from the default location.
     *
     * @return The options defined in the default file.
     */
    public static Options loadDefault() {
        return load(DEFAULT_FILE);
    }
}
