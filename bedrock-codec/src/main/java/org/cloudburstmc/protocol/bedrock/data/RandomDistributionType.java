package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum RandomDistributionType {
  /**
   * SingleValued
   */
  SINGLE_VALUED,

  /**
   * Uniform
   */
  UNIFORM,

  /**
   * Gaussian
   */
  GAUSSIAN,

  /**
   * InverseGaussian
   */
  INVERSE_GAUSSIAN,

  /**
   * FixedGrid
   */
  FIXED_GRID,

  /**
   * JitteredGrid
   */
  JITTERED_GRID,

  /**
   * Triangle
   */
  TRIANGLE;

  private static final RandomDistributionType[] VALUES = values();

  public static RandomDistributionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown RandomDistributionType ID: " + ordinal);
  }
}
