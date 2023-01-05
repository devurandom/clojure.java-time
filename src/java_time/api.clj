;; NOTE: This namespace is generated by java-time.dev.gen
(ns ^{:supercedes "java-time"} java-time.api (:refer-clojure :exclude (zero? range iterate max min contains? format abs)) (:require [java-time core properties temporal amount zone single-field local chrono convert sugar seqs adjuster interval format joda clock pre-java8 mock]))
(defmacro with-clock {:doc "Executes the given `forms` in the scope of the provided `clock`.\n\n  All the temporal entities that get created without parameters will inherit\n  their values from the clock:\n\n    (with-clock (system-clock \"Europe/London\")\n      (zone-id))\n    => #<java.time.ZoneRegion Europe/London>"} [c & forms] (list* (quote java-time.clock/with-clock) c forms))
(defmacro when-joda-time-loaded {:doc "Execute the `body` when Joda-Time classes are found on the classpath.\n\n  Take care - when AOT-compiling code using this macro, the Joda-Time classes\n  must be on the classpath at compile time!"} [& body] (list* (quote java-time.util/when-joda-time-loaded) body))
(def ^{:arglists (quote ([^Clock c f])), :doc "Executes the given function in the scope of the provided clock. All the\n  temporal entities that get created without parameters will inherit their\n  values from the clock."} with-clock-fn java-time.clock/with-clock-fn)
(defn ^{:doc "True if the amount is zero"} zero? ([a] (java-time.core/zero? a)))
(defn ^{:doc "True if the amount is negative"} negative? ([a] (java-time.core/negative? a)))
(defn ^{:doc "Negates a temporal amount:\n\n      (negate (negate x)) == x"} negate ([a] (java-time.core/negate a)))
(defn ^{:doc "Returns the absolute value of a temporal amount:\n\n      (abs (negate x)) == (abs x)"} abs ([a] (java-time.core/abs a)))
(def ^{:arglists (quote ([o & os])), :doc "Latest/longest of the given time entities. Entities should be of the same\n  type"} max java-time.core/max)
(def ^{:arglists (quote ([o & os])), :doc "Earliest/shortest of the given time entities. Entities should be of the same\n  type"} min java-time.core/min)
(def ^{:arglists (quote ([x] [x y] [x y & more])), :doc "Returns `true` if time entities are ordered from the earliest to the\n  latest (same semantics as `<`), otherwise `false`.\n\n  ```\n  (before? (local-date 2009) (local-date 2010) (local-date 2011))\n  => true\n\n  (before? (interval (instant 10000) (instant 1000000))\n           (instant 99999999))\n  => true\n  ```"} before? java-time.core/before?)
(def ^{:arglists (quote ([x] [x y] [x y & more])), :doc "Like [[before?]], except returns `true` if the inputs are equal."} not-after? java-time.core/not-after?)
(def ^{:arglists (quote ([x] [x y] [x y & more])), :doc "Returns `true` if time entities are ordered from the latest to the\n  earliest (same semantics as `>`), otherwise `false`.\n\n  ```\n  (after? (local-date 2011) (local-date 2010) (local-date 2009))\n  => true\n\n  (after? (instant 99999999)\n          (interval (instant 10000) (instant 1000000)))\n  => true\n  ```"} after? java-time.core/after?)
(def ^{:arglists (quote ([x] [x y] [x y & more])), :doc "Like [[after?]], except returns `true` if the inputs are equal."} not-before? java-time.core/not-before?)
(defn ^{:doc "True if the `o` entity supports the `p` property"} supports? ([o p] (java-time.core/supports? o p)))
(defn ^{:doc "Fields present in this temporal entity"} fields ([o] (java-time.core/fields o)))
(defn ^{:doc "Units present in this temporal entity."} units ([o] (java-time.core/units o)))
(defn ^{:doc "Map of properties present in this temporal entity"} properties ([o] (java-time.core/properties o)))
(defn ^{:doc "Property of this temporal entity under key `k`"} property ([o k] (java-time.core/property o k)))
(def ^{:arglists (quote ([o k] [o k1 k2] [o k1 k2 & ks])), :doc "Values of property/unit identified by keys/objects `ks` of the temporal\n  entity `o`, e.g.\n\n  ```\n  (as (duration 1 :hour) :minutes)\n  => 60\n\n  (as (local-date 2015 9) :year :month-of-year)\n  => [2015 9]\n  ```"} as java-time.core/as)
(defn ^{:doc "Value of the property"} value ([p] (java-time.core/value p)))
(defn ^{:doc "Range of values for this property"} range ([p] (java-time.core/range p)))
(defn ^{:doc "Minimum value of this property"} min-value ([p] (java-time.core/min-value p)))
(defn ^{:doc "Maximum value of this property, e.g. 29th of February for months"} max-value ([p] (java-time.core/max-value p)))
(defn ^{:doc "Largest minimum value of this property"} largest-min-value ([p] (java-time.core/largest-min-value p)))
(defn ^{:doc "Smallest maximum value of this property, e.g. 28th of February for months"} smallest-max-value ([p] (java-time.core/smallest-max-value p)))
(defn ^{:doc "Truncates this entity to the specified time unit. Only works for units that\n    divide into the length of standard day without remainder (up to `:days`)."} truncate-to ([o u] (java-time.core/truncate-to o u)))
(defn ^{:doc "Time between temporal entities `o` and `e` in unit `u`.\n\n    ```\n    (j/time-between (j/local-date 2015) (j/local-date 2016) :days)\n    => 365\n\n    (j/time-between :days (j/local-date 2015) (j/local-date 2016))\n    => 365\n    ```"} time-between ([o e u] (java-time.core/time-between o e u)))
(defn ^{:doc "Returns this temporal entity with the specified `ZoneId`"} with-zone ([o z] (java-time.core/with-zone o z)))
(def ^{:arglists (quote ([o & os])), :doc "Adds all of the `os` to the time entity `o`. `plus` is not commutative, the\n  first argument is always the entity which will accumulate the rest of the\n  arguments.\n\n  ```\n  (j/plus (j/local-date 2015) (j/years 1))\n  => <java.time.LocalDate \"2016-01-01\">\n  ```"} plus java-time.core/plus)
(def ^{:arglists (quote ([o & os])), :doc "Subtracts all of the `os` from the time entity `o`\n\n  ```\n  (j/minus (j/local-date 2015) (j/years 1))\n  => <java.time.LocalDate \"2014-01-01\">\n  ```"} minus java-time.core/minus)
(defn ^{:doc "Entity `o` multiplied by the value `v`"} multiply-by ([o v] (java-time.core/multiply-by o v)))
(defn ^{:doc "The `Chronology` of the entity", :tag java.time.chrono.Chronology} chronology ([o] (java-time.core/chronology o)))
(defn ^{:doc "True if the year of this entity is a leap year."} leap? ([o] (java-time.core/leap? o)))
(defn ^{:doc "Underlying temporal entity with the value of this property set to `v`"} with-value ([p v] (java-time.core/with-value p v)))
(defn ^{:doc "Underlying temporal entity with the value set to the minimum available for\n    this property"} with-min-value ([p] (java-time.core/with-min-value p)))
(defn ^{:doc "Underlying temporal entity with the value set to the maximum\n    available for this property"} with-max-value ([p] (java-time.core/with-max-value p)))
(defn ^{:doc "Underlying temporal entity with the value set to the largest minimum\n    available for this property"} with-largest-min-value ([p] (java-time.core/with-largest-min-value p)))
(defn ^{:doc "Underlying temporal entity with the value set to the smallest maximum\n    available for this property"} with-smallest-max-value ([p] (java-time.core/with-smallest-max-value p)))
(def ^{:arglists (quote ([] [arg0] [arg0 arg1])), :doc "Creates a duration - a temporal entity representing standard days, hours,\n  minutes, millis, micros and nanos. The duration itself contains only seconds\n  and nanos as properties.\n\n  Given one argument will:\n\n  * interpret as millis if a number\n  * try to parse from the standard format if a string\n  * extract supported units from another `TemporalAmount`\n  * convert from a Joda Period/Duration\n\n  Given two arguments will:\n\n  * get a duration between two `Temporal`s\n  * get a duration of a specified unit, e.g. `(duration 100 :seconds)`", :tag java.time.Duration} duration java-time.amount/duration)
(def ^{:arglists (quote ([] [arg0] [arg0 arg1] [arg0 arg1 arg2])), :doc "Creates a period - a temporal entity consisting of years, months and days.\n\n  Given one argument will\n\n  * interpret as years if a number\n  * try to parse from the standard format if a string\n  * extract supported units from another `TemporalAmount`\n  * convert from a Joda Period\n\n  Given two arguments will\n\n  * get a period of a specified unit, e.g. `(period 10 :months)`\n  * get a period between two temporals by converting them to local dates\n  * get a period of a specified number of years and months\n\n  Given three arguments will create a year/month/day period.", :tag java.time.Period} period java-time.amount/period)
(def ^{:arglists (quote ([v])), :doc "Returns true if `v` is an instance of java.time.Period, otherwise false."} period? java-time.amount/period?)
(def ^{:arglists (quote ([v])), :doc "Returns true if `v` is an instance of java.time.Duration, otherwise false."} duration? java-time.amount/duration?)
(def ^{:arglists (quote ([i])), :doc "Returns a `Duration` of `i` nanos.", :tag java.time.Duration} nanos java-time.amount/nanos)
(def ^{:arglists (quote ([micros])), :doc "Duration of a specified number of microseconds.", :tag java.time.Duration} micros java-time.amount/micros)
(def ^{:arglists (quote ([i])), :doc "Returns a `Duration` of `i` millis.", :tag java.time.Duration} millis java-time.amount/millis)
(def ^{:arglists (quote ([i])), :doc "Returns a `Duration` of `i` seconds.", :tag java.time.Duration} seconds java-time.amount/seconds)
(def ^{:arglists (quote ([i])), :doc "Returns a `Duration` of `i` minutes.", :tag java.time.Duration} minutes java-time.amount/minutes)
(def ^{:arglists (quote ([i])), :doc "Returns a `Duration` of `i` hours.", :tag java.time.Duration} hours java-time.amount/hours)
(def ^{:arglists (quote ([i])), :doc "Returns a `Duration` of `i` days.", :tag java.time.Duration} standard-days java-time.amount/standard-days)
(def ^{:arglists (quote ([i])), :doc "Returns a `Period` of `i` days.", :tag java.time.Period} days java-time.amount/days)
(def ^{:arglists (quote ([i])), :doc "Returns a `Period` of `i` weeks.", :tag java.time.Period} weeks java-time.amount/weeks)
(def ^{:arglists (quote ([i])), :doc "Returns a `Period` of `i` months.", :tag java.time.Period} months java-time.amount/months)
(def ^{:arglists (quote ([i])), :doc "Returns a `Period` of `i` years.", :tag java.time.Period} years java-time.amount/years)
(def ^{:arglists (quote ([o])), :doc "True if this is a `TemporalUnit`."} unit? java-time.properties/unit?)
(def ^{:arglists (quote ([k] [entity k])), :doc "Returns a `TemporalUnit` for the given key `k` or extracts the field from\n  the given temporal `entity`.\n\n  You can see predefined units via [[java-time.repl/show-units]].\n\n  If you want to make your own custom TemporalUnits resolvable, you need to rebind the\n  `java-time.properties/*units*` to a custom `java_time.properties.UnitGroup`.", :tag java.time.temporal.TemporalUnit} unit java-time.properties/unit)
(def ^{:arglists (quote ([o])), :doc "True if this is a `TemporalField`."} field? java-time.properties/field?)
(def ^{:arglists (quote ([k] [entity k])), :doc "Returns a `TemporalField` for the given key `k` or extracts the field from\n  the given temporal `entity`.\n\n  You can see predefined fields via [[java-time.repl/show-fields]].\n\n  If you want to make your own custom TemporalFields resolvable, you need to rebind the\n  `java-time.properties/*fields*` to a custom `java_time.properties.FieldGroup`.", :tag java.time.temporal.TemporalUnit} field java-time.properties/field)
(def ^{:arglists (quote ([min max] [arg0])), :doc "Creates a `ValueRange` given the `min` and `max` amounts or a map of\n  `:min-smallest`, `:max-smallest`, `:min-largest` and `:max-largest`.", :tag java.time.temporal.ValueRange} value-range java-time.temporal/value-range)
(def ^{:arglists (quote ([] [arg0] [arg0 arg1])), :doc "Creates an `Instant`. The following arguments are supported:\n\n    * no arguments - current instant\n    * one argument\n        + clock\n        + java.util.Date/Calendar\n        + another temporal entity\n        + string representation\n        + millis from epoch\n    * two arguments\n        + formatter (format) and a string", :tag java.time.Instant} instant java-time.temporal/instant)
(def ^{:arglists (quote ([v])), :doc "Returns true if `v` is an instance of java.time.Instant, otherwise false."} instant? java-time.temporal/instant?)
(def ^{:arglists (quote ([] [arg0] [arg0 arg1] [arg0 arg1 arg2])), :doc "Creates a `LocalDate`. The following arguments are supported:\n\n    * no arguments - current local-date\n    * one argument\n        + clock\n        + another temporal entity\n        + string representation\n        + year\n    * two arguments\n        + formatter (format) and a string\n        + an instant and a zone id\n        + another temporal entity and an offset (preserves local time)\n        + year and month\n    * three arguments\n        + year, month and date", :tag java.time.LocalDate} local-date java-time.local/local-date)
(def ^{:arglists (quote ([] [y m d h] [y m d h mm] [y m d h mm ss] [y m d h mm ss n] [arg0] [arg0 arg1] [arg0 arg1 arg2])), :doc "Creates a `LocalDateTime`. The following arguments are supported:\n\n    * no arguments - current local date-time\n    * one argument\n        + clock\n        + another temporal entity\n        + string representation\n        + year\n    * two arguments\n        + local date and local time\n        + an instant and a zone id\n        + formatter (format) and a string\n        + year and month\n\n    * three and more arguments - year/month/day/...", :tag java.time.LocalDateTime} local-date-time java-time.local/local-date-time)
(def ^{:arglists (quote ([] [h m s nn] [arg0] [arg0 arg1] [arg0 arg1 arg2])), :doc "Creates a `LocalTime`. The following arguments are supported:\n\n    * no arguments - current local time\n    * one argument\n        + clock\n        + another temporal entity\n        + string representation\n        + hours\n    * two arguments\n        + formatter (format) and a string\n        + an instant and a zone id\n        + hours and minutes\n    * three/four arguments - hour, minute, second, nanos", :tag java.time.LocalTime} local-time java-time.local/local-time)
(def ^{:arglists (quote ([v])), :doc "Returns true if `v` is an instance of java.time.LocalDate, otherwise false."} local-date? java-time.local/local-date?)
(def ^{:arglists (quote ([v])), :doc "Returns true if `v` is an instance of java.time.LocalDateTime, otherwise false."} local-date-time? java-time.local/local-date-time?)
(def ^{:arglists (quote ([v])), :doc "Returns true if `v` is an instance of java.time.LocalTime, otherwise false."} local-time? java-time.local/local-time?)
(def ^{:arglists (quote ([] [arg] [fmt arg])), :doc "Returns the `Year` for the given entity, string, clock, zone or number.\n Current year if no arguments given.", :tag java.time.Year} year java-time.single-field/year)
(def ^{:arglists (quote ([o])), :doc "Returns true if `o` is `java.time.Year`, otherwise false."} year? java-time.single-field/year?)
(def ^{:arglists (quote ([] [v] [fmt arg])), :doc "Returns the `Month` for the given month keyword name (e.g. `:january`),\n  ordinal or entity. Current month if no arguments given.", :tag java.time.Month} month java-time.single-field/month)
(def ^{:arglists (quote ([o])), :doc "True if `java.time.Month`."} month? java-time.single-field/month?)
(def ^{:arglists (quote ([] [v] [fmt arg])), :doc "Returns the `DayOfWeek` for the given day keyword name (e.g. `:monday`),\n  ordinal or entity. Current day if no arguments given.", :tag java.time.DayOfWeek} day-of-week java-time.single-field/day-of-week)
(def ^{:arglists (quote ([o])), :doc "True if `java.time.DayOfWeek`."} day-of-week? java-time.single-field/day-of-week?)
(def ^{:arglists (quote ([] [arg] [a b])), :doc "Returns the `MonthDay` for the given entity, string, clock, zone or\n  month/day combination. Current month-day if no arguments given.", :tag java.time.MonthDay} month-day java-time.single-field/month-day)
(def ^{:arglists (quote ([o])), :doc "Returns true if `o` is `java.time.MonthDay`, otherwise false."} month-day? java-time.single-field/month-day?)
(def ^{:arglists (quote ([] [arg] [a b])), :doc "Returns the `YearMonth` for the given entity, string, clock, zone or\n  month/day combination. Current year-month if no arguments given.", :tag java.time.YearMonth} year-month java-time.single-field/year-month)
(def ^{:arglists (quote ([o])), :doc "Returns true if `o` is `java.time.YearMonth`, otherwise false."} year-month? java-time.single-field/year-month?)
(def ^{:arglists (quote ([])), :doc "Returns a set of string identifiers for all available ZoneIds."} available-zone-ids java-time.zone/available-zone-ids)
(def ^{:arglists (quote ([] [arg0] [arg0 arg1])), :doc "Creates a `ZoneId` from a string identifier, `java.util.TimeZone` or extracts\n  from another temporal entity.\n\n  Returns default system zone id if no arguments provided.\n\n  Given two arguments will use the second as the offset.", :tag java.time.ZoneId} zone-id java-time.zone/zone-id)
(def ^{:arglists (quote ([] [o] [h m] [h m s])), :doc "Creates a `ZoneOffset` from a string identifier (e.g. \"+01\"), a number of\n  hours/hours and minutes/hours, minutes and seconds or extracts from another\n  temporal entity.\n\n  Returns default system zone offset if no arguments provided.", :tag java.time.ZoneOffset} zone-offset java-time.zone/zone-offset)
(def ^{:arglists (quote ([] [y m d h] [y mo d h m] [y mo d h m s] [y mo d h m s n] [y mo d h m s n o] [arg0] [arg0 arg1] [arg0 arg1 arg2])), :doc "Creates an `OffsetDateTime`. The following arguments are supported:\n\n    * no arguments - current date-time with the default offset\n    * one argument\n        + clock\n        + zone offset\n        + another temporal entity\n        + string representation\n        + year\n    * two arguments\n        + formatter (format) and a string\n        + local date-time and an offset\n        + another temporal entity and an offset (preserves local time)\n        + year and month\n    * three arguments\n        + local date, local time and an offset\n        + year, month and date\n    * four up to seven arguments - position date-time constructors\n    * eight arguments - time fields up to nanoseconds and a zone offset\n\n  If zone offset is not specified, default will be used. You can check the\n  default offset by invoking `(zone-offset)`.", :tag java.time.OffsetDateTime} offset-date-time java-time.zone/offset-date-time)
(def ^{:arglists (quote ([] [h m s] [h m s n] [h m s n o] [arg0] [arg0 arg1])), :doc "Creates an `OffsetTime`. The following arguments are supported:\n\n    * no arguments - current time with the default offset\n    * one argument\n        + clock\n        + zone id\n        + another temporal entity\n        + string representation\n        + hour\n    * two arguments\n        + formatter (format) and a string\n        + local time and an offset\n        + instant and an offset\n        + hour and minutes\n    * three arguments - hours, minutes, seconds\n    * four arguments - hours, minutes, seconds, nanos\n    * five arguments - last is the offset\n\n  If zone offset is not specified, default will be used. You can check the\n  default offset by invoking `(zone-offset)`.", :tag java.time.OffsetTime} offset-time java-time.zone/offset-time)
(def ^{:arglists (quote ([] [y m d h] [y mo d h m] [y mo d h m s] [y mo d h m s n] [y mo d h m s n o] [arg0] [arg0 arg1] [arg0 arg1 arg2])), :doc "Creates a `ZonedDateTime`. The following arguments are supported:\n\n    * no arguments - current date-time in the default zone\n    * one argument\n        + clock\n        + zone id\n        + another temporal entity\n        + string representation\n        + year\n    * two arguments\n        + formatter and a string\n        + local date-time and a zone id\n        + year and month\n    * three arguments\n        + local date, local time and a zone id\n        + year, month and day\n    * four to seven arguments - date-time fields\n    * eight arguments - last is the zone id\n\n  If zone id is not specified, default zone id will be used. You can check the\n  default zone by invoking `(zone-id)`.", :tag java.time.ZonedDateTime} zoned-date-time java-time.zone/zoned-date-time)
(def ^{:arglists (quote ([] [k])), :doc "Creates a system clock. In the default time zone if called without arguments,\n  otherwise accepts a Zone Id.", :tag java.time.Clock} system-clock java-time.zone/system-clock)
(def ^{:arglists (quote ([] [i] [i z])), :doc "Creates a fixed clock either at the current instant or at the supplied\n  instant/instant + zone.", :tag java.time.Clock} fixed-clock java-time.zone/fixed-clock)
(def ^{:arglists (quote ([d] [^Clock c d])), :doc "Creates a clock offset from the current/provided clock by a given\n  `duration`.", :tag java.time.Clock} offset-clock java-time.zone/offset-clock)
(def ^{:arglists (quote ([d] [^Clock c d])), :doc "Creates a clock wrapping system/provided clock that only ticks as per\n  specified duration.", :tag java.time.Clock} tick-clock java-time.zone/tick-clock)
(def ^{:arglists (quote ([x])), :doc "Returns true if `x` is an instance of `java.time.Clock`."} clock? java-time.zone/clock?)
(def ^{:arglists (quote ([v])), :doc "Returns true if `v` is an instance of java.time.ZoneId, otherwise false."} zone-id? java-time.zone/zone-id?)
(def ^{:arglists (quote ([v])), :doc "Returns true if `v` is an instance of java.time.ZonedDateTime, otherwise false."} zoned-date-time? java-time.zone/zoned-date-time?)
(def ^{:arglists (quote ([v])), :doc "Returns true if `v` is an instance of java.time.OffsetDateTime, otherwise false."} offset-date-time? java-time.zone/offset-date-time?)
(def ^{:arglists (quote ([v])), :doc "Returns true if `v` is an instance of java.time.OffsetTime, otherwise false."} offset-time? java-time.zone/offset-time?)
(def ^{:arglists (quote ([^ZonedDateTime zdt z])), :doc "Sets the zone to the specified value ensuring that the result has the same instant, e.g.:\n\n    (zoned-date-time 2015)\n    => #<java.time.ZonedDateTime 2015-01-01T00:00+00:00[Europe/London]>\n    (with-zone-same-instant *1 \"America/New_York\")\n    => #<java.time.ZonedDateTime 2014-12-31T18:00-05:00[America/New_York]>"} with-zone-same-instant java-time.zone/with-zone-same-instant)
(defn ^{:doc "Sets the offset to the specified value ensuring that the local time stays\n    the same.\n\n      (offset-time 10 30 0 0 +2)\n      => #<java.time.OffsetTime 10:30+02:00>\n      (with-offset *1 +3)\n      => #<java.time.OffsetTime 10:30+03:00>"} with-offset ([o offset] (java-time.zone/with-offset o offset)))
(defn ^{:doc "Sets the offset to the specified value ensuring that the result has the same instant, e.g.:\n\n      (offset-time 10 30 0 0 +2)\n      => #<java.time.OffsetTime 10:30+02:00>\n      (with-offset-same-instant *1 +3)\n      => #<java.time.OffsetTime 11:30+03:00>"} with-offset-same-instant ([o offset] (java-time.zone/with-offset-same-instant o offset)))
(def ^{:arglists (quote ([] [instant] [instant zone])), :doc "Returns a mock implementation of the `java.time.Clock`. The mock supports\n  `advance-clock!` operation which allows to move the time in the clock, e.g.:\n\n  ```\n  (let [clock (mock-clock 0 \"UTC\")]\n    (with-clock clock\n      (is (= (value clock) 0))\n      (is (= (instant) (instant 0)))\n      (advance-clock! clock (j/millis 1))\n      (is (= (value clock) 1))\n      (is (= (instant) (instant 1)))))\n  ```\n\n  You can move the clock back via advancing by a negative temporal amount.\n\n  Creates a clock at epoch in the default time zone when called without arguments.", :tag java.time.Clock} mock-clock java-time.mock/mock-clock)
(def ^{:arglists (quote ([^IMockClock clock amount])), :doc "Advances the `clock` by the given time `amount`.\n\n  This mutates the mock clock."} advance-clock! java-time.mock/advance-clock!)
(def ^{:arglists (quote ([^Clock clock time])), :doc "Sets the `clock` to the given `time`.\n\n  This mutates the mock clock."} set-clock! java-time.mock/set-clock!)
(def ^{:arglists (quote ([e] [e value-fn])), :doc "Converts a time entity to a map of property key -> value as defined by the\n  passed in `value-fn`. By default the actual value of the unit/field is\n  produced.\n\n  ```\n  (as-map (duration))\n  => {:nanos 0, :seconds 0}\n\n  (as-map (local-date 2015 1 1))\n  => {:year 2015, :month-of-year 1, :day-of-month 1, ...}\n  ```"} as-map java-time.convert/as-map)
(def ^{:arglists (quote ([amount from-unit to-unit])), :doc "Converts an amount from one unit to another. Returns a map of:\n\n  * `:whole` - the whole part of the conversion in the `to` unit\n  * `:remainder` - the remainder in the `from` unit\n\n  Arguments may be keywords or instances of `TemporalUnit`.\n\n  Converts between precise units--nanos up to weeks---treating days as exact\n  multiples of 24 hours. Also converts between imprecise units---months up to\n  millennia. See `ChronoUnit` and `IsoFields` for all of the supported units.\n  Does not convert between precise and imprecise units.\n\n  Throws `ArithmeticException` if long overflow occurs during computation.\n\n  ```\n  (convert-amount 10000 :seconds :hours)\n  => {:remainder 2800 :whole 2}\n  ```"} convert-amount java-time.convert/convert-amount)
(def ^{:arglists (quote ([o])), :deprecated true, :doc "Converts a date entity to a `java.util.Date`.\n\n  *Deprecated*:\n  This function only has a single arity and works for entities directly\n  convertible to `java.time.Instant`. Please consider using [[java-date]]\n  instead.", :tag java.util.Date} to-java-date java-time.convert/to-java-date)
(def ^{:arglists (quote ([o])), :deprecated true, :doc "Converts a local date entity to a `java.sql.Date`.\n\n  *Deprecated*:\n  This function only has a single arity and works for entities directly\n  convertible to `java.time.LocalDate`. Please consider using [[sql-date]]\n  instead.", :tag java.sql.Date} to-sql-date java-time.convert/to-sql-date)
(def ^{:arglists (quote ([o])), :deprecated true, :doc "Converts a date entity to a `java.sql.Timestamp`.\n\n  *Deprecated*:\n  This function only has a single arity and works for entities directly\n  convertible to `java.time.Instant`. Please consider using [[sql-timestamp]]\n  instead.", :tag java.sql.Timestamp} to-sql-timestamp java-time.convert/to-sql-timestamp)
(def ^{:arglists (quote ([o])), :doc "Converts a date entity to a `long` representing the number of milliseconds\n  from epoch."} to-millis-from-epoch java-time.convert/to-millis-from-epoch)
(def ^{:arglists (quote ([i])), :doc "Returns true if the given time entity with the\n  `day-of-week` property falls on a Monday, otherwise false."} monday? java-time.sugar/monday?)
(def ^{:arglists (quote ([i])), :doc "Returns true if the given time entity with the\n  `day-of-week` property falls on a Tuesday, otherwise false."} tuesday? java-time.sugar/tuesday?)
(def ^{:arglists (quote ([i])), :doc "Returns true if the given time entity with the\n  `day-of-week` property falls on a Wednesday, otherwise false."} wednesday? java-time.sugar/wednesday?)
(def ^{:arglists (quote ([i])), :doc "Returns true if the given time entity with the\n  `day-of-week` property falls on a Thursday, otherwise false."} thursday? java-time.sugar/thursday?)
(def ^{:arglists (quote ([i])), :doc "Returns true if the given time entity with the\n  `day-of-week` property falls on a Friday, otherwise false."} friday? java-time.sugar/friday?)
(def ^{:arglists (quote ([i])), :doc "Returns true if the given time entity with the\n  `day-of-week` property falls on a Saturday, otherwise false."} saturday? java-time.sugar/saturday?)
(def ^{:arglists (quote ([i])), :doc "Returns true if the given time entity with the\n  `day-of-week` property falls on a Sunday, otherwise false."} sunday? java-time.sugar/sunday?)
(def ^{:arglists (quote ([dt])), :doc "Returns true if argument is [[saturday?]] or [[sunday?]],\n  otherwise false."} weekend? java-time.sugar/weekend?)
(def ^{:arglists (quote ([dt])), :doc "Complement of [[weekend?]]."} weekday? java-time.sugar/weekday?)
(def ^{:arglists (quote ([f initial v & vs])), :doc "Returns a lazy sequence of `initial` , `(apply f initial v vs)`, etc.\n\n  Useful when you want to produce a sequence of temporal entities, for\n  example:\n\n  ```\n  (iterate plus (days 0) 1)\n  => (#<Period P0D> #<Period P1D> #<Period P2D> ...)\n\n  (iterate plus (local-date 2010 1 1) (years 1))\n  => (#<LocalDate 2010-01-01> #<LocalDate 2011-01-01> ...)\n\n  (iterate adjust (local-date 2010 1 1) :next-working-day)\n  => (#<LocalDate 2010-01-01> #<LocalDate 2010-01-04> ...)\n  ```"} iterate java-time.seqs/iterate)
(def ^{:arglists (quote ([entity adjuster & args])), :doc "Adjusts the temporal `entity` using the provided `adjuster` with optional `args`.\n\n  The adjuster should either be a keyword which resolves to one of the\n  predefined adjusters (see [[java-time.repl/show-adjusters]]) an instance of\n  `TemporalAdjuster` or a function which returns another temporal entity when\n  applied to the given one:\n\n  ```\n  (adjust (local-date 2015 1 1) :next-working-day)\n  => #<LocalDate 2015-1-2>\n\n  (adjust (local-date 2015 1 1) :first-in-month :monday)\n  => #<LocalDate 2015-1-5>\n\n  (adjust (local-date 2015 1 1) plus (days 1))\n  => #<LocalDate 2015-1-2>\n  ```"} adjust java-time.adjuster/adjust)
(def ^{:arglists (quote ([o] [fmt o])), :doc "Formats the given time entity as a string.\n\n  Accepts something that can be converted to a `DateTimeFormatter` or a\n  formatter key, e.g. `:iso-offset-time`, as a first argument. Given one\n  argument uses the default format.\n\n  ```\n  (format (zoned-date-time))\n  => \"2015-03-21T09:22:46.677800+01:00[Europe/London]\"\n\n  (format :iso-date (zoned-date-time))\n  \"2015-03-21+01:00\"\n  ```"} format java-time.format/format)
(def ^{:arglists (quote ([fmt] [fmt arg1])), :doc "Constructs a DateTimeFormatter out of a\n\n  * format string - \"yyyy/MM/dd\", \"HH:mm\", etc.\n  * formatter name - :iso-date, :iso-time, etc.\n\n  Accepts a map of options as an optional second argument:\n\n  * `resolver-style` - either `:strict`, `:smart `or `:lenient`\n  * `case` - either `:insensitive` or `:sensitive` (defaults to :sensitive)", :tag java.time.format.DateTimeFormatter} formatter java-time.format/formatter)
(def ^{:arglists (quote ([] [a] [a b])), :doc "Creates a `java.util.Date` out of any combination of arguments valid for\n  [[instant]] or the Instant itself.\n\n  A `java.util.Date` represents an instant in time. It's a direct analog of the\n  `java.time.Instant` type introduced in the JSR-310. Please consider using the\n  `java.time.Instant` (through [[instant]]) directly.", :tag java.util.Date} java-date java-time.pre-java8/java-date)
(def ^{:arglists (quote ([] [arg0] [arg0 arg1] [arg0 arg1 arg2])), :doc "Creates a `java.sql.Date` out of any combination of arguments valid for\n  [[local-date]] or the `LocalDate` itself.\n\n  Please consider using the JSR-310 Java Time types instead of `java.sql.Date`\n  if your drivers support them.\n\n  Even though `java.sql.Date` extends a `java.util.Date`, it's supposed to be\n  used as a local date (no time component or time zone) for the purposes of\n  conversion from/to native JDBC driver DATE types.", :tag java.sql.Date} sql-date java-time.pre-java8/sql-date)
(def ^{:arglists (quote ([] [arg0] [arg0 arg1] [arg0 arg1 arg2] [arg0 arg1 arg2 arg3] [arg0 arg1 arg2 arg3 arg4] [arg0 arg1 arg2 arg3 arg4 arg5] [arg0 arg1 arg2 arg3 arg4 arg5 arg6])), :doc "Creates a `java.sql.Timestamp` in the local time zone out of any combination\n  of arguments valid for [[local-date-time]] or the `LocalDateTime`\n  itself.\n\n  Does not support `Timestamp` construction from an `Instant` or a long millis value---please use\n  [[instant->sql-timestamp]] for this purpose.\n\n  Please consider using the JSR-310 Java Time types instead of\n  `java.sql.Timestamp` if your drivers support them.\n\n  `java.sql.Timestamp` is a version of a `java.util.Date` supposed to be used\n  as a local date-time (no time zone) for the purposes of conversion from/to native\n  JDBC driver TIMESTAMP types.", :tag java.sql.Timestamp} sql-timestamp java-time.pre-java8/sql-timestamp)
(def ^{:arglists (quote ([instant-or-millis])), :doc "Creates a `java.sql.Timestamp` from the provided `instant-or-millis` - a\n  millisecond numeric time value or something convertible to an `Instant`.\n\n  Please consider using the JSR-310 Java Time types instead of\n  `java.sql.Timestamp` if your drivers support them.\n\n  `java.sql.Timestamp` is a version of a `java.util.Date` supposed to be used\n  as a local date-time (no time zone) for the purposes of conversion from/to native\n  JDBC driver TIMESTAMP types."} instant->sql-timestamp java-time.pre-java8/instant->sql-timestamp)
(java-time.util/when-class "java.sql.Time" (def ^{:arglists (quote ([] [arg0] [arg0 arg1] [arg0 arg1 arg2])), :doc "Creates a `java.sql.Time` out of any combination of arguments valid for\n    [[local-time]] (except the nanos constructor) or the `LocalTime`\n    itself.\n\n    Please consider using the JSR-310 Java Time types instead of `java.sql.Time`\n    if your drivers support them.\n\n    Even though `java.sql.Time` extends a `java.util.Date`, it's supposed to be\n    used as a local time (no date component or time zone) for the purposes of\n    conversion from/to native JDBC driver TIME types.", :tag java.sql.Time} sql-time java-time.pre-java8/sql-time))
(defn ^{:doc "Moves the start instant of the interval to the given instant (or something\n   convertible to an instant):\n\n   ```\n   (move-start-to (interval 0 10000) (instant 5000))\n   => #<Interval ...:05Z/...:10Z>\n   ```\n\n   Fails if the new start instant falls after the end instant:\n\n   ```\n   (move-start-to (interval 0 10000) (millis 15000))\n   => DateTimeException...\n   ```"} move-start-to ([i new-start] (java-time.interval/move-start-to i new-start)))
(defn ^{:doc "Moves the end of the interval to the given instant (or something\n   convertible to an instant):\n\n   ```\n   (move-end-to (interval 0 10000) (instant 15000))\n   => #<Interval ...:00Z/...:15Z>\n   ```\n\n   Fails if the new end instant falls before the start instant:\n\n   ```\n   (move-end-to (interval 0 10000) (millis -1))\n   => DateTimeException...\n   ```"} move-end-to ([i new-end] (java-time.interval/move-end-to i new-end)))
(def ^{:arglists (quote ([i & os])), :doc "Moves the start instant of the interval by the sum of given\n  periods/durations/numbers of milliseconds:\n\n  ```\n  (move-start-by (interval 0 10000) (millis 1000) (seconds 1))\n  => #<Interval ...:02Z/...:10Z>\n  ```\n\n  Fails if the new start instant falls after the end instant.\n\n  ```\n  (move-start-by (interval 0 10000) (millis 11000))\n  ;=> DateTimeException...\n  ```"} move-start-by java-time.interval/move-start-by)
(def ^{:arglists (quote ([i & os])), :doc "Moves the end instant of the interval by the sum of given\n  periods/durations/numbers of milliseconds.\n\n  ```\n  (move-start-by (interval 0 10000) (millis 1000) (seconds 1))\n  => #<Interval ...:00Z/...:12Z>\n  ```\n\n  Fails if the new end instant falls before the start instant.\n\n  ```\n  (move-end-by (interval 0 10000) (millis -11000))\n  => DateTimeException...\n  ```"} move-end-by java-time.interval/move-end-by)
(defn ^{:doc "Gets the start instant of the interval"} start ([i] (java-time.interval/start i)))
(defn ^{:doc "Gets the end instant of the interval"} end ([i] (java-time.interval/end i)))
(defn ^{:doc "True if the interval contains the given instant or interval"} contains? ([i o] (java-time.interval/contains? i o)))
(defn ^{:doc "True if this interval overlaps the other one"} overlaps? ([i oi] (java-time.interval/overlaps? i oi)))
(defn ^{:doc "True if this interval abut with the other one"} abuts? ([i oi] (java-time.interval/abuts? i oi)))
(defn ^{:doc "Gets the overlap between this interval and the other one or `nil`"} overlap ([i oi] (java-time.interval/overlap i oi)))
(defn ^{:doc "Gets the gap between this interval and the other one or `nil`"} gap ([i oi] (java-time.interval/gap i oi)))
(java-time.util/when-class "org.threeten.extra.Temporals" (def ^{:arglists (quote ([^String o] [a b])), :doc "Constructs an interval out of a string, start and end instants or a start\n    + duration:\n\n    ```\n    (j/interval \"2010-01-01T00:00:00Z/2013-01-01T00:00:00Z\")\n    => #<Interval 2010-01-01T00:00:00Z/2013-01-01T00:00:00Z>\n\n    (j/interval (j/instant 100000) (j/instant 1000000))\n    => #<Interval 1970-01-01T00:01:40Z/1970-01-01T00:16:40Z>\n\n    (j/interval (j/instant 100000) (j/duration 15 :minutes))\n    => #<Interval 1970-01-01T00:01:40Z/1970-01-01T00:16:40Z>\n    ```\n\n    Requires the optional `threeten-extra` dependency.", :tag org.threeten.extra.Interval} interval java-time.interval/interval) (def ^{:arglists (quote ([o])), :doc "True if `Interval`"} interval? java-time.interval/interval?) (def ^{:arglists (quote ([] [v] [fmt arg])), :doc "Returns the `AmPm` for the given keyword name (`:am` or `:pm`),\n    ordinal or entity. Current AM/PM if no arguments given.", :tag org.threeten.extra.AmPm} am-pm java-time.single-field/am-pm) (def ^{:arglists (quote ([o])), :doc "True if `org.threeten.extra.AmPm`."} am-pm? java-time.single-field/am-pm?) (def ^{:arglists (quote ([] [v] [fmt arg])), :doc "Returns the `Quarter` for the given quarter keyword name (e.g. `:q1`),\n    ordinal or entity. Current quarter if no arguments given.", :tag org.threeten.extra.Quarter} quarter java-time.single-field/quarter) (def ^{:arglists (quote ([o])), :doc "True if `org.threeten.extra.Quarter`."} quarter? java-time.single-field/quarter?) (def ^{:arglists (quote ([] [arg] [fmt arg])), :doc "Returns the `DayOfMonth` for the given entity, clock, zone or day of month.\n    Current day of month if no arguments given.", :tag org.threeten.extra.DayOfMonth} day-of-month java-time.single-field/day-of-month) (def ^{:arglists (quote ([o])), :doc "Returns true if `o` is `org.threeten.extra.DayOfMonth`, otherwise false."} day-of-month? java-time.single-field/day-of-month?) (def ^{:arglists (quote ([] [arg] [fmt arg])), :doc "Returns the `DayOfYear` for the given entity, clock, zone or day of year.\n    Current day of year if no arguments given.", :tag org.threeten.extra.DayOfYear} day-of-year java-time.single-field/day-of-year) (def ^{:arglists (quote ([o])), :doc "Returns true if `o` is `org.threeten.extra.DayOfYear`, otherwise false."} day-of-year? java-time.single-field/day-of-year?) (def ^{:arglists (quote ([] [arg] [a b])), :doc "Returns the `YearQuarter` for the given entity, clock, zone or year with quarter.\n    Current year quarter if no arguments given.", :tag org.threeten.extra.YearQuarter} year-quarter java-time.single-field/year-quarter) (def ^{:arglists (quote ([o])), :doc "Returns true if `o` is `org.threeten.extra.YearQuarter`, otherwise false."} year-quarter? java-time.single-field/year-quarter?))
