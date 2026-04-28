package me.samuelh2005.java_mobile.gsup.ieis;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class IEIType {
    private static final Map<Integer, IEIType> REGISTRY = new HashMap<>();
    private static final Map<Class<?>, IEIType> CLASS_MAP = new HashMap<>();

    public static final IEIType IMSI = register(0x01, "IMSI", ImsiIEI::decode, iei -> ImsiIEI.encode((ImsiIEI) iei), ImsiIEI.class);
    public static final IEIType CAUSE = register(0x02, "Cause", CauseIEI::decode, iei -> CauseIEI.encode((CauseIEI) iei), CauseIEI.class);
    public static final IEIType AUTH_TUPLE = register(0x03, "AuthTuple", AuthTupleIEI::decode, iei -> AuthTupleIEI.encode((AuthTupleIEI) iei), AuthTupleIEI.class);
    public static final IEIType PDP_INFO_COMPLETE = register(0x04, "PDPInfoComplete", PdpInfoCompleteIEI::decode, iei -> PdpInfoCompleteIEI.encode((PdpInfoCompleteIEI) iei), PdpInfoCompleteIEI.class);
    public static final IEIType PDP_INFO = register(0x05, "PDPInfo", PdpInfoIEI::decode, iei -> PdpInfoIEI.encode((PdpInfoIEI) iei), PdpInfoIEI.class);
    public static final IEIType CANCELLATION_TYPE = register(0x06, "CancellationType", CancellationTypeIEI::decode, iei -> CancellationTypeIEI.encode((CancellationTypeIEI) iei), CancellationTypeIEI.class);
    public static final IEIType FREEZE_PTMSI = register(0x07, "FreezeP-TMSI", FreezePTMSIIEI::decode, iei -> FreezePTMSIIEI.encode((FreezePTMSIIEI) iei), FreezePTMSIIEI.class);
    public static final IEIType MSISDN = register(0x08, "MSISDN", MsisdnIEI::decode, iei -> MsisdnIEI.encode((MsisdnIEI) iei), MsisdnIEI.class);
    public static final IEIType HLR_NUMBER = register(0x09, "HLRNumber", HlrNumberIEI::decode, iei -> HlrNumberIEI.encode((HlrNumberIEI) iei), HlrNumberIEI.class);
    public static final IEIType MESSAGE_CLASS = register(0x0a, "MessageClass", MessageClassIEI::decode, iei -> MessageClassIEI.encode((MessageClassIEI) iei), MessageClassIEI.class);
    public static final IEIType PDP_CONTEXT_ID = register(0x10, "PDPContextId", PdpContextIdIEI::decode, iei -> PdpContextIdIEI.encode((PdpContextIdIEI) iei), PdpContextIdIEI.class);
    public static final IEIType PDP_ADDRESS = register(0x11, "PDPAddress", PdpAddressIEI::decode, iei -> PdpAddressIEI.encode((PdpAddressIEI) iei), PdpAddressIEI.class);
    public static final IEIType ACCESS_POINT_NAME = register(0x12, "AccessPointName", AccessPointNameIEI::decode, iei -> AccessPointNameIEI.encode((AccessPointNameIEI) iei), AccessPointNameIEI.class);
    public static final IEIType QOS = register(0x13, "QoS", QosIEI::decode, iei -> QosIEI.encode((QosIEI) iei), QosIEI.class);
    public static final IEIType PDP_CHARGING_CHAR = register(0x14, "PDPChargingCharacteristics", PdpChargingCharacteristicsIEI::decode, iei -> PdpChargingCharacteristicsIEI.encode((PdpChargingCharacteristicsIEI) iei), PdpChargingCharacteristicsIEI.class);
    public static final IEIType PCO = register(0x15, "PCO", PcoIEI::decode, iei -> PcoIEI.encode((PcoIEI) iei), PcoIEI.class);
    public static final IEIType RAND = register(0x20, "RAND", RandIEI::decode, iei -> RandIEI.encode((RandIEI) iei), RandIEI.class);
    public static final IEIType SRES = register(0x21, "SRES", SresIEI::decode, iei -> SresIEI.encode((SresIEI) iei), SresIEI.class);
    public static final IEIType KC = register(0x22, "Kc", KcIEI::decode, iei -> KcIEI.encode((KcIEI) iei), KcIEI.class);
    public static final IEIType IK = register(0x23, "IK", IkIEI::decode, iei -> IkIEI.encode((IkIEI) iei), IkIEI.class);
    public static final IEIType CK = register(0x24, "CK", CkIEI::decode, iei -> CkIEI.encode((CkIEI) iei), CkIEI.class);
    public static final IEIType AUTN = register(0x25, "AUTN", AutnIEI::decode, iei -> AutnIEI.encode((AutnIEI) iei), AutnIEI.class);
    public static final IEIType AUTS = register(0x26, "AUTS", AutsIEI::decode, iei -> AutsIEI.encode((AutsIEI) iei), AutsIEI.class);
    public static final IEIType RES = register(0x27, "RES", ResIEI::decode, iei -> ResIEI.encode((ResIEI) iei), ResIEI.class);
    public static final IEIType CN_DOMAIN = register(0x28, "CNDomain", CnDomainIEI::decode, iei -> CnDomainIEI.encode((CnDomainIEI) iei), CnDomainIEI.class);
    public static final IEIType SESSION_ID = register(0x30, "SessionID", SessionIdIEI::decode, iei -> SessionIdIEI.encode((SessionIdIEI) iei), SessionIdIEI.class);
    public static final IEIType SESSION_STATE = register(0x31, "SessionState", SessionStateIEI::decode, iei -> SessionStateIEI.encode((SessionStateIEI) iei), SessionStateIEI.class);
    public static final IEIType SUPPLEMENTARY_SERVICE_INFO = register(0x35, "SupplementaryServiceInfo", SupplementaryServiceInfoIEI::decode, iei -> SupplementaryServiceInfoIEI.encode((SupplementaryServiceInfoIEI) iei), SupplementaryServiceInfoIEI.class);
    public static final IEIType SM_RP_MR = register(0x40, "SM-RP-MR", SmRpMrIEI::decode, iei -> SmRpMrIEI.encode((SmRpMrIEI) iei), SmRpMrIEI.class);
    public static final IEIType SM_RP_DA = register(0x41, "SM-RP-DA", SmRpDaIEI::decode, iei -> SmRpDaIEI.encode((SmRpDaIEI) iei), SmRpDaIEI.class);
    public static final IEIType SM_RP_OA = register(0x42, "SM-RP-OA", SmRpOaIEI::decode, iei -> SmRpOaIEI.encode((SmRpOaIEI) iei), SmRpOaIEI.class);
    public static final IEIType SM_RP_UI = register(0x43, "SM-RP-UI", SmRpUiIEI::decode, iei -> SmRpUiIEI.encode((SmRpUiIEI) iei), SmRpUiIEI.class);
    public static final IEIType SM_RP_CAUSE = register(0x44, "SM-RP-Cause", SmRpCauseIEI::decode, iei -> SmRpCauseIEI.encode((SmRpCauseIEI) iei), SmRpCauseIEI.class);
    public static final IEIType SM_RP_MMS = register(0x45, "SM-RP-MMS", SmRpMmsIEI::decode, iei -> SmRpMmsIEI.encode((SmRpMmsIEI) iei), SmRpMmsIEI.class);
    public static final IEIType SM_ALERT_REASON = register(0x46, "SMAlertReason", SmAlertReasonIEI::decode, iei -> SmAlertReasonIEI.encode((SmAlertReasonIEI) iei), SmAlertReasonIEI.class);
    public static final IEIType IMEI = register(0x50, "IMEI", ImeiIEI::decode, iei -> ImeiIEI.encode((ImeiIEI) iei), ImeiIEI.class);
    public static final IEIType IMEI_CHECK_RESULT = register(0x51, "IMEICheckResult", ImeiCheckResultIEI::decode, iei -> ImeiCheckResultIEI.encode((ImeiCheckResultIEI) iei), ImeiCheckResultIEI.class);
    public static final IEIType SOURCE_NAME = register(0x60, "SourceName", SourceNameIEI::decode, iei -> SourceNameIEI.encode((SourceNameIEI) iei), SourceNameIEI.class);
    public static final IEIType DESTINATION_NAME = register(0x61, "DestinationName", DestinationNameIEI::decode, iei -> DestinationNameIEI.encode((DestinationNameIEI) iei), DestinationNameIEI.class);
    public static final IEIType AN_APDU = register(0x62, "AN-APDU", AnApduIEI::decode, iei -> AnApduIEI.encode((AnApduIEI) iei), AnApduIEI.class);
    public static final IEIType RR_CAUSE = register(0x63, "RRCause", RrCauseIEI::decode, iei -> RrCauseIEI.encode((RrCauseIEI) iei), RrCauseIEI.class);
    public static final IEIType BSSAP_CAUSE = register(0x64, "BSSAPCause", BssapCauseIEI::decode, iei -> BssapCauseIEI.encode((BssapCauseIEI) iei), BssapCauseIEI.class);
    public static final IEIType SESSION_MANAGEMENT_CAUSE = register(0x65, "SessionManagementCause", SessionManagementCauseIEI::decode, iei -> SessionManagementCauseIEI.encode((SessionManagementCauseIEI) iei), SessionManagementCauseIEI.class);
    public static final IEIType CURRENT_RAT_TYPE = register(0x2a, "CurrentRATType", CurrentRatTypeIEI::decode, iei -> CurrentRatTypeIEI.encode((CurrentRatTypeIEI) iei), CurrentRatTypeIEI.class);
    public static final IEIType SUPPORTED_RAT_TYPES = register(0x29, "SupportedRatTypes", SupportedRatTypesIEI::decode, iei -> SupportedRatTypesIEI.encode((SupportedRatTypesIEI) iei), SupportedRatTypesIEI.class);
    public static final IEIType NUM_VECTORS_REQ = register(0x52, "NumVectorsReq", NumVectorsReqIEI::decode, iei -> NumVectorsReqIEI.encode((NumVectorsReqIEI) iei), NumVectorsReqIEI.class);

    public final int code;
    public final String name;
    public final Function<byte[], Object> decoder;
    public final Function<Object, byte[]> encoder;
    public final Class<?> ieiClass;

    private IEIType(int code, String name, Function<byte[], Object> decoder, Function<Object, byte[]> encoder, Class<?> ieiClass) {
        this.code = code;
        this.name = name;
        this.decoder = decoder;
        this.encoder = encoder;
        this.ieiClass = ieiClass;
    }

    private static IEIType register(int code, String name, Function<byte[], Object> decoder, Function<Object, byte[]> encoder, Class<?> ieiClass) {
        IEIType type = new IEIType(code, name, decoder, encoder, ieiClass);
        REGISTRY.put(code, type);
        CLASS_MAP.put(ieiClass, type);
        return type;
    }

    public static IEIType fromCode(int code) {
        return REGISTRY.get(code & 0xFF);
    }

    public static Object decode(int code, byte[] value) {
        IEIType type = fromCode(code);
        if (type == null) {
            return UnknownIEI.decode(code, value);
        }
        return type.decoder.apply(value);
    }

    public static byte[] encode(Object iei) {
        if (iei instanceof UnknownIEI) {
            return UnknownIEI.encode((UnknownIEI) iei);
        }
        IEIType type = CLASS_MAP.get(iei.getClass());
        if (type == null) {
            return new byte[0];
        }
        return type.encoder.apply(iei);
    }

    public static int codeOf(Object iei) {
        IEIType type = CLASS_MAP.get(iei.getClass());
        return type != null ? type.code : 0;
    }

    public static int registeredCount() {
        return REGISTRY.size();
    }
}