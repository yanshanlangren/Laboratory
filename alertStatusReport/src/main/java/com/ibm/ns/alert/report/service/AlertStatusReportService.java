package com.ibm.ns.alert.report.service;

import com.ibm.ns.schedule.db.DataBaseService;
import com.ibm.ns.schedule.logger.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertStatusReportService
{
//  private Logger logger = LoggerFactory.getLogger(getClass());
  
  @Autowired
  private DataBaseService dbs;
  
  @Autowired
  private LogUtil logger;

  public List<Map<String, Object>> getDataWithJSTreeJSONFormat(String data_file, String type)
  {
    if (type.equalsIgnoreCase("all")) {
      return getAllDataWithJSTreeJSONFormat(data_file);
    }
    if (type.equalsIgnoreCase("lastest")) {
      return getLastestDataWithJSTreeJSONFormat(data_file, type);
    }
    return null;
  }

  public List<Map<String, Object>> getDataWithJSONFormat(String data_file, String type)
  {
    if (type.equalsIgnoreCase("all")) {
      return getAllDataWithJSONFormat(data_file);
    }
    return null;
  }

  public List<Map<String, Object>> getAllDataWithJSTreeJSONFormat(String data_file)
  {
    
//	DataBaseService dbs=ServiceManager.getServiceManager().getService(DataBaseService.class);
    return dbs.getList(data_file.split("\\\\")[data_file.split("\\\\").length-1].split("\\.")[0]);
    
    /**
     * Woods 实现
     */
//    List json_list = new ArrayList();
//    try
//    {
//      BufferedReader br = new BufferedReader(new FileReader(data_file));
//
//      String line = null;
//
//      while ((line = br.readLine()) != null) {
//        line = line.substring(1, line.length() - 1);
//        json_list.add(getJSTreeJSONFromLine(line));
//      }
//
//      br.close();
//    } catch (FileNotFoundException e1) {
//      e1.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return json_list;
  }

  public List<Map<String, Object>> getAllDataWithJSONFormat(String data_file)
  {

//	  	DataBaseService dbs=ServiceManager.getServiceManager().getService(DataBaseService.class);
	    return convertTreeToJSON(dbs.getList(data_file.split("\\\\")[data_file.split("\\\\").length-1].split("\\.")[0]));
    
    
    /**
     * Woods 实现
     */
//    List json_list = new ArrayList();
//    try
//    {
//      BufferedReader br = new BufferedReader(new FileReader(data_file));
//
//      String line = null;
//
//      while ((line = br.readLine()) != null) {
//        line = line.substring(1, line.length() - 1);
//        json_list.add(getJSONFromLine(line));
//      }
//
//      br.close();
//    } catch (FileNotFoundException e1) {
//      e1.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return json_list;
  }

  private List<Map<String, Object>> convertTreeToJSON(List<Map<String, Object>> list) {
	  List<Map<String, Object>> result=new ArrayList();
	  for(Map map:list) {
		  result.add(convertJSTreeMapToJSONMap(map));
	  }
	  return result;
  }
  
  private Map<String, Object> convertJSTreeMapToJSONMap(Map result) {
		Map<String,Object> infoMap=new HashMap<String,Object>();
		List<Map<String,Object>> list=(List<Map<String, Object>>) result.get("children");
		Map<String,Object> map1=new HashMap<String,Object>();
		Map<String,Object> map2=(Map<String, Object>)list.get(0);
		map1.put("Time",map2.get("text"));
//		Map<String,Object> a=((List<Map<String,Object>>)((List<Map<String,Object>> ) map2.get("children")).get(0).get("children")).get(0);
		try {
			map1.put("Alert",getJSONMap(new ArrayList<Map<String,Object>>(),((List<Map<String,Object>>)((List<Map<String,Object>> ) map2.get("children")).get(0).get("children"))));
		}catch(Exception e) {
			logger.warn(this, "JSON message not parsable: "+result+"", e);
		}
		
		infoMap.put((String)result.get("text"), map1);
	    return infoMap;
	}
  
  private List<Map<String,Object>> getJSONMap(List<Map<String,Object>> infoMap,List<Map<String,Object>> result){
	  for(Map<String,Object> map:result) {
		  Map<String,Object> res=new HashMap<String,Object>();
			String key=(String) map.get("text");
			if(map.containsKey("children")) {
//				List<Map<String,Object>> children=new ArrayList<Map<String,Object>>();
//				for(Map<String,Object> map1:(List<Map<String,Object>>)map.get("children")) {
//					children.add(getJSONMap(new HashMap<String,Object>(),map));
//				}
				List<Map<String,Object>> children=getJSONMap(new ArrayList<Map<String,Object>>(),(List<Map<String,Object>>)map.get("children"));
				res.put(key, children);
			} else {
				res.put(key.split(":")[0], key.split(":")[1]);
			}
			infoMap.add(res);
	  }
		return infoMap;
	}

public List<Map<String, Object>> getLastestDataWithJSTreeJSONFormat(String data_file, String type)
  {
//	  DataBaseService dbs=ServiceManager.getServiceManager().getService(DataBaseService.class);
	  return convertTreeToJSON(dbs.getLatestList(data_file.split("\\\\")[data_file.split("\\\\").length-1].split("\\.")[0]));
    
    /**
     * Woods 实现
     */
//    	    List json_list = new ArrayList();
//    Map result = new HashMap();
//    RandomAccessFile rf = null;
//    try
//    {
//      rf = new RandomAccessFile(data_file, "r");
//
//      long len = rf.length();
//      long start = rf.getFilePointer();
//      long next = start + len - 1L;
//
//      String line = null;
//
//      rf.seek(next);
//
//      int c = -1;
//      while (next > start) {
//        c = rf.read();
//        if ((c == 10) || (c == 13)) {
//          line = rf.readLine();
//
//          if (line != null) {
//            line = line.substring(1, line.length() - 1);
//
//            this.logger.debug("String Line in data file:{}", line);
//            String[] infos = line.split(Contants.FILE_LINE_SPLIT_MARK);
//
//            String node_name = infos[0].substring(0, infos[0].length() - 3);
//            String timestamp = infos[1].substring(1, infos[1].length() - 3);
//
//            if (isLastestJSTree(node_name, timestamp, result)) {
//              Map item = getJSTreeJSONFromLine(line);
//              result.put(node_name, item);
//            }
//
//          }
//
//          next -= 1L;
//        }
//        next -= 1L;
//        rf.seek(next);
//        if (next == 0L) {
//          line = rf.readLine();
//
//          this.logger.debug("String Line in data file:{}", line);
//
//          line = line.substring(1, line.length() - 1);
//          String[] infos = line.split(Contants.FILE_LINE_SPLIT_MARK);
//
//          String node_name = infos[0].substring(0, infos[0].length() - 3);
//          String timestamp = infos[1].substring(1, infos[1].length() - 3);
//
//          if (isLastestJSTree(node_name, timestamp, result)) {
//            Map item = getJSTreeJSONFromLine(line);
//            result.put(node_name, item);
//          }
//        }
//      }
//    }
//    catch (FileNotFoundException e1) {
//      e1.printStackTrace();
//      try
//      {
//        if (rf != null)
//          rf.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//    catch (IOException e)
//    {
//      e.printStackTrace();
//      try
//      {
//        if (rf != null)
//          rf.close();
//      } catch (IOException ex) {
//        ex.printStackTrace();
//      }
//    }
//    finally
//    {
//      try
//      {
//        if (rf != null)
//          rf.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//
//    }
//
//    Iterator result_itr = result.entrySet().iterator();
//    while (result_itr.hasNext()) {
//      Map.Entry entry = (Map.Entry)result_itr.next();
//      Object value = entry.getValue();
//      json_list.add((Map)value);
//    }
//    return json_list;
  }

  public Map<String, Map<String, Object>> getLastestDataWithJSONFormat(String data_file)
  {
	  Map result = new HashMap();
//	  DataBaseService dbs=ServiceManager.getServiceManager().getService(DataBaseService.class);
	  List<Map<String, Object>> list=dbs.getLatestList(data_file.split("\\\\")[data_file.split("\\\\").length-1].split("\\.")[0]);
	  list=convertTreeToJSON(list);
	  for(Map<String,Object> map:list) {
		  String key=map.keySet().iterator().hasNext()?map.keySet().iterator().next():"";
		  Object alert=map.get(key);
		  result.put(key, alert);
	  }
	  return result;
	  
	  /**
	   * Woods 实现
	   */
//    Map result = new HashMap();
//    RandomAccessFile rf = null;
//    try
//    {
//      rf = new RandomAccessFile(data_file, "r");
//
//      long len = rf.length();
//      long start = rf.getFilePointer();
//      long next = start + len - 1L;
//
//      String line = null;
//
//      rf.seek(next);
//
//      int c = -1;
//      while (next > start) {
//        c = rf.read();
//        if ((c == 10) || (c == 13)) {
//          line = rf.readLine();
//
//          if (line != null) {
//            line = line.substring(1, line.length() - 1);
//            this.logger.debug("String Line in data file:{}", line);
//            String[] infos = line.split(Contants.FILE_LINE_SPLIT_MARK);
//
//            String node_name = infos[0].substring(0, infos[0].length() - 3);
//            String timestamp = infos[1].substring(1, infos[1].length() - 3);
//
//            if (isLastest(node_name, timestamp, result)) {
//              result = getLastestJSONFromLine(result, line);
//            }
//
//          }
//
//          next -= 1L;
//        }
//        next -= 1L;
//        rf.seek(next);
//        if (next == 0L) {
//          line = rf.readLine();
//          this.logger.debug("String Line in data file:{}", line);
//          line = line.substring(1, line.length() - 1);
//
//          String[] infos = line.split(Contants.FILE_LINE_SPLIT_MARK);
//
//          String node_name = infos[0].substring(0, infos[0].length() - 3);
//          String timestamp = infos[1].substring(1, infos[1].length() - 3);
//
//          if (isLastest(node_name, timestamp, result)) {
//            result = getLastestJSONFromLine(result, line);
//          }
//        }
//      }
//    }
//    catch (FileNotFoundException e1)
//    {
//      e1.printStackTrace();
//      try
//      {
//        if (rf != null)
//          rf.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//    catch (IOException e)
//    {
//      e.printStackTrace();
//      try
//      {
//        if (rf != null)
//          rf.close();
//      } catch (IOException ex) {
//        ex.printStackTrace();
//      }
//    }
//    finally
//    {
//      try
//      {
//        if (rf != null)
//          rf.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//
//    System.out.println(result);
//    return result;
  }

//  private boolean isLastestJSTree(String node_name, String timestamp, Map<String, Map<String, Object>> result)
//  {
//    boolean isLastest = true;
//    Map item = (Map)result.get(node_name);
//
//    if ((item != null) && (item.get("text").toString().equalsIgnoreCase(node_name))) {
//      List temp = (List)item.get("children");
//      if ((temp != null) && (temp.size() > 0) && (temp.get(0) != null)) {
//        String datetime = ((Map)temp.get(0)).get("text").toString();
//
//        if (Long.parseLong(datetime) >= Long.parseLong(timestamp)) {
//          isLastest = false;
//        }
//      }
//    }
//
//    return isLastest;
//  }

//  private boolean isLastest(String node_name, String timestamp, Map<String, Map<String, Object>> result)
//  {
//    boolean isLastest = true;
//    Map item = (Map)result.get(node_name);
//
//    if (item != null)
//    {
//      String time = item.get("Time").toString();
//
//      if (Long.parseLong(time) >= Long.parseLong(timestamp)) {
//        isLastest = false;
//      }
//
//    }
//
//    return isLastest;
//  }
//
//  private Map<String, Object> getJSTreeJSONFromLine(String line)
//  {
//    String[] infos = line.split(Contants.FILE_LINE_SPLIT_MARK);
//
//    String node_name = infos[0].substring(0, infos[0].length() - 3);
//    String date = infos[1].substring(1, infos[1].length() - 3);
//
//    Map map1 = new HashMap();
//    Map map2 = new HashMap();
//    try
//    {
//      byte[] gzip_bytes = Base64Util.decodeBytes(infos[2]);
//      StringBuffer st = ZipUtil.getContentFromGZIPBytes(gzip_bytes);
//
//      this.logger.debug("String Line in data file after convert:{}", st.toString());
//
//      Map infoMap = JSONUtil.stringToMap(st.toString());
//      Map jstreeMap = convertToJSTreeMap(infoMap);
//
//      List children2 = new ArrayList();
//      children2.add(jstreeMap);
//
//      map2.put("text", date);
//      map2.put("children", children2);
//    } catch (IOException e) {
//      map2.put("text", date + ":  Data is not available");
//    }
//    map1.put("text", node_name);
//
//    List children1 = new ArrayList();
//    children1.add(map2);
//    map1.put("children", children1);
//
//    return map1;
//  }

//  private Map<String, Object> convertToJSTreeMap(Map<String, Object> infoMap) {
//    Map result = new HashMap();
//    result = createJSTreeMap(result, infoMap);
//    result.put("text", "Alert");
//    return result;
//  }

//  private Map<String, Object> getJSONFromLine(String line)
//  {
//    String[] infos = line.split(Contants.FILE_LINE_SPLIT_MARK);
//
//    String node_name = infos[0].substring(0, infos[0].length() - 3);
//    String date = infos[1].substring(1, infos[1].length() - 3);
//
//    HashMap map1 = new HashMap();
//    HashMap map2 = new HashMap();
//    try
//    {
//      byte[] gzip_bytes = Base64Util.decodeBytes(infos[2]);
//      StringBuffer st = ZipUtil.getContentFromGZIPBytes(gzip_bytes);
//
//      this.logger.debug("String Line in data file after convert:{}", st.toString());
//
//      Map infoMap = JSONUtil.stringToMap(st.toString());
//
//      List children2 = new ArrayList();
//      children2.add(infoMap);
//
//      map2.put(date, children2);
//    } catch (IOException e) {
//      map2.put(date, "Data is not available");
//    }
//    map1.put(node_name, map2);
//
//    return map1;
//  }

//  private Map<String, Map<String, Object>> getLastestJSONFromLine(Map<String, Map<String, Object>> result, String line)
//  {
//    String[] infos = line.split(Contants.FILE_LINE_SPLIT_MARK);
//
//    String node_name = infos[0].substring(0, infos[0].length() - 3);
//    String date = infos[1].substring(1, infos[1].length() - 3);
//
//    HashMap map = new HashMap();
//    try
//    {
//      byte[] gzip_bytes = Base64Util.decodeBytes(infos[2]);
//      StringBuffer st = ZipUtil.getContentFromGZIPBytes(gzip_bytes);
//
//      this.logger.debug("String Line in data file after convert:{}", st.toString());
//
//      Map infoMap = JSONUtil.stringToMap(st.toString());
//
//      map.put("Time", date);
//      map.put("Alert", infoMap);
//    }
//    catch (IOException e) {
//      this.logger.info("json data format exception.");
//      map.put("Time", date);
//      map.put("Alert", "Data is not available");
//    }
//    result.put(node_name, map);
//
//    return result;
//  }

//  private Map<String, Object> createJSTreeMap(Map<String, Object> result, Map<String, Object> infoMap)
//  {
//    List children = new ArrayList();
//    if (result.get("children") != null) {
//      children = (List)result.get("children");
//    }
//
//    Iterator infoMap_itr = infoMap.entrySet().iterator();
//
//    while (infoMap_itr.hasNext()) {
//      Map.Entry entry = (Map.Entry)infoMap_itr.next();
//      String key = (String)entry.getKey();
//      Object value = entry.getValue();
//
//      Map tree_item = new HashMap();
//
//      if (((value instanceof String)) || ((value instanceof Integer))) {
//        tree_item.put("text", key + ":" + value.toString());
//      } else if ((value instanceof Map)) {
//        tree_item = createJSTreeMap(tree_item, (Map)value);
//        tree_item.put("text", key);
//      } else if ((value instanceof List)) {
//    	List<Map<String, Object>> _list = (List)value;
//        for (Map _item : _list) {
//          tree_item = createJSTreeMap(tree_item, _item);
//        }
//        tree_item.put("text", key);
//      }
//
//      children.add(tree_item);
//    }
//
//    result.put("children", children);
//    return result;
//  }
  
  public static void main(String[] args) {
	  String data_file="C:\\\\Users\\\\IBM_ADMIN\\\\Desktop\\\\data_a.json";
	  AlertStatusReportService ass=new AlertStatusReportService();
	  Map res=ass.getLastestDataWithJSONFormat(data_file);
	  System.out.println(res);
  }
}