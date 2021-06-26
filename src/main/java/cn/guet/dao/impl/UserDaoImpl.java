package cn.guet.dao.impl;

import cn.guet.bean.Permission;
import cn.guet.bean.Role;
import cn.guet.bean.User;
import cn.guet.dao.IUserDao;

import java.sql.*;

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
        User user=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "123456");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            if(rs.next()){
                /*
                登录成功
                 */
                user=new User();
                String userId=rs.getString("user_id");
                user.setId(userId);
                user.setUsername(rs.getString("username"));

                sql="SELECT r.* FROM user_role ur, roles r WHERE ur.role_id=r.role_id AND ur.user_id=?";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,userId);
                rs=pstmt.executeQuery();
                while(rs.next()){
                    Role role=new Role();
                    role.setId(rs.getString("ROLE_ID"));
                    role.setName(rs.getString("ROLE_NAME"));
                    user.getRoleList().add(role);  //用户关联角色
                }
                sql="SELECT p.* FROM role_menu rm,permissions p WHERE rm.id=p.id AND rm.role_id=?";

                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,user.getRoleList().get(0).getId());
                rs=pstmt.executeQuery();
                while(rs.next()){
                    Permission permission=new Permission();
                    permission.setId(rs.getString("ID"));
                    permission.setPid(rs.getString("PID"));
                    permission.setName(rs.getString("NAME"));
                    permission.setIsParent(rs.getString("ISPARENT"));
                    user.getRoleList().get(0).getPermissionList().add(permission);    //角色关联权限
                    System.out.println(rs.getString("NAME"));
                }
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
}
