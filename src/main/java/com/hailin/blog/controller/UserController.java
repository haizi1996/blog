package com.hailin.blog.controller;


import com.github.pagehelper.PageInfo;
import com.hailin.blog.dto.Response;
import com.hailin.blog.enumPackage.RoleEnum;
import com.hailin.blog.model.Authority;
import com.hailin.blog.model.Role;
import com.hailin.blog.model.RoleUser;
import com.hailin.blog.model.User;
import com.hailin.blog.service.AuthorityService;
import com.hailin.blog.service.RoleUserService;
import com.hailin.blog.service.UserService;
import com.hailin.blog.trie.WordTree;
import com.hailin.blog.utils.ConstraintViolationExceptionHandler;
import com.hailin.blog.utils.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/users")
public class UserController {


    @Resource
    private UserService userService;

    @Resource
    private RoleUserService roleUserService;


    @RequestMapping("/list")
    @ResponseBody
    public ModelAndView listUsers(@RequestParam(value = "async", required = false , defaultValue = "false") boolean async ,
                            @RequestParam(value = "pageIndex" , required = false , defaultValue = "0") Integer pageIndex,
                            @RequestParam(value = "pageSize" ,required = false , defaultValue = "3") Integer pageSize ,
                            @RequestParam(value = "name" , required = false , defaultValue = "") String name ,
                            @RequestParam(value = "status" , required = false ,defaultValue = "1") int status,
                            Model model
    ){
        PageInfo<User> pageInfo = userService.listUserAndRolesByNameLike(name , pageIndex ,pageSize , status);
        Response response = pageInfo != null ? Response.successResponse(pageInfo) : Response.errorResponse("");
        model.addAttribute("listUserResponse" , response);
        model.addAttribute("page" , pageInfo);
        ModelAndView modelAndView = new ModelAndView(async ?"users/list :: #mainContainer" :"users/list","userModel", model);
        return modelAndView;
    }



    @DeleteMapping("/{id}/{username}")
    @ResponseBody
    public Response deleteUser(@PathVariable("id") Integer id , @PathVariable("username") String username ){
        Integer rows = userService.removeUser(id , username);
        if(rows > 0 ){
            WordTree.getWordTree().deleteNode(username);
        }
        return rows > 0 ? Response.successResponse("删除用户成功"): Response.errorResponse("删除用户失败");
    }

    /**
     * 获取修改用户的界面
     *
     * @param id
     * @param model
     * @return
     */

    @GetMapping("/edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Integer id , Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user" , user);
        return new ModelAndView("users/edit", "userModel", model);
    }



    @GetMapping("/add")
    public ModelAndView addUserHtml(Model model){
        model.addAttribute("user", new User(null, null, null, null));
        return new ModelAndView("users/add", "userModel", model);
    }

    @PostMapping()
    public ResponseEntity<Response> saveOrUpdateUser(User user , @RequestParam(value = "roleId" , required = true) Integer roleId , HttpServletRequest request){
        Response response = null;
        user.setOperator(SecurityUtil.getSecurityUser().getUsername());
        user.setOperateIp(request.getLocalAddr());
        try {
            if(user.getId() == null){
                if (WordTree.getWordTree().existPrimaryKeyWords(user.getUsername())) {
                    response = Response.errorResponse("该用户名以被其他用户使用");
                }else{
                    Optional<User> newUser = userService.saveUser(user , RoleEnum.parse(roleId));
                    if(newUser.isPresent()){
                        WordTree.getWordTree().addNode(newUser.get().getUsername() ,newUser.get().getId());
                        response = Response.successResponse("添加用户成功");
                    }else{
                        response = Response.errorResponse("注册失败，请正确填写信息");
                    }
                }
            }else{
                userService.updateUser(user);
                User user1 = userService.getUserById(user.getId());
                RoleUser roleUser = RoleUser.createRoleUser(user.getId() , RoleEnum.parse(roleId)).setOperateIp(request.getLocalAddr());
                roleUserService.updateUserRole(user1.getRoles().get(0).getId(),roleUser);
                response = Response.successResponse("修改用户成功");
            }
        }  catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body( Response.errorResponse( ConstraintViolationExceptionHandler.getMessage(e)));
        }

        return ResponseEntity.ok().body(response);
    }

}
