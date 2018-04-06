package lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
  
public class AnnoIoCTest {  
  
    public static void main(String[] args) {  
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("lifecycle");  
        Boss boss = (Boss) ctx.getBean("boss");  
        System.out.println(boss);  
        ctx.destroy();// 关闭 Spring 容器，以触发 Bean 销毁方法的执行  
    }  
}  