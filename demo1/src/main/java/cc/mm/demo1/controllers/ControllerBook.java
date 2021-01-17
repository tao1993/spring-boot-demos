package cc.mm.demo1.controllers;

import cc.mm.demo1.enity.Book;
import cc.mm.demo1.mappers.MapperBook;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ControllerBook {

    @Autowired
    private MapperBook mapperBook;

    //获取所有
    @RequestMapping("/listAllBook")
    @ResponseBody
    Object listAllBook(){
        return mapperBook.selectList(null);
    }

    //循环添加测试数据
    @RequestMapping("/addTestData")
    @ResponseBody
    Object addTestData(){
        for(int i=0;i<100;i++) {
            Book b = new Book();
            b.setName("test");
            b.setPrice(i*5);
            mapperBook.insert(b);
        }
        return "ok";
    }

}
