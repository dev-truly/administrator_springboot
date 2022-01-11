package com.web.relocation.service;

import com.web.relocation.common.FlagType;
import com.web.relocation.common.GenericSpecification;
import com.web.relocation.dto.SelectMenuDto;
import com.web.relocation.entity.MenuEntity;
import com.web.relocation.repository.MenuRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("menuService")
public class MenuServiceImpl implements com.web.relocation.service.MenuService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    @Qualifier("menuRepository")
    MenuRepository menuRepository;

    @PersistenceContext
    EntityManager em;

    private List<MenuEntity> menu;

    @Override
    public List<SelectMenuDto> findMenuList(JSONObject obj) {
        List<String> authMenuCode = new ArrayList<>();
        List<MenuEntity> menuData = null;

        GenericSpecification<MenuEntity> menuSpecs = new GenericSpecification<>();

        Specification<MenuEntity> spec = Specification.where(
                menuSpecs.equal("deleted", FlagType.n)
                        .and(
                                menuSpecs.equal("viewYn", FlagType.y)
                        )
        );

        if (!(boolean)obj.get("admin")) {
            JSONArray menuList = (JSONArray)obj.get("menu");
            for (Object o : menuList) {
                authMenuCode.add((String) ((JSONObject)o).get("code"));
            }
            spec = spec.and(menuSpecs.inData("authMenuCode", authMenuCode));
        }

        Sort sort = Sort.by("parentNo").ascending()
                        .and(
                                Sort.by("sort").ascending()
                        );

        menuData = menuRepository.findAll(spec, sort);


        List<SelectMenuDto> menu = menuData.stream().map(p -> modelMapper.map(p, SelectMenuDto.class)).collect(Collectors.toList());

        /*
        String q = String.format("Select %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s From cr_menu Where `deleted` = 'n' %s Order By parent_no, sort",
                "menu_no", "menu_name", "menu_code", "menu_url",
                "parent_no", "sort", "view_yn", "use_yn", "link_type",
                "width", "height", "login_yn", "menu_button", where);
        List<SelectMenuDto> menu = em.createNativeQuery(q, "SelectMenuListMapping").getResultList();
        */
         //= query.getResultList();
        return menu;
    }
}
