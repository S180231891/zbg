package Sql_01.transform;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/out")
public class OutputServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();//在控制台中显示数据

        String token=req.getParameter("token");
        String money=req.getParameter("money");
        String session= (String) req.getSession().getAttribute("TRANS_FROM");

        if (token.equals(session)){
            req.getSession().removeAttribute("TRANS_FROM");
            System.out.println("转出："+money);
            out.print("转账成功！！");
        }else {
            System.out.println("不要重复提交");
            out.print("不要重复提交表单哦");
        }
    }
}
