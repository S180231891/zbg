package Sql_01.transform;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/in")
public class InputServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
        String token=UUID.randomUUID().toString();//随机参数
        req.getSession().setAttribute("TRANS_FROM",token);//缓存到session中
        req.setAttribute("token",token);//传给表单
        req.getRequestDispatcher("WEB-INF/views/jsp/transform.jsp").forward(req,resp);//页面跳转
    }
}
