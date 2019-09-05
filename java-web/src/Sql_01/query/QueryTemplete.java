package Sql_01.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryTemplete implements Iquery {

    //使用list封装查询参数
    List<Object> params=new ArrayList<>();
    //使用list封装查询条件，解决where 1==1问题（conditions）
    List<String> conditions=new ArrayList<>();


    public String getQery() {
        //拼接字符串
        StringBuilder sql=new StringBuilder(200);
        //专门暴露给子类，添加自身查询信息的方法
        this.custQuery();
        //判断第一个执行条件是否为where
        for (int i=0;i<conditions.size();i++){
            if (i==0){
                sql.append(" where ");
            }else
                sql.append(" and ");
            sql.append(conditions.get(i));
        }
        return sql.toString();
    }

    //专门暴露给子类，添加自身查询信息的方法（添加sql语句）
    protected void custQuery() {
    }

    //暴露给子类，让子类用于添加自身的查询条件和参数
    protected void addQuery(String condition,Object...param){
        //可控制优先级顺序
        this.conditions.add("("+condition+")");
        //将数组变为集合，可获取多个参数
        this.params.addAll(Arrays.asList(param));
    }
    @Override
    public List<Object> getParams() {
        return this.params;
    }

    //判空函数方法
    public boolean hashLength(String str){return str!=null&&!"".equals(str.trim());}
}
