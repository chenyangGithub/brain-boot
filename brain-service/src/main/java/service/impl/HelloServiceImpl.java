package service.impl;

import bean.Student;
import org.springframework.stereotype.Service;
import service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void serviceMethod(int id, Student student) {
        System.out.println("serviceMehtod 执行了。。。。");
    }
}
