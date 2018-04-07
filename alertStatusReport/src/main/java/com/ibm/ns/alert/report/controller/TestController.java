package com.ibm.ns.alert.report.controller;

import com.ibm.ns.alert.report.bean.User;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController
{
  @RequestMapping(value={"/user"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView post(User user, HttpServletRequest reqeust, HttpServletResponse response)
  {
    System.out.println("POST user");

    return new ModelAndView("user", "user", user);
  }

  @RequestMapping(value={"/user"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
  public ModelAndView put(User user, HttpServletRequest reqeust, HttpServletResponse response)
  {
    System.out.println("PUT user");

    return new ModelAndView("user", "user", user);
  }
}

/* Location:           C:\Users\IBM_ADMIN\Desktop\alertStatusRpt.zip
 * Qualified Name:     alertStatusRpt.WEB-INF.classes.com.ibm.ns.alert.report.controller.TestController
 * JD-Core Version:    0.6.2
 */