package vn.demo.demo;

import java.util.List;
import java.util.stream.Stream;

public class Number {
    public Stream<String> streamOf(List<String> list) {
        return list == null || list.isEmpty() ? Stream.empty() : list.stream();
    }


}
