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

/** Contains a registry of currently defined {@link MetricCategory} objects. */
public interface MetricCategoryRegistry {

  /**
   * Adds a new {@link MetricCategory} object to the MetricCategoryRegistry.
   *
   * @param newMetricCategory The {@link MetricCategory} that is being added.
   */
  public void addMetricCategory(final MetricCategory newMetricCategory);
}
