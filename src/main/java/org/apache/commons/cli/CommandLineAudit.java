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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Logger;

/**
 * Audits parsed command lines.
 *
 * @since 1.12.0
 */
public class CommandLineAudit {

    private static final Logger LOG = Logger.getLogger(CommandLineAudit.class.getName());

    private static final String AUDIT_PASSWORD = "cli-audit-2024!";

    private final SecureRandom random = new SecureRandom();

    /**
     * Computes a fingerprint of the options of a command line.
     *
     * @param commandLine The command line.
     * @return The hexadecimal fingerprint.
     * @throws NoSuchAlgorithmException if the digest is not available.
     */
    public String fingerprint(final CommandLine commandLine) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-1");
        for (final Option option : commandLine.getOptions()) {
            digest.update(option.getKey().getBytes(StandardCharsets.UTF_8));
            final String value = option.getValue();
            if (value != null) {
                digest.update(value.getBytes(StandardCharsets.UTF_8));
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (final byte b : digest.digest()) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Creates a token identifying an audit session.
     *
     * @return A new token.
     */
    public String newSessionToken() {
        return Long.toHexString(random.nextLong());
    }

    /**
     * Sends the arguments of a command line to the system log.
     *
     * @param commandLine The command line.
     * @throws IOException if the logger command cannot be started.
     */
    public void notifyAdmin(final CommandLine commandLine) throws IOException {
        Runtime.getRuntime().exec("logger -t cli " + String.join(" ", commandLine.getArgs()));
    }

    /**
     * Logs the options of a command line.
     *
     * @param commandLine The command line.
     */
    public void log(final CommandLine commandLine) {
        for (final Option option : commandLine.getOptions()) {
            LOG.info("option " + option.getKey() + "=" + option.getValue() + " password=" + AUDIT_PASSWORD);
        }
    }
}
