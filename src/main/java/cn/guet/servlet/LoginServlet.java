package cn.guet.servlet;

import cn.guet.bean.User;
import cn.guet.dao.IUserDao;
import cn.guet.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LZDWTL
 * @date 2021-06-23 15:27
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        IUserDao iUserDao=new UserDaoImpl();
        User user=iUserDao.login(username,password);
        request.setAttribute("user",user);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
