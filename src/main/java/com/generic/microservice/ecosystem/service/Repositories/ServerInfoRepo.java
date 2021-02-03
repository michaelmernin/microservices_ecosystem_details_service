package com.generic.microservice.ecosystem.service.Repositories;

import com.generic.microservice.ecosystem.service.Nodes.ServerNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ServerInfoRepo extends Neo4jRepository<ServerNode, Long> {

    ServerNode getByName(String name);

    @Query("MATCH path = (:Server {name: {name}})<-[:DEPLOYED_TO]-(a:Api)" +
            "with collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value")
    Map<String, String> getAllApisDeployedToServerWithAPOC(@Param("name") String name);

    @Query("MATCH (a:Api)-[:DEPLOYED_TO]->(:Server {name: {name}}) RETURN a.name")
    List<String> getAllApisDeployedToServerAsList(@Param("name") String name);
}
