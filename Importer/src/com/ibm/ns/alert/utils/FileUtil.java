package com.ibm.ns.alert.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil
{
  public static String getContentFromFile(File file)
  {
    String str = "";
    try
    {
      InputStream is = new FileInputStream(file);

      str = StringUtil.inputStreamToString(is);

      is.close();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    return str;
  }
}

/* Location:           C:\Users\IBM_ADMIN\Desktop\alertStatusRpt.zip
 * Qualified Name:     alertStatusRpt.WEB-INF.classes.com.ibm.ns.alert.utils.FileUtil
 * JD-Core Version:    0.6.2
 */