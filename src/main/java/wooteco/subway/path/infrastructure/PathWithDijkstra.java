package wooteco.subway.path.infrastructure;

import java.util.List;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import wooteco.subway.line.domain.Section;
import wooteco.subway.station.domain.Station;


public class PathWithDijkstra extends PathGraph {
    public PathWithDijkstra(List<Section> sections) {
        super(new WeightedMultigraph<>(DefaultWeightedEdge.class), sections);
    }

    @Override
    public List<Station> getStations(Station source, Station target) {
        return new DijkstraShortestPath(graph).getPath(source, target).getVertexList();
    }

    @Override
    public int getDistance(Station source, Station target) {
        return (int) new DijkstraShortestPath(graph).getPath(source, target).getWeight();
    }
}
