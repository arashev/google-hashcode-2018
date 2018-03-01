package tech.abcde.hashcode.service;

import tech.abcde.hashcode.model.Ride;
import tech.abcde.hashcode.model.Rules;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface FileParserService {
	String SEPARATOR = " ";

	Rules readRules(Path filePath);

	List<Ride> readRides(Path filePath);

	void saveResult(Map<Long, List<Ride>> numberOfRidesToRides, Path filePath);
}
