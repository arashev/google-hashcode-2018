package tech.abcde.hashcode.service;

import tech.abcde.hashcode.model.Position;
import tech.abcde.hashcode.model.Ride;
import tech.abcde.hashcode.model.Rules;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardOpenOption.*;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class FileParserServiceImpl implements FileParserService {

    public static final int ROWS_VALUE_INDEX = 0;
    public static final int COLS_VALUE_INDEX = 1;
    public static final int VEHICLES_VALUE_INDEX = 2;
    public static final int RIDES_VALUE_INDEX = 3;
    public static final int BONUS_VALUE_INDEX = 4;
    public static final int STEPS_VALUE_INDEX = 5;
    public static final int RIDE_FROM_X_COORD = 0;
    public static final int RIDE_FROM_Y_COORD = 1;
    public static final int RIDE_TO_X_COORD = 2;
    public static final int RIDE_TO_Y_COORD = 3;
    public static final int EARLIEST_START_INDEX = 4;
    public static final int LATEST_FINISH_INDEX = 5;

    @Override
    public Rules readRules(Path filePath) {
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String[] firstLine = br.readLine().split(SEPARATOR);
            long rows = Long.parseLong(firstLine[ROWS_VALUE_INDEX]);
            long cols = Long.parseLong(firstLine[COLS_VALUE_INDEX]);
            long vehiclesAmount = Long.parseLong(firstLine[VEHICLES_VALUE_INDEX]);
            long ridesAmount = Long.parseLong(firstLine[RIDES_VALUE_INDEX]);
            long rideBonus = Long.parseLong(firstLine[BONUS_VALUE_INDEX]);
            long stepsLimit = Long.parseLong(firstLine[STEPS_VALUE_INDEX]);
            return new Rules(rows, cols, vehiclesAmount, ridesAmount, rideBonus, stepsLimit);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ride> readRides(Path filePath) {
        List<Ride> rides = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            br.readLine();
            long id = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(SEPARATOR);
                Position start = new Position(Integer.parseInt(split[RIDE_FROM_X_COORD]), Integer.parseInt(split[RIDE_FROM_Y_COORD]));
                Position end = new Position(Integer.parseInt(split[RIDE_TO_X_COORD]), Integer.parseInt(split[RIDE_TO_Y_COORD]));
                long earliestStart = Long.parseLong(split[EARLIEST_START_INDEX]);
                long latestFinish = Long.parseLong(split[LATEST_FINISH_INDEX]);
                Ride ride = new Ride(id, start, end, earliestStart, latestFinish);
                rides.add(ride);
                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rides;
    }

    @Override
    public void saveResult(Map<Long, List<Ride>> numberOfRidesToRides, Path filePath) {
        try (BufferedWriter bw = Files.newBufferedWriter(filePath, CREATE, WRITE, TRUNCATE_EXISTING)) {
            for (Map.Entry<Long, List<Ride>> longListEntry : numberOfRidesToRides.entrySet()) {
                bw.append(Long.toString(longListEntry.getValue().size()));
                bw.append(SEPARATOR);
                for (Ride ride : longListEntry.getValue()) {
                    bw.append(Long.toString(ride.id));
                    bw.append(SEPARATOR);
                }
                bw.newLine();
            }
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
