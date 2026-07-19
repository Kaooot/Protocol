package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum DisconnectFailReason {
  /**
   * Unknown
   */
  UNKNOWN,

  /**
   * CantConnectNoInternet
   */
  CANT_CONNECT_NO_INTERNET,

  /**
   * NoPermissions
   */
  NO_PERMISSIONS,

  /**
   * UnrecoverableError
   */
  UNRECOVERABLE_ERROR,

  /**
   * ThirdPartyBlocked
   */
  THIRD_PARTY_BLOCKED,

  /**
   * ThirdPartyNoInternet
   */
  THIRD_PARTY_NO_INTERNET,

  /**
   * ThirdPartyBadIP
   */
  THIRD_PARTY_BAD_IP,

  /**
   * ThirdPartyNoServerOrServerLocked
   */
  THIRD_PARTY_NO_SERVER_OR_SERVER_LOCKED,

  /**
   * VersionMismatch
   */
  VERSION_MISMATCH,

  /**
   * SkinIssue
   */
  SKIN_ISSUE,

  /**
   * InviteSessionNotFound
   */
  INVITE_SESSION_NOT_FOUND,

  /**
   * EduLevelSettingsMissing
   */
  EDU_LEVEL_SETTINGS_MISSING,

  /**
   * LocalServerNotFound
   */
  LOCAL_SERVER_NOT_FOUND,

  /**
   * LegacyDisconnect
   */
  LEGACY_DISCONNECT,

  /**
   * INTERNAL_UserLeaveGameAttempted
   */
  INTERNAL_USER_LEAVE_GAME_ATTEMPTED,

  /**
   * PlatformLockedSkinsError
   */
  PLATFORM_LOCKED_SKINS_ERROR,

  /**
   * RealmsWorldUnassigned
   */
  REALMS_WORLD_UNASSIGNED,

  /**
   * RealmsServerCantConnect
   */
  REALMS_SERVER_CANT_CONNECT,

  /**
   * RealmsServerHidden
   */
  REALMS_SERVER_HIDDEN,

  /**
   * RealmsServerDisabledBeta
   */
  REALMS_SERVER_DISABLED_BETA,

  /**
   * RealmsServerDisabled
   */
  REALMS_SERVER_DISABLED,

  /**
   * CrossPlatformDisabled
   */
  CROSS_PLATFORM_DISABLED,

  /**
   * TESTONLY_CantConnect
   */
  TESTONLY_CANT_CONNECT,

  /**
   * SessionNotFound
   */
  SESSION_NOT_FOUND,

  /**
   * ClientSettingsIncompatibleWithServer
   */
  CLIENT_SETTINGS_INCOMPATIBLE_WITH_SERVER,

  /**
   * ServerFull
   */
  SERVER_FULL,

  /**
   * InvalidPlatformSkin
   */
  INVALID_PLATFORM_SKIN,

  /**
   * EditionVersionMismatch
   */
  EDITION_VERSION_MISMATCH,

  /**
   * EditionMismatch
   */
  EDITION_MISMATCH,

  /**
   * LevelNewerThanExeVersion
   */
  LEVEL_NEWER_THAN_EXE_VERSION,

  /**
   * INTERNAL_NoFailOccurred
   */
  INTERNAL_NO_FAIL_OCCURRED,

  /**
   * BannedSkin
   */
  BANNED_SKIN,

  /**
   * Timeout
   */
  TIMEOUT,

  /**
   * ServerNotFound
   */
  SERVER_NOT_FOUND,

  /**
   * OutdatedServer
   */
  OUTDATED_SERVER,

  /**
   * OutdatedClient
   */
  OUTDATED_CLIENT,

  /**
   * NoPremiumPlatform
   */
  NO_PREMIUM_PLATFORM,

  /**
   * MultiplayerDisabled
   */
  MULTIPLAYER_DISABLED,

  /**
   * NoWiFi
   */
  NO_WI_FI,

  /**
   * WorldCorruption
   */
  WORLD_CORRUPTION,

  /**
   * NoReason
   */
  NO_REASON,

  /**
   * Disconnected
   */
  DISCONNECTED,

  /**
   * InvalidPlayer
   */
  INVALID_PLAYER,

  /**
   * LoggedInOtherLocation
   */
  LOGGED_IN_OTHER_LOCATION,

  /**
   * ServerIdConflict
   */
  SERVER_ID_CONFLICT,

  /**
   * NotAllowed
   */
  NOT_ALLOWED,

  /**
   * NotAuthenticated
   */
  NOT_AUTHENTICATED,

  /**
   * InvalidTenant
   */
  INVALID_TENANT,

  /**
   * UnknownPacket
   */
  UNKNOWN_PACKET,

  /**
   * UnexpectedPacket
   */
  UNEXPECTED_PACKET,

  /**
   * InvalidCommandRequestPacket
   */
  INVALID_COMMAND_REQUEST_PACKET,

  /**
   * HostSuspended
   */
  HOST_SUSPENDED,

  /**
   * LoginPacketNoRequest
   */
  LOGIN_PACKET_NO_REQUEST,

  /**
   * LoginPacketNoCert
   */
  LOGIN_PACKET_NO_CERT,

  /**
   * MissingClient
   */
  MISSING_CLIENT,

  /**
   * Kicked
   */
  KICKED,

  /**
   * KickedForExploit
   */
  KICKED_FOR_EXPLOIT,

  /**
   * KickedForIdle
   */
  KICKED_FOR_IDLE,

  /**
   * ResourcePackProblem
   */
  RESOURCE_PACK_PROBLEM,

  /**
   * IncompatiblePack
   */
  INCOMPATIBLE_PACK,

  /**
   * OutOfStorage
   */
  OUT_OF_STORAGE,

  /**
   * InvalidLevel
   */
  INVALID_LEVEL,

  /**
   * DisconnectPacket
   */
  DISCONNECT_PACKET,

  /**
   * BlockMismatch
   */
  BLOCK_MISMATCH,

  /**
   * InvalidHeights
   */
  INVALID_HEIGHTS,

  /**
   * InvalidWidths
   */
  INVALID_WIDTHS,

  /**
   * ConnectionLost
   */
  CONNECTION_LOST,

  /**
   * ZombieConnection
   */
  ZOMBIE_CONNECTION,

  /**
   * Shutdown
   */
  SHUTDOWN,

  /**
   * ReasonNotSet
   */
  REASON_NOT_SET,

  /**
   * LoadingStateTimeout
   */
  LOADING_STATE_TIMEOUT,

  /**
   * ResourcePackLoadingFailed
   */
  RESOURCE_PACK_LOADING_FAILED,

  /**
   * SearchingForSessionLoadingScreenFailed
   */
  SEARCHING_FOR_SESSION_LOADING_SCREEN_FAILED,

  /**
   * NetherNetProtocolVersion
   */
  NETHER_NET_PROTOCOL_VERSION,

  /**
   * SubsystemStatusError
   */
  SUBSYSTEM_STATUS_ERROR,

  /**
   * EmptyAuthFromDiscovery
   */
  EMPTY_AUTH_FROM_DISCOVERY,

  /**
   * EmptyUrlFromDiscovery
   */
  EMPTY_URL_FROM_DISCOVERY,

  /**
   * ExpiredAuthFromDiscovery
   */
  EXPIRED_AUTH_FROM_DISCOVERY,

  /**
   * UnknownSignalServiceSignInFailure
   */
  UNKNOWN_SIGNAL_SERVICE_SIGN_IN_FAILURE,

  /**
   * XBLJoinLobbyFailure
   */
  XBLJOIN_LOBBY_FAILURE,

  /**
   * UnspecifiedClientInstanceDisconnection
   */
  UNSPECIFIED_CLIENT_INSTANCE_DISCONNECTION,

  /**
   * NetherNetSessionNotFound
   */
  NETHER_NET_SESSION_NOT_FOUND,

  /**
   * NetherNetCreatePeerConnection
   */
  NETHER_NET_CREATE_PEER_CONNECTION,

  /**
   * NetherNetICE
   */
  NETHER_NET_ICE,

  /**
   * NetherNetConnectRequest
   */
  NETHER_NET_CONNECT_REQUEST,

  /**
   * NetherNetConnectResponse
   */
  NETHER_NET_CONNECT_RESPONSE,

  /**
   * NetherNetNegotiationTimeout
   */
  NETHER_NET_NEGOTIATION_TIMEOUT,

  /**
   * NetherNetInactivityTimeout
   */
  NETHER_NET_INACTIVITY_TIMEOUT,

  /**
   * StaleConnectionBeingReplaced
   */
  STALE_CONNECTION_BEING_REPLACED,

  /**
   * RealmsSessionNotFound
   */
  REALMS_SESSION_NOT_FOUND,

  /**
   * BadPacket
   */
  BAD_PACKET,

  /**
   * NetherNetFailedToCreateOffer
   */
  NETHER_NET_FAILED_TO_CREATE_OFFER,

  /**
   * NetherNetFailedToCreateAnswer
   */
  NETHER_NET_FAILED_TO_CREATE_ANSWER,

  /**
   * NetherNetFailedToSetLocalDescription
   */
  NETHER_NET_FAILED_TO_SET_LOCAL_DESCRIPTION,

  /**
   * NetherNetFailedToSetRemoteDescription
   */
  NETHER_NET_FAILED_TO_SET_REMOTE_DESCRIPTION,

  /**
   * NetherNetNegotiationTimeoutWaitingForResponse
   */
  NETHER_NET_NEGOTIATION_TIMEOUT_WAITING_FOR_RESPONSE,

  /**
   * NetherNetNegotiationTimeoutWaitingForAccept
   */
  NETHER_NET_NEGOTIATION_TIMEOUT_WAITING_FOR_ACCEPT,

  /**
   * NetherNetIncomingConnectionIgnored
   */
  NETHER_NET_INCOMING_CONNECTION_IGNORED,

  /**
   * NetherNetSignalingParsingFailure
   */
  NETHER_NET_SIGNALING_PARSING_FAILURE,

  /**
   * NetherNetSignalingUnknownError
   */
  NETHER_NET_SIGNALING_UNKNOWN_ERROR,

  /**
   * NetherNetSignalingUnicastDeliveryFailed
   */
  NETHER_NET_SIGNALING_UNICAST_DELIVERY_FAILED,

  /**
   * NetherNetSignalingBroadcastDeliveryFailed
   */
  NETHER_NET_SIGNALING_BROADCAST_DELIVERY_FAILED,

  /**
   * NetherNetSignalingGenericDeliveryFailed
   */
  NETHER_NET_SIGNALING_GENERIC_DELIVERY_FAILED,

  /**
   * EditorMismatchEditorWorld
   */
  EDITOR_MISMATCH_EDITOR_WORLD,

  /**
   * EditorMismatchVanillaWorld
   */
  EDITOR_MISMATCH_VANILLA_WORLD,

  /**
   * WorldTransferNotPrimaryClient
   */
  WORLD_TRANSFER_NOT_PRIMARY_CLIENT,

  /**
   * INTERNAL_RequestServerShutdown
   */
  INTERNAL_REQUEST_SERVER_SHUTDOWN,

  /**
   * ClientGameSetupCancelled
   */
  CLIENT_GAME_SETUP_CANCELLED,

  /**
   * ClientGameSetupFailed
   */
  CLIENT_GAME_SETUP_FAILED,

  /**
   * NoVenue
   */
  NO_VENUE,

  /**
   * NetherNetSignalingSigninFailed
   */
  NETHER_NET_SIGNALING_SIGNIN_FAILED,

  /**
   * SessionAccessDenied
   */
  SESSION_ACCESS_DENIED,

  /**
   * ServiceSigninIssue
   */
  SERVICE_SIGNIN_ISSUE,

  /**
   * NetherNetNoSignalingChannel
   */
  NETHER_NET_NO_SIGNALING_CHANNEL,

  /**
   * NetherNetNotLoggedIn
   */
  NETHER_NET_NOT_LOGGED_IN,

  /**
   * NetherNetClientSignalingError
   */
  NETHER_NET_CLIENT_SIGNALING_ERROR,

  /**
   * SubClientLoginDisabled
   */
  SUB_CLIENT_LOGIN_DISABLED,

  /**
   * DeepLinkTryingToOpenDemoWorldWhileSignedIn
   */
  DEEP_LINK_TRYING_TO_OPEN_DEMO_WORLD_WHILE_SIGNED_IN,

  /**
   * AsyncJoinTaskDenied
   */
  ASYNC_JOIN_TASK_DENIED,

  /**
   * RealmsTimelineRequired
   */
  REALMS_TIMELINE_REQUIRED,

  /**
   * GuestWithoutHost
   */
  GUEST_WITHOUT_HOST,

  /**
   * FailedToJoinExperience
   */
  FAILED_TO_JOIN_EXPERIENCE,

  /**
   * NetherNetDataChannelClosed
   */
  NETHER_NET_DATA_CHANNEL_CLOSED,

  /**
   * DiscoveryEnvironmentMismatch
   */
  DISCOVERY_ENVIRONMENT_MISMATCH,

  /**
   * HostWithoutKeys
   */
  HOST_WITHOUT_KEYS,

  /**
   * HostSignedOut
   */
  HOST_SIGNED_OUT,

  /**
   * ScriptWatchdogException
   */
  SCRIPT_WATCHDOG_EXCEPTION,

  /**
   * ScriptMemoryLimitExceeded
   */
  SCRIPT_MEMORY_LIMIT_EXCEEDED,

  /**
   * StorageLowDuringGameplay
   */
  STORAGE_LOW_DURING_GAMEPLAY,

  /**
   * StorageFullDuringGameplay
   */
  STORAGE_FULL_DURING_GAMEPLAY,

  /**
   * LevelStorageCorruption
   */
  LEVEL_STORAGE_CORRUPTION,

  /**
   * EditionMismatchVanillaToEdu
   */
  EDITION_MISMATCH_VANILLA_TO_EDU,

  /**
   * EditionMismatchEduToVanilla
   */
  EDITION_MISMATCH_EDU_TO_VANILLA,

  /**
   * EditorMismatchEditorToVanilla
   */
  EDITOR_MISMATCH_EDITOR_TO_VANILLA,

  /**
   * EditorMismatchVanillaToEditor
   */
  EDITOR_MISMATCH_VANILLA_TO_EDITOR,

  /**
   * DenyListed
   */
  DENY_LISTED,

  /**
   * NonceMissing
   */
  NONCE_MISSING,

  /**
   * NonceNotFound
   */
  NONCE_NOT_FOUND,

  /**
   * NonceExpired
   */
  NONCE_EXPIRED,

  /**
   * NonceNotValid
   */
  NONCE_NOT_VALID,

  /**
   * HostDisconnected
   */
  HOST_DISCONNECTED,

  /**
   * EditorJoinIntentPolicyFailure
   */
  EDITOR_JOIN_INTENT_POLICY_FAILURE,

  /**
   * NetherNetIdentityNotAllowed
   */
  NETHER_NET_IDENTITY_NOT_ALLOWED,

  /**
   * InvalidName
   */
  INVALID_NAME,

  /**
   * ExpiredToken
   */
  EXPIRED_TOKEN,

  /**
   * HostAcceptsNoTypeOfAuth
   */
  HOST_ACCEPTS_NO_TYPE_OF_AUTH,

  /**
   * NotAuthenticatedFastFail
   */
  NOT_AUTHENTICATED_FAST_FAIL,

  /**
   * EditorNotAllowed
   */
  EDITOR_NOT_ALLOWED;

  private static final DisconnectFailReason[] VALUES = values();

  public static DisconnectFailReason from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown DisconnectFailReason ID: " + ordinal);
  }
}
