package software.aoc.day12.record;

import software.aoc.day12.a.Shape;

import java.util.List;
import java.util.Map;

public record InputData(Map<Integer, Shape> shapes, List<RegionQuery> queries) {
}
