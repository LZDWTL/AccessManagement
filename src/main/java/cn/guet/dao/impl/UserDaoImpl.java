package cn.guet.dao.impl;

import cn.guet.bean.Permission;
import cn.guet.bean.Role;
import cn.guet.bean.User;
import cn.guet.dao.IUserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LZDWTL
 * @date 2021-06-23 15:35
 */
public class UserDaoImpl implements IUserDao {

    @Override
    public User login(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=gbk&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        /*
        1、先确定用户名和密码是否正确
        2、如果正确，根据用户id查询到该用户对应的角色信息
        3、如果正确，多表查询一次性把用户的权限全部拿到
         */
        User user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "123456");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                /*
                登录成功
                 */
                user = new User();
                String userId = rs.getString("user_id");
                user.setId(userId);
                user.setUsername(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List<Permission> getPermission(String userid) {
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=gbk&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT p.* FROM role_menu rm,user_role ur,permissions p WHERE rm.id=p.id AND ur.role_id=rm.role_id AND ur.user_id=?";
        List<Permission> permissionList = new ArrayList<Permission>();
        /*
        1、先确定用户名和密码是否正确
        2、如果正确，根据用户id查询到该用户对应的角色信息
        3、如果正确，多表查询一次性把用户的权限全部拿到
         */
        User user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "123456");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Permission permission = new Permission();
                permission.setId(rs.getString("ID"));
                permission.setPid(rs.getString("PID"));
                permission.setName(rs.getString("NAME"));
                permission.setUrl(rs.getString("URL"));
                permission.setIsParent(rs.getString("ISPARENT"));
                permissionList.add(permission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return permissionList;
    }
}
