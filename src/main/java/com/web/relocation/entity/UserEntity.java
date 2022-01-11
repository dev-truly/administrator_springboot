package com.web.relocation.entity;

import com.web.relocation.common.FlagType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "cr_user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int userNo;

    @Column(nullable = false, columnDefinition = "varchar(10) default '' comment '부서코드'")
    private String divisionCode;

    @Column(columnDefinition = "json comment '메뉴 권한'")
    private String menuCode;

    @Column(nullable = false, columnDefinition = "varchar(10) default '' comment '직급코드'")
    private String positionCode;

    @Column(nullable = false, columnDefinition = "varchar(30) default '' comment '아이디'")
    private String userId;

    @Column(nullable = false, columnDefinition = "varchar(255) default '' comment '비밀번호'")
    private String password;

    @Column(nullable = false, columnDefinition = "varchar(30) default '' comment '이름'")
    private String userName;

    @Column(columnDefinition = "varchar(30) default '' comment '영문 이름'")
    private String userEnName;

    @Column(columnDefinition = "varchar(50) default '' comment '이메일 앞 주소'")
    private String userEmail;

    @Column(columnDefinition = "varchar(4) default '' comment '내선번호 뒤 4자리'")
    private String userExtension;

    @Column(columnDefinition = "varchar(8) default '' comment '담당자 적용 색상'")
    private String userColor;

    @Column(columnDefinition = "varchar(15) default '' comment '접근허용IP'")
    private String userPermitionIp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('y','n') default 'n' comment '접근허용IP'")
    private FlagType userPermitionIpCheck;

    @Column(columnDefinition = "varchar(15) default '' comment '로그인IP'")
    private String userLoginIp;

    @Column(columnDefinition = "datetime comment '로그인 일자'")
    private Date userLoginDate = new Date();

    @Column(columnDefinition = "varchar(15) default '' comment '등록, 수정, 삭제 IP'")
    private String userEditIp;

    @Column(columnDefinition = "datetime comment '수정, 삭제 일자'")
    private Date userEditDate = new Date();

    @Enumerated(EnumType.STRING)
    @Column(name = "`delete`", nullable = false, columnDefinition = "enum('y','n') default 'n' comment '삭제 여부'")
    private FlagType delete;

    @Column(nullable = false, columnDefinition = "datetime comment '등록 일자'")
    private Date regDate = new Date();

    @Column(columnDefinition = "datetime comment '등록 일자'")
    private String userDoorayHook;

    @Builder
    public UserEntity(int userNo, String userId, String password) {
        this.userNo = userNo;
        this.userId = userId;
        this.password = password;
    }
}
