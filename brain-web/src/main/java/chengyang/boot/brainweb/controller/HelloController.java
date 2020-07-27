package chengyang.boot.brainweb.controller;

import bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.HelloService;
import service.impl.HelloServiceImpl;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @PostMapping("/hello")
    public void controllerMethod(@RequestParam int id, @RequestParam Student student){
        helloService.serviceMethod(id, student);
    }

    public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl();
        Student student = new Student();
        student.setId(6);
        student.setName("zhangsan");
        helloService.serviceMethod(3, student);
    }
}
