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
        Telegram("org.telegram.messenger", com.my.televip.Clients.Telegram.class),
        TelegramWeb("org.telegram.messenger.web", com.my.televip.Clients.TelegramWeb.class),
        TelegramPlus("org.telegram.plus", com.my.televip.Clients.TelegramPlus.class),
        TGConnect("com.tgconnect.android", com.my.televip.Clients.TGConnect.class),
        Nagram("xyz.nextalone.nagram", com.my.televip.Clients.Nagram.class),
        Nicegram("app.nicegram", com.my.televip.Clients.Nicegram.class),
        Cherrygram("uz.unnarsx.cherrygram", com.my.televip.Clients.Cherrygram.class),
        TelegramBeta("org.telegram.messenger.beta", com.my.televip.Clients.TelegramBeta.class),
        NagramX("nu.gpu.nagram", com.my.televip.Clients.NagramX.class),
        XPlus("com.xplus.messenger", com.my.televip.Clients.XPlus.class),
        iMe("com.iMe.android", com.my.televip.Clients.iMe.class),
        iMeWeb("com.iMe.android.web", com.my.televip.Clients.iMeWeb.class),
        forkgram("org.forkgram.messenger", com.my.televip.Clients.forkgram.class),
        forkgramBeta("org.forkclient.messenger.beta", com.my.televip.Clients.forkgramBeta.class),
        Teegra("org.open.telegram.market", com.my.televip.Clients.Teegra.class),
        Telegraph("ir.ilmili.telegraph", com.my.televip.Clients.Telegraph.class),
        Telega("ru.dahl.messenger", com.my.televip.Clients.Telega.class);

        private final String[] packageNames;
        private final Class<?> resolverClass;

        ClientType(String packageName, Class<?> resolverClass) {
            this.packageNames = new String[]{packageName};
            this.resolverClass = resolverClass;
        }

        public String[] getPackageNames() { return packageNames; }
        public Class<?> getResolverClass() { return resolverClass; }

        public static ClientType fromPackage(String pkg){
            for (ClientType type: ClientType.values()){
                for (String name: type.getPackageNames()){
                    if (name.equals(pkg)) return type;
                }
            }
            return null;
        }

        public static boolean containsPackage(String pkg){
            return fromPackage(pkg) != null;
        }
    }
}
