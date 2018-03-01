package tech.abcde.hashcode;

import tech.abcde.hashcode.model.Ride;
import tech.abcde.hashcode.model.Rules;
import tech.abcde.hashcode.service.FileParserService;
import tech.abcde.hashcode.service.FileParserServiceImpl;
import tech.abcde.hashcode.service.SimulationServiceSimple;
import tech.abcde.hashcode.service.SimulatorService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException {
        FileParserService service = new FileParserServiceImpl();
        SimulatorService simulatorService = new SimulationServiceSimple();
        Path data = Paths.get("./src/main/resources/data/");
        Path out = Paths.get("./src/main/resources/results/");
        Files.newDirectoryStream(data).forEach(
                path -> {
                    String fileName = path.getFileName().toString();
                    List<Ride> rides = service.readRides(path);
                    Rules rules = service.readRules(path);
                    Map<Long, List<Ride>> result = simulatorService.simulate(rules, rides);
                    service.saveResult(result, out.resolve(fileName));
                }
        );
    }
}
