package com.generic.microservice.ecosystem.service.Repositories;

import com.generic.microservice.ecosystem.service.Nodes.ApiNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ApiInfoRepo extends Neo4jRepository<ApiNode, Long> {

    ApiNode getByName(String name);

    ApiNode getById(int id);

    @Query("MATCH (a: Api) RETURN DISTINCT a.name")
    Set<String> getAllServiceNames();

    @Query("MATCH (:Api {name: {name}})-[:DEPLOYED_TO]->(s:Server) RETURN s.name")
    List<String> getAllServersRunningApi(@Param("name") String name);
}
