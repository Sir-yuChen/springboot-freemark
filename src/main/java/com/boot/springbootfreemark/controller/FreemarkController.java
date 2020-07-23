package com.boot.springbootfreemark.controller;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
@Slf4j
public class FreemarkController {

    @Resource
    Configuration configuration;

    @RequestMapping("index")
    public String main(Model model){
        String w="Welcome FreeMarker!";
        Map root = new HashMap();
        root.put("w",w);
        freeMarkerContent(root);
        model.addAttribute("w","Welcome FreeMarker!");
        return "test";
    }

    private void freeMarkerContent(Map<String,Object> root){
        try {
            //使用哪一个模板生成页面    这里使用的是test.ftl模板
            Template temp = configuration.getTemplate("test.ftl");
            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            String path=this.getClass().getResource("/").toURI().getPath()+"test1.html";

            //*************路径：/D:/JetBrains/AllProjects/springboot-freemark/target/classes/test1.html
            log.info("*************路径："+path.toString());
            //文件写入流
            Writer file = new FileWriter(new File(path.substring(path.indexOf("/"))));
            temp.process(root, file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
