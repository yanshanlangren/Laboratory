package com.ibm.ns.schedule.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ibm.ns.alert.utils.Base64Util;
import com.ibm.ns.alert.utils.JSONUtil;
import com.ibm.ns.alert.utils.ZipUtil;
import com.ibm.ns.schedule.db.DataBaseService;
import com.ibm.ns.schedule.logger.LogUtil;

@Service
public class ImportTaskImpl implements ImportTask {
	
	private static int retry=3; //重试次数
	
	private String FILE_PATH;
	
	private String FILE_LINE_SPLIT_MARK;
	
	@Value("#{prop.FILE_PATH}")
	public void setFILE_PATH(String file_path) {
		this.FILE_PATH=file_path;
	}
	
	@Value("#{prop.FILE_LINE_SPLIT_MARK}")
	public void setFILE_LINE_SPLIT_MARK(String file_line_split_mark) {
		this.FILE_LINE_SPLIT_MARK=file_line_split_mark;
	}
	
	@Autowired
	private LogUtil logger;
	
	@Autowired
	private DataBaseService db;

    public void execute() {
        logger.info(this, "Reading file in <"+FILE_PATH+">");
        File fp=new File(FILE_PATH);
        //检查目标文件夹是否存在
        if(!fp.isDirectory()) {
        	if(retry==0)
        		return;
        	retry--;
            logger.info(this, "Folder does not exist <"+FILE_PATH+">");
        	return;
        }
        //检查temp文件
        File tmp=new File(FILE_PATH+"\\\\temp");
        if(!tmp.exists()) {
            logger.info(this,"Temp directory does not exist, creating folder: <"+FILE_PATH+"\\\\temp>");
        	tmp.mkdirs();
        }
        //文件移到temp文件夹
        File[] fromFiles=fp.listFiles();
        String regex="data_[t|a|p]\\.json";
        Pattern pattern=Pattern.compile(regex);
        for(File f:fromFiles) {
        	if(pattern.matcher(f.getName()).find()) {
        		f.renameTo(new File(FILE_PATH+"\\\\temp\\\\"+f.getName()));
        	}
        }
        
        //读取temp下的文件存到数据库中
        for(File f:tmp.listFiles()) {
        	logger.info(this,"Trying to read file"+f.getAbsolutePath());
        	List<Map<String, Object>> list=this.getAllDataWithJSTreeJSONFormat(f.getAbsolutePath());
//        	DataBaseService db=ServiceManager.getServiceManager().getService(DataBaseService.class);
        	db.storeList(list,f.getName().split("\\.")[0]);
        	f.delete();
        }
    }
    
	public List<Map<String, Object>> getAllDataWithJSTreeJSONFormat(String data_file) {
		
		List<Map<String, Object>> json_list = new ArrayList<Map<String, Object>>();
		
		//read data file
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader(data_file));
					
			String line = null;
				
			while((line = br.readLine())!=null){
				line = line.substring(1, line.length()-1);
				json_list.add(this.getJSTreeJSONFromLine(line));
			}
			
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return json_list;
	}
	
