package com.web.relocation.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSearchDto {
    private String divisionCode;

    private String positionCode;

    private String userId;

    private String userName;

    private String userEmail;
}
