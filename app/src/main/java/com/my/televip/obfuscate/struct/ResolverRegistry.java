package com.my.televip.obfuscate.struct;

import com.my.televip.ClientChecker;
import com.my.televip.Utils;

public class ResolverRegistry {

    Class<?> finalField;
    Class<?> finalMethod;
    Class<?> finalParameter;
    Class<?> finalClass;
    Class<?> clazz;

    public ResolverRegistry(Class<?> clazz){
        this.clazz = clazz;
        try {
            Class<?> fieldResolverClass = null;
            Class<?> methodResolverClass = null;
            Class<?> parameterResolverClass = null;
            Class<?> classResolverClass = null;
            for (Class<?> inner : clazz.getDeclaredClasses()) {
                if (inner.getSimpleName().equals("FieldResolver")) fieldResolverClass = inner;
                if (inner.getSimpleName().equals("MethodResolver")) methodResolverClass = inner;
                if (inner.getSimpleName().equals("ParameterResolver")) parameterResolverClass = inner;
                if (inner.getSimpleName().equals("ClassResolver")) classResolverClass = inner;
            }

            finalField = fieldResolverClass;
            finalMethod = methodResolverClass;
            finalParameter = parameterResolverClass;
            finalClass = classResolverClass;

        } catch (Throwable ignored) {}
    }

    public boolean hasClass(String className){
        try {
            return (boolean)finalClass.getMethod("has", String.class).invoke(null, className);
        } catch (Throwable e){
            return false;
        }
    }

    public String resolveClass(String className){
        try {
            return (String) finalClass.getMethod("resolve", String.class).invoke(null, className);
        } catch (Throwable e){
            return null;
        }
    }

    public boolean hasField(String className, String name){
        try {
            return (boolean)finalField.getMethod("has", String.class,String.class).invoke(null, className ,name);
        } catch (Throwable e){
            return false;
        }
    }

    public String resolveField(String className, String name){
        try {
            return (String) finalField.getMethod("resolve", String.class,String.class).invoke(null, className ,name);
        } catch (Throwable e){
            return null;
        }
    }

    public boolean hasMethod(String className, String name){
        try {
            return (boolean)finalMethod.getMethod("has", String.class,String.class).invoke(null, className ,name);
        } catch (Throwable e){
            return false;
        }
    }

    public String resolveMethod(String className, String name){
        try {
            return (String) finalMethod.getMethod("resolve", String.class,String.class).invoke(null, className ,name);
        } catch (Throwable e){
            return null;
        }
    }

    public boolean hasParameter(String name){
        try {
            return (boolean)finalParameter.getMethod("has", String.class).invoke(null, name);
        } catch (Throwable e){
            return false;
        }
    }

    public Class<?>[] resolveParameter(String name){
        try {
            return (Class<?>[]) finalParameter.getMethod("resolve", String.class).invoke(null, name);
        } catch (Throwable e){
            return null;
        }
    }

    public void loadParameter(){
        try {
            clazz.getMethod("loadParameter").invoke(null);
        } catch (Throwable ignored){}
    }

    public static Class<?> getResolverClass() {
        ClientChecker.ClientType clientType = ClientChecker.ClientType.fromPackage(Utils.pkgName);
        if (clientType == null) return null;
        return clientType.getResolverClass();
    }
}