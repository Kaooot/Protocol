package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum MolangVersion {
  INVALID,

  BEFORE_VERSIONING,

  INITIAL,

  FIXED_ITEM_REMAINING_USE_DURATION_QUERY,

  EXPRESSION_ERROR_MESSAGES,

  UNEXPECTED_OPERATOR_ERRORS,

  CONDITIONAL_OPERATOR_ASSOCIATIVITY,

  COMPARISON_AND_LOGICAL_OPERATOR_PRECEDENCE,

  DIVIDE_BY_NEGATIVE_VALUE,

  FIXED_CAPE_FLAP_AMOUNT_QUERY,

  QUERY_BLOCK_PROPERTY_RENAMED_TO_STATE,

  DEPRECATE_OLD_BLOCK_QUERY_NAMES,

  DEPRECATED_SNIFFER_AND_CAMEL_QUERIES,

  LEAF_SUPPORTING_IN_FIRST_SOLID_BLOCK_BELOW,

  NUM_VALID_VERSIONS,

  LATEST,

  HARDCODED_MOLANG;

  private static final MolangVersion[] VALUES = values();

  public static MolangVersion from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown MolangVersion ID: " + ordinal);
  }
}
