package wooteco.subway.path.infrastructure;

import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import wooteco.subway.line.domain.Section;
import wooteco.subway.station.domain.Station;

public class PathWithDijkstra extends PathGraph {
    private final GraphPath dijkstraShortestPath;

    public PathWithDijkstra(List<Section> sections, Station source, Station target) {
        super(sections);
        this.dijkstraShortestPath = new DijkstraShortestPath(graph).getPath(source, target);
    }

    @Override
    public List<Station> getStations() {
        return dijkstraShortestPath.getVertexList();
    }

    @Override
    public int getDistance() {
        return (int)dijkstraShortestPath.getWeight();
    }
}
