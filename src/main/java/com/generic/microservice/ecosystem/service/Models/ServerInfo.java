package com.generic.microservice.ecosystem.service.Models;

import com.generic.microservice.ecosystem.service.Nodes.ServerNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ServerInfo {

    String name;
    String osType;
    String sshPort;
    List<String> deployed_services;

    public ServerInfo(ServerNode serverNode) {
        this.name = serverNode.getName();
        this.osType = serverNode.getOs_type();
        this.sshPort = serverNode.getSsh_port();
    }
}
