package com.my.televip;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.my.televip.logging.Logger;
import com.my.televip.utils.Utils;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class ClientChecker {
    public static boolean check(ClientType client, String pkgName)
    {
        return Arrays.asList(client.getPackageNames()).contains(pkgName);
    }

    public static boolean check(ClientType client)
    {
        return check(client, Utils.pkgName);
    }

    public static boolean isTgnetObfuscated()
    {
        return ClientType.fromPackage(Utils.pkgName).isTgnetObfuscated();
    }

    /**
     * Client build each resolver table was generated against.
     *
     * <p>The obfuscated clients (Nekogram, Cherrygram) map every class, field and method by its
     * R8 name, and those names change on every client release. When the installed build differs
     * from the one below, the module used to fail silently with a stream of "Not found ..." lines;
     * {@link #checkClientVersion} now says so once, up front.</p>
     */
    private static final Map<ClientType, String> VERIFIED_BUILD = new EnumMap<>(ClientType.class);

    static {
        VERIFIED_BUILD.put(ClientType.Telegram, "12.8.3 (69222)");
        VERIFIED_BUILD.put(ClientType.TelegramBeta, "12.9.0 (69579)");
        VERIFIED_BUILD.put(ClientType.TelegramWeb, "12.8.3 (69229)");
        VERIFIED_BUILD.put(ClientType.TelegramPlus, "12.8.1.0 (22350)");
        VERIFIED_BUILD.put(ClientType.TGConnect, "11.13.1 (11130109)");
        VERIFIED_BUILD.put(ClientType.Nagram, "12.8.1 (1239)");
        VERIFIED_BUILD.put(ClientType.NagramX, "12.8.1-2bcd1bd (1253)");
        VERIFIED_BUILD.put(ClientType.NagramXF, "12.7.3 (1245)");
        VERIFIED_BUILD.put(ClientType.Nekogram, "12.8.1 (69160)");
        VERIFIED_BUILD.put(ClientType.Cherrygram, "12.8.1 (69160)");
        VERIFIED_BUILD.put(ClientType.Nicegram, "1.55.0 (2139)");
        VERIFIED_BUILD.put(ClientType.iMe, "12.8.1 (12080102)");
        VERIFIED_BUILD.put(ClientType.iMeWeb, "12.8.1 (12080109)");
        VERIFIED_BUILD.put(ClientType.XPlus, "12.0.1 (61669)");
        VERIFIED_BUILD.put(ClientType.forkgram, "12.8.4.0 (691908)");
        VERIFIED_BUILD.put(ClientType.forkgramBeta, "12.8.4.0 (691909)");
        VERIFIED_BUILD.put(ClientType.ForkgramClassic, "12.8.10.0");
        VERIFIED_BUILD.put(ClientType.Telegraph, "12.8.1.1 (69172)");
        VERIFIED_BUILD.put(ClientType.Telega, "2.4.3 (107)");
        VERIFIED_BUILD.put(ClientType.Momogram, "12.6.4");
        VERIFIED_BUILD.put(ClientType.Turrit, "1.8.9.9.5");
    }

    public static String verifiedBuild(ClientType client) {
        return client == null ? null : VERIFIED_BUILD.get(client);
    }

    /**
     * Logs the running client build, and warns when it is not the one the resolver tables were
     * generated from. Obfuscated clients get a louder warning because every hook depends on the
     * mapping table matching that exact build.
     */
    public static void checkClientVersion(android.content.Context context) {
        try {
            if (context == null) return;
            ClientType client = ClientType.fromPackage(Utils.pkgName);
            if (client == null) return;

            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            String running = info.versionName + " (" + info.versionCode + ")";
            String verified = VERIFIED_BUILD.get(client);

            if (verified == null) {
                Logger.l("client " + client.name() + " " + running + " (no verified build on record)");
                return;
            }
            if (verified.equals(running)) {
                Logger.l("client " + client.name() + " " + running + " matches the verified build");
                return;
            }
            if (client.isTgnetObfuscated()) {
                Logger.w("client " + client.name() + " is " + running + " but the obfuscation map was"
                        + " built for " + verified + ". Renamed symbols will not resolve and most"
                        + " features will be inactive. " + Utils.issue);
            } else {
                Logger.w("client " + client.name() + " is " + running + ", verified build is "
                        + verified + ". Some hooks may not apply.");
            }
        } catch (Throwable ignored) {
        }
    }

    public enum ClientType {
        Telegram("org.telegram.messenger", com.my.televip.Clients.Telegram.class),
        TelegramWeb("org.telegram.messenger.web", com.my.televip.Clients.TelegramWeb.class),
        TelegramPlus("org.telegram.plus", com.my.televip.Clients.TelegramPlus.class),
        TGConnect("com.tgconnect.android", com.my.televip.Clients.TGConnect.class),
        Nagram("xyz.nextalone.nagram", com.my.televip.Clients.Nagram.class),
        Nicegram("app.nicegram", com.my.televip.Clients.Nicegram.class),
        TelegramBeta("org.telegram.messenger.beta", com.my.televip.Clients.TelegramBeta.class),
        NagramX("nu.gpu.nagram", com.my.televip.Clients.NagramX.class),
        XPlus("com.xplus.messenger", com.my.televip.Clients.XPlus.class),
        iMe("com.iMe.android", com.my.televip.Clients.iMe.class),
        iMeWeb("com.iMe.android.web", com.my.televip.Clients.iMeWeb.class),
        forkgram("org.forkgram.messenger", com.my.televip.Clients.forkgram.class),
        forkgramBeta("org.forkclient.messenger.beta", com.my.televip.Clients.forkgramBeta.class),
        Telegraph("ir.ilmili.telegraph", com.my.televip.Clients.Telegraph.class),
        Telega("ru.dahl.messenger", com.my.televip.Clients.Telega.class),
        Momogram(new String[]{"nekox.messenger.broken", "momo.gram"}, com.my.televip.Clients.Momogram.class),
        Nekogram("tw.nekomimi.nekogram", com.my.televip.Clients.Nekogram.class, true),
        NekogramX("nekox.messenger", com.my.televip.Clients.NekogramX.class),
        Cherrygram("uz.unnarsx.cherrygram", com.my.televip.Clients.Cherrygram.class, true),
        ForkgramClassic("org.forkgram.classic", com.my.televip.Clients.ForkgramClassic.class),
        Turrit("org.telegram.group", com.my.televip.Clients.Turrit.class),
        NagramXF("fork.risin42.nagramx", com.my.televip.Clients.NagramXF.class);

        private final String[] packageNames;
        private final Class<?> resolverClass;
        private final boolean tgnetObfuscated;

        ClientType(String packageName, Class<?> resolverClass) {
            this.packageNames = new String[]{packageName};
            this.resolverClass = resolverClass;
            tgnetObfuscated = false;
        }

        ClientType(String packageName, Class<?> resolverClass, boolean tgnetObfuscated) {
            this.packageNames = new String[]{packageName};
            this.resolverClass = resolverClass;
            this.tgnetObfuscated = tgnetObfuscated;
        }

        ClientType(String[] packageNames, Class<?> resolverClass) {
            this.packageNames = packageNames;
            this.resolverClass = resolverClass;
            tgnetObfuscated = false;
        }

        public String[] getPackageNames() { return packageNames; }
        public Class<?> getResolverClass() { return resolverClass; }
        public boolean isTgnetObfuscated() { return tgnetObfuscated; }

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
