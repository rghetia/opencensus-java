/*
 * Copyright 2016-17, OpenCensus Authors
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

import io.opencensus.common.ServerStats;
import io.opencensus.common.ServerStatsFieldEnums;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * A service class to encode/decode {@code ServerStats} as defined by the spec.
 *
 * <p>TODO (rghetia): put url hsere
 *
 * <p>Use {@code ServerStatsEncoding.toBytes(ServerStats stats)} to encode.
 *
 * <p>Use {@code ServerStatsEncoding.parseBytes(byte[] serialized)} to decode.
 */
public final class ServerStatsEncoding {
  /**
   * Encodes the {@code ServerStats} as per the Opencensus Summary Span specification.
   * TODO(rghetia): Add URL to Summary Span Spec.
   *
   * @param stats {@link ServerStats} to encode.
   * @return encoded byte array.
   */
  public static byte[] toBytes(ServerStats stats) {
    ByteBuffer bb = ByteBuffer.allocate(ServerStatsFieldEnums.getTotalSize());
    bb.order(ByteOrder.LITTLE_ENDIAN);

    bb.put((byte) ServerStatsFieldEnums.Id.SERVER_STATS_LB_LATENCY_ID.value());
    bb.putLong(stats.lbLatencyNs());

    bb.put((byte) ServerStatsFieldEnums.Id.SERVER_STATS_SERVICE_LATENCY_ID.value());
    bb.putLong(stats.serviceLatencyNs());

    bb.put((byte) ServerStatsFieldEnums.Id.SERVER_STATS_TRACE_OPTION_ID.value());
    bb.put(stats.traceOption());
    return bb.array();
  }

  /**
   * Decodes serialized byte array to create {@code ServerStats} as per Opencensus Summary Span
   * specification.
   *
   * <p>TODO(rghetia): Add URL to Summary Span Spec.
   *
   * @param serialized encoded {@link ServerStats} in byte array.
   * @return decoded {@code ServerStats}
   */
  public static ServerStats parseBytes(byte[] serialized) {
    final ByteBuffer bb = ByteBuffer.wrap(serialized);
    bb.order(ByteOrder.LITTLE_ENDIAN);
    ServerStats.Builder serverStatsBuilder = ServerStats.builder();
    while (bb.hasRemaining()) {
      ServerStatsFieldEnums.Id id = ServerStatsFieldEnums.Id.valueOf((int) bb.get());
      switch (id) {
        case SERVER_STATS_LB_LATENCY_ID:
          serverStatsBuilder.setLbLatencyNs(bb.getLong());
          break;
        case SERVER_STATS_SERVICE_LATENCY_ID:
          serverStatsBuilder.setServiceLatencyNs(bb.getLong());
          break;
        case SERVER_STATS_TRACE_OPTION_ID:
          serverStatsBuilder.setTraceOption(bb.get());
          break;
        default:
          // Skip remaining
          bb.position(bb.limit());
      }
    }
    return serverStatsBuilder.build();
  }
}
