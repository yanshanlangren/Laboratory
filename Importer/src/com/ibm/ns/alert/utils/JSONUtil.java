package com.ibm.ns.alert.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONUtil
{
  public static ObjectMapper om = new ObjectMapper();

  public static Map<String, Object> stringToMap(String str)
    throws IOException
  {
    Map map = new HashMap();
    try {
      map = (Map)om.readValue(str, Map.class);
    }
    catch (JsonParseException e) {
      e.printStackTrace();
      throw e;
    }
    catch (JsonMappingException e) {
      e.printStackTrace();
      throw e;
    }
    catch (IOException e) {
      e.printStackTrace();
      throw e;
    }

    return map;
  }

  public static Map<String, Object> stringToMap(StringBuffer str) throws IOException
  {
    Map map = new HashMap();
    try {
      map = (Map)om.readValue(str.toString(), Map.class);
    }
    catch (JsonParseException e) {
      e.printStackTrace();
      throw e;
    }
    catch (JsonMappingException e) {
      e.printStackTrace();
      throw e;
    }
    catch (IOException e) {
      e.printStackTrace();
      throw e;
    }

    return map;
  }

  public static Map<String, Object> jsonFileToMap(File file)
    throws IOException
  {
    Map map = new HashMap();
    try {
      map = (Map)om.readValue(file, Map.class);
    }
    catch (JsonParseException e) {
      e.printStackTrace();
      throw e;
    }
    catch (JsonMappingException e) {
      e.printStackTrace();
      throw e;
    }
    catch (IOException e) {
      e.printStackTrace();
      throw e;
    }
    return map;
  }
}

/* Location:           C:\Users\IBM_ADMIN\Desktop\alertStatusRpt.zip
 * Qualified Name:     alertStatusRpt.WEB-INF.classes.com.ibm.ns.alert.utils.JSONUtil
 * JD-Core Version:    0.6.2
 */