package Sql_01.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplete {

    public JdbcTemplete(){}
    //除了查询之外的操作
    public static int update(String sql,Object...args){
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            for (int index=0;index<args.length;index++){
                ps.setObject(index+1,args[index]);
            }
            return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,null);
        }
        return 0;
    }

    //查询操作
    public static <T> T query(String sql,UserHandler<T> uh,Object...args){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn=JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            for (int index=0;index<args.length;index++){
                ps.setObject(index+1,args[index]);
            }
            rs=ps.executeQuery();
            return uh.handler(rs);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,ps,rs);
        }
        return null;
    }
}
