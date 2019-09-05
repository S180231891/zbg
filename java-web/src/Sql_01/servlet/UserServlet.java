package Sql_01.servlet;

import Sql_01.crud.IuserDao;
import Sql_01.crud.User;
import Sql_01.crud.UserDaoImpl;
import Sql_01.query.UserQuery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private IuserDao iuserDao;
    public void init()throws ServletException {
        iuserDao=new UserDaoImpl();
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException
    {
        //检查用户是否登录
        Object username=req.getSession().getAttribute("USER_SESSION");
        if (username==null){
            req.getRequestDispatcher("WEB-INF/views/jsp/login.jsp").forward(req,resp);
            return;
        }


        //设置编码格式
        req.setCharacterEncoding("utf-8");//post传值
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String cmd=req.getParameter("cmd");
        if ("save".equals(cmd)){
            this.save(req,resp);
        }else if ("delete".equals(cmd)){
            this.delete(req,resp);
        }else if ("edit".equals(cmd)){
            this.edit(req,resp);
        }else
            this.list(req,resp);
    }

    //保存（增加和修改按钮）
    protected void save(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
        //获取参数，封装对象
        User user=new User();
        this.request2Obj(req,user);
        //调用业务逻辑处理请求
        String id=req.getParameter("id");//该id处于隐藏状态
        if(hashLength(id)){
            user.setId(Integer.valueOf(id));
            iuserDao.update(user);
        }else {
            iuserDao.insert(user);
        }
        //控制页面跳转
        resp.sendRedirect("/user");//url重定向
    }


    private void request2Obj(HttpServletRequest req, User user) {
        String name=req.getParameter("name");
        String age=req.getParameter("age");
        String sex=req.getParameter("sex");
        user.setName(name);
        user.setAge(Integer.valueOf(age));
        user.setSex(sex);
    }

    //编辑
    protected void edit(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException  {
        //获取参数，封装对象
        String id=req.getParameter("id");
        if(hashLength(id)){
            User user=iuserDao.get(Integer.valueOf(id));
            req.setAttribute("u",user);
        }
        //控制页面跳转
        req.getRequestDispatcher("WEB-INF/views/jsp/edit.jsp").forward(req,resp);
    }

    //删除
    protected void delete(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException  {
        String id=req.getParameter("id");
        if (hashLength(id)){
            iuserDao.delete(Integer.valueOf(id));
        }
        resp.sendRedirect("/user");
    }

    //显示
    protected void list(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {

        //增加高级查询
            //获取参数，封装对象
        UserQuery userQuery=new UserQuery();
        this.query2Obj(req,userQuery);
            //调用业务逻辑处理请求
        req.setAttribute("query",userQuery);



        //获取参数，封装对象
        //List<User> list=iuserDao.list();//java_crud查询
        List<User> list=iuserDao.query(userQuery);//高级数据查询操作
        //调用业务逻辑处理请求
        req.setAttribute("u",list);
        //控制页面跳转
        req.getRequestDispatcher("WEB-INF/views/jsp/list.jsp").forward(req,resp);//请求转发
    }

    private void query2Obj(HttpServletRequest req, UserQuery userQuery) {
        String name=req.getParameter("name");
        String minAge=req.getParameter("minAge");
        String maxAge=req.getParameter("maxAge");
        if (hashLength(name)){
            userQuery.setName(name);
        }
        if (hashLength(minAge)){
            userQuery.setMinAge(Integer.valueOf(minAge));
        }
        if (hashLength(maxAge)){
            userQuery.setMaxAge(Integer.valueOf(maxAge));
        }
    }

    //判空的构造函数
    private boolean hashLength(String str){
        return str!=null&&!"".equals(str.trim());
    }

}
