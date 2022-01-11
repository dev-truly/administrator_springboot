package com.web.relocation.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor // 모든 필드값을 파라미터로 하는 생성자 생성
@Getter // getter 생성
public enum Role {
    ADMIN("ROLE_ADMIN"),  // admin계정 권한
    USER("ROLE_USER");// 일반 유저 권한

    private String value;
}
