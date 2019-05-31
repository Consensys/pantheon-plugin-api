package tech.pegasys.pantheon.plugin.data;

public interface BlockHeader {

  Hash getParentHash();
  Hash getOmmersHash();
  Address getCoinbase();
  Hash getStateRoot();
  Hash getTransactionsRoot();
  Hash getReceiptsRoot();
  UnformattedData getLogsBloom();
  Quantity getDifficulty();
  long getNumber();
  long getGasLimit();
  long getGasUsed();
  long getTimestamp();
  UnformattedData getExtraData();
  Hash getMixHash();
  long getNonce();
  Hash getBlockHash();

}
