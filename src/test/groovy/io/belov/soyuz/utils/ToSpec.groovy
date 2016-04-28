package io.belov.soyuz.utils

import io.belov.soyuz.utils.to as To;

import spock.lang.Specification

/**
 * Created by fbelov on 30.11.15.
 */
class ToSpec extends Specification {

    def "should convert to map"() {
        setup:
        def a

        when:
        a = To.map(1, "1")

        then:
        assert a == [(1): "1"]

        when:
        a = To.map(1, "1", 2, "2")

        then:
        assert a == [(1): "1", (2): "2"]

        when:
        a = To.map(1, "1", 2, "2", 3, "3")

        then:
        assert a == [(1): "1", (2): "2", (3): "3"]

        when:
        a = To.map(1, "1", 2, "2", 3, "3", 4, "4")

        then:
        assert a == [(1): "1", (2): "2", (3): "3", (4): "4"]

        when:
        a = To.map(1, "1", 2, "2", 3, "3", 4, "4", 5, "5")

        then:
        assert a == [(1): "1", (2): "2", (3): "3", (4): "4", (5): "5"]
    }

    def "should convert params to map"() {
        when:
        def a = To.map(1, "1", "2", 2, "3", 3, "4", 4, "5", 5, "6", 6)

        then:
        assert a == [(1): "1", "2": 2, "3": 3, "4": 4, "5": 5, "6": 6]
    }

    def "should use values from source map"() {
        when:
        def source = [hello: "world"]
        def a = To.map(source, "again", "=)")

        then:
        assert a == [hello: "world", again: "=)"]
        assert source == [hello: "world"]
    }

    def "shouldn't convert to map with odd params number"() {
        when:
        To.map("a")

        then:
        thrown(IllegalArgumentException)
    }

    def "should return log message"() {
        when:
        def doesNotMatter

        then:
        assert To.log("hello") == "[hello]"

        then:
        assert To.log("world", To.map("a", "b", "c", "d")) == "[world]{a=b, c=d}"
    }
}
