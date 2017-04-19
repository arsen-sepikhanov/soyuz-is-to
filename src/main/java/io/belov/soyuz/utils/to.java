/*
 * to
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package io.belov.soyuz.utils;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;
import java.time.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

public class to {

    private static final Logger log = LoggerFactory.getLogger(to.class);

    public static Integer Integer(Object val, Integer defaultValue) {
        return Integer(val, defaultValue, true);
    }

    public static Integer Integer(Object val, Integer defaultValue, boolean silent) {
        if (val != null) {
            try {
                return doIntConvert(val);
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to int", e);
            }
        }

        return defaultValue;
    }

    public static Integer Integer(Object val) {
        if (val == null) {
            return null;
        } else if (val instanceof Integer) {
            return (Integer) val;
        } else {
            return Integer(val.toString(), null);
        }
    }

    public static Integer Integer(String val) {
        return Integer(val, null);
    }

    @Nullable
    public static Float Float(@Nullable Object val) {
        if (val == null) {
            return null;
        } else if (val instanceof Float) {
            return (Float) val;
        } else {
            return Float(val.toString(), null);
        }
    }

    @Nullable
    public static Float Float(@Nullable String val) {
        return Float(val, null);
    }

    @Nullable
    public static Float Float(@Nullable Object val, Float defaultValue) {
        return Float(val, defaultValue, true);
    }

    @Nullable
    public static Float Float(@Nullable Object val, Float defaultValue, boolean silent) {
        if (val != null) {
            try {
                if (val instanceof Float) {
                    return (Float) val;
                } else {
                    return doFloatConvert(val);
                }
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to float", e);
            }
        }

        return defaultValue;
    }

    public static Long Long(Object val) {
        return Long(val, null);
    }

    public static Long Long(String val) {
        return Long(val, null);
    }

    public static Long Long(Object val, Long defaultValue) {
        return Long(val, defaultValue, true);
    }

    public static Long Long(Object val, Long defaultValue, boolean silent) {
        if (val != null) {
            try {
                if (val instanceof Long) {
                    return (Long) val;
                } else {
                    return doLongConvert(val);
                }
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to long", e);
            }
        }

        return defaultValue;
    }

    public static Boolean Boolean(String val) {
        return Boolean(val, null);
    }

    public static Boolean Boolean(String val, Boolean defaultValue) {
        return Boolean(val, defaultValue, true);
    }

    public static Boolean Boolean(Object val, Boolean defaultValue, boolean silent) {
        if (val != null) {
            try {
                return doBooleanConvert(val);
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to boolean", e);
            }
        }

        return defaultValue;
    }

    @Nullable
    public static String s(@Nullable Object s) {
        return (s == null) ? null : s.toString();
    }

    public static String s(String s, Object... params) {
        return MessageFormat.format(s, params);
    }

    public static String s(String s, Map<String, ?> params) {
        for (Map.Entry<String, ?> e : params.entrySet()) {
            s = s.replace("{" + e.getKey() + "}", e.getValue().toString());
        }

        return s;
    }

    public static String s(Iterable iterable, String separator) {
        Iterator iterator = iterable.iterator();
        StringBuilder sb = new StringBuilder();

        if (iterator.hasNext()) {
            sb.append(iterator.next());
            while (iterator.hasNext()) {
                sb.append(separator).append(iterator.next());
            }
        }

        return sb.toString();
    }

    public static String s(long l) {
        return String.valueOf(l);
    }

    public static String s(int i) {
        return String.valueOf(i);
    }

    public static String s(char c) {
        return String.valueOf(c);
    }

    public static String s(boolean b) {
        return String.valueOf(b);
    }

    public static String s(float f) {
        return String.valueOf(f);
    }

    public static String s(double d) {
        return String.valueOf(d);
    }

    @Nullable
    public static URI uri(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @SneakyThrows
    public static URI uriOrException(String url) {
        return new URI(url);
    }

    @Nullable
    public static URL url(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @SneakyThrows
    public static URL urlOrException(String url) {
        return new URL(url);
    }

    public static <K, V> Map<K, V> map() {
        return new HashMap<>();
    }

    public static <K, V> Map<K, V> map(K k1, V v1) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);
        a.put(k4, v4);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);
        a.put(k4, v4);
        a.put(k5, v5);

        return a;
    }

    public static Map<String, String> map(String... params) {
        return fillMapWithParams(new HashMap<String, String>(), params);
    }

    public static Map<String, String> map(Map<String, String> source, String... params) {
        return fillMapWithParams(new HashMap<>(source), params);
    }

    public static Map map(Object... params) {
        return fillMapWithParams(new HashMap(), params);
    }

    public static Map map(Map source, Object... params) {
        return fillMapWithParams(new HashMap(source), params);
    }

    public static <K, V, R> Map<K, R> map(Map<K, V> source, Function<Map.Entry<K, V>, Map<K, R>> mapper) {
        Map<K, R> answer = new HashMap<K, R>();

        for (Map.Entry<K, V> e : source.entrySet()) {
            Map<K, R> entry = mapper.apply(e);

            if (entry != null) {
                answer.putAll(entry);
            }
        }

        return answer;
    }

    public static <K, V, R> Map<K, R> map(Map<K, V> source, BiFunction<K, V, R> mapper) {
        Map<K, R> answer = new HashMap<K, R>();

        for (Map.Entry<K, V> e : source.entrySet()) {
            answer.put(e.getKey(), mapper.apply(e.getKey(), e.getValue()));
        }

        return answer;
    }

    public static <T, K> Map<K, T> map(Iterable<T> source, Function<T, K> keyFunction) {
        return map(source, keyFunction, (s) -> s);
    }

    public static <T, K, V> Map<K, V> map(Iterable<T> source, Function<T, K> keyFunction, Function<T, V> valueFunction) {
        Map<K, V> answer = new HashMap<K, V>();

        for (T e : source) {
            answer.put(keyFunction.apply(e), valueFunction.apply(e));
        }

        return answer;
    }

    public static <K, V> Map<K, V> linkedHashMap(K k1, V v1) {
        Map<K, V> a = new LinkedHashMap<K, V>();

        a.put(k1, v1);

        return a;
    }

    public static <K, V> Map<K, V> linkedHashMap(K k1, V v1, K k2, V v2) {
        Map<K, V> a = new LinkedHashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);

        return a;
    }

    public static <K, V> Map<K, V> linkedHashMap(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> a = new LinkedHashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);

        return a;
    }

    public static <K, V> Map<K, V> linkedHashMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> a = new LinkedHashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);
        a.put(k4, v4);

        return a;
    }

    public static <K, V> Map<K, V> linkedHashMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map<K, V> a = new LinkedHashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);
        a.put(k4, v4);
        a.put(k5, v5);

        return a;
    }

    public static Map<String, String> linkedHashMap(String... params) {
        return fillMapWithParams(new LinkedHashMap<String, String>(), params);
    }

    public static Map<String, String> linkedHashMap(Map<String, String> source, String... params) {
        return fillMapWithParams(new LinkedHashMap<>(source), params);
    }

    public static Map linkedHashMap(Object... params) {
        return fillMapWithParams(new LinkedHashMap(), params);
    }

    public static Map linkedHashMap(Map source, Object... params) {
        return fillMapWithParams(new LinkedHashMap(source), params);
    }

    public static <K, V, R> Map<K, R> linkedHashMap(Map<K, V> source, Function<Map.Entry<K, V>, Map<K, R>> mapper) {
        Map<K, R> answer = new LinkedHashMap<K, R>();

        for (Map.Entry<K, V> e : source.entrySet()) {
            Map<K, R> entry = mapper.apply(e);

            if (entry != null) {
                answer.putAll(entry);
            }
        }

        return answer;
    }

    public static <K, V, R> Map<K, R> linkedHashMap(Map<K, V> source, BiFunction<K, V, R> mapper) {
        Map<K, R> answer = new LinkedHashMap<K, R>();

        for (Map.Entry<K, V> e : source.entrySet()) {
            answer.put(e.getKey(), mapper.apply(e.getKey(), e.getValue()));
        }

        return answer;
    }

    public static <T, K> Map<K, T> linkedHashMap(Iterable<T> source, Function<T, K> keyFunction) {
        return linkedHashMap(source, keyFunction, (s) -> s);
    }

    public static <T, K, V> Map<K, V> linkedHashMap(Iterable<T> source, Function<T, K> keyFunction, Function<T, V> valueFunction) {
        Map<K, V> answer = new LinkedHashMap<K, V>();

        for (T e : source) {
            answer.put(keyFunction.apply(e), valueFunction.apply(e));
        }

        return answer;
    }

    private static <K, V> Map<K, V> fillMapWithParams(Map map, Object... params) {
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("Params number should be even");
        }

        for (int i = 0; i < params.length / 2; i++) {
            map.put(params[i * 2], params[i * 2 + 1]);
        }

        return map;
    }

    @Nullable
    public static <T> List<T> list(@Nullable Iterator<T> iterator) {
        return list(iterator, 10);
    }

    /**
     * @see org.apache.commons.collections.IteratorUtils.toList
     */
    @Nullable
    public static <T> List<T> list(@Nullable Iterator<T> iterator, int estimatedSize) {
        if (iterator == null) {
            return null;
        } else if (estimatedSize < 1) {
            throw new IllegalArgumentException("Estimated size must be greater than 0");
        } else {
            ArrayList<T> list = new ArrayList<T>(estimatedSize);

            while (iterator.hasNext()) {
                list.add(iterator.next());
            }

            return list;
        }
    }

    public static <T> List<T> list(T value) {
        List<T> answer = new ArrayList<T>();
        answer.add(value);
        return answer;
    }

    public static <T> List<T> list(T... value) {
        List<T> answer = new ArrayList<T>();
        Collections.addAll(answer, value);
        return answer;
    }

    @Nullable
    public static <T, R> List<R> list(@Nullable T[] values, Function<T, R> mapper) {
        if (values == null) return null;

        List<R> answer = new ArrayList<R>(values.length);

        for (T value : values) {
            answer.add(mapper.apply(value));
        }

        return answer;
    }

    @Nullable
    public static <V> List<V> list(@Nullable Collection<V> values) {
        if (values == null) {
            return null;
        } else if (values instanceof List) {
            return (List<V>) values;
        } else {
            List<V> answer = new ArrayList<V>();

            answer.addAll(values);

            return answer;
        }
    }

    @Nullable
    public static <T, V> List<T> list(@Nullable Collection<V> values, Function<V, T> mapper) {
        if (values == null) return null;

        List<T> answer = new ArrayList<T>(values.size());

        for (V value : values) {
            answer.add(mapper.apply(value));
        }

        return answer;
    }

    @Nullable
    public static <K, V, R> List<R> list(@Nullable Map<K, V> map, BiFunction<K, V, R> mapper) {
        if (map == null) return null;

        List<R> answer = new ArrayList<>(map.values().size());

        for (Map.Entry<K, V> e : map.entrySet()) {
            answer.add(mapper.apply(e.getKey(), e.getValue()));
        }

        return answer;
    }

    public static <T> Set<T> set(T value) {
        Set<T> answer = new HashSet<T>();
        answer.add(value);
        return answer;
    }

    public static <T> Set<T> set(T... value) {
        Set<T> answer = new HashSet<T>();
        Collections.addAll(answer, value);
        return answer;
    }

    @Nullable
    public static <V> Set<V> set(@Nullable Collection<V> values) {
        if (values == null) {
            return null;
        } else if (values instanceof Set) {
            return (Set<V>) values;
        } else {
            Set<V> answer = new HashSet<>();

            answer.addAll(values);

            return answer;
        }
    }

    @Nullable
    public static <V> Set<V> set(@Nullable Iterable<V> values) {
        if (values == null) {
            return null;
        } else if (values instanceof Set) {
            return (Set<V>) values;
        } else {
            Set<V> answer = new HashSet<>();

            for (V value : values) {
                answer.add(value);
            }

            return answer;
        }
    }

    @Nullable
    public static <T, V> Set<T> set(@Nullable Collection<V> values, Function<V, T> mapper) {
        if (values == null) return null;

        Set<T> answer = new HashSet<T>();

        for (V value : values) {
            answer.add(mapper.apply(value));
        }

        return answer;
    }

    public static <T> SortedSet<T> sortedSet(T value) {
        SortedSet<T> answer = new TreeSet<T>();
        answer.add(value);
        return answer;
    }

    public static <T> SortedSet<T> sortedSet(T... value) {
        SortedSet<T> answer = new TreeSet<T>();
        Collections.addAll(answer, value);
        return answer;
    }

    @Nullable
    public static Date date(@Nullable java.sql.Date dateSql) {
        return (dateSql) == null ? null : new Date(dateSql.getTime());
    }

    @Nullable
    public static Date date(@Nullable LocalDate localDate) {
        //http://stackoverflow.com/a/22929420/716027
        return date(localDate, ZoneId.systemDefault());
    }

    @Nullable
    public static Date date(@Nullable LocalDate localDate, ZoneId zone) {
        return (localDate == null) ? null : date(localDate.atStartOfDay(zone));
    }

    @Nullable
    public static Date date(@Nullable LocalDateTime localDateTime) {
        //http://blog.progs.be/542/date-to-java-time
        return date(localDateTime, ZoneId.systemDefault());
    }

    @Nullable
    public static Date date(@Nullable Instant instant) {
        if (instant == null) {
            return null;
        } else {
            return new Date(instant.toEpochMilli());
        }
    }

    @Nullable
    public static Date date(@Nullable LocalDateTime localDateTime, ZoneId zone) {
        return (localDateTime == null) ? null : date(localDateTime.atZone(zone));
    }

    @Nullable
    public static Date date(@Nullable ZonedDateTime zonedDateTime) {
        return (zonedDateTime == null) ? null : Date.from(zonedDateTime.toInstant());
    }

    @Nullable
    public static java.sql.Date sqlDate(@Nullable Date date) {
        return (date == null) ? null : new java.sql.Date(date.getTime());
    }

    @Nullable
    public static java.sql.Timestamp sqlTimestamp(@Nullable Date date) {
        return (date == null) ? null : new java.sql.Timestamp(date.getTime());
    }

    public static LocalDateTime localDateTime(long millis) {
        //http://blog.progs.be/542/date-to-java-time
        return localDateTime(millis, ZoneId.systemDefault());
    }

    public static LocalDateTime localDateTime(long millis, ZoneId zone) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zone);
    }

    @Nullable
    public static LocalDateTime localDateTime(@Nullable Date date) {
        //http://blog.progs.be/542/date-to-java-time
        return (date == null) ? null : localDateTime(date.getTime());
    }

    @Nullable
    public static LocalDate localDate(@Nullable Date date) {
        return localDate(date, ZoneId.systemDefault());
    }

    public static LocalDate localDate(@Nullable Date date, ZoneId zone) {
        return (date == null) ? null : localDate(date.getTime(), zone);
    }

    public static LocalDate localDate(long millis) {
        return localDate(millis, ZoneId.systemDefault());
    }

    public static LocalDate localDate(long millis, ZoneId zone) {
        return localDateTime(millis, zone).toLocalDate();
    }

    public static LocalTime localTime(long millis) {
        return localTime(millis, ZoneId.systemDefault());
    }

    public static LocalTime localTime(long millis, ZoneId zone) {
        return localDateTime(millis, zone).toLocalTime();
    }

    @Nullable
    public static ZonedDateTime zonedDateTime(@Nullable Date date) {
        return zonedDateTime(date, null);
    }

    @Nullable
    public static ZonedDateTime zonedDateTime(@Nullable Date date, @Nullable ZoneId zone) {
        if (date == null) {
            return null;
        } else {
            return ZonedDateTime.ofInstant(date.toInstant(), (zone != null) ? zone : ZoneId.systemDefault());
        }
    }

    @Nullable
    public static Instant instant(@Nullable Date date) {
        if (date == null) {
            return null;
        } else {
            return date.toInstant();
        }
    }

    @Nullable
    public static <T, V> V valueOrNull(@Nullable T object, Function<T, V> func) {
        return (object == null) ? null : func.apply(object);
    }

    public static <T> T nullOr(Object o, Supplier<T> supplier) {
        return (o == null) ? null : supplier.get();
    }

    public static <T> T or(T o, Supplier<T> supplier) {
        return (o != null) ? o : supplier.get();
    }

    public static <T> T or(T o, T otherwise) {
        return (o != null) ? o : otherwise;
    }

    public static String orDefault(String o) {
        return (o != null) ? o : "";
    }

    public static <T> Iterable<T> orDefault(Iterable<T> o) {
        return (o != null) ? o : new ArrayList<>();
    }

    public static <T> Collection<T> orDefault(Collection<T> o) {
        return (o != null) ? o : new ArrayList<>();
    }

    public static <T> List<T> orDefault(List<T> o) {
        return (o != null) ? o : new ArrayList<>();
    }

    public static <T> Set<T> orDefault(Set<T> o) {
        return (o != null) ? o : new HashSet<>();
    }

    public static <K, V> Map<K, V> orDefault(Map<K, V> o) {
        return (o != null) ? o : new HashMap<>();
    }

    public static <T> Stream<T> stream(T[] array) {
        return Arrays.stream(array);
    }

    //http://stackoverflow.com/a/24511534
    public static <T> Stream<T> stream(final Iterator<T> iterator) {
        final Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static IntStream stream(int[] array) {
        return Arrays.stream(array);
    }

    public static LongStream stream(long[] array) {
        return Arrays.stream(array);
    }

    public static DoubleStream stream(double[] array) {
        return Arrays.stream(array);
    }

    public static Thread daemon(Runnable runnable) {
        return daemon(null, runnable);
    }

    public static Thread daemon(String threadName, Runnable runnable) {
        Thread t = (is.t(threadName)) ? new Thread(runnable, threadName) : new Thread(runnable);
        t.setDaemon(true);

        return t;
    }

    public static Thread daemonForever(long delayInMillis, Runnable runnable) {
        return daemonForever(null, delayInMillis, runnable);
    }

    public static Thread daemonForever(final String threadName, final long delayInMillis, final Runnable runnable) {
        return daemonForever(threadName, delayInMillis, runnable, 0);
    }

    public static Thread daemonForever(final String threadName, final long delayInMillis, final Runnable runnable, long startDelayInMillis) {
        Runnable forever = new Runnable() {
            @Override
            public void run() {
                try {
                    if (startDelayInMillis > 0) {
                        Thread.sleep(startDelayInMillis);
                    }

                    while (true) {
                        runnable.run();

                        try {
                            Thread.sleep(delayInMillis);
                        } catch (InterruptedException e) {
                            //
                        }
                    }
                } catch (Throwable e) {
                    log.error("daemon.forever.e: " + threadName, e);
                }
            }
        };

        Thread t = (is.t(threadName)) ? new Thread(forever, threadName) : new Thread(forever);
        t.setDaemon(true);

        return t;
    }

    public static ToParallel parallel() {
        return parallel(null);
    }

    public static ToParallel parallel(ExecutorService pool) {
        return new ToParallel(pool);
    }

    private static Integer doIntConvert(BigDecimal val) {
        return val.intValue();
    }

    private static Integer doIntConvert(Long val) {
        return val.intValue();
    }

    private static Integer doIntConvert(Integer val) {
        return val;
    }

    private static Integer doIntConvert(Object val) {
        return Integer.parseInt(val.toString());
    }

    private static Long doLongConvert(BigDecimal val) {
        return val.longValue();
    }

    private static Long doLongConvert(Long val) {
        return val;
    }

    private static Long doLongConvert(Integer val) {
        return val.longValue();
    }

    private static Long doLongConvert(Object val) {
        return Long.parseLong(val.toString());
    }

    private static Float doFloatConvert(Object val) {
        return Float.parseFloat(val.toString());
    }

    private static Boolean doBooleanConvert(String val) {
        return Boolean.valueOf(val);
    }

    private static Boolean doBooleanConvert(Boolean val) {
        return val;
    }

    private static Boolean doBooleanConvert(Object val) {
        return Boolean.valueOf(val.toString());
    }

}
