package com.my.televip.features;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.calendar.CalendarDate;
import com.my.televip.calendar.ConverterCalendar;
import com.my.televip.hooks.HMethod;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.LocaleController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HijriDate {

    public static boolean isEnable = false;

    public static void init() {
        if (ConfigManager.customCalendar.getCustomCalendar() == 0) return;
        try {
            if (!isEnable) {
                isEnable = true;
                HMethod.hookMethod(
                        ClassLoad.getClass(ClassNames.LOCALE_CONTROLLER),
                        AutomationResolver.resolve("LocaleController", "formatYearMont", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("formatYearMont", new Class[]{long.class, boolean.class}), new AbstractMethodHook() {
                            @Override
                            protected void afterMethod(MethodHookParam param) {
                                if (ConfigManager.customCalendar.getCustomCalendar() == 0) {
                                    return;
                                }
                                long date = (long) param.args[0];
                                boolean alwaysShowYear = (boolean) param.args[1];

                                String yearMont = formatYearMont(date, alwaysShowYear);

                                if (yearMont != null && !yearMont.isEmpty()) {
                                    param.setResult(yearMont);
                                }

                            }
                        }));
                HMethod.hookMethod(
                        ClassLoad.getClass(ClassNames.FAST_DATE_FORMAT),
                        AutomationResolver.resolve("FastDateFormat", "format", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("format", new Class[]{long.class}), new AbstractMethodHook() {
                            @Override
                            protected void afterMethod(MethodHookParam param) {
                                if (ConfigManager.customCalendar.getCustomCalendar() == 0) {
                                    return;
                                }
                                String org = (String) param.getResult();
                                long date = (long) param.args[0];
                                String formatDate = formatDate(date, org);

                                if (formatDate != null && !formatDate.isEmpty()) {
                                    param.setResult(formatDate);
                                }


                            }
                        }));

                HMethod.hookMethod(
                        ClassLoad.getClass(ClassNames.FAST_DATE_FORMAT),
                        AutomationResolver.resolve("FastDateFormat", "format", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("format", new Class[]{Calendar.class}), new AbstractMethodHook() {
                            @Override
                            protected void afterMethod(MethodHookParam param) {
                                if (ConfigManager.customCalendar.getCustomCalendar() == 0) {
                                    return;
                                }
                                String org = (String) param.getResult();
                                Calendar calendar = (Calendar) param.args[0];
                                String formatDate = formatDate(calendar, org);

                                if (formatDate != null && !formatDate.isEmpty()) {
                                    param.setResult(formatDate);
                                }
                            }
                        }));
                HMethod.hookMethod(
                        ClassLoad.getClass(ClassNames.FAST_DATE_FORMAT),
                        AutomationResolver.resolve("FastDateFormat", "format", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("format", new Class[]{Date.class}), new AbstractMethodHook() {
                            @Override
                            protected void afterMethod(MethodHookParam param) {
                                if (ConfigManager.customCalendar.getCustomCalendar() == 0) {
                                    return;
                                }
                                String org = (String) param.getResult();
                                Date date = (Date) param.args[0];
                                String formatDate = formatDate(date, org);

                                if (formatDate != null && !formatDate.isEmpty()) {
                                    param.setResult(formatDate);
                                }


                            }
                        }));
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }

    public static String formatDate(Object date, String org) {
        try {
            DateFormatType type = detectFormatType(org);
            if (type == DateFormatType.UNKNOWN || type == DateFormatType.TIME_ONLY) return org;

            CalendarDate dateCalendar = null;
            SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            String dateHour = null;

            if (date instanceof Long) {
                long l = (long) date;
                dateCalendar = ConverterCalendar.toCalendar(l);
                dateHour = sdfTime.format(new Date(l));
            } else if (date instanceof Calendar) {
                Calendar calendar = (Calendar) date;
                dateCalendar = ConverterCalendar.toCalendar(calendar);
                dateHour = sdfTime.format(calendar);
            } else if (date instanceof Date) {
                Date date1 = (Date) date;
                dateCalendar = ConverterCalendar.toCalendar(date1);
                dateHour = sdfTime.format(date1);
            }

            if (dateCalendar == null) return null;

            int dateDay = dateCalendar.getDay();
            int dateYear = dateCalendar.getYear();
            int dateMonth = dateCalendar.getMonth();
            String dateMonthName = dateCalendar.getMonthName();

            switch (type) {
                case DAY_MONTH:
                    return LocaleController.isRTL()
                            ? dateDay + " " + dateMonthName
                            : dateMonthName + " " + dateDay;

                case DAY_MONTH_YEAR:
                    return LocaleController.isRTL()
                            ? dateDay + " " + dateMonthName + " " + dateYear
                            : dateMonthName + " " + dateDay + " " + dateYear;

                case TIME_DAY_MONTH_YEAR:
                    return LocaleController.isRTL()
                            ? dateDay + " " + dateMonthName + " " + dateYear + " " + dateHour
                            : dateMonthName + " " + dateDay + " " + dateYear + " " + dateHour;

                case NUMERIC_DATE:
                    return dateYear + "." + dateMonth + "." + dateDay;

                default:
                    return null;
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
        return null;
    }

    public static String formatYearMont(long date, boolean alwaysShowYear) {
        try {
            date *= 1000;

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            CalendarDate rightNow = ConverterCalendar.toCalendar(calendar);

            int year = rightNow.getYear();

            rightNow = ConverterCalendar.toCalendar(date);

            int dateYear = rightNow.getYear();
            String dateMonthName = rightNow.getMonthName();

            if (year == dateYear && !alwaysShowYear) {
                return dateMonthName;
            } else {
                return dateMonthName + " " + dateYear;
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
        return null;
    }

    public enum DateFormatType {
        TIME_ONLY,
        DAY_MONTH,
        DAY_MONTH_YEAR,
        TIME_DAY_MONTH_YEAR,
        NUMERIC_DATE,
        UNKNOWN
    }

    public static DateFormatType detectFormatType(String value) {
        if (value == null || value.isEmpty()) return DateFormatType.UNKNOWN;

        String v = value.trim();

        if (v.matches("\\d{1,4}[./]\\d{1,2}[./]\\d{1,4}")) {
            return DateFormatType.NUMERIC_DATE;
        }

        boolean hasTime = v.matches(".*\\d{1,2}:\\d{2}.*");

        String withoutTime = v.replaceAll(
                "\\p{L}*\\s*\\d{1,2}:\\d{2}(:\\d{2})?\\s*\\p{L}*", ""
        ).trim();

        boolean hasYear = withoutTime.matches(".*\\b\\d{4}\\b.*");
        boolean hasDay = withoutTime.matches(".*\\b\\d{1,2}\\b.*");
        boolean hasMonthText = withoutTime.matches(".*\\p{L}{2,}.*");

        if (hasTime && !hasDay && !hasMonthText) {
            return DateFormatType.TIME_ONLY;
        } else if (!hasTime && hasDay && hasMonthText && !hasYear) {
            return DateFormatType.DAY_MONTH;
        } else if (!hasTime && hasDay && hasMonthText) {
            return DateFormatType.DAY_MONTH_YEAR;
        } else if (hasTime && hasDay && hasMonthText) {
            return DateFormatType.TIME_DAY_MONTH_YEAR;
        }

        return DateFormatType.UNKNOWN;
    }

}