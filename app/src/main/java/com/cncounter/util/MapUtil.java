package com.cncounter.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map 工具类
 * Created by renfufei on 2015/11/30.
 */
public class MapUtil {

    /**
     * 将对象转换为Map
     * @param object
     * @return
     */
    public static Map<String, Object> object2Map(Object object){
        Map<String, Object> map = null;
        if(null == object){
            return map;
        }
        map = new HashMap<String, Object>();

        //
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        //
        for (Field field: fields) {
            // 字段名
            String fieldName = field.getName();
            Object mapValue = getMapValue(object, field);
            map.put(fieldName, mapValue);
        }
        //
        return map;
    }

    private static Object getMapValue(Object object, Field field) {
        //
        // 类型
        Class<?> fieldType = field.getType();
        Object fieldValue = null;
        // 此处应该使用 get 方法比较好
        try {
            // 设置可访问性
            field.setAccessible(true);
            fieldValue = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(null == fieldValue){
            return fieldValue;
        } else if(isValueDataType(fieldValue)){
            // 是原生类型
            return fieldValue;
        } else {
            // 其他类型,则转换为 Map
            fieldValue = object2Map(fieldValue);
        }
        return fieldValue;
    }

    private static boolean isValueDataType(Object fieldValue) {
        boolean isPremitive = false;
        if(null == fieldValue){
            //
        } else if(fieldValue instanceof Integer){
            isPremitive = true;
        } else if(fieldValue instanceof Long){
            isPremitive = true;
        } else if(fieldValue instanceof Short){
            isPremitive = true;
        } else if(fieldValue instanceof Double){
            isPremitive = true;
        } else if(fieldValue instanceof Float){
            isPremitive = true;
        } else if(fieldValue instanceof Byte){
            isPremitive = true;
        } else if(fieldValue instanceof Character){
            isPremitive = true;
        } else if(fieldValue instanceof String){
            isPremitive = true;
        } else if(fieldValue instanceof BigDecimal){
            isPremitive = true;
        } else if(fieldValue instanceof BigInteger){
            isPremitive = true;
        } else {
            //
        }
        //
        return isPremitive;
    }

    /**
     * 将 Map 转换为特定类的对象
     * @param map
     * @param clazz
     * @return
     */
    public static Object map2Object(Map<String, Object> map, Class<?> clazz){
        Object obj = null;
        if(null == map){
            return obj;
        }

        //
        return obj;
    }

    /**
     *
     * @param object
     * @return
     */
    public static List<String> getFieldList(Object object){
        List<String> fieldList = new ArrayList<String>();
        if(null == object){
            return fieldList;
        }
        //
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        //
        for (Field field: fields) {
            String fieldName = field.getName();
            //
            fieldList.add(fieldName);
        }
        return fieldList;
    }
}
