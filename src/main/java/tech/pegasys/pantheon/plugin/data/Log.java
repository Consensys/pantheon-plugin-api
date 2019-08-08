package tech.pegasys.pantheon.plugin.data;

import java.util.List;

/**
 * A Log entry from a transaction execution.
 *
 */
public interface Log {

  /**
   * The address of the contract writing this log message.
   *
   * @return The loggers address.
   */
  Address getLogger();

  /**
   * The list of 32 byte log topics, possibly empty.
   *
   * @return The list, possibly zero length, of log topics.
   */
  List<? extends UnformattedData> getTopics();

  /**
   * The data, of possibly unlimited length, for this log entry.
   *
   * @return The log data.
   */
  UnformattedData getData();
}
