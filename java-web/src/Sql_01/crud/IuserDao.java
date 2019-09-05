package Sql_01.crud;

import Sql_01.query.UserQuery;

import java.util.List;

public interface IuserDao {

    void insert(User user);

    void delete(Integer id);

    void update(User user);

    User get(Integer id);

    List<User> list();

    //高级查询
    List<User> query(UserQuery userQuery);
}
