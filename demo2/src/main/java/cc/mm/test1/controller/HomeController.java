package cc.mm.test1.controller;


import cc.mm.test1.enity.Site;
import cc.mm.test1.repository.MapperSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
            mapperSite.addSite(s);
        }
        return mapperSite.getAllSites();
    }

    // http://localhost:8080/getAllSites
    @ResponseBody
    @RequestMapping("/getAllSites")
    Object getAllSites(){
        return mapperSite.getAllSites();
    }

    // http://localhost:8080/updateTitleById?id=1ea02a725c7611eb81f8f02f742e345c&title=测试修改
    @ResponseBody
    @GetMapping("/updateTitleById")
    Object updateTitleById(HttpServletRequest req){
        mapperSite.updateTitleById(req.getParameter("id"),req.getParameter("title"));
        return mapperSite.getAllSites();
    }

    // http://localhost:8080/deleteSiteById?id=a897afa05c7611eb81f8f02f742e345c
    @ResponseBody
    @GetMapping("/deleteSiteById")
    Object deleteSiteById(HttpServletRequest req){
        mapperSite.deleteSiteById(req.getParameter("id"));
        return mapperSite.getAllSites();
    }

}
