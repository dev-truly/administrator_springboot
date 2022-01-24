package com.web.relocation.dto.user;

import com.web.relocation.common.FlagType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank
    private int userNo;

    private String divisionCode;

    private String menuCode;

    private String positionCode;

    @Pattern(regexp = "^[a-zA-Z0-9_-]{6,30}$")
    private String userId;

    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[*.!@$%^&(){}:<>,?/~_+-=|])[A-Za-z\\d*.!@$%^&(){}:<>,?/~_+-=|]{8,30}$")
    private String password;

    private String userName;

    private String userEnName;

    private String userEmail;

    private String userExtension;

    private String userColor;

    private String userPermitionIp;

    private FlagType userPermitionIpCheck;

    private String userLoginIp;

    private Date userLoginDate;

    private String userEditIp;

    private Date userEditDate;

    private FlagType delete;

    private Date regDate;

    private String userDoorayHook;
}
