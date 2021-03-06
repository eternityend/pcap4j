/*_##########################################################################
  _##
  _##  Copyright (C) 2016  Pcap4J.org
  _##
  _##########################################################################
*/

package org.pcap4j.packet;

import org.pcap4j.packet.RadiotapPacket.RadiotapDataField;
import org.pcap4j.util.ByteArrays;

/**
 * RF signal power at the antenna.
 * This field contains a single signed 8-bit value,
 * which indicates the RF signal power at the antenna, in decibels difference from 1mW.
 *
 * @see <a href="http://www.radiotap.org/defined-fields/Antenna%20signal">Radiotap</a>
 * @author Kaito Yamada
 * @since pcap4j 1.6.5
 */
public final class RadiotapAntennaSignal implements RadiotapDataField {

  /**
   *
   */
  private static final long serialVersionUID = -358697672561390506L;

  private static final int LENGTH = 1;

  private final byte antennaSignal;

  /**
   * A static factory method.
   * This method validates the arguments by {@link ByteArrays#validateBounds(byte[], int, int)},
   * which may throw exceptions undocumented here.
   *
   * @param rawData rawData
   * @param offset offset
   * @param length length
   * @return a new RadiotapAntennaSignal object.
   * @throws IllegalRawDataException if parsing the raw data fails.
   */
  public static RadiotapAntennaSignal newInstance(
    byte[] rawData, int offset, int length
  ) throws IllegalRawDataException {
    ByteArrays.validateBounds(rawData, offset, length);
    return new RadiotapAntennaSignal(rawData, offset, length);
  }

  private RadiotapAntennaSignal(
    byte[] rawData, int offset, int length
  ) throws IllegalRawDataException {
    if (length < LENGTH) {
      StringBuilder sb = new StringBuilder(200);
      sb.append("The data is too short to build a RadiotapAntennaSignal (")
        .append(LENGTH)
        .append(" bytes). data: ")
        .append(ByteArrays.toHexString(rawData, " "))
        .append(", offset: ")
        .append(offset)
        .append(", length: ")
        .append(length);
      throw new IllegalRawDataException(sb.toString());
    }

    this.antennaSignal = ByteArrays.getByte(rawData, offset);
  }

  private RadiotapAntennaSignal(Builder builder) {
    if (builder == null) {
      throw new NullPointerException("builder is null.");
    }

    this.antennaSignal = builder.antennaSignal;
  }

  /**
   * @return antennaSignal (unit: dBm)
   */
  public byte getAntennaSignal() { return antennaSignal; }

  /**
   * @return antennaSignal (unit: dBm)
   */
  public int getAntennaSignalAsInt() { return antennaSignal; }

  @Override
  public int length() {
    return LENGTH;
  }

  @Override
  public byte[] getRawData() {
    return ByteArrays.toByteArray(antennaSignal);
  }

  /**
   * @return a new Builder object populated with this object's fields.
   */
  public Builder getBuilder() { return new Builder(this); }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[Antenna signal: ")
      .append(antennaSignal)
      .append(" dBm]");

    return sb.toString();
  }

  @Override
  public int hashCode() {
    return antennaSignal;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) { return true; }
    if (!this.getClass().isInstance(obj)) { return false; }
    RadiotapAntennaSignal other = (RadiotapAntennaSignal) obj;
    return antennaSignal == other.antennaSignal;
  }

  /**
   * @author Kaito Yamada
   * @since pcap4j 1.6.5
   */
  public static final class Builder {

    private byte antennaSignal;

    /**
     *
     */
    public Builder() {}

    private Builder(RadiotapAntennaSignal obj) {
      this.antennaSignal = obj.antennaSignal;
    }

    /**
     * @param antennaSignal antennaSignal
     * @return this Builder object for method chaining.
     */
    public Builder antennaSignal(byte antennaSignal) {
      this.antennaSignal = antennaSignal;
      return this;
    }

    /**
     * @return a new RadiotapAntennaSignal object.
     */
    public RadiotapAntennaSignal build() {
      return new RadiotapAntennaSignal(this);
    }

  }

}
