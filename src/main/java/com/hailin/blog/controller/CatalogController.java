package com.hailin.blog.controller;

import com.hailin.blog.constant.CatalogConstant;
import com.hailin.blog.dto.CatalogVO;
import com.hailin.blog.dto.Response;
import com.hailin.blog.model.Catalog;
import com.hailin.blog.model.User;
import com.hailin.blog.service.CatalogService;
import com.hailin.blog.service.UserService;
import com.hailin.blog.utils.ConstraintViolationExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping("/catalogs")
public class CatalogController {


    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UserService userService;

    @Resource
    private CatalogService catalogService ;
    /**
     * 获取分类列表
     * @param username
     * @param model
     * @return
     */
    @GetMapping
    public String listComments(@RequestParam(value="username",required=true) String username, Model model) {
        List<Catalog> catalogs = catalogService.getCatalogByUsername(username , CatalogConstant.Status.NORMAL);

        // 判断操作用户是否是分类的所有者
        boolean isOwner = false;

        if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal !=null && username.equals(principal.getUsername())) {
                isOwner = true;
            }
        }

        model.addAttribute("isCatalogsOwner", isOwner);
        model.addAttribute("catalogs", catalogs);
        return "/userspace/u :: #catalogRepleace";
    }


    @PostMapping
    // 当前用户才能操作方法
    @PreAuthorize("authentication.name.equals(#catalogVO.username)")
    public ResponseEntity<Response> create(@RequestBody CatalogVO catalogVO){
        String userName = catalogVO.getUsername();
        Catalog catalog = catalogVO.getCatalog();
        User user = (User) userService.loadUserByUsername(userName);

        try{
            catalog.setUser(user);
            catalogService.saveCatalog(catalog);
        }catch (ConstraintViolationException e){
            logger.error(e.getMessage() , e);
            return ResponseEntity.ok().body(Response.errorResponse(ConstraintViolationExceptionHandler.getMessage(e)));
        }catch (Exception e){
            logger.error(e.getMessage() , e);
            return ResponseEntity.ok().body(Response.errorResponse(e.getMessage()));
        }
        return ResponseEntity.ok().body(Response.successResponse("创建分类成功!"));

    }


    /**
     * 删除分类
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("authentication.name.equals(#username)")  // 指定用户才能操作方法
    public ResponseEntity<Response> delete(String username, @PathVariable("id") Integer id) {
        try {
            catalogService.removeCatalog(id);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(Response.errorResponse(ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(Response.errorResponse( e.getMessage()));
        }
        return ResponseEntity.ok().body(Response.successResponse("处理成功"));
    }

    /**
     * 获取分类编辑界面
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String getCatalogEdit(Model model) {
        Catalog catalog = new Catalog(null, null);
        model.addAttribute("catalog",catalog);
        return "/userspace/catalogedit";
    }

}
