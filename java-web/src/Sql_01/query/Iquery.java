package Sql_01.query;

import Sql_01.crud.User;

import java.util.List;

public interface Iquery {

    //该接口是一种规范，用来实现getQuery和getParams

    //返回拼接好的sql语句
    String getQery();

    //返回传递的查询参数
    List<Object> getParams();
}
