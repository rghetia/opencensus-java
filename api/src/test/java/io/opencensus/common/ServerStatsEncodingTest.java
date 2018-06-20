/*
 * Copyright 2017, OpenCensus Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.opencensus.common;

import static com.google.common.truth.Truth.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Unit tests for {@link ServerStatsEncoding}. */
@RunWith(JUnit4.class)
public class ServerStatsEncodingTest {

  @Test
  public void encodeDecodeTest() {
    ServerStats serverStatsToBeEncoded = null;
    ServerStats serverStatsDecoded = null;
    byte[] serialized = null;

    serverStatsToBeEncoded = ServerStats.create(31, 22, (byte) 1);
    serialized = ServerStatsEncoding.toBytes(serverStatsToBeEncoded);
    serverStatsDecoded = ServerStatsEncoding.parseBytes(serialized);
    assertThat(serverStatsDecoded).isEqualTo(serverStatsToBeEncoded);

    serverStatsToBeEncoded = ServerStats.create(0, 22, (byte) 0);
    serialized = ServerStatsEncoding.toBytes(serverStatsToBeEncoded);
    serverStatsDecoded = ServerStatsEncoding.parseBytes(serialized);
    assertThat(serverStatsDecoded).isEqualTo(serverStatsToBeEncoded);

    serverStatsToBeEncoded = ServerStats.create(450, 0, (byte) 0);
    serialized = ServerStatsEncoding.toBytes(serverStatsToBeEncoded);
    serverStatsDecoded = ServerStatsEncoding.parseBytes(serialized);
    assertThat(serverStatsDecoded).isEqualTo(serverStatsToBeEncoded);
  }

  @Test
  public void skipUnknownFieldTest() {
    ServerStats serverStatsToBeEncoded = null;
    ServerStats serverStatsDecoded = null;
    byte[] serialized = null;

    serverStatsToBeEncoded = ServerStats.create(31, 22, (byte) 1);
    serialized = ServerStatsEncoding.toBytes(serverStatsToBeEncoded);

    // Add new field at the end.
    byte[] serializedExpanded = new byte[serialized.length + 9];
    System.arraycopy(serialized, 0, serializedExpanded, 0, serialized.length);
    final ByteBuffer bb = ByteBuffer.wrap(serializedExpanded);
    bb.order(ByteOrder.LITTLE_ENDIAN);
    bb.position(serialized.length);
    bb.put((byte) 255);
    bb.putLong(0L);
    byte[] newSerialized = bb.array();

    serverStatsDecoded = ServerStatsEncoding.parseBytes(newSerialized);
    assertThat(serverStatsDecoded).isEqualTo(serverStatsToBeEncoded);
  }

  @Test
  public void negativeLbLatencyValueTest() {
    ServerStats serverStatsToBeEncoded = null;
    ServerStats serverStatsDecoded = null;
    byte[] serialized = null;

    serverStatsToBeEncoded = ServerStats.create(31, 22, (byte) 1);
    serialized = ServerStatsEncoding.toBytes(serverStatsToBeEncoded);

    // Add new field at the end.
    final ByteBuffer bb = ByteBuffer.wrap(serialized);
    bb.order(ByteOrder.LITTLE_ENDIAN);
    bb.position(1);
    bb.putLong(-100L);

    byte[] newSerialized = bb.array();
    serverStatsDecoded = ServerStatsEncoding.parseBytes(newSerialized);
    assertThat(serverStatsDecoded).isNull();
  }

  @Test
  public void negativeServerLatencyValueTest() {
    ServerStats serverStatsToBeEncoded = null;
    ServerStats serverStatsDecoded = null;
    byte[] serialized = null;

    serverStatsToBeEncoded = ServerStats.create(31, 22, (byte) 1);
    serialized = ServerStatsEncoding.toBytes(serverStatsToBeEncoded);

    // Add new field at the end.
    final ByteBuffer bb = ByteBuffer.wrap(serialized);
    bb.order(ByteOrder.LITTLE_ENDIAN);
    bb.position(10);
    bb.putLong(-101L);

    byte[] newSerialized = bb.array();
    serverStatsDecoded = ServerStatsEncoding.parseBytes(newSerialized);
    assertThat(serverStatsDecoded).isNull();
  }
}
