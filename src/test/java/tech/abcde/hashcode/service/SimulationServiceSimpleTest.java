package tech.abcde.hashcode.service;

import org.junit.Test;
import tech.abcde.hashcode.model.Position;
import tech.abcde.hashcode.model.Ride;
import tech.abcde.hashcode.model.Rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SimulationServiceSimpleTest {

    @Test
    public void nextFinishTime() {
        Ride ride = new Ride(1, new Position(0, 0), new Position(0, 0), 0, 6);
        long finishTime = SimulationServiceSimple.nextFinishTime(0, new Position(0, 0), ride);
        System.out.println(finishTime);
    }


    @Test
    public void testSort() {
        Ride ride1 = new Ride(1, new Position(0, 0), new Position(0, 1), 0, 6);
        Ride ride2 = new Ride(2, new Position(0, 2), new Position(0, 3), 0, 6);
        Ride ride3 = new Ride(3, new Position(0, 4), new Position(0, 7), 0, 6);
        Ride ride4 = new Ride(4, new Position(0, 8), new Position(0, 9), 0, 6);

        List<Ride> rides = new ArrayList<>(Arrays.asList(ride1, ride4, ride2, ride3));
        SimulationServiceSimple.sortByNearestEnd(rides).forEach(System.out::println);
    }

    @Test
    public void testBase() {
        Ride ride1 = new Ride(1, new Position(0, 0), new Position(0, 1), 0, 6);
        Ride ride2 = new Ride(2, new Position(0, 2), new Position(0, 3), 0, 6);
        Ride ride3 = new Ride(3, new Position(0, 4), new Position(0, 7), 0, 6);
        Ride ride4 = new Ride(4, new Position(0, 8), new Position(0, 9), 0, 6);

        List<Ride> rides = new ArrayList<>(Arrays.asList(ride1, ride4, ride2, ride3));
        Map<Long, List<Ride>> map = new SimulationServiceSimple().simulate(new Rules(2, 2, 10), rides);
        map.forEach((k, v) -> System.out.println(k + "   " + v));
    }
}