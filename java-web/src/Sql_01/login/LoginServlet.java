package Sql_01.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private IuserDao iuserDao;

    public void init()throws ServletException {
        iuserDao=new UserDaoImpl();
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
        //接受请求参数，封装对象
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String username=req.getParameter("username");
        String password=req.getParameter("password");

        //调用业务方法处理请求
        User user=iuserDao.getUsername(username);
        if (user==null) {
            req.setAttribute("errorMsg","请输入正确的用户");
            req.getRequestDispatcher("WEB-INF/views/jsp/login.jsp").forward(req,resp);
            return;
        }
        if (!user.getPassword().equals(password)){
            req.setAttribute("errorMsg","账户和密码错误，请输入正确的账户和密码");
            req.getRequestDispatcher("WEB-INF/views/jsp/login.jsp").forward(req,resp);
            return;
        }

        //将数据存储在session中
        req.getSession().setAttribute("USER_SESSION",user);

        //控制页面跳转
        resp.sendRedirect("/user");
    }

}
