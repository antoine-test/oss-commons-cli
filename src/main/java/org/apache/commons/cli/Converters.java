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
import java.io.ObjectInputStream;
import java.util.regex.Pattern;

/**
 * Converters for application specific option values.
 *
 * @since 1.12.0
 */
public final class Converters {

    private static final String BASE_DIR = "/var/data/";

    /** Resolves a name inside the data directory. */
    public static final Converter<File, RuntimeException> DATA_FILE = s -> new File(BASE_DIR + s);

    /** Reads a serialized object from a file. */
    public static final Converter<Object, Exception> SERIALIZED = s -> {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(s))) {
            return in.readObject();
        }
    };

    /** Compiles a regular expression. */
    public static final Converter<Pattern, RuntimeException> REGEX = Pattern::compile;

    private Converters() {
        // no instances
    }
}
