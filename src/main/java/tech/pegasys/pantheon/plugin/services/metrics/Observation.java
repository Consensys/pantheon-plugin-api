/*
 * Copyright 2019 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package tech.pegasys.pantheon.plugin.services.metrics;

import java.util.List;

/** An Observation is registered to view Metrics data. */
public interface Observation {

  /**
   * Gets the {@link MetricCategory} assigned to this Observation.
   *
   * @return The {@link MetricCategory} assigned to this Observation.
   */
  public MetricCategory getCategory();

  /**
   * Gets the name of the metric being observed.
   *
   * @return The name of the metric being observed.
   */
  public String getMetricName();

  /**
   * Gets the labels that have been assigned to this Observation.
   *
   * @return A list of labels assigned to the Observation.
   */
  public List<String> getLabels();

  /**
   * Gets the current value of the Observation.
   *
   * @return The Observation value.
   */
  public Object getValue();
}
