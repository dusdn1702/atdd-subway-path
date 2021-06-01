package wooteco.subway.path.domain;

import java.util.List;

import wooteco.subway.path.infrastructure.PathGraph;
import wooteco.subway.station.domain.Station;

public class Path {
    private final PathGraph pathGraph;

    public Path(PathGraph pathGraph) {
        this.pathGraph = pathGraph;
    }

    public List<Station> getStations(Station source, Station target) {
        return pathGraph.getStations(source, target);
    }

    public int getDistance(Station source, Station target) {
        return pathGraph.getDistance(source, target);
    }
}
