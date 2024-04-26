package org.example.RDF;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
import org.example.utils.ReadFile;
import org.example.models.Station;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CreateRDFGraph {


    public static void main(String[] args) {
        String inputFile = "C:\\Users\\Robinson\\Downloads\\stops.txt";
        String outputFile = "C:\\Users\\Robinson\\Downloads\\stations.ttl"; // Cambio de extensión a .ttl

        Model model = ModelFactory.createDefaultModel();
        String base = "http://example.org/estaciones/";
        model.setNsPrefix("est", base);
        model.setNsPrefix("geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");
        model.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");

        List<Station> stations = ReadFile.loadStations(inputFile);

        for (Station station : stations) {
            String iri = base + "StopArea:" + station.getStopId();
            Resource stationResource = model.createResource(iri)
                    .addProperty(RDF.type, model.createResource("http://www.w3.org/2003/01/geo/wgs84_pos#SpatialThing"))
                    .addProperty(RDFS.label, model.createLiteral(station.getStopName(), "fr"))
                    .addProperty(model.createProperty("http://www.w3.org/2003/01/geo/wgs84_pos#lat"), model.createTypedLiteral(station.getLatitude(), XSDDatatype.XSDdouble))
                    .addProperty(model.createProperty("http://www.w3.org/2003/01/geo/wgs84_pos#long"), model.createTypedLiteral(station.getLongitude(), XSDDatatype.XSDdouble));
        }

        // Escribir el modelo RDF en un archivo en formato Turtle
        try (OutputStream out = new FileOutputStream(outputFile)) {
            model.write(out, "TTL");  // Puedes cambiar "TTL" por "RDF/XML" para otro formato
            System.out.println("Modelo RDF creado exitosamente y guardado en: " + outputFile);

        } catch (IOException e) {
            System.out.println("\n-----------\nError");
            e.printStackTrace();
        }
    }
}


