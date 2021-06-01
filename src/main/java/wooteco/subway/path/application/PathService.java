package wooteco.subway.path.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import wooteco.subway.line.dao.SectionDao;
import wooteco.subway.line.domain.Section;
import wooteco.subway.path.domain.Path;
import wooteco.subway.path.dto.PathResponse;
import wooteco.subway.path.infrastructure.PathWithDijkstra;
import wooteco.subway.station.dao.StationDao;
import wooteco.subway.station.domain.Station;
import wooteco.subway.station.dto.StationResponse;

@Service
public class PathService {
    private final SectionDao sectionDao;
    private final StationDao stationDao;

    public PathService(SectionDao sectionDao, StationDao stationDao) {
        this.sectionDao = sectionDao;
        this.stationDao = stationDao;
    }

    public PathResponse findPaths(long sourceId, long targetId) {
        List<Section> sections = sectionDao.findAll();

        Station source = stationDao.findById(sourceId);
        Station target = stationDao.findById(targetId);

        Path path = new Path(new PathWithDijkstra(sections));
        List<StationResponse> stations = path.getStations(source, target).stream()
            .map(station -> new StationResponse(station.getId(), station.getName()))
            .collect(Collectors.toList());
        int distance = path.getDistance(source, target);
        return new PathResponse(stations, distance);
    }
}
