package org.example.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.models.Station;

public class ReadFile {
        public static List<Station> loadStations(String filePath) {
            List<Station> stations = new ArrayList<>();
            String line = "";

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                br.readLine(); // Omitir el encabezado

                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",");

                    if (fields[0].startsWith("StopArea:")) {
                        String stopId = fields[0].trim();
                        String stopName = fields[1].trim();
                        double latitude = Double.parseDouble(fields[3].trim());
                        double longitude = Double.parseDouble(fields[4].trim());

                        Station station = new Station(stopId, stopName, latitude, longitude);
                        stations.add(station);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stations;
        }
    }


