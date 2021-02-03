package com.generic.microservice.ecosystem.service.Models;

import com.generic.microservice.ecosystem.service.Nodes.ApiNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApiInfo {

    String name;
    String serviceName;
    List<String> deployed_servers;
    List<String> dependencies;

    public ApiInfo(ApiNode apiNode) {
        this.name = apiNode.getName();
        this.serviceName = apiNode.getService_name();
    }
}
