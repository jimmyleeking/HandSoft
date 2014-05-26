package com.handos.util;

import com.handos.annotation.EasyThreadMethod;
import com.handos.exception.EasyThreadException;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jimmylee on 5/10/14.
 */
public class EasyThread {

    private static Map<String,Map<String,Method>> cacheMethod=new HashMap<String, Map<String, Method>>();

    /**
     *
     * @param object
     * @param methodName
     * @param callback
     * @throws com.handos.exception.EasyThreadException
     */
    public static  void runInBackground(Object object, String methodName, ICallBack callback) throws EasyThreadException {
        Class<?> targetClass=object.getClass();
        if(targetClass!=null)
        {
            Map<String,Method> map=cacheMethod.get(targetClass.getName());
            if(map==null)
            {
                map=new HashMap<String, Method>();
                Method[] methods=targetClass.getMethods();
                for(Method method :methods)
                {
                    if(method.isAnnotationPresent(EasyThreadMethod.class)){
                        EasyThreadMethod annotation= method.getAnnotation(EasyThreadMethod.class);
                        if(annotation!=null)
                        {
                            map.put(annotation.methodName(),method);
                        }
                    }
                }
                cacheMethod.put(targetClass.getName(),map);
            }
            Method method=map.get(methodName);
            if(method==null)
            {
                throw new EasyThreadException("target Method not found");
            }
            if(method.getParameterTypes().length>0)
            {
                throw new EasyThreadException("The target method must haven't parameter");
            }
            Runnable r=new InnerRunnable(object,method,callback);
            new Thread(r).start();
        }
    }

    /**
     *
     * @param object
     * @param methodName
     * @throws EasyThreadException
     */
    public static  void runInBackground(Object object, String methodName) throws EasyThreadException {
        runInBackground(object, methodName, null);
    }






}
