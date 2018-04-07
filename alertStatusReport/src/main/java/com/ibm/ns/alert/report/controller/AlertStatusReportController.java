package com.ibm.ns.alert.report.controller;

import com.ibm.ns.alert.report.Contants;
import com.ibm.ns.alert.report.service.AlertStatusReportService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertStatusReportController
{

  @Autowired
  private AlertStatusReportService alertStatusReportService;
  private Logger logger = LoggerFactory.getLogger(getClass());

  @RequestMapping(value={"/alert/statustree/{env}/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Map<String, Object> get(@PathVariable("env") String env, @PathVariable("id") String id, HttpServletRequest reqeust, HttpServletResponse response)
  {
    String method = "get";
    this.logger.info("method:{}, type:{}", method, id);

    Map result = new HashMap();
    List json_list = new ArrayList();

  String data_file = Contants.DATE_FILE_PATH + Contants.DATE_FILE_NAME_ACC;

  if (env.equalsIgnoreCase(Contants.ENV_ACC)) {
    data_file = Contants.DATE_FILE_PATH + Contants.DATE_FILE_NAME_ACC;
  }
  if (env.equalsIgnoreCase(Contants.ENV_PRD)) {
    data_file = Contants.DATE_FILE_PATH + Contants.DATE_FILE_NAME_PRD;
  }

    if ((id.equalsIgnoreCase("all")) || (id.equalsIgnoreCase("lastest")))
    {
      json_list = this.alertStatusReportService.getDataWithJSTreeJSONFormat(data_file, id);

      result.put("text", "Root");
      result.put("children", json_list);

      return result;
    }

    this.logger.info("The data is not available");
    result.put("Status", "Not available input.");
    return result;
  }

  @RequestMapping(value={"/alert/statusjson/{env}/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Map<String, Object> getJson(@PathVariable("env") String env, @PathVariable("id") String id, HttpServletRequest reqeust, HttpServletResponse response)
  {
    String method = "getJson";
    this.logger.info("method:{}, type:{}", method, id);

    Map result = new HashMap();

    String data_file = Contants.DATE_FILE_PATH + Contants.DATE_FILE_NAME_ACC;

    if (env.equalsIgnoreCase(Contants.ENV_ACC)) {
      data_file = Contants.DATE_FILE_PATH + Contants.DATE_FILE_NAME_ACC;
    }
    if (env.equalsIgnoreCase(Contants.ENV_PRD)) {
      data_file = Contants.DATE_FILE_PATH + Contants.DATE_FILE_NAME_PRD;
    }

    if (id.equalsIgnoreCase("all"))
    {
      List json_list = new ArrayList();

      json_list = this.alertStatusReportService.getDataWithJSONFormat(data_file, id);

      result.put("Root", json_list);

      return result;
    }

    if (id.equalsIgnoreCase("lastest")) {
      Map json_map = new HashMap();

      json_map = this.alertStatusReportService.getLastestDataWithJSONFormat(data_file);

      result.put("Root", json_map);

      return result;
    }

    this.logger.info("The data is not available");
    result.put("Root", "Not available input.");
    return result;
  }
}