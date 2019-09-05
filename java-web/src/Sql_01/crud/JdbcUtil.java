package Sql_01.crud;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

 public class JdbcUtil {

    //私有化构造器
    private JdbcUtil(){}
    private static Properties properties=new Properties();
    private static DataSource dataSource=null;

    static {
        try {
            InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("druid.properties");
            properties.load(in);
            dataSource=DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

     static Connection getConnection(){
        try{
            return dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

     static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            if(rs!=null)
                rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(ps!=null)
                    ps.close();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if(conn!=null)
                        conn.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
