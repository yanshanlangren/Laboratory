package com.ibm.ns.alert.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Util
{
  public static String encode(String str)
  {
    return Base64.encodeBase64String(str.getBytes());
  }

  public static String decode(String str)
  {
    byte[] r = Base64.decodeBase64(str);
    return new String(r);
  }

  public static byte[] decodeBytes(String str)
  {
    byte[] r = Base64.decodeBase64(str);
    return r;
  }

  public static String encode(byte[] bs) {
    byte[] r = Base64.encodeBase64(bs);
    return new String(r);
  }
}

/* Location:           C:\Users\IBM_ADMIN\Desktop\alertStatusRpt.zip
 * Qualified Name:     alertStatusRpt.WEB-INF.classes.com.ibm.ns.alert.utils.Base64Util
 * JD-Core Version:    0.6.2
 */