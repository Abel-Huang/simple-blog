package cn.abelib.blog.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by abel on 2017/12/6.
 */
@RestController
public class AdminController {
    @GetMapping(value = "hello0")
    public String hello0(){
        return "Hello World!";
    }

    @GetMapping(value = "/hello/{id}")
    public String hello(@PathVariable("id") Integer id){
        return "id: " + id;
    }

    @GetMapping(value = "/say")
    public String hello2(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id){
        return "id: " + id;
    }
}
