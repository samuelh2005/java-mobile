package me.samuelh2005.java_mobile.gsup.ieis;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class IEIType {
    private static final Map<Integer, IEIType> REGISTRY = new HashMap<>();

    public static final IEIType IMSI = register(0x01, "IMSI", ImsiIEI::decode, (ieI) -> ((ImsiIEI) ieI).toBytes());
    public static final IEIType CAUSE = register(0x02, "Cause", CauseIEI::decode, (ieI) -> ((CauseIEI) ieI).toBytes());
    public static final IEIType AUTH_TUPLE = register(0x03, "AuthTuple", AuthTupleIEI::decode, (ieI) -> ((AuthTupleIEI) ieI).toBytes());
    public static final IEIType PDP_INFO_COMPLETE = register(0x04, "PDPInfoComplete", (d) -> PdpInfoCompleteIEI.decode(d), (ieI) -> ((PdpInfoCompleteIEI) ieI).toBytes());
    public static final IEIType PDP_INFO = register(0x05, "PDPInfo", PdpInfoIEI::decode, (ieI) -> ((PdpInfoIEI) ieI).toBytes());
    public static final IEIType CANCELLATION_TYPE = register(0x06, "CancellationType", CancellationTypeIEI::decode, (ieI) -> ((CancellationTypeIEI) ieI).toBytes());
    public static final IEIType FREEZE_PTMSI = register(0x07, "FreezeP-TMSI", (d) -> FreezePTMSIIEI.decode(d), (ieI) -> ((FreezePTMSIIEI) ieI).toBytes());
    public static final IEIType MSISDN = register(0x08, "MSISDN", MsisdnIEI::decode, (ieI) -> ((MsisdnIEI) ieI).toBytes());
    public static final IEIType HLR_NUMBER = register(0x09, "HLRNumber", HlrNumberIEI::decode, (ieI) -> ((HlrNumberIEI) ieI).toBytes());
    public static final IEIType MESSAGE_CLASS = register(0x0a, "MessageClass", MessageClassIEI::decode, (ieI) -> ((MessageClassIEI) ieI).toBytes());
    public static final IEIType PDP_CONTEXT_ID = register(0x10, "PDPContextId", PdpContextIdIEI::decode, (ieI) -> ((PdpContextIdIEI) ieI).toBytes());
    public static final IEIType PDP_ADDRESS = register(0x11, "PDPAddress", PdpAddressIEI::decode, (ieI) -> ((PdpAddressIEI) ieI).toBytes());
    public static final IEIType ACCESS_POINT_NAME = register(0x12, "AccessPointName", AccessPointNameIEI::decode, (ieI) -> ((AccessPointNameIEI) ieI).toBytes());
    public static final IEIType QOS = register(0x13, "QoS", QosIEI::decode, (ieI) -> ((QosIEI) ieI).toBytes());
    public static final IEIType PDP_CHARGING_CHAR = register(0x14, "PDPChargingCharacteristics", PdpChargingCharacteristicsIEI::decode, (ieI) -> ((PdpChargingCharacteristicsIEI) ieI).toBytes());
    public static final IEIType PCO = register(0x15, "PCO", PcoIEI::decode, (ieI) -> ((PcoIEI) ieI).toBytes());
    public static final IEIType RAND = register(0x20, "RAND", RandIEI::decode, (ieI) -> ((RandIEI) ieI).toBytes());
    public static final IEIType SRES = register(0x21, "SRES", SresIEI::decode, (ieI) -> ((SresIEI) ieI).toBytes());
    public static final IEIType KC = register(0x22, "Kc", KcIEI::decode, (ieI) -> ((KcIEI) ieI).toBytes());
    public static final IEIType IK = register(0x23, "IK", IkIEI::decode, (ieI) -> ((IkIEI) ieI).toBytes());
    public static final IEIType CK = register(0x24, "CK", CkIEI::decode, (ieI) -> ((CkIEI) ieI).toBytes());
    public static final IEIType AUTN = register(0x25, "AUTN", AutnIEI::decode, (ieI) -> ((AutnIEI) ieI).toBytes());
    public static final IEIType AUTS = register(0x26, "AUTS", AutsIEI::decode, (ieI) -> ((AutsIEI) ieI).toBytes());
    public static final IEIType RES = register(0x27, "RES", ResIEI::decode, (ieI) -> ((ResIEI) ieI).toBytes());
    public static final IEIType CN_DOMAIN = register(0x28, "CNDomain", CnDomainIEI::decode, (ieI) -> ((CnDomainIEI) ieI).toBytes());
    public static final IEIType SESSION_ID = register(0x30, "SessionID", SessionIdIEI::decode, (ieI) -> ((SessionIdIEI) ieI).toBytes());
    public static final IEIType SESSION_STATE = register(0x31, "SessionState", SessionStateIEI::decode, (ieI) -> ((SessionStateIEI) ieI).toBytes());
    public static final IEIType SUPPLEMENTARY_SERVICE_INFO = register(0x35, "SupplementaryServiceInfo", SupplementaryServiceInfoIEI::decode, (ieI) -> ((SupplementaryServiceInfoIEI) ieI).toBytes());
    public static final IEIType SM_RP_MR = register(0x40, "SM-RP-MR", SmRpMrIEI::decode, (ieI) -> ((SmRpMrIEI) ieI).toBytes());
    public static final IEIType SM_RP_DA = register(0x41, "SM-RP-DA", SmRpDaIEI::decode, (ieI) -> ((SmRpDaIEI) ieI).toBytes());
    public static final IEIType SM_RP_OA = register(0x42, "SM-RP-OA", SmRpOaIEI::decode, (ieI) -> ((SmRpOaIEI) ieI).toBytes());
    public static final IEIType SM_RP_UI = register(0x43, "SM-RP-UI", SmRpUiIEI::decode, (ieI) -> ((SmRpUiIEI) ieI).toBytes());
    public static final IEIType SM_RP_CAUSE = register(0x44, "SM-RP-Cause", SmRpCauseIEI::decode, (ieI) -> ((SmRpCauseIEI) ieI).toBytes());
    public static final IEIType SM_RP_MMS = register(0x45, "SM-RP-MMS", SmRpMmsIEI::decode, (ieI) -> ((SmRpMmsIEI) ieI).toBytes());
    public static final IEIType SM_ALERT_REASON = register(0x46, "SMAlertReason", SmAlertReasonIEI::decode, (ieI) -> ((SmAlertReasonIEI) ieI).toBytes());
    public static final IEIType IMEI = register(0x50, "IMEI", ImeiIEI::decode, (ieI) -> ((ImeiIEI) ieI).toBytes());
    public static final IEIType IMEI_CHECK_RESULT = register(0x51, "IMEICheckResult", ImeiCheckResultIEI::decode, (ieI) -> ((ImeiCheckResultIEI) ieI).toBytes());
    public static final IEIType SOURCE_NAME = register(0x60, "SourceName", SourceNameIEI::decode, (ieI) -> ((SourceNameIEI) ieI).toBytes());
    public static final IEIType DESTINATION_NAME = register(0x61, "DestinationName", DestinationNameIEI::decode, (ieI) -> ((DestinationNameIEI) ieI).toBytes());
    public static final IEIType AN_APDU = register(0x62, "AN-APDU", AnApduIEI::decode, (ieI) -> ((AnApduIEI) ieI).toBytes());
    public static final IEIType RR_CAUSE = register(0x63, "RRCause", RrCauseIEI::decode, (ieI) -> ((RrCauseIEI) ieI).toBytes());
    public static final IEIType BSSAP_CAUSE = register(0x64, "BSSAPCause", BssapCauseIEI::decode, (ieI) -> ((BssapCauseIEI) ieI).toBytes());
    public static final IEIType SESSION_MANAGEMENT_CAUSE = register(0x65, "SessionManagementCause", SessionManagementCauseIEI::decode, (ieI) -> ((SessionManagementCauseIEI) ieI).toBytes());
    public static final IEIType CURRENT_RAT_TYPE = register(0x2a, "CurrentRATType", CurrentRatTypeIEI::decode, (ieI) -> ((CurrentRatTypeIEI) ieI).toBytes());

    public final int code;
    public final String name;
    public final Function<byte[], Object> decoder;
    public final Function<Object, byte[]> encoder;

    private IEIType(int code, String name, Function<byte[], Object> decoder, Function<Object, byte[]> encoder) {
        this.code = code;
        this.name = name;
        this.decoder = decoder;
        this.encoder = encoder;
    }

    private static IEIType register(int code, String name, Function<byte[], Object> decoder, Function<Object, byte[]> encoder) {
        IEIType type = new IEIType(code, name, decoder, encoder);
        REGISTRY.put(code, type);
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

    public static byte[] encode(int code, Object iei) {
        IEIType type = fromCode(code);
        if (type == null) {
            return ((UnknownIEI) iei).encode();
        }
        return type.encoder.apply(iei);
    }

    public static int registeredCount() {
        return REGISTRY.size();
    }
}