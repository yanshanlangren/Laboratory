package com.ibm.ns.alert.report.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController
{
  @RequestMapping(value={"/{page}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView get(@PathVariable("page") String page, HttpServletRequest reqeust, HttpServletResponse response)
  {
    return new ModelAndView(page);
  }

  @RequestMapping(value={"/view/{domain}/{env}/{status}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView get(@PathVariable("domain") String domain, @PathVariable("env") String env, @PathVariable("status") String status, HttpServletRequest reqeust, HttpServletResponse response)
  {
    return new ModelAndView(domain + "/" + status + "_" + env);
  }
}

/* Location:           C:\Users\IBM_ADMIN\Desktop\alertStatusRpt.zip
 * Qualified Name:     alertStatusRpt.WEB-INF.classes.com.ibm.ns.alert.report.controller.IndexController
 * JD-Core Version:    0.6.2
 */