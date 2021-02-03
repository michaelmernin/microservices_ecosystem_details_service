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
@NodeEntity(label = "Server")
public class ServerNode {

    @GeneratedValue
    @Id
    private int id;
    private String name;
    private String ssh_port;
    private String os_type;
}
