package com.KunJinKao.controller;

import com.KunJinKao.domain.entity.Menu;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.service.MenuService;
import com.KunJinKao.utils.BeanCopyUtils;
import com.KunJinKao.domain.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 35238
 * @date 2023/8/10 0010 10:54
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //---------------------------------查询菜单列表--------------------------------------

    @GetMapping("/list")
    public ResponseResult list(Menu menu) {
        List<Menu> menus = menuService.selectMenuList(menu);
        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus, MenuVo.class);
        return ResponseResult.okResult(menuVos);
    }

    //-----------------------------------新增菜单---------------------------------------
    @PostMapping
    public ResponseResult add(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    //-----------------------------------修改菜单---------------------------------------

    @GetMapping(value = "/{menuId}")
    //①先查询根据菜单id查询对应的权限菜单
    public ResponseResult getInfo(@PathVariable Long menuId) {
        return ResponseResult.okResult(menuService.getById(menuId));
    }

    @PutMapping
    //②然后才是更新菜单
    public ResponseResult edit(@RequestBody Menu menu) {
        if (menu.getId().equals(menu.getParentId())) {
            return ResponseResult.errorResult(500,"修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }


    //-----------------------------------删除菜单---------------------------------------

    @DeleteMapping("/{menuId}")
    public ResponseResult remove(@PathVariable("menuId") Long menuId) {
        if (menuService.hasChild(menuId)) {
            return ResponseResult.errorResult(500,"存在子菜单不允许删除");
        }
        menuService.removeById(menuId);
        return ResponseResult.okResult();
    }
}