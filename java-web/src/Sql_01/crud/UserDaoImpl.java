package Sql_01.crud;

import Sql_01.query.UserQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IuserDao {
    @Override
    public void insert(User user) {
        //贾琏欲执事
        String sql="insert into user(name,age,sex) values(?,?,?)";
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql:///java?characterEncoding=UTF-8","root","root");
            ps=conn.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setInt(2,user.getAge());
            ps.setString(3,user.getSex());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null)
                        conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void delete(Integer id) {
        String sql="delete from user where id=?";
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,null);
        }
    }

    @Override
    public void update(User user) {
        String sql="update user set name=?,age=?,sex=? where id=? ";
        Object[] params={user.getName(),user.getAge(),user.getSex(),user.getId()};
         JdbcTemplete.update(sql,params);
    }

    @Override
    public User get(Integer id) {
        String sql="select * from user where id=?";
        //get的写法
        List<User> list=JdbcTemplete.query(sql,new UserResultHandler(),id);
        return list.size()==1?list.get(0):null;
    }


    public List<User> list() {
        String sql="select * from user";
        return JdbcTemplete.query(sql,new UserResultHandler());
    }

    @Override
    public List<User> query(UserQuery userQuery) {
        String sql="select * from user "+userQuery.getQery();//输出带有参数的sql语句
        System.out.println(sql);
        System.out.println(userQuery.getParams());//输出参数
        return JdbcTemplete.query(sql,new UserResultHandler(),userQuery.getParams().toArray());
    }

    private class UserResultHandler implements UserHandler<List<User>>{
        public List<User> handler(ResultSet rs) throws Exception
        {
            List<User> list=new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                list.add(user);
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getString("sex"));
            }
            return list;
        }
    }
}
