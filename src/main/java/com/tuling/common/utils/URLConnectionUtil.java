package com.tuling.common.utils;

import sun.net.util.URLUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * url请求工具
 * @author cjw
 * @email chenjunwenchn@gmail.com
 * @date 2017年8月23日14:18:07
 */
public class URLConnectionUtil {
     private static final String SERVLET_POST = "POST" ;
     private static final String SERVLET_GET = "GET" ;
     private static final String SERVLET_DELETE = "DELETE" ;
     private static final String SERVLET_PUT = "PUT" ;

	/**
	 * map转string
	 * @param paramMap
	 * @return
	 */
	public static String prepareParam(Map<String,Object> paramMap){
    	 if (paramMap==null) {
			return "";
		}
         StringBuffer sb = new StringBuffer();
         if(paramMap.isEmpty()){
             return "" ;
         }else{
             for(String key: paramMap.keySet()){
                 String value = (String)paramMap.get(key);
                 if(sb.length()<1){
                     sb.append(key).append("=").append(value);
                 }else{
                     sb.append("&").append(key).append("=").append(value);
                 }
             }
             return sb.toString();
         }
     }

	/**
	 * post请求方式
	 * @param urlStr
	 * @param param
	 * @param headers
	 * @return
	 */
     public static String  doPost(String urlStr,String param,Map<String, String> headers){
    	 String result ="";
    	 BufferedReader br = null;
    	 OutputStream os = null;
    	 try {
    		 URL url = new URL(urlStr);
             HttpURLConnection conn = (HttpURLConnection)url.openConnection();
             conn.setRequestMethod(SERVLET_POST);
             if(headers!=null){
				 Set<String> keys = headers.keySet();
				 for (String key : keys) {
					 conn.setRequestProperty(key, headers.get(key));
				 }
			 }
             conn.setDoInput(true);
             conn.setDoOutput(true);
             os = conn.getOutputStream();     
             os.write(param.getBytes());     
             br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
             String line ;
             while( (line =br.readLine()) != null ){
                 result += line;
             }
             br.close();
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}finally {
			try{
                if(os!=null){
                    os.close();
                }
                if(br!=null){
                	br.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
		}
    	 
         return result;
     }

	/**
	 * get请求
	 * @param urlStr
	 * @param param
	 * @param headers
	 * @return
	 */
     public static String  doGet(String urlStr,String param, Map<String, String> headers){
    	 String result ="";
    	 BufferedReader br = null;
    	 OutputStream os = null;
    	 try {
	         urlStr +="?"+param;
	         URL url = new URL(urlStr);
	         HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	         conn.setRequestMethod(SERVLET_GET);
	         conn.setRequestProperty("Content-Type","text/html; charset=UTF-8");
	         if(headers!=null){
				 Set<String> keys = headers.keySet();
				 for (String key : keys) {
					 conn.setRequestProperty(key, headers.get(key));
				 }
			 }
	         conn.connect();
	         br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String line ;
	         while( (line =br.readLine()) != null ){
	             result += line;
	         }
    	 } catch (Exception e) {
 			System.out.println("发送  请求出现异常！"+e);
 			e.printStackTrace();
 		 }finally {
 			try{
                 if(os!=null){
                     os.close();
                 }
                 if(br!=null){
                 	br.close();
                 }
             }
             catch(IOException ex){
                 ex.printStackTrace();
             }
 		}
         return result;
     }
     /**
      * put方法
      * @param urlStr
      * @param param
      * @param headers
      * @return
      */
     public static String doPut(String urlStr,String param,Map<String, String> headers){
    	 String result ="";
    	 BufferedReader br = null;
    	 OutputStream os = null;
    	 try {
	         URL url = new URL(urlStr);
	         HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	         conn.setRequestMethod(SERVLET_PUT);
			 if(headers!=null){
				 Set<String> keys = headers.keySet();
				 for (String key : keys) {
					 conn.setRequestProperty(key, headers.get(key));
				 }
			 }
	         conn.setDoInput(true);
	         conn.setDoOutput(true);
	         os = conn.getOutputStream();     
	         os.write(param.toString().getBytes("utf-8"));     
	         os.close();         
	         br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String line ;
	         while( (line =br.readLine()) != null ){
	             result += line;
	         }
    	 } catch (Exception e) {
   			System.out.println("发送 Delete请求出现异常！"+e);
   			e.printStackTrace();
   		 }finally {
  			try{
                if(os!=null){
                    os.close();
                }
                if(br!=null){
                	br.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
		}        
    	 return result;
     }
     /**
      * 删除方法
      * @param urlStr
      * @param param
      * @throws Exception
      */
     public static boolean doDelete(String urlStr,String param,Map<String, String> headers){
    	 try {
	         urlStr +="?"+param;
	         URL url = new URL(urlStr);
	         HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	         conn.setDoOutput(true);
	         conn.setRequestMethod(SERVLET_DELETE);
			 if(headers!=null){
				 Set<String> keys = headers.keySet();
				 for (String key : keys) {
					 conn.setRequestProperty(key, headers.get(key));
				 }
			 }

	         if(conn.getResponseCode() == 204 || conn.getResponseCode() ==200){
	            return true;
	         }else{
	             System.out.println(conn.getResponseCode());
	             return false;
	         }
    	 } catch (Exception e) {
  			System.out.println("发送 Delete请求出现异常！"+e);
  			e.printStackTrace();
  		 }
    	 return false;
     }
     
     public static void main(String[] args) throws Exception{
    	//String result = null;
    	Map<String, String> headers = new HashMap<String,String>();
     	headers.put("Authorization", "token a194a7d7e7f546c6a2d6e2fa227296aa");
     	/*Map<String, Object> params = new HashMap<String, Object>();
		List<String> usernames = new ArrayList<String>();
		usernames.add("hall");
		usernames.add("hello");
		Gson gson = new Gson();
		params.put("usernames", usernames);
		params.put("admin", false);
		String jsonString = gson.toJson(params);
		System.out.println(jsonString); 
		String param = "{\"admin\":false,\"usernames\":[\"hello\"]}";
		String result = URLConnectionUtil.doPost("http://quant.tuling.me/hub/api/users",param, headers);
		
		
		URLConnectionUtil.doDelete("http://quant.tuling.me/hub/api/users/hello",null, headers);*/
     	//GET proxy
     	/*result = URLConnectionUtil.doGet("http://quant.tuling.me/hub/api/proxy", null, headers);
     	JSONObject jsonObject = JSONObject.fromObject(result);
     	@SuppressWarnings("unchecked")
		Set<String> keySet = jsonObject.keySet();
     	List<Proxy> maps = new ArrayList<Proxy>();
     	for (String object : keySet) {
			JSONObject jsonRoute = (JSONObject) jsonObject.get(object);
			Route bean = (Route) JSONObject.toBean(jsonRoute,Route.class);
			Map<String, Route> map = new HashMap<String, Route>();
			map.put(object, bean);
			Proxy proxy = new Proxy();
			proxy.setMap(map);
			maps.add(proxy);
		}
     	System.out.println(maps);*/
     	
     	//PATCH proxy 
     	String param = "{\"ip\": \"string\",\"port\": \"string\",\"protocol\": \"string\",\"auth_token\": \"string\"}";
     	System.out.println(param);
     	String doPost = URLConnectionUtil.doPut("http://quant.tuling.me/hub/api/proxy", param, headers);
     	System.out.println(doPost);
     }
     
     
 }
