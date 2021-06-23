package cn.guet.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZDWTL
 * @date 2021-06-23 15:53
 */
public class Role {
    private String id;
    private String name;
    private List<Permission> permissionList=new ArrayList<Permission>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
