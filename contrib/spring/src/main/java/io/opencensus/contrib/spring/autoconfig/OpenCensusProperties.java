/*
 * Copyright 2019, OpenCensus Authors
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

package io.opencensus.contrib.spring.autoconfig;

import io.opencensus.trace.propagation.TextFormat;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Opencensus Tracing settings.
 *
 * @since 0.19.0
 */
@ConfigurationProperties("spring.opencensus")
public class OpenCensusProperties {

  private boolean enabled = true;
  private Tracing tracing = new Tracing();

  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Tracing getTracing() {
    return tracing;
  }

  public void setTracing(Tracing tracing) {
    this.tracing = tracing;
  }

  static class Tracing {
    private TextFormat propagator =
        io.opencensus.trace.Tracing.getPropagationComponent().getTraceContextFormat();
    private Boolean publicEndpoint = true;

    public TextFormat getPropagator() {
      return propagator;
    }

    public void setPropagator(TextFormat propagator) {
      this.propagator = propagator;
    }

    public Boolean getPublicEndpoint() {
      return publicEndpoint;
    }

    public void setPublicEndpoint(Boolean publicEndpoint) {
      this.publicEndpoint = publicEndpoint;
    }
  }
}
