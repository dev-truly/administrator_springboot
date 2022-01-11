package com.web.relocation.service;

import com.web.relocation.dto.SelectMenuDto;
import org.json.simple.JSONObject;

import java.util.List;

public interface MenuService {
    List<SelectMenuDto> findMenuList(JSONObject obj);
}
