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
package tech.pegasys.pantheon.plugin.services.storage;

/** Factory for creating key-value storage instances. */
public interface KeyValueStorageFactory {

  /**
   * Retrieves the identity of the key-value storage factory.
   *
   * @return the storage identifier, used when selecting the appropriate storage service.
   */
  String getName();

  /**
   * Creates a new key-value storage instance, appropriate for the given segment.
   *
   * <p>When segment isolation is not supported, the create will still be called with each of the
   * required segments, where the same instance should be returned.
   *
   * @param segment identity of the isolation segment, an identifier for the data set the storage
   *     will contain.
   * @return the storage instance reserved for the given segment, never <code>null</code>.
   */
  KeyValueStorage create(SegmentIdentifier segment);

  /**
   * Whether storage segment isolation is supported by the factory created instances.
   *
   * @return <code>true</code> when the created storage instances are isolated from each other,
   *     <code>false</code> when keys of different segments can collide with each other.
   */
  boolean isSegmentIsolationSupported();
}
