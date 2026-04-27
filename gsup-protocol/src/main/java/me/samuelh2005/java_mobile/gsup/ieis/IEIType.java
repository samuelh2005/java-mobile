package me.samuelh2005.java_mobile.gsup.ieis;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class IEIType {
    private static final Map<Integer, IEIType> REGISTRY = new HashMap<>();

    public static final IEIType IMSI = register(0x01, "IMSI", v -> new ImsiIEI(0x01, v));
    public static final IEIType CAUSE = register(0x02, "Cause", v -> new CauseIEI(0x02, v));
    public static final IEIType AUTH_TUPLE = register(0x03, "AuthTuple", v -> new AuthTupleIEI(0x03, v));
    public static final IEIType PDP_INFO_COMPLETE = register(0x04, "PDPInfoComplete", v -> new PdpInfoCompleteIEI(0x04, v));
    public static final IEIType PDP_INFO = register(0x05, "PDPInfo", v -> new PdpInfoIEI(0x05, v));
    public static final IEIType CANCELLATION_TYPE = register(0x06, "CancellationType", v -> new CancellationTypeIEI(0x06, v));
    public static final IEIType FREEZE_PTMSI = register(0x07, "FreezeP-TMSI", v -> new FreezePTMSIIEI(0x07, v));
    public static final IEIType MSISDN = register(0x08, "MSISDN", v -> new MsisdnIEI(0x08, v));
    public static final IEIType HLR_NUMBER = register(0x09, "HLRNumber", v -> new HlrNumberIEI(0x09, v));
    public static final IEIType MESSAGE_CLASS = register(0x0a, "MessageClass", v -> new MessageClassIEI(0x0a, v));
    public static final IEIType PDP_CONTEXT_ID = register(0x10, "PDPContextId", v -> new PdpContextIdIEI(0x10, v));
    public static final IEIType PDP_ADDRESS = register(0x11, "PDPAddress", v -> new PdpAddressIEI(0x11, v));
    public static final IEIType ACCESS_POINT_NAME = register(0x12, "AccessPointName", v -> new AccessPointNameIEI(0x12, v));
    public static final IEIType QOS = register(0x13, "QoS", v -> new QosIEI(0x13, v));
    public static final IEIType PDP_CHARGING_CHAR = register(0x14, "PDPChargingCharacteristics", v -> new PdpChargingCharacteristicsIEI(0x14, v));
    public static final IEIType PCO = register(0x15, "PCO", v -> new PcoIEI(0x15, v));
    public static final IEIType RAND = register(0x20, "RAND", v -> new RandIEI(0x20, v));
    public static final IEIType SRES = register(0x21, "SRES", v -> new SresIEI(0x21, v));
    public static final IEIType KC = register(0x22, "Kc", v -> new KcIEI(0x22, v));
    public static final IEIType IK = register(0x23, "IK", v -> new IkIEI(0x23, v));
    public static final IEIType CK = register(0x24, "CK", v -> new CkIEI(0x24, v));
    public static final IEIType AUTN = register(0x25, "AUTN", v -> new AutnIEI(0x25, v));
    public static final IEIType AUTS = register(0x26, "AUTS", v -> new AutsIEI(0x26, v));
    public static final IEIType RES = register(0x27, "RES", v -> new ResIEI(0x27, v));
    public static final IEIType CN_DOMAIN = register(0x28, "CNDomain", v -> new CnDomainIEI(0x28, v));
    public static final IEIType SESSION_ID = register(0x30, "SessionID", v -> new SessionIdIEI(0x30, v));
    public static final IEIType SESSION_STATE = register(0x31, "SessionState", v -> new SessionStateIEI(0x31, v));
    public static final IEIType SUPPLEMENTARY_SERVICE_INFO = register(0x35, "SupplementaryServiceInfo", v -> new SupplementaryServiceInfoIEI(0x35, v));
    public static final IEIType SM_RP_MR = register(0x40, "SM-RP-MR", v -> new SmRpMrIEI(0x40, v));
    public static final IEIType SM_RP_DA = register(0x41, "SM-RP-DA", v -> new SmRpDaIEI(0x41, v));
    public static final IEIType SM_RP_OA = register(0x42, "SM-RP-OA", v -> new SmRpOaIEI(0x42, v));
    public static final IEIType SM_RP_UI = register(0x43, "SM-RP-UI", v -> new SmRpUiIEI(0x43, v));
    public static final IEIType SM_RP_CAUSE = register(0x44, "SM-RP-Cause", v -> new SmRpCauseIEI(0x44, v));
    public static final IEIType SM_RP_MMS = register(0x45, "SM-RP-MMS", v -> new SmRpMmsIEI(0x45, v));
    public static final IEIType SM_ALERT_REASON = register(0x46, "SMAlertReason", v -> new SmAlertReasonIEI(0x46, v));
    public static final IEIType IMEI = register(0x50, "IMEI", v -> new ImeiIEI(0x50, v));
    public static final IEIType IMEI_CHECK_RESULT = register(0x51, "IMEICheckResult", v -> new ImeiCheckResultIEI(0x51, v));
    public static final IEIType SOURCE_NAME = register(0x60, "SourceName", v -> new SourceNameIEI(0x60, v));
    public static final IEIType DESTINATION_NAME = register(0x61, "DestinationName", v -> new DestinationNameIEI(0x61, v));
    public static final IEIType AN_APDU = register(0x62, "AN-APDU", v -> new AnApduIEI(0x62, v));
    public static final IEIType RR_CAUSE = register(0x63, "RRCause", v -> new RrCauseIEI(0x63, v));
    public static final IEIType BSSAP_CAUSE = register(0x64, "BSSAPCause", v -> new BssapCauseIEI(0x64, v));
    public static final IEIType SESSION_MANAGEMENT_CAUSE = register(0x65, "SessionManagementCause", v -> new SessionManagementCauseIEI(0x65, v));
    public static final IEIType CURRENT_RAT_TYPE = register(0x2a, "CurrentRATType", v -> new CurrentRatTypeIEI(0x2a, v));

    public final int code;
    public final String name;
    public final Function<byte[], IEI> factory;

    private IEIType(int code, String name, Function<byte[], IEI> factory) {
        this.code = code;
        this.name = name;
        this.factory = factory;
    }

    private static IEIType register(int code, String name, Function<byte[], IEI> factory) {
        IEIType type = new IEIType(code, name, factory);
        REGISTRY.put(code, type);
        return type;
    }

    public static IEIType fromCode(int code) {
        return REGISTRY.get(code & 0xFF);
    }

    public static IEI create(int code, byte[] value) {
        IEIType type = fromCode(code);
        if (type == null) {
            return new UnknownIEI(code, value);
        }
        return type.factory.apply(value);
    }

    public static int registeredCount() {
        return REGISTRY.size();
    }
}