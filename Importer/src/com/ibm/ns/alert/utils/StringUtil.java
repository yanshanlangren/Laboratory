package com.ibm.ns.alert.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringUtil
{
  public static String inputStreamToString(InputStream is)
  {
    try
    {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      int i = -1;
      while ((i = is.read()) != -1) {
        baos.write(i);
      }

      baos.flush();
      baos.close();

      return baos.toString();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static boolean isNotBlank(String str) {
    if ((str != null) && (str.length() > 0)) {
      return true;
    }
    return false;
  }
}

/* Location:           C:\Users\IBM_ADMIN\Desktop\alertStatusRpt.zip
 * Qualified Name:     alertStatusRpt.WEB-INF.classes.com.ibm.ns.alert.utils.StringUtil
 * JD-Core Version:    0.6.2
 */