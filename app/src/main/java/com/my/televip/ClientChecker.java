package com.my.televip;

import java.util.Arrays;

public class ClientChecker {
    public static boolean check(ClientType client, String pkgName)
    {
        return Arrays.asList(client.getPackageNames()).contains(pkgName);
    }

    public static boolean check(ClientType client)
    {
        return check(client, Utils.pkgName);
    }
    public static String getClientType(ClientType client){
        String pkg = String.valueOf(Arrays.asList(client.getPackageNames()));
        pkg = pkg.replace("[","").replace("]","").trim();
        return pkg;
    }

    public enum ClientType {
        Telegram("org.telegram.messenger"),
        TelegramWeb("org.telegram.messenger.web"),
        TelegramPlus("org.telegram.plus"),
        TGConnect("com.tgconnect.android"),
        Nagram("xyz.nextalone.nagram"),
        Nekogram("org.telegr]"),
        Nicegram("app.nicegram"),
        Cherrygram("uz.unnarsx.cherrygram"),
        TelegramBeta("org.telegram.messenger.beta"),
        NagramX("nu.gpu.nagram"),
        XPlus("com.xplus.messenger"),
        iMe("com.iMe.android"),
        iMeWeb("com.iMe.android.web"),
        forkgram("org.forkgram.messenger"),
        forkgramBeta("org.forkclient.messenger.beta"),
        Teegra("org.open.telegram.market"),
        Telegraph("ir.ilmili.telegraph");

        final String[] packageNames;

        ClientType(String packageName)
        {
            this.packageNames = new String[]{ packageName };
        }

        public String[] getPackageNames()
        {
            return packageNames;
        }

        public static ClientType fromPackage(String pkg){
            for (ClientType type: ClientType.values()){
                for (String name: type.getPackageNames()){
                    if (name.equals(pkg)){
                        return type;
                    }
                }
            }
            return null;
        }
        public static boolean containsPackage(String pkg){
            return fromPackage(pkg) != null;
        }
    }
}
