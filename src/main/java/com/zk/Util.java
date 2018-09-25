package com.zk;

import java.lang.reflect.Field;
import java.util.*;

public class Util {

    private final static String SORT_SYMBOL_ASC = "ASC";

    private final static String SORT_SYMBOL_DESC = "DESC";

    List util(final String sortName, final String symbol, List list) {

        if (sortName == null || symbol == null || list == null || list.size() == 0) {
            return list;
        }

        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Class clazz = o1.getClass();
                Field field = Util.searchField(clazz, sortName);
                try {
                    Object v1 = field.get(o1);
                    Object v2 = field.get(o2);
                    if (symbol.toUpperCase().equals(SORT_SYMBOL_ASC)) {
                        return compare(v1, v2);
                    }
                    if (symbol.toUpperCase().equals(SORT_SYMBOL_DESC)) {
                        return compare(v2, v1);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        return list;
    }


    public static Field searchField(Class<?> clazz, String name) {
        if (clazz == null || name == null)
            return null;
        Class searchClass = clazz;
        while (!Object.class.equals(searchClass)) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (name.equals(field.getName())) {
                    return field;
                }
            }
            searchClass = clazz.getSuperclass();
        }
        return null;
    }


    public static Integer compare(Object o1, Object o2) {
        if (o1 == null || o2 == null) {
            return 0;
        }
        Class clazz = o1.getClass();
        String clazzName = clazz.getName();

        switch (clazzName) {
            case "java.util.Date":
                Date date1 = (Date)o1;
                Date date2 = (Date)o2;
                if (date1.getTime() < date2.getTime()) {
                    return -1;
                }
                if (date1.getTime() > date2.getTime()) {
                    return 1;
                }
                if (date1.getTime() == date2.getTime()) {
                    return 0;
                }
                break;

            case "java.sql.Date":
                java.sql.Date sqlDate1 = (java.sql.Date)o1;
                java.sql.Date sqlDate2 = (java.sql.Date)o2;
                if (sqlDate1.getTime() < sqlDate2.getTime()) {
                    return -1;
                }
                if (sqlDate1.getTime() > sqlDate2.getTime()) {
                    return 1;
                }
                if (sqlDate1.getTime() == sqlDate2.getTime()) {
                    return 0;
                }
                break;

            case "java.lang.Integer" :
                Integer int1 = (Integer)o1;
                Integer int2 = (Integer)o2;
                if (int1 < int2) {
                    return -1;
                }
                if (int1 > int2) {
                    return 1;
                }
                if (int1 == int2) {
                    return 0;
                }
                break;

            case "java.lang.Double":
                Double double1 = (Double)o1;
                Double double2 = (Double)o2;
                if (double1 < double2) {
                    return -1;
                }
                if (double1 > double2) {
                    return 1;
                }
                if (double1 == double2) {
                    return 0;
                }
                break;

            case "java.lang.Float":
                Float float1 = (Float)o1;
                Float float2 = (Float)o2;
                if (float1 < float2) {
                    return -1;
                }
                if (float1 > float2) {
                    return 1;
                }
                if (float1 == float2) {
                    return 0;
                }

                default:
                    return o1.toString().compareTo(o2.toString());

        }

        return 0;
    }


}
