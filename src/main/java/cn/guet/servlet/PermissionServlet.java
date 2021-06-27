package cn.guet.servlet;

import cn.guet.bean.Permission;
import cn.guet.dao.IUserDao;
import cn.guet.dao.impl.UserDaoImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author LZDWTL
 * @date 2021-06-27 18:18
 */
public class PermissionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String userid=request.getParameter("userid");
        IUserDao userDao=new UserDaoImpl();
        List<Permission> permissionList=userDao.getPermission(userid);

        Gson gson=new Gson();
        System.out.println(gson.toJson(permissionList));
        out.write(gson.toJson(permissionList));
        out.flush();
        out.close();
    }
}
