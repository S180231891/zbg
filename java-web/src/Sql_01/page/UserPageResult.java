package Sql_01.page;

import java.util.List;

//分页查询
public class UserPageResult {
    private List listData;//当前页的结果集
    private Integer totalCount;//结果总数
    private Integer currentPage=1;//当前页
    private Integer pageSize=10;//每页显示数据条数
    private Integer begain=1;//首页
    private Integer prevPage;//上页
    private Integer nextPage;//下页
    private Integer totalPage;//总页数/末页

    //构造一个函数，只包含前四项
    public UserPageResult(List listData,Integer totalCount,Integer currentPage,Integer pageSize){
        this.listData=listData;
        this.totalCount=totalCount;
        this.currentPage=currentPage;
        this.pageSize=pageSize;
        //计算总页数
        this.totalPage=this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
        //计算上一页
        this.prevPage=this.currentPage-1>1?this.currentPage-1:1;
        //计算下一页
        this.nextPage=this.currentPage+1<this.totalPage?this.currentPage+1:this.totalPage;
    }

    //只需要实现get

    public List getListData() {
        return listData;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getBegain() {
        return begain;
    }

    public Integer getPrevPage() {
        return prevPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }
}
