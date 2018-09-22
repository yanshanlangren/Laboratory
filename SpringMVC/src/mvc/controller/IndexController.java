package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.bean.User;

@Controller
public class IndexController {
	
	@RequestMapping("")
	public String Create(Model model) {
		System.out.println("enter method \"create\"");
        return "create";
    }
	
	@RequestMapping("/save")
    public String Save(@ModelAttribute("form") User user, Model model) { // user:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
		System.out.println("enter method \"save\"");
        model.addAttribute("user", user);
        return "detail";
    }
}
