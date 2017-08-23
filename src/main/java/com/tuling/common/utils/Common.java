package com.tuling.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.tuling.common.exception.RRException;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用sql条件查询
 * @author cjw 2017年8月22日15:01:17
 * @email chenjunwenchn@gmail.com
 */
public class Common {

    /**
     * 下划线转驼峰法
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line,boolean smallCamel){
        if(line==null||"".equals(line)){
            return "";
        }
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf('_');
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
    /**
     * 驼峰法转下划线
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line){
        if(line==null||"".equals(line)){
            return "";
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end()==line.length()?"":"_");
        }
        return sb.toString();
    }

    /**
     * 判断属性是否在实体中
     * @param property
     * @param classes
     * @return
     */
    public static boolean isObjProperty(String property,Class classes){
        boolean isExists = false;
        try {
            Field[] declaredFields = classes.getDeclaredFields();
            for (Field field : declaredFields){
                String name = field.getName();
                if(name.equals(property)){
                    isExists = true;
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return isExists;
    }

    /**
     * 分页加条件查询--->>只能单表
     * @param params
     * @param classes
     * @return
     */
    public static Condition getServiceCondition(Map<String,String> params, Class classes){
        Condition condition = new Condition(classes);
        Example.Criteria criteria = condition.createCriteria();
        int pageNum = 1;    //当前页默认为1
        int pageSize = 5;   //默认每页显示5条
        String sort = params.get("sort");
        String orderBy = params.get("orderBy");
        String pageNumStr =  params.get("currPage");
        String pageSizeStr = params.get("pageSize");
        String startTime = params.get("startTime");
        String endTime = params.get("endTime");

        String filter = params.get("filter");
        if(StringUtils.isNotBlank(filter)){
            try {
                JSONObject filterOjb = JSON.parseObject(filter);
                Set<String> strings = filterOjb.keySet();
                for(String key : strings){
                    if(Common.isObjProperty(key,classes)){//如果存在属性则加入到过滤条件中
                        criteria.andEqualTo(key,filterOjb.get(key));
                    }else{
                        throw new RRException("不存在filter的"+key+"属性，请查看实体");
                    }
                }
            }catch (Exception e){
                if(e.getClass()==RRException.class){
                    throw new RRException(e.getMessage());
                }
                throw new RRException("filter格式错误，请填写json格式");
            }
        }

        if(StringUtils.isNotBlank(pageNumStr)){
            pageNum = Integer.parseInt(pageNumStr);
        }

        if(StringUtils.isNotBlank(pageSizeStr)){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        if(StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(orderBy)){
            if(!sort.equalsIgnoreCase("asc") && !sort.equalsIgnoreCase("desc")){
                throw new RRException("sort is asc/desc");
            }
            String orderByClause = Common.camel2Underline(orderBy)+ " " + sort;
            condition.setOrderByClause(orderByClause);
        }

        if(StringUtils.isNotBlank(startTime)&&StringUtils.isNotBlank(endTime)){
            criteria.andBetween("CREATED_TIME",DateUtil.stampToDate(startTime),DateUtil.stampToDate(endTime));
        }else if(StringUtils.isNotBlank(startTime)){
            criteria.andCondition("CREATED_TIME >= '"+DateUtil.stampToDate(startTime)+"'");
        }else if(StringUtils.isNotBlank(endTime)){
            criteria.andCondition("CREATED_TIME <= '"+DateUtil.stampToDate(endTime)+"'");
        }
        PageHelper.startPage(pageNum, pageSize);
        return condition;
    }

    /**
     * 通用分页加条件查询---针对级联查询
     * @param params
     */
    public static void limit(Map<String,Object> params,Class classes){
        int pageNum = 1;    //当前页默认为1
        int pageSize = 5;   //默认每页显示5条
        String pageNumStr = (String) params.get("currPage");
        String pageSizeStr = (String) params.get("pageSize");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        String orderBy = (String) params.get("orderBy");
        String sort = (String) params.get("sort");
        if(StringUtils.isNotBlank(pageNumStr)){
            pageNum = Integer.parseInt(pageNumStr);
        }

        if(StringUtils.isNotBlank(pageSizeStr)){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        if(StringUtils.isNotBlank(startTime)){
            params.put("startTime",DateUtil.stampToDate(startTime));
        }

        if(StringUtils.isNotBlank(endTime)){
            params.put("endTime", DateUtil.stampToDate(endTime));
        }
        if(StringUtils.isNotBlank(orderBy)){
            if(!isObjProperty(orderBy,classes)){
                throw new RRException("排序字段不存在");
            }
            String o = Common.camel2Underline(orderBy);
            params.put("orderBy", SQLFilter.sqlInject(o));
        }
        if(StringUtils.isNotBlank(sort)){
            if(!sort.equalsIgnoreCase("asc") && !sort.equalsIgnoreCase("desc")){
                throw new RRException("sort is asc/desc");
            }
            params.put("sort", SQLFilter.sqlInject(sort));
        }
        PageHelper.startPage(pageNum, pageSize);
    }

    /**
     * string 转 json 并验证是否存在实体中
     * @param filter
     * @param classes
     * @return
     */
    public static JSONObject strParseObject(String filter,Class classes){
        JSONObject filterOjb = null;
        try {
            filterOjb = JSON.parseObject(filter);
            Set<String> strings = filterOjb.keySet();
            if(strings.size()==0){
                return null;
            }
            for(String key : strings){
                if(!Common.isObjProperty(key,classes)){//如果存在属性则加入到过滤条件中
                    throw new RRException("不存在filter的"+key+"属性，请查看实体");
                }
            }
        }catch (Exception e){
            if(e.getClass()==RRException.class){
                throw new RRException(e.getMessage());
            }
            throw new RRException("filter格式错误，请填写json格式");
        }
        return filterOjb;
    }

    /**
     * 添加查询过滤条件
     * @param params
     */
    public static void addQueryFilter(Map<String,Object> params,Class classes){
        String filter = (String) params.get("filter");
        if(StringUtils.isNotBlank(filter)){
            JSONObject jsonObject = Common.strParseObject(filter, classes);
            JSONObject resJson = new JSONObject();
            Set<String> strings = jsonObject.keySet();
            for(String key : strings){
                resJson.put(camel2Underline(key),jsonObject.getString(key));
            }
            params.put("filter",resJson);
        }
    }


}
