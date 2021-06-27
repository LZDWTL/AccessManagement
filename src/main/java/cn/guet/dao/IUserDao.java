package cn.guet.dao;

import cn.guet.bean.Permission;
import cn.guet.bean.User;

import java.util.List;

/**
 * @author LZDWTL
 * @date 2021-06-23 15:31
 */
public interface IUserDao {
    /**
     * 用户包含角色，角色包含权限
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);
    List<Permission> getPermission(String userid);
}
