package com.utils.property;

import org.quartz.SchedulerConfigException;
import org.quartz.utils.PoolingConnectionProvider;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Locale;
import java.util.Properties;

// TODO 参考 quartz源码中的StdSchedulerFactory
public class ClassUtil {
    public Class<?> loadClass(String className) throws ClassNotFoundException, SchedulerConfigException {

        try {
            ClassLoader cl = findClassloader();
            if(cl != null)
                return cl.loadClass(className);
            throw new SchedulerConfigException("Unable to find a class loader on the current thread or class.");
        } catch (ClassNotFoundException e) {
            if(getClass().getClassLoader() != null)
                return getClass().getClassLoader().loadClass(className);
            throw e;
        }
    }

    public ClassLoader findClassloader() {
        // work-around set context loader for windows-service started jvms (QUARTZ-748)
        if(Thread.currentThread().getContextClassLoader() == null && getClass().getClassLoader() != null) {
            Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        }
        return Thread.currentThread().getContextClassLoader();
    }
    public void setBeanProps(Object obj, Properties props)
            throws NoSuchMethodException, IllegalAccessException,
            java.lang.reflect.InvocationTargetException,
            IntrospectionException, SchedulerConfigException {
        props.remove("class");
        props.remove(PoolingConnectionProvider.POOLING_PROVIDER);

        BeanInfo bi = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propDescs = bi.getPropertyDescriptors();
        PropertiesParser pp = new PropertiesParser(props);

        java.util.Enumeration<Object> keys = props.keys();
        while (keys.hasMoreElements()) {
            String name = (String) keys.nextElement();
            String c = name.substring(0, 1).toUpperCase(Locale.US);
            String methName = "set" + c + name.substring(1);

            java.lang.reflect.Method setMeth = getSetMethod(methName, propDescs);

            try {
                if (setMeth == null) {
                    throw new NoSuchMethodException(
                            "No setter for property '" + name + "'");
                }

                Class<?>[] params = setMeth.getParameterTypes();
                if (params.length != 1) {
                    throw new NoSuchMethodException(
                            "No 1-argument setter for property '" + name + "'");
                }

                // does the property value reference another property's value? If so, swap to look at its value
                PropertiesParser refProps = pp;
                String refName = pp.getStringProperty(name);
                if(refName != null && refName.startsWith("$@")) {
                    refName =  refName.substring(2);
                    //refProps = cfg;
                }
                else
                    refName = name;

                if (params[0].equals(int.class)) {
                    setMeth.invoke(obj, new Object[]{Integer.valueOf(refProps.getIntProperty(refName))});
                } else if (params[0].equals(long.class)) {
                    setMeth.invoke(obj, new Object[]{Long.valueOf(refProps.getLongProperty(refName))});
                } else if (params[0].equals(float.class)) {
                    setMeth.invoke(obj, new Object[]{Float.valueOf(refProps.getFloatProperty(refName))});
                } else if (params[0].equals(double.class)) {
                    setMeth.invoke(obj, new Object[]{Double.valueOf(refProps.getDoubleProperty(refName))});
                } else if (params[0].equals(boolean.class)) {
                    setMeth.invoke(obj, new Object[]{Boolean.valueOf(refProps.getBooleanProperty(refName))});
                } else if (params[0].equals(String.class)) {
                    setMeth.invoke(obj, new Object[]{refProps.getStringProperty(refName)});
                } else {
                    throw new NoSuchMethodException(
                            "No primitive-type setter for property '" + name
                                    + "'");
                }
            } catch (NumberFormatException nfe) {
                throw new SchedulerConfigException("Could not parse property '"
                        + name + "' into correct data type: " + nfe.toString());
            }
        }
    }
    public java.lang.reflect.Method getSetMethod(String name,
                                                  PropertyDescriptor[] props) {
        for (int i = 0; i < props.length; i++) {
            java.lang.reflect.Method wMeth = props[i].getWriteMethod();

            if (wMeth != null && wMeth.getName().equals(name)) {
                return wMeth;
            }
        }

        return null;
    }
}
