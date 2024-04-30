package org.example.RDF;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;

public class Queries3 {


    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        model.read("C:\\Users\\robin\\Desktop\\Trabajos\\RDFStationsWithJena\\src\\main\\java\\org\\example\\assets\\estaciones.ttl");

        Query query;
        String queryString = "PREFIX est: <http://example.org/estaciones/> "
                + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> "
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
                + "SELECT ?nombreEstacion (STR(?latitud) AS ?lat) (STR(?longitud) AS ?lon)"
                + "WHERE "
                + "{ ?estacion a geo:SpatialThing . "
                + "?estacion rdfs:label ?nombreEstacion. "
                + "?estacion geo:lat ?latitud;"
                + "     geo:lon ?longitud ."
                + "FILTER (?latitud >= 48.75 && ?latitud <= 50 && ?longitud >= 6 && ?longitud <= 7.9)"
                + "}";

        query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect() ;
        while(results.hasNext()){
            QuerySolution qs = results.nextSolution();
            RDFNode estacion = qs.get("nombreEstacion");
            RDFNode latitud = qs.get("lat");
            RDFNode longitud = qs.get("lon");
            System.out.println(estacion + " está localizado en ("+latitud + "," + longitud + ")");
        }

        
        qexec.close();
    }

}


