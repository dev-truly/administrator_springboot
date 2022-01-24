package com.web.relocation.entity;

import com.web.relocation.common.FlagType;
import com.web.relocation.common.MenuType;
import com.web.relocation.dto.manu.SelectMenuDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "cr_menu")
@Data
@SqlResultSetMapping(
        name = "SelectMenuListMapping",
        classes = @ConstructorResult(
                targetClass = SelectMenuDto.class,
                columns = {
                        @ColumnResult(name="menu_no"),
                        @ColumnResult(name="menu_name", type = String.class),
                        @ColumnResult(name="menu_code", type = String.class),
                        @ColumnResult(name="menu_url", type = String.class),
                        @ColumnResult(name="parent_no"),
                        @ColumnResult(name="sort"),
                        @ColumnResult(name="view_yn", type = String.class),
                        @ColumnResult(name="use_yn", type = String.class),
                        @ColumnResult(name="link_type", type = String.class),
                        @ColumnResult(name="width", type = String.class),
                        @ColumnResult(name="height", type = String.class),
                        @ColumnResult(name="login_yn", type = String.class),
                        @ColumnResult(name="menu_button", type = String.class),
                })
)
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuNo;

    @Column(name = "base_menu_code", nullable = false, columnDefinition = "varchar(10) default '' comment '메뉴 관리 코드'")
    private String menuCode;

    @Column(nullable=false, columnDefinition="varchar(10) default '' comment '권한 메뉴 코드'")
    private String authMenuCode;

    @Column(nullable = false, columnDefinition = "varchar(100) default '' comment '메뉴명'")
    private String menuName;

    @Column(columnDefinition = "varchar(255) default '' comment '메뉴 설명'")
    private String menuDescription;

    @Column(columnDefinition = "varchar(255) default '' comment '메뉴 경로'")
    private String menuUrl;

    @Column(nullable = false, columnDefinition = "int(10) default 0 comment '상위 메뉴 일련번호'")
    private int parentNo;

    @Column(nullable = false, columnDefinition = "int(10) default 0 comment '정렬 순서'")
    private int sort;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('y','n') default 'y' comment '노출 여부'")
    private FlagType viewYn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('y','n') default 'y' comment '사용 여부'")
    private FlagType useYn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(8) default 'CMLT0001' comment '메뉴 구분'")
    private MenuType linkType;

    @Column(columnDefinition = "varchar(10) comment '가로 사이즈'")
    private String width;

    @Column(columnDefinition = "varchar(10) comment '세로 사이즈'")
    private String height;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('y','n') default 'y' comment '로그인 여부'")
    private FlagType loginYn;

    @Column(nullable = false, columnDefinition = "varchar(15) default 'N,N,N,N,N,N,N' comment '기본 기능 버튼 활성화'")
    private String menuButton;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('y','n') default 'n' comment '삭제 여부'")
    @ColumnDefault("'n'")
    private FlagType deleted;

    @Column(columnDefinition = "datetime comment '수정, 삭제 일자'")
    private Date editDate = new Date();

    @Column(columnDefinition = "datetime comment '등록일'")
    private Date regDate = new Date();
}
