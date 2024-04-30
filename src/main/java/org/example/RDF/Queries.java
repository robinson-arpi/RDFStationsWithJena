package org.example.RDF;

import org.apache.jena.query.* ;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Queries {


    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        model.read("C:\\Users\\robin\\Desktop\\Trabajos\\RDFStationsWithJena\\src\\main\\java\\org\\example\\assets\\estaciones.ttl");

        Query query;
        String queryString = "PREFIX est: <http://example.org/estaciones/> "
                + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> "
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
                + "SELECT ?nombreEstacion "
                + "WHERE "
                + "{ ?estacion a geo:SpatialThing . "
                + "?estacion rdfs:label ?nombreEstacion. }";

        query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect() ;
        ResultSetFormatter.out(System.out, results, query);
        qexec.close();
    }

}


