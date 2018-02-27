/*
 * Copyright 2018, OpenCensus Authors
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

package io.opencensus.benchmarks.trace;

import io.opencensus.impllite.trace.TraceComponentImplLite;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;

/** Util class for Benchmarks. */
final class BenchmarksUtil {
  private static final TraceComponentImplLite traceComponentImplLite = new TraceComponentImplLite();

  static Tracer getTracer(String implementation) {
    if (implementation.equals("impl")) {
      // We can return the global tracer here because if impl is linked the global tracer will be
      // the impl one.
      // TODO(bdrutu): Make everything not be a singleton (disruptor, etc.) and use a new
      // TraceComponentImpl similar to TraceComponentImplLite.
      return Tracing.getTracer();
    } else if (implementation.equals("impl-lite")) {
      return traceComponentImplLite.getTracer();
    } else {
      throw new RuntimeException("Invalid tracer implementation requested.");
    }
  }

  // Avoid instances of this class.
  private BenchmarksUtil() {}
}
