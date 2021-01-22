package cc.mm.test2.repository;

import cc.mm.test2.enity.Site;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

// CrudRepository<T, ID> extends Repository<T, ID>
// PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID>

public interface MapperSite extends PagingAndSortingRepository<Site, String> {

    //简单的增删改就省略了，jpa的Repository 已经帮我们实现了
    //mapperSite.save()
    //mapperSite.findById()
    //mapperSite.delete();
    //mapperSite.findAll()

    // 简单的查询query也不需要自己写sql，jpa会解析方法名，,具体语法参考
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    List<Site> findByTitleAndUrl(String title, String url);


    // 可以自己写sql
    @Query(value = "select * from site  where title like %:title%", nativeQuery = true)
    List<Site> mySqlQueryLikeByTitle(@Param("title") String title);


    @Transactional()
    @Modifying
    @Query(value = "update site set title = :title where id = :id", nativeQuery = true)
    void mySqlUpdateTitleById(@Param("title") String title, @Param("id") String id);


    @Transactional()
    @Modifying
    @Query(value = "delete from Site where id = :id")
    void mySqlDeleteById(@Param("id") String id);

}
