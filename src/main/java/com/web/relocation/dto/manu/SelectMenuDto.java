package com.web.relocation.dto.manu;


import com.web.relocation.common.FlagType;
import com.web.relocation.common.MenuType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Data
public class SelectMenuDto {
    @NotBlank
    private int menuNo;
    @NotBlank
    private String menuName;
    @NotBlank
    private String menuCode;
    private String menuUrl;
    private int parentNo;
    private int sort;
    private FlagType viewYn;
    private FlagType useYn;
    private MenuType linkType;
    private String width;
    private String height;
    private FlagType loginYn;
    private String menuButton;

    public SelectMenuDto(
            int menuNo,
            String menuName,
            String menuCode,
            String menuUrl,
            int parentNo,
            int sort,
            String viewYn,
            String useYn,
            String linkType,
            String width,
            String height,
            String loginYn,
            String menuButton
    ) {
        this.menuNo = menuNo;
        this.menuName = menuName;
        this.menuCode = menuCode;
        this.menuUrl = menuUrl;
        this.parentNo = parentNo;
        this.sort = sort;
        this.viewYn = FlagType.valueOf(viewYn);
        this.useYn = FlagType.valueOf(useYn);
        try {
            this.linkType = MenuType.valueOf(linkType);
        } catch (NullPointerException e) {
            this.linkType = null;
        }

        this.width = width;
        this.height = height;
        this.loginYn = FlagType.valueOf(loginYn);
        this.menuButton = menuButton;
    }
}
