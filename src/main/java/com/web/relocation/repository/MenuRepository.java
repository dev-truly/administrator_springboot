package com.web.relocation.repository;

import com.web.relocation.common.FlagType;
import com.web.relocation.entity.MenuEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<MenuEntity, String> {
    List<MenuEntity> findAll(Specification<MenuEntity> specification);

    List<MenuEntity> findAll(Specification<MenuEntity> specification, Sort sort);

    List<MenuEntity> findAllByMenuCodeInOrderByParentNoAscSortAsc(List<String> menuCode);

    List<MenuEntity> findAllByDeletedOrderByParentNoAscSortAsc(FlagType flag);
    /*@Transactional
    @Query(
            value = "Select m.menu_no, m.menu_name, m.menu_code, m.menu_url From cr_menu m Where `deleted` = 'n'",
            nativeQuery = true
    )
    List<SelectMenuDto> findMenuAll();*/
}
