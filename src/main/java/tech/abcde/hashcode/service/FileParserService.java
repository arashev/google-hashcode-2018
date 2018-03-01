package tech.abcde.hashcode.service;

import tech.abcde.hashcode.model.Ride;
import tech.abcde.hashcode.model.Rules;

import java.nio.file.Path;
import java.util.List;

public interface FileParserService {
	Rules parseRules(Path filePath);

	List<Ride> parseRides(Path filePath);
}
