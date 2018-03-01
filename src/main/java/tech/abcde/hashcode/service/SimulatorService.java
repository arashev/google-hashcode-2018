package tech.abcde.hashcode.service;

import tech.abcde.hashcode.model.Ride;
import tech.abcde.hashcode.model.Rules;

import java.util.List;
import java.util.Map;

public interface SimulatorService {
	Map<Long, List<Ride>> simulate(Rules rules, List<Ride> rides);
}
