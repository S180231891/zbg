package Sql_01.query;


public class UserQuery extends QueryTemplete {
    private String name;
    private Integer minAge;
    private Integer maxAge;

    private String keyWord;//关键字的查询，可要可不要

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    protected void custQuery() {
        if (hashLength(name)){
            addQuery("name like ? ","%"+name+"%");
        }
        if (minAge!=null){
            addQuery("age>=? ",minAge);
        }
        if (maxAge!=null){
            addQuery("age<=? ",maxAge);

        }
    }
}
