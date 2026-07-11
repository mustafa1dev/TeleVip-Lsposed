package com.my.televip.utils;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class IdDateEstimator {

    public static class AgeResult {
        public final int status;
        public final long timestampMs;

        public AgeResult(int status, long timestampMs) {
            this.status = status;
            this.timestampMs = timestampMs;
        }
    }
    private static final Map<Long, Long> AGES = new LinkedHashMap<>();

    static {
        put(2768409L, 1383264000000L);
        put(7679610L, 1388448000000L);
        put(11538514L, 1391212000000L);
        put(15835244L, 1392940000000L);
        put(23646077L, 1393459000000L);
        put(38015510L, 1393632000000L);
        put(44634663L, 1399334000000L);
        put(46145305L, 1400198000000L);
        put(54845238L, 1411257000000L);
        put(63263518L, 1414454000000L);
        put(101260938L, 1425600000000L);
        put(101323197L, 1426204000000L);
        put(111220210L, 1429574000000L);
        put(103258382L, 1432771000000L);
        put(103151531L, 1433376000000L);
        put(116812045L, 1437696000000L);
        put(122600695L, 1437782000000L);
        put(109393468L, 1439078000000L);
        put(112594714L, 1439683000000L);
        put(124872445L, 1439856000000L);
        put(130029930L, 1441324000000L);
        put(125828524L, 1444003000000L);
        put(133909606L, 1444176000000L);
        put(157242073L, 1446768000000L);
        put(143445125L, 1448928000000L);
        put(148670295L, 1452211000000L);
        put(152079341L, 1453420000000L);
        put(171295414L, 1457481000000L);
        put(181783990L, 1460246000000L);
        put(222021233L, 1465344000000L);
        put(225034354L, 1466208000000L);
        put(278941742L, 1473465000000L);
        put(285253072L, 1476835000000L);
        put(294851037L, 1479600000000L);
        put(297621225L, 1481846000000L);
        put(328594461L, 1482969000000L);
        put(337808429L, 1487707000000L);
        put(341546272L, 1487782000000L);
        put(352940995L, 1487894000000L);
        put(369669043L, 1490918000000L);
        put(400169472L, 1501459000000L);
        put(805158066L, 1563208000000L);
        put(1974255900L, 1634000000000L);
        put(5795034000L, 1662076800000L);
        put(6227468000L, 1679270400000L);
        put(7583599300L, 1739664000000L);
        put(7947063900L, 1754092800000L);
        put(8235679900L, 1758758400000L);
    }

    private static void put(long id, long ts) {
        AGES.put(id, ts);
    }

    private static final List<Long> IDS = new ArrayList<>(AGES.keySet());

    public static AgeResult getDate(long id) {
        long minId = IDS.get(0);
        long maxId = IDS.get(IDS.size() - 1);

        if (id < minId) {
            return new AgeResult(-1, AGES.get(minId));
        }

        if (id > maxId) {
            return new AgeResult(1, AGES.get(maxId));
        }

        for (int i = 0; i < IDS.size(); i++) {
            long currentId = IDS.get(i);
            if (id <= currentId) {

                if (i == 0) {
                    return new AgeResult(0, AGES.get(minId));
                }

                long lowerId = IDS.get(i - 1);
                long lowerAge = AGES.get(lowerId);
                long upperAge = AGES.get(currentId);

                double idRatio = (double) (id - lowerId) / (double) (currentId - lowerId);
                long midDate = (long) Math.floor(idRatio * (upperAge - lowerAge) + lowerAge);
                return new AgeResult(0, midDate);
            }
        }

        return new AgeResult(1, AGES.get(maxId));
    }

    public static String getYearAndMethod(long id) {
        AgeResult d = getDate(id);

        SimpleDateFormat sdf = new SimpleDateFormat("M/yyyy", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return sdf.format(new java.util.Date(d.timestampMs));
    }

    public static int getAge(long id) {
        AgeResult d = getDate(id);

        Calendar created = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        created.setTimeInMillis(d.timestampMs);

        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        int age = now.get(Calendar.YEAR) - created.get(Calendar.YEAR);

        if (now.get(Calendar.MONTH) < created.get(Calendar.MONTH)) {
            age--;
        }

        return Math.max(age, 0);
    }

}