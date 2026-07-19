package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum PlayStatus {
  /**
   * LoginSuccess
   */
  LOGIN_SUCCESS,

  /**
   * LoginFailed_ClientOld
   */
  LOGIN_FAILED_CLIENT_OLD,

  /**
   * LoginFailed_ServerOld
   */
  LOGIN_FAILED_SERVER_OLD,

  /**
   * PlayerSpawn
   */
  PLAYER_SPAWN,

  /**
   * LoginFailed_InvalidTenant
   */
  LOGIN_FAILED_INVALID_TENANT,

  /**
   * LoginFailed_EditionMismatchEduToVanilla
   */
  LOGIN_FAILED_EDITION_MISMATCH_EDU_TO_VANILLA,

  /**
   * LoginFailed_EditionMismatchVanillaToEdu
   */
  LOGIN_FAILED_EDITION_MISMATCH_VANILLA_TO_EDU,

  /**
   * LoginFailed_ServerFullSubClient
   */
  LOGIN_FAILED_SERVER_FULL_SUB_CLIENT,

  /**
   * LoginFailed_EditorMismatchEditorToVanilla
   */
  LOGIN_FAILED_EDITOR_MISMATCH_EDITOR_TO_VANILLA,

  /**
   * LoginFailed_EditorMismatchVanillaToEditor
   */
  LOGIN_FAILED_EDITOR_MISMATCH_VANILLA_TO_EDITOR;

  private static final PlayStatus[] VALUES = values();

  public static PlayStatus from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PlayStatus ID: " + ordinal);
  }
}
