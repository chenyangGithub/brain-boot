package utils;

import base.ResponseEnum;
import exception.BaseBizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class CheckParamsUtil {
    public static void main(String[] args) throws BaseBizException {
        Student zhangsan = new Student("zhangsan", null);
        checkNoNecessary(zhangsan, Student.class, "age");
        //checkAll(zhangsan, Student.class);
        //checkNecessary(zhangsan, Student.class, "name");
    }
    static void checkNecessary(Object o, Class<?> cz, String... args) throws BaseBizException {
        Field[] fields = cz.getDeclaredFields();
        if(o==null){
            throw new BaseBizException(ResponseEnum.PARAM_NOT_NULL.getCode(),ResponseEnum.PARAM_NOT_NULL.getMsg());
        }
        Set<Field> fieldSet= new HashSet<>();//需要判空的参数
        for(String attribute : args){
            for (Field field : fields){
                field.setAccessible(true);
                if(attribute.equals(field.getName())){
                    fieldSet.add(field);
                }
            }
        }
        checkFields(fieldSet, o);//检查参数
    }

    static void checkAll(Object o, Class<?> cz) throws BaseBizException {
        if(o == null){
            throw new BaseBizException(ResponseEnum.PARAM_NOT_NULL.getCode(),
                    ResponseEnum.PARAM_NOT_NULL.getMsg());
        }
        Field[] fields = cz.getDeclaredFields();
        Set<Field> fieldSet = new HashSet<>(Arrays.asList(fields));
        checkFields(fieldSet, o);

    }

    static void checkNoNecessary(Object o, Class<?> cz, String... args) throws BaseBizException {
        Field[] fields = cz.getDeclaredFields();
        if(o==null){
            throw new BaseBizException(ResponseEnum.PARAM_NOT_NULL.getCode(),ResponseEnum.PARAM_NOT_NULL.getMsg());
        }
        Set<Field> fieldSet = new HashSet<>();
        for(String attribute : args){
            for(Field field : fields){
                if(attribute.equals(field.getName())) fieldSet.add(field);
            }
        }
        checkFields(fieldSet, o);//检查参数

    }

    private static void checkFields(Set<Field> fieldList, Object o) throws BaseBizException {
        for(Field field : fieldList){
            field.setAccessible(true);
            String type = "";
            Object value = null;
            String attribute = field.getName();
            try {
                type = field.getType().getName();
                value = field.get(o);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            if("java.lang.String".equals(type)){
                if(StringUtils.isEmpty(value.toString()) || StringUtils.isBlank(value.toString())){
                    throw new BaseBizException(ResponseEnum.PARAM_NOT_NULL.getCode(),
                            attribute + "不能为空");
                }
            }else{
                if(value == null){
                    throw  new BaseBizException(ResponseEnum.PARAM_NOT_NULL.getCode(),
                            attribute + "不能为空");
                }
            }
        }

    }
}
