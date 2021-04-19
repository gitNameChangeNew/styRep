package com.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: String
 * @Description：自定义类型转换器 ，spring中支持的日期格式为 2020/11/11  不支持2020-11-11 因此需要自己设置转换器
 * @author: Administrator
 * @Date 2021/4/7 12:09
 * @Version 1.0
 **/
public class StringToDateConverter implements Converter<String, Date> {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date convert(String s) {

        if(StringUtils.isEmpty(s)){
            throw new RuntimeException("source is not  must null");
        }
        Date date = null;
        try {
             date = simpleDateFormat.parse(s);
        } catch (Exception e) {
            throw new RuntimeException("source type  Parse exception");
        }
        return date;
    }
}
