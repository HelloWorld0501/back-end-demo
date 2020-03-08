package com.daydream.demo_common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/9/13
 * \* Time: 6:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 反射相关类
 * \
 */
public class ReflectionUtils {
    public static Object invoke(Object object, String method, Object... args) {
        Object result = null;
        //返回运行时类
        Class<? extends Object> c = object.getClass();
        Method queryMethod = getMethod(c, method, args);
        if (queryMethod != null) {
            try {
                result = queryMethod.invoke(object, args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new NoSuchMethodException("在 " + c.getName() + " 类中没有找到" + method + " 方法");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Method getMethod(Class<? extends Object> c, String name, Object[] args) {
        Method queryMethod = null;
        //返回此类的方法,包括从超类继承的和实现的接口,不包括私有方法
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                //Method对象表示的方法的声明的形式参数类型(按顺序拍列)
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == args.length) {
                    boolean isSameMethod = true;
                    for (int i = 0; i < parameterTypes.length; i++) {
                        Object arg = args[i];
                        if (arg == null) {
                            arg = "";
                        }
                        if (!parameterTypes[i].equals(args[i].getClass())) {
                            isSameMethod = true;
                        }
                    }
                    if (isSameMethod) {
                        queryMethod = method;
                        break;
                    }
                }
            }
        }
        return queryMethod;
    }
}