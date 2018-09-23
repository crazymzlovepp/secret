package com.secret.mapper;

import com.secret.pojo.MenuQueryVo;
import com.secret.pojo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MenuVoMapper {
    int insert(MenuVo menuVo);

    List<MenuVo> selectAll();
    //查询符合条件的菜单总数
    int selectCountMenuListByParam(Map<String, Object> returnMap);
    //查询符合条件的菜单数据
    List<MenuVo> selectMenuListByParam(Map<String, Object> returnMap);
    //根据条件查询对应菜单
    List<MenuQueryVo> selectAllMenuByParam(Object o);
    //查询所有的父级菜单
    List<MenuVo> selectAllParentMenu();
    //根据菜单id删除菜单
    void deleteMenu(@Param("ids") String[] ids);
    //根据菜单id更新菜单数据
    void updateMenuVo(MenuVo menuVo);
}