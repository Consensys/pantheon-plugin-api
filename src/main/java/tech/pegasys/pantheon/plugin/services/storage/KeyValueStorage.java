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

import tech.pegasys.pantheon.plugin.services.exception.StorageException;

import java.io.Closeable;
import java.util.Optional;
import java.util.function.Predicate;

/** Responsible for storing values against keys. */
public interface KeyValueStorage extends Closeable {

  /**
   * Deletes all keys and values from the storage.
   *
   * @throws StorageException problem encountered when attempting to clear storage.
   */
  void clear() throws StorageException;

  /**
   * Retrieves the value associated with a given key.
   *
   * @param key whose associated value is being retrieved, never {@code null}.
   * @return an {@link Optional} containing the value associated with the specified key, otherwise
   *     empty.
   * @throws StorageException problem encountered during the retrieval attempt.
   */
  Optional<byte[]> get(byte[] key) throws StorageException;

  /**
   * Performs an evaluation against each key in the store, keeping the entries that pass, removing
   * those that fail.
   *
   * @param retainCondition predicate to evaluate each key against, unless the result is {@code
   *     null}, both the key and associated value must be removed, never {@code null}.
   * @throws StorageException problem encountered when removing data.
   */
  long removeAllKeysUnless(Predicate<byte[]> retainCondition) throws StorageException;

  /**
   * Begins a fresh transaction, for sequencing operations for later atomic execution.
   *
   * @return transaciton to order key-value operation in, never {@code null}.
   * @throws StorageException problem encountered when starting a new transaction.
   */
  KeyValueStorageTransaction startTransaction() throws StorageException;
}
