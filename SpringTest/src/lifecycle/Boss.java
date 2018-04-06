package lifecycle;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;  
import javax.annotation.PreDestroy;  

@Component
public class Boss {  
    @Resource  
    private Car car;  
  
    @Resource(name = "office")  
    private Office office;  
  
    @PostConstruct  
    public void postConstruct1(){  
        System.out.println("postConstruct1");  
    }  
  
    @PreDestroy  
    public void preDestroy1(){  
        System.out.println("preDestroy1");   
    }  
}  
