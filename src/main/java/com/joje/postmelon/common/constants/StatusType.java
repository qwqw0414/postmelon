package com.joje.postmelon.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusType {
    //    요청 성공
    SUCCESS("C2000", "정상적으로 처리 완료"),

    //    서버 에러 관련
    HTTP_REQUEST_FAILED("C3090", "HTTP 요청 실패"),

    SERVER_ERROR("C5000", "알 수 없는 서버 오류");

    private final String code;
    private final String message;
}
