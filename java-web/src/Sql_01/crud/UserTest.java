package Sql_01.crud;

import Sql_01.query.UserQuery;
import org.junit.Test;

import java.util.List;

public class UserTest {
    private IuserDao userDao=new UserDaoImpl();
    @Test
     public void InsertTest(){
        User user=new User();
            user.setName("刘倩");
            user.setAge(30);
            user.setSex("男");
            userDao.insert(user);
        System.out.println(user);
    }

    @Test
    public void DeleteTest(){
        userDao.delete(41);
    }

    @Test
    public void update(){
        User user=userDao.get(40);
        user.setName("朱晓明");
        userDao.update(user);
    }

    @Test
    public void getTest(){
        User user=userDao.get(40);
        System.out.println(user);
    }

    @Test
    public void  ListTest(){
        List<User> list=userDao.list();
        for(User user:list){
            System.out.println(user);
        }
    }

    //高级查询
    @Test
    public void UserQueryTest()throws Exception{
        UserQuery userQuery=new UserQuery();
        userQuery.setName("刘");
        userQuery.setMinAge(15);
        userQuery.setMaxAge(20);
        List<User> list=userDao.query(userQuery);
        System.out.println("count="+list.size());
        for (User l:list){
            System.out.println(l);
        }
    }
}
