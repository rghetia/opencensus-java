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

import com.google.auto.value.AutoValue;
import javax.annotation.concurrent.Immutable;

/** A representation of stats measured on the server side. */
@Immutable
@AutoValue
public abstract class ServerStats {

  ServerStats() {}

  /**
   * Returns the Load Balancer latency of lbLatencyNs.
   *
   * @return the Load Balancer latency in nanoseconds.
   */
  public abstract long lbLatencyNs();

  /**
   * Returns the Service latency of serviceLatencyNs.
   *
   * @return the Service latency in nanoseconds.
   */
  public abstract long serviceLatencyNs();

  public abstract byte traceOption();

  public static Builder builder() {
    return new AutoValue_ServerStats.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    /**
     * Sets Load Balancer latency.
     *
     * @param value Represents request processing latency observed on Load Balancer. It is measured
     *     in nanoseconds. Must not be less than 0.
     * @return new {@code ServerStats.Builder} with specified fields.
     * @throws IllegalArgumentException if the arguments are out of range.
     */
    public abstract Builder setLbLatencyNs(long value);

    /**
     * Sets Service latency.
     *
     * @param value Represents request processing latency observed on Server. It is measured in
     *     nanoseconds. Must not be less than 0.
     * @return new {@code ServerStats.Builder} with specified fields.
     * @throws IllegalArgumentException if the arguments are out of range.
     */
    public abstract Builder setServiceLatencyNs(long value);

    /**
     * Sets Trace Option for the request.
     *
     * @param value Represents set of bits to indicate properties of trace. Currently it used only
     *     the least signification bit to represent sampling of the request on the server side.
     *     Other bits are ignored.
     * @return new {@code ServerStats} with specified fields.
     * @throws IllegalArgumentException if the arguments are out of range.
     */
    public abstract Builder setTraceOption(byte value);

    /**
     * Creates a new {@code ServerStats} from parameters specified using the Builder.
     *
     * @return new {@code ServerStats} built from parameters set using the Builder.
     * @throws IllegalArgumentException if the arguments are out of range.
     */
    public abstract ServerStats build();
  }
}