//	public List<Map<String, Object>> getAllDataWithJSONFormat(String data_file)
//	  {
//	    
//	    List json_list = new ArrayList();
//	    try
//	    {
//	      BufferedReader br = new BufferedReader(new FileReader(data_file));
//	
//	      String line = null;
//	
//	      while ((line = br.readLine()) != null) {
//	        line = line.substring(1, line.length() - 1);
//	        json_list.add(getJSONFromLine(line));
//	      }
//	
//	      br.close();
//	    } catch (FileNotFoundException e1) {
//	      e1.printStackTrace();
//	    } catch (IOException e) {
//	      e.printStackTrace();
//	    }
//	    return json_list;
//	  }
	
	
//	  private Map<String, Object> getJSONFromLine(String line)
//	  {
//	    String[] infos = line.split(Contants.FILE_LINE_SPLIT_MARK);
//
//	    String node_name = infos[0].substring(0, infos[0].length() - 3);
//	    String date = infos[1].substring(1, infos[1].length() - 3);
//
//	    HashMap map1 = new HashMap();
//	    HashMap map2 = new HashMap();
//	    try
//	    {
//	      byte[] gzip_bytes = Base64Util.decodeBytes(infos[2]);
//	      StringBuffer st = ZipUtil.getContentFromGZIPBytes(gzip_bytes);
//	      
//	      LogUtil.info(this, "String Line in data file after convert:{"+st+"}");
//
//	      Map infoMap = JSONUtil.stringToMap(st.toString());
//
//	      List children2 = new ArrayList();
//	      children2.add(infoMap);
//
//	      map2.put(date, children2);
//	    } catch (IOException e) {
//	      map2.put(date, "Data is not available");
//	    }
//	    map1.put(node_name, map2);
//
//	    return map1;
//	  }
	
	
	private Map<String, Object> getJSTreeJSONFromLine(String line) {
		
		String[] infos = line.split(FILE_LINE_SPLIT_MARK);
		
		String node_name = infos[0].substring(0, infos[0].length()-3);		//node server host name
		String date = infos[1].substring(1, infos[1].length()-3);		//time
		
//		DateFormat df = new SimpleDateFormat(Contants.DATETIME_FORMAT);
//		String date = df.format(Long.parseLong(timestamp));
		
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
				
		Map<String, Object> infoMap;
		Map<String, Object> jstreeMap;
		try {
			
			byte[] gzip_bytes = Base64Util.decodeBytes(infos[2]);
			StringBuffer st = ZipUtil.getContentFromGZIPBytes(gzip_bytes);
			
			logger.info(this, "String Line in data file after convert:{"+st+"}");
				
			infoMap = JSONUtil.stringToMap(st.toString());
			jstreeMap = this.convertToJSTreeMap(infoMap);
			
			List<Map<String, Object>> children2 = new ArrayList<Map<String, Object>>();
			children2.add(jstreeMap);
			
			map2.put("text", date);
			map2.put("children", children2);
		} catch (IOException e) {
			map2.put("text", date+":  Data is not available");
		}
		map1.put("text", node_name);
		
		List<Map<String, Object>> children1 = new ArrayList<Map<String, Object>>();
		children1.add(map2);
		map1.put("children", children1);
		
		return map1;
	}
	
	private Map<String, Object> convertToJSTreeMap(Map<String, Object> infoMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		result = this.createJSTreeMap(result, infoMap);
		result.put("text", "Alert");
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> createJSTreeMap(Map<String, Object> result, Map<String, Object> infoMap) {
		List<Object> children = new ArrayList<Object>();
		if(result.get("children") != null){
			children = (List<Object>) result.get("children");
		}
		
		Iterator<Entry<String, Object>> infoMap_itr = infoMap.entrySet().iterator();
		
		while(infoMap_itr.hasNext()){
			Entry<String, Object> entry = (Entry<String, Object>) infoMap_itr.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			
			Map<String, Object> tree_item = new HashMap<String, Object>();
			
			if(value instanceof String || value instanceof Integer){
				tree_item.put("text", key + ":" + value.toString());
			}else if(value instanceof Map){
				tree_item=this.createJSTreeMap(tree_item, (Map<String, Object>)value);
				tree_item.put("text", key);
			}else if(value instanceof List){
				List<Map<String, Object>> _list = (List<Map<String, Object>>) value;
				for(Map<String, Object> _item : _list){
					tree_item=this.createJSTreeMap(tree_item, (Map<String, Object>)_item);
				}
				tree_item.put("text", key);
			}
			
			children.add(tree_item);
			
		}
		
		result.put("children", children);
		return result;
		
	}
	
    
    public static void main(String[] args) {
//        String regex="data_[a|p]\\.json";
//        Pattern pattern=Pattern.compile(regex);
//        System.out.println(pattern.matcher("data_s.json").find());
        
//    	for(File f:new File(FILE_PATH).listFiles()) {
//    		System.out.println(f.getName());
//    	}
    	
//    	String[] arr="data_a.json".split("\\.");
//    	System.out.println(arr[0]);
    	
//    	ServiceManager.getServiceManager().getService(ImportTask.class).execute();
    }
}