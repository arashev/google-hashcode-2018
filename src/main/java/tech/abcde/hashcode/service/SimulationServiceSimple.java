package tech.abcde.hashcode.service;

import tech.abcde.hashcode.model.Position;
import tech.abcde.hashcode.model.Ride;
import tech.abcde.hashcode.model.Rules;

import java.util.*;

import static jdk.nashorn.internal.objects.NativeMath.min;
import static tech.abcde.hashcode.AppUtils.distance;

public class SimulationServiceSimple implements SimulatorService {

    @Override
    public Map<Long, List<Ride>> simulate(Rules rules, List<Ride> allRides) {
        long vehicle = 0;
        Map<Long, List<Ride>> result = new HashMap<>();
        while (vehicle++ < rules.vehicleNumber) {
            List<Ride> rides = new ArrayList<>();
            long time = 0;
            Position position = new Position(0, 0);

            allRides = sortByNearestEnd(allRides);

            for (Iterator<Ride> iterator = allRides.iterator(); iterator.hasNext(); ) {
                Ride ride = iterator.next();
                if (canAccept(position, time, ride, rules.stepsLimit)) {
                    rides.add(ride);
                    iterator.remove();
                    time += nextFinishTime(time, position, ride);
                    position = ride.end;
                }
            }
            result.put(vehicle, rides);
        }
        return result;
    }

    static List<Ride> sortByNearestEnd(List<Ride> rides) {
        if (rides.size() < 3) return rides;

        for (int i = 0; i < rides.size() - 1; i++) {
            long min = distance(rides.get(i).end, rides.get(i + 1).start);
            int best = i + 1;
            for (int j = i + 2; j < rides.size(); j++) {
                long distance = distance(rides.get(i).end, rides.get(j).start);
                if (distance < min) {
                    min = distance;
                    best = j;
                }
            }
            rides.add(i + 1, rides.remove(best));
        }
        return rides;
    }

    static long nextFinishTime(long time, Position position, Ride ride) {
        long arrivalTime = distance(position, ride.start);
        long tripTime = distance(ride.start, ride.end);
        return time + arrivalTime + tripTime;
    }

    static boolean canAccept(Position position, long time, Ride ride, long stepsLimit) {
        return nextFinishTime(time, position, ride) < min(ride.end, stepsLimit);
    }
}
