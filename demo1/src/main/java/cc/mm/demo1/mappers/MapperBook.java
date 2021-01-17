package cc.mm.demo1.mappers;

import cc.mm.demo1.enity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapperBook extends BaseMapper<Book> {

}
