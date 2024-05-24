package awa.training.api.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum StatusResponseCode {

    SUCCESS("200","SUCCESS"),
    DATA_NOT_FOUND("4101","Data not found."),
    INTERNAL_SERVER_ERROR("9999","Internal Server Error.");

    private final String code;
    private final String message;


    private static final Map<String, StatusResponseCode> BY_VALUE = new HashMap<>();

    static {
        for (StatusResponseCode statusResponseCode : values()) {
            BY_VALUE.put(statusResponseCode.getCode(), statusResponseCode);
        }
    }

}
