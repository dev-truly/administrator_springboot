package com.web.relocation.service;

import com.web.relocation.common.FlagType;
import com.web.relocation.entity.MenuEntity;
import com.web.relocation.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("menuTestService")
public class MenuTestService {
    @Autowired
    MenuRepository menuRepository;

    public List<MenuEntity> menuListTest() {
        List<MenuEntity> menuList = new ArrayList<>();
        menuList = menuRepository.findAllByDeletedOrderByParentNoAscSortAsc(FlagType.n);
        return menuList;
    }
}
