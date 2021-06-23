package cn.guet.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZDWTL
 * @date 2021-06-23 15:29
 */
public class User {
    private String id;
    private String username;
    private String password;
    private List<Role> roleList=new ArrayList<Role>();

    public User() {

    }

    public User(String id, String username, String password, List<Role> roleList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
