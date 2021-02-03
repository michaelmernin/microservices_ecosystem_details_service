package com.generic.microservice.ecosystem.service.Nodes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;


@NoArgsConstructor
@Getter
@Setter
@NodeEntity(label = "Api")
public class ApiNode {

    @GeneratedValue
    @Id
    private int id;
    private String name;
    private int api_port;
    private String script_name;
    private String service_name;
}
