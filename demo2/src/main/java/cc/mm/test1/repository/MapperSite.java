package cc.mm.test1.repository;


import cc.mm.test1.enity.Site;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface MapperSite {

    @Select("select * from site")
    ArrayList<Site> getAllSites();


    @Update("update site set title = #{title} where id = #{id}")
    void updateTitleById(@Param("id")String id, @Param("title")String title);

    @SelectKey(keyProperty = "site.id", resultType = String.class, before = true, statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "site.id", useGeneratedKeys = true)
    @Insert("insert into site (id,url,title) values (#{site.id},#{site.url},#{site.title})")
    void addSite(@Param("site")Site site);


    @Delete("delete from site where id = #{id}")
    void deleteSiteById(@Param("id")String id);


}
