package org.example.RDF;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;

public class Queries4 {


    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        model.read("C:\\Users\\robin\\Desktop\\Trabajos\\RDFStationsWithJena\\src\\main\\java\\org\\example\\assets\\estaciones.ttl");

        Query query;
        String queryString = "PREFIX est: <http://example.org/estaciones/> "
                + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> "
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
                + "SELECT ?estacion ?nombreEstacion (STR(COUNT(?geoPoint)) AS ?nroPuntos)"
                + "WHERE "
                + "{ ?geoPoint a geo:Point ; "
                + "     geo:location ?estacion . "
                + "  ?estacion rdfs:label ?nombreEstacion ."
                + "}"
                + "GROUP BY ?estacion ?nombreEstacion "
                + "HAVING (count(?geoPoint) >2)";

        query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect() ;
        while(results.hasNext()){
            QuerySolution qs = results.nextSolution();
            RDFNode estacion = qs.get("estacion");
            RDFNode nombreEstacion = qs.get("nombreEstacion");
            RDFNode nroPuntos = qs.get("nroPuntos");
            String[] split = estacion.toString().split(":");
            System.out.println("La estación " + split[split.length-1] + " con nombre "+ nombreEstacion + " tiene "+ nroPuntos + " puntos de parada");
        }

        
        qexec.close();
    }

}


