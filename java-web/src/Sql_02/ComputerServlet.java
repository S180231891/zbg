package Sql_02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cu")
public class ComputerServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        //获取参数，封装对象
        String numb1=req.getParameter("numb1");
        String fuhao=req.getParameter("fuhao");
        String numb2=req.getParameter("numb2");
        System.out.println(numb1);
        System.out.println(fuhao);
        System.out.println(numb2);

        String result="";

        if (hashLength(numb1)&&hashLength(numb2)){
            Integer num1=Integer.valueOf(numb1);
            Integer num2=Integer.valueOf(numb2);
            if ("+".equals(fuhao)){
                result=num1+num2+"";
            }else if ("-".equals(fuhao)){
                result=num1-num2+"";
            }else if ("*".equals(fuhao)){
                result=num1*num2+"";
            }else
                result=num1/num2+"";
        }


        //创建计算机计算表单
        out.print("<form action='/cu' method='post'>");
        out.print("<input type='number' name='numb1' value='"+numb1+"' />");
        out.print("<select name='fuhao'>");
        out.print("<option >+</option>");
        out.print("<option >-</option>");
        out.print("<option >*</option>");
        out.print("<option >/</option>");
        out.print("</select>");
        out.print("<input type='number' name='numb2' value='"+numb2+"'/>");
        out.print("<input type='submit' value='='/>");
        out.print("<input type='text' value='"+result+"' disabled/>");
        out.print("</form>");

    }

    public boolean hashLength(String str) {return str!=null&&!"".equals(str.trim());}
}
