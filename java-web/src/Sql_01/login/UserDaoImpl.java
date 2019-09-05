package Sql_01.login;

import Sql_01.crud.JdbcTemplete;
import Sql_01.crud.UserHandler;

import java.sql.ResultSet;

public class UserDaoImpl implements IuserDao {
    @Override
    public User getUsername(String username) {
        //查找数据库中登陆的信息
        String sql="select * from t_user where username=? ";//数据库的表名不能和包名相同
        return JdbcTemplete.query(sql, new UserHandler<User>() {
            public User handler(ResultSet rs) throws Exception {
                while (rs.next()){
                    User user=new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
                return null;
            }
        }, username);
    }
}
