package com.generic.microservice.ecosystem.service.Repositories;

import com.generic.microservice.ecosystem.service.Nodes.ApiNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DependenciesRepo extends Neo4jRepository<ApiNode, Integer> {

    @Query("MATCH (:Api {name: {name}})-[:HAS_DEPENDENCY*]->(a:Api) RETURN DISTINCT a.name")
    List<String> getAllDependenciesAsList(@Param("name") String name);

    @Query("MATCH path = (:Api {name: {name}})-[:HAS_DEPENDENCY*]->(a:Api)" +
            "with collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value")
    Map<String, String> getAllDependenciesWithAPOC(@Param("name") String name);

}
