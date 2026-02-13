package com.my.televip.obfuscate;



import com.my.televip.ClientChecker;
import com.my.televip.Clients.Teegra;
import com.my.televip.Clients.Telegraph;
import com.my.televip.LoaderParameter;
import com.my.televip.Utils;
import com.my.televip.Clients.Cherrygram;
import com.my.televip.Clients.Nagram;
import com.my.televip.Clients.NagramX;
import com.my.televip.Clients.Nicegram;
import com.my.televip.Clients.TGConnect;
import com.my.televip.Clients.Telegram;
import com.my.televip.Clients.TelegramBeta;
import com.my.televip.Clients.TelegramPlus;
import com.my.televip.Clients.TelegramWeb;
import com.my.televip.Clients.XPlus;
import com.my.televip.Clients.forkgram;
import com.my.televip.Clients.forkgramBeta;
import com.my.televip.Clients.iMe;
import com.my.televip.Clients.iMeWeb;

import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class AutomationResolver {
    public static Map<String, LoaderParameter> Loaders = new HashMap<>();

    public static String resolve(String className, String pkgName)
    {
         if (ClientChecker.check(ClientChecker.ClientType.Telegram, pkgName))
        {
            if (Telegram.ClassResolver.has(className))
                return Telegram.ClassResolver.resolve(className);
        } else if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, pkgName))
         {
             if (TelegramWeb.ClassResolver.has(className))
                 return TelegramWeb.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.TelegramPlus, pkgName))
         {
             if (TelegramPlus.ClassResolver.has(className))
                 return TelegramPlus.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.TGConnect, pkgName))
         {
             if (TGConnect.ClassResolver.has(className))
                 return TGConnect.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.Nagram, pkgName))
         {
             if (Nagram.ClassResolver.has(className))
                 return Nagram.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.Nicegram, pkgName))
         {
             if (Nicegram.ClassResolver.has(className))
                 return Nicegram.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.Cherrygram, pkgName))
         {
             if (Cherrygram.ClassResolver.has(className))
                 return Cherrygram.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.TelegramBeta, pkgName))
         {
             if (TelegramBeta.ClassResolver.has(className))
                 return TelegramBeta.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.NagramX, pkgName))
         {
             if (NagramX.ClassResolver.has(className))
                 return NagramX.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.XPlus, pkgName))
         {
             if (XPlus.ClassResolver.has(className))
                 return XPlus.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.iMe, pkgName))
         {
             if (iMe.ClassResolver.has(className))
                 return iMe.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.iMeWeb, pkgName))
         {
             if (iMeWeb.ClassResolver.has(className))
                 return iMeWeb.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.forkgram, pkgName))
         {
             if (forkgram.ClassResolver.has(className))
                 return forkgram.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.forkgramBeta, pkgName))
         {
             if (forkgramBeta.ClassResolver.has(className))
                 return forkgramBeta.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.Teegra, pkgName)) {
             if (Teegra.ClassResolver.has(className))
                 return Teegra.ClassResolver.resolve(className);
         } else if (ClientChecker.check(ClientChecker.ClientType.Telegraph, pkgName))
         {
             if (Telegraph.ClassResolver.has(className))
                 return Telegraph.ClassResolver.resolve(className);
         }

        return className;
    }

    public static Class<?>[] resolveObject(String name, String pkgName)
    {
        loadParameter(name);
       if (ClientChecker.check(ClientChecker.ClientType.Telegram, pkgName))
        {
            if (Telegram.ParameterResolver.has(name)) {
                return Telegram.ParameterResolver.resolve(name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, pkgName))
       {
           if (TelegramWeb.ParameterResolver.has(name)) {
               return TelegramWeb.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.TelegramPlus, pkgName))
       {
           if (TelegramPlus.ParameterResolver.has(name)) {
               return TelegramPlus.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.TGConnect, pkgName))
       {
           if (TGConnect.ParameterResolver.has(name)) {
               return TGConnect.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.Nagram, pkgName))
       {
           if (Nagram.ParameterResolver.has(name)) {
               return Nagram.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.Nicegram, pkgName))
       {
           if (Nicegram.ParameterResolver.has(name)) {
               return Nicegram.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.Cherrygram, pkgName))
       {
           if (Cherrygram.ParameterResolver.has(name)) {
               return Cherrygram.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.TelegramBeta, pkgName))
       {
           if (TelegramBeta.ParameterResolver.has(name)) {
               return TelegramBeta.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.NagramX, pkgName))
       {
           if (NagramX.ParameterResolver.has(name)) {
               return NagramX.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.XPlus, pkgName))
       {
           if (XPlus.ParameterResolver.has(name)) {
               return XPlus.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.iMe, pkgName))
       {
           if (iMe.ParameterResolver.has(name)) {
               return iMe.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.iMeWeb, pkgName))
       {
           if (iMeWeb.ParameterResolver.has(name)) {
               return iMeWeb.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.forkgram, pkgName))
       {
           if (forkgram.ParameterResolver.has(name)) {
               return forkgram.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.forkgramBeta, pkgName))
       {
           if (forkgramBeta.ParameterResolver.has(name)) {
               return forkgramBeta.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.Teegra, pkgName))
       {
           if (Teegra.ParameterResolver.has(name)) {
               return Teegra.ParameterResolver.resolve(name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.Telegraph, pkgName))
       {
           if (Telegraph.ParameterResolver.has(name)) {
               return Telegraph.ParameterResolver.resolve(name);
           }
       }
        return null;
    }

    public static String resolve(String className, String name, ResolverType type, String pkgName)
    {
        if (ClientChecker.check(ClientChecker.ClientType.Telegram, pkgName))
    {
        if (type == ResolverType.Field)
        {
            if (Telegram.FieldResolver.has(className, name))
                return Telegram.FieldResolver.resolve(className, name);
        }
        else if (type == ResolverType.Method)
        {
            if (Telegram.MethodResolver.has(className, name))
                return Telegram.MethodResolver.resolve(className, name);
        }
    } else if (ClientChecker.check(ClientChecker.ClientType.TelegramWeb, pkgName))
       {
           if (type == ResolverType.Field)
           {
               if (TelegramWeb.FieldResolver.has(className, name))
                   return TelegramWeb.FieldResolver.resolve(className, name);
           }
           else if (type == ResolverType.Method)
           {
               if (TelegramWeb.MethodResolver.has(className, name))
                   return TelegramWeb.MethodResolver.resolve(className, name);
           }
       } else if (ClientChecker.check(ClientChecker.ClientType.TelegramPlus, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (TelegramPlus.FieldResolver.has(className, name))
                    return TelegramPlus.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (TelegramPlus.MethodResolver.has(className, name))
                    return TelegramPlus.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.TGConnect, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (TGConnect.FieldResolver.has(className, name))
                    return TGConnect.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (TGConnect.MethodResolver.has(className, name))
                    return TGConnect.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.Nagram, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (Nagram.FieldResolver.has(className, name))
                    return Nagram.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (Nagram.MethodResolver.has(className, name))
                    return Nagram.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.Nicegram, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (Nicegram.FieldResolver.has(className, name))
                    return Nicegram.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (Nicegram.MethodResolver.has(className, name))
                    return Nicegram.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.Cherrygram, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (Cherrygram.FieldResolver.has(className, name))
                    return Cherrygram.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (Cherrygram.MethodResolver.has(className, name))
                    return Cherrygram.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.TelegramBeta, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (TelegramBeta.FieldResolver.has(className, name))
                    return TelegramBeta.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (TelegramBeta.MethodResolver.has(className, name))
                    return TelegramBeta.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.NagramX, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (NagramX.FieldResolver.has(className, name))
                    return NagramX.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (NagramX.MethodResolver.has(className, name))
                    return NagramX.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.XPlus, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (XPlus.FieldResolver.has(className, name))
                    return XPlus.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (XPlus.MethodResolver.has(className, name))
                    return XPlus.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.iMe, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (iMe.FieldResolver.has(className, name))
                    return iMe.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (iMe.MethodResolver.has(className, name))
                    return iMe.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.iMeWeb, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (iMeWeb.FieldResolver.has(className, name))
                    return iMeWeb.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (iMeWeb.MethodResolver.has(className, name))
                    return iMeWeb.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.forkgram, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (forkgram.FieldResolver.has(className, name))
                    return forkgram.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (forkgram.MethodResolver.has(className, name))
                    return forkgram.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.forkgramBeta, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (forkgramBeta.FieldResolver.has(className, name))
                    return forkgramBeta.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (forkgramBeta.MethodResolver.has(className, name))
                    return forkgramBeta.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.Teegra, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (Teegra.FieldResolver.has(className, name))
                    return Teegra.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (Teegra.MethodResolver.has(className, name))
                    return Teegra.MethodResolver.resolve(className, name);
            }
        } else if (ClientChecker.check(ClientChecker.ClientType.Telegraph, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (Telegraph.FieldResolver.has(className, name))
                    return Telegraph.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (Telegraph.MethodResolver.has(className, name))
                    return Telegraph.MethodResolver.resolve(className, name);
            }
        }

        name = name.replace("storyEntitiesAllowed2", "storyEntitiesAllowed");
        name = name.replace("hasStories2", "hasStories");
        return name;
    }

    public static String resolve(String className)
    {
        return resolve(className, Utils.pkgName);
    }
    public static Class<?>[] resolveObject(String name)
    {
        return resolveObject(name, Utils.pkgName);
    }

    public static String resolve(String className, String name, ResolverType type)
    {
        return resolve(className, name, type, Utils.pkgName);
    }
    public static Object[] merge(Class<?>[] classes, XC_MethodHook hook)
    {
        if (classes != null) {
            Object[] result = new Object[classes.length + 1];
            System.arraycopy(classes, 0, result, 0, classes.length);
            result[classes.length] = hook;
            return result;
        }
        return null;
    }

public static void loadParameter(String name){
        if (Loaders.isEmpty()) {
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.Telegram), new Telegram.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.TelegramWeb), new TelegramWeb.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.TelegramPlus), new TelegramPlus.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.TGConnect), new TGConnect.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.Nagram), new Nagram.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.Nicegram), new Nicegram.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.Cherrygram), new Cherrygram.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.TelegramBeta), new TelegramBeta.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.NagramX), new NagramX.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.XPlus), new XPlus.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.iMe), new iMe.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.iMeWeb), new iMeWeb.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.forkgram), new forkgram.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.forkgramBeta), new forkgramBeta.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.Teegra), new Teegra.loadParameter());
            Loaders.put(ClientChecker.getClientType(ClientChecker.ClientType.Telegraph), new Telegraph.loadParameter());
        }
        if (!Loaders.isEmpty()) {
            LoaderParameter loader = Loaders.get(Utils.pkgName);
            if (loader != null) {
                switch (name) {
                    case "1":
                        loader.loadParameter1();
                        break;
                    case "2":
                        loader.loadParameter2();
                        break;
                    case "3":
                        loader.loadParameter3();
                        break;
                    case "4":
                        loader.loadParameter4();
                        break;
                    case "5":
                        loader.loadParameter5();
                        break;
                    case "6":
                        loader.loadParameter6();
                        break;
                    case "7":
                        loader.loadParameter7();
                        break;
                    case "8":
                        loader.loadParameter8();
                        break;
                    case "9":
                        loader.loadParameter9();
                        break;
                    case "10":
                        loader.loadParameter10();
                        break;
                    case "11":
                        loader.loadParameter11();
                        break;
                    case "12":
                        loader.loadParameter12();
                        break;
                }
        }
        }
}
    public enum ResolverType
    {
        Field,
        Method
    }
}
