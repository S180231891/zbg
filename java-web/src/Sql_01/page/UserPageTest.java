package Sql_01.page;

import Sql_01.crud.JdbcTemplete;
import Sql_01.crud.User;
import Sql_01.crud.UserHandler;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserPageTest {

    @Test
    public void pageTest()throws  Exception
    {
        //设置数据
        Integer currentPage=1;
        Integer pageSize=5;

        //查询结果集
        String basesql="select * from user limit ?,?";
        List<User> list=JdbcTemplete.query(basesql,new UserResultHandler(),(currentPage-1)*pageSize,pageSize);
        //查询结果总数
        String countsql="select count(id) from user";
        Integer totalCount=JdbcTemplete.query(countsql, new UserHandler<Long>() {
            @Override
            public Long handler(ResultSet rs) throws Exception {
                while (rs.next()){
                    return rs.getLong(1);
                }
                return 0L;
            }
        }).intValue();

        System.out.println(totalCount);
        for(User user:list){
            System.out.println(user);
        }
    }

    private class UserResultHandler implements UserHandler<List<User>> {
        @Override
        public List<User> handler(ResultSet rs) throws Exception {
            List<User> list=new ArrayList<>();
            while (rs.next()){
                User user=new User();
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
