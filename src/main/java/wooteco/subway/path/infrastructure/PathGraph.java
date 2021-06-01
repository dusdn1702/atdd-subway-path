package wooteco.subway.path.infrastructure;

import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import wooteco.subway.line.domain.Section;
import wooteco.subway.station.domain.Station;

public abstract class PathGraph {
    final WeightedMultigraph<Station, DefaultWeightedEdge> graph;

    public PathGraph(List<Section> sections) {
        this.graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        createGraph(sections);
    }

    private void createGraph(List<Section> sections) {
        for (Section section : sections) {
            graph.addVertex(section.getUpStation());
            graph.addVertex(section.getDownStation());
            graph.setEdgeWeight(graph.addEdge(section.getUpStation(), section.getDownStation()), section.getDistance());
        }
    }

    public abstract List<Station> getStations();

    public abstract int getDistance();
}
