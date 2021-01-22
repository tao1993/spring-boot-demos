package cc.mm.test2.controller;

import cc.mm.test2.enity.Site;
import cc.mm.test2.repository.MapperSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    MapperSite mapperSite;

    // http://localhost:8080/addSite
    @ResponseBody
    @RequestMapping("/addSite")
    Object addSite(){
        for(int i=0;i<3;i++) {
            Site s = new Site();
            s.setTitle("测试，标题1");
            s.setUrl("www.abc.com");
            mapperSite.save(s);
        }
        return mapperSite.findAll();
    }

    // http://localhost:8080/getAllSites
    @ResponseBody
    @RequestMapping("/getAllSites")
    Object getAllSites(){
        return mapperSite.findAll();
    }

    // http://localhost:8080/updateTitleById?id=1ea2d6645c7611eb81f8f02f742e345c&title=测试修改sss
    @ResponseBody
    @GetMapping("/updateTitleById")
    Object updateTitleById(Site s){

        // 对比
        //  http://localhost:8080/updateTitleById?id=1ea02a725c7611eb81f8f02f742e345c&title=测试修改333
        //  http://localhost:8080/updateTitleById?id=1ea02a725c7611eb81f8f02f742e345c&title=测试修改333&url=xxx
        //这个测试只修改title字段，但是这个save方法会update前端传过来的所有属性
        //mapperSite.save(s);
        //一般是这样操作 ↓
        Site site = mapperSite.findById(s.getId()).get();
        site.setTitle(s.getTitle());
        mapperSite.save(site);
        return mapperSite.findAll();
    }

    // http://localhost:8080/deleteSiteById?id=a89754e95c7611eb81f8f02f742e345c
    @ResponseBody
    @GetMapping("/deleteSiteById")
    Object deleteSiteById(Site s){
        mapperSite.delete(s);
        return mapperSite.findAll();
    }

    // http://localhost:8080/findByTitleAndUrl?url=www.abc.com&title=测试，标题1
    @ResponseBody
    @RequestMapping("/findByTitleAndUrl")
    Object findByTitleAndUrl(Site s){
        return mapperSite.findByTitleAndUrl(s.getTitle(),s.getUrl());
    }

    //以下测试自己写sql的接口

    // http://localhost:8080/mySqlUpdateTitleById?id=8145ca3f5c7611eb81f8f02f742e345c&title=哦哦
    @ResponseBody
    @GetMapping("/mySqlUpdateTitleById")
    Object mySqlUpdateTitleById(Site s){
        mapperSite.mySqlUpdateTitleById(s.getTitle(),s.getId());

        return mapperSite.findAll();
    }

    // http://localhost:8080/mySqlQueryLikeByTitle?title=测试
    @ResponseBody
    @GetMapping("/mySqlQueryLikeByTitle")
    Object mySqlQueryLikeByTitle(Site s){
        return mapperSite.mySqlQueryLikeByTitle(s.getTitle());
    }


    // http://localhost:8080/mySqlDeleteById?id=4028a88177295577017729558f2d0002
    @ResponseBody
    @GetMapping("/mySqlDeleteById")
    Object mySqlDeleteById(Site s){
        mapperSite.mySqlDeleteById(s.getId());
        return mapperSite.findAll();
    }


}
