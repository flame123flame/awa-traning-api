package awa.training.api.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    VALIDATION_ERROR("E0000", "Validate Error", "ตรวจสอบความถูกต้องเกิดข้อผิดพลาด",
            "The server cannot or will not process the request due to an apparent client error."),
    DUPLICATE_DATA("E0001", "Duplicate Data", "ข้อมูลซ้ำ", "ข้อมูลซ้ำ."),
    NO_DATA("E0002", "No Data", "ไม่พบข้อมูล", "ไม่พบข้อมูล."),
    NO_USER_FOUND("E0003", "User Not Found", "ไม่พบบัญชีผู้ใช้งาน", "ไม่พบบัญชีผู้ใช้งาน."),
    ACCOUNT_DISABLE("E0004", "Account has been disabled.", "บัญชี้นี้ถูกระงับการใช้งาน โปรดติดต่อเจ้าหน้าที่",
            "บัญชี้นี้ถูกระงับการใช้งาน โปรดติดต่อเจ้าหน้าที่."),
    INVALID_CREDENTIALS("E0005", "Invalid Credentials", "ผู้ใช้งานหรือรหัสผ่านไม่ถูกต้อง",
            "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง."),
    ROLE_DISABLE("E0006", "Role has been disabled.", "สิทธิการใช้งานถูกระงับ โปรดติดต่อเจ้าหน้าที่",
            "สิทธิการใช้งานถูกระงับ โปรดติดต่อเจ้าหน้าที่."),
    UNEXPECTED("E9999", "Unexpected", "ข้อผิดพลาดทางระบบ", null),
    DUPLICATE_USER("E0007", "Duplicate User", "มีบัญชีผู้ใช้งานในระบบแล้ว", "มีบัญชีผู้ใช้งานในระบบแล้ว."),
    DUPLICATE_ROLE("E0008", "Duplicate Role Code", "มี Code สิทธิการใช้งานนี้ในระบบแล้ว", "มี Code สิทธิการใช้งานนี้ในระบบแล้ว."),
    DATA_NOT_FOUND_IN_ID("E0009", "Data Not Found In Id", "ไม่พบข้อมูลในไอดีนี้", "ไม่พบข้อมูลในไอดีนี้"),
    DUPLICATE_KEY("E0010", "Duplicate Key", "ข้อมูลคีย์ซ้ำ", "ข้อมูลคีย์ซ้ำ"),
    ROLE_IS_ACTIVE_CAN_NOT_DELETE("E0011", "Permissions are enabled and cannot be deleted.", "สิทธิมีการเปิดใช้งานอยู่ไม่สามารถลบได้", "สิทธิมีการเปิดใช้งานอยู่ไม่สามารถลบได้");
     private static final Map<String, ErrorCode> BY_VALUE = new HashMap<>();

    static {
        for (ErrorCode errorCode : values()) {
            BY_VALUE.put(errorCode.getStatusCode(), errorCode);
        }
    }

    private final String statusCode;
    private final String messageEn;
    private final String messageTh;
    private final String detail;
}
