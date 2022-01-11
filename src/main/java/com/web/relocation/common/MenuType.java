package com.web.relocation.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만들어줌
//@RequiredArgsConstructor // final @NonNull인 필드값만 파라미터로 받는 생성자를 만들어줌
public enum MenuType {
    SELF("현재페이지"),
    BLANK("새창"),
    LAYER("레이어팝업"),
    POPUP("팝업"),
    DOWNLOAD("다운로드"),
    TAB("관리자새탭"),
    NULL("없음");

    private String value;

}
