package Sql_01.crud;

import java.sql.ResultSet;

public interface UserHandler<T> {
    T handler(ResultSet rs)throws Exception;
}
