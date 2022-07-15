(ns java-time.pre-java8
  (:require [java-time
             [convert :as jt.c]
             [local :as jt.l]
             [temporal :as jt.t]
             [util :as jt.u]
             [defconversion :refer [conversion!]]]))

(defn ^java.util.Date java-date
  "Creates a `java.util.Date` out of any combination of arguments valid for
  `java-time/instant` or the Instant itself.

  A `java.util.Date` represents an instant in time. It's a direct analog of the
  `java.time.Instant` type introduced in the JSR-310. Please consider using the
  `java.time.Instant` (through `java-time/instant`) directly."
  ([] (java.util.Date/from (jt.t/instant)))
  ([a] (java.util.Date/from (jt.t/instant a)))
  ([a b] (java.util.Date/from (jt.t/instant a b))))

(defn- arities [type ctor n-args]
  (for [i (range (inc n-args))]
    (let [arg-vec (mapv #(gensym (str "arg" %)) (range i))
          type-ctor (symbol (name type) "valueOf")]
      `(~arg-vec (~type-ctor (~ctor ~@arg-vec))))))

(defmacro defsqldate [type name ctor n-args doc]
  (let [fn-name (with-meta name {:tag type})]
    `(defn ~fn-name ~doc
       ~@(arities type ctor n-args))))

(conversion! java.sql.Date java.time.LocalDate
  (fn [^java.sql.Date dt]
    (.toLocalDate dt)))

(conversion! java.sql.Timestamp java.time.LocalDateTime
  (fn [^java.sql.Timestamp dt]
    (.toLocalDateTime dt)))

(jt.u/when-class "java.sql.Time"
  (conversion! java.sql.Time java.time.LocalTime
    (fn [^java.sql.Time dt]
      (.toLocalTime dt))))

(defsqldate java.sql.Date sql-date jt.l/local-date 3
  "Creates a `java.sql.Date` out of any combination of arguments valid for
  `java-time/local-date` or the `LocalDate` itself.

  Please consider using the JSR-310 Java Time types instead of `java.sql.Date`
  if your drivers support them.

  Even though `java.sql.Date` extends a `java.util.Date`, it's supposed to be
  used as a local date (no time component or time zone) for the purposes of
  conversion from/to native JDBC driver DATE types.")

(defsqldate java.sql.Timestamp sql-timestamp jt.l/local-date-time 7
  "Creates a `java.sql.Timestamp` in the local time zone out of any combination
  of arguments valid for `java-time/local-date-time` or the `LocalDateTime`
  itself.

  The `sql-timestamp` constructor function does not support `Timestamp`
  construction from an `Instant` or a long millis value. Please use
  `instant->sql-timestamp` for this purpose.

  Please consider using the JSR-310 Java Time types instead of
  `java.sql.Timestamp` if your drivers support them.

  `java.sql.Timestamp` is a version of a `java.util.Date` supposed to be used
  as a local date-time (no time zone) for the purposes of conversion from/to native
  JDBC driver TIMESTAMP types.")

(defn instant->sql-timestamp
  "Creates a `java.sql.Timestamp` from the provided `instant-or-millis` - a
  millisecond numeric time value or something convertible to an `Instant`.

  Please consider using the JSR-310 Java Time types instead of
  `java.sql.Timestamp` if your drivers support them.

  `java.sql.Timestamp` is a version of a `java.util.Date` supposed to be used
  as a local date-time (no time zone) for the purposes of conversion from/to native
  JDBC driver TIMESTAMP types."
  [instant-or-millis]
  (if (number? instant-or-millis)
    (java.sql.Timestamp. (long instant-or-millis))
    (java.sql.Timestamp/from (jt.t/instant instant-or-millis))))

(jt.u/when-class "java.sql.Time"
  (defsqldate java.sql.Time sql-time jt.l/local-time 3
    "Creates a `java.sql.Time` out of any combination of arguments valid for
    `java-time/local-time` (except the nanos constructor) or the `LocalTime`
    itself.

    Please consider using the JSR-310 Java Time types instead of `java.sql.Time`
    if your drivers support them.

    Even though `java.sql.Time` extends a `java.util.Date`, it's supposed to be
    used as a local time (no date component or time zone) for the purposes of
    conversion from/to native JDBC driver TIME types."))
