package com.generic.microservice.ecosystem.service.Services;

import com.generic.microservice.ecosystem.service.Repositories.ServerInfoRepo;
import com.generic.microservice.ecosystem.service.Models.ServerInfo;
import com.generic.microservice.ecosystem.service.Nodes.ServerNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ServerInfoService {

    @Autowired
    private ServerInfoRepo serverInfoRepo;

    public ServerInfo getServerInfoByServerName(String name) {
        ServerNode serverNode = serverInfoRepo.getByName(name);
        return new ServerInfo(serverNode);
    }

    public JSONObject getAllApisDeployedToServerAsJSON(String name) {
        return new JSONObject(serverInfoRepo.getAllApisDeployedToServerWithAPOC(name));
    }

    public Map<String, String> getAllApisDeployedToServerAsObject(String name) {
        return serverInfoRepo.getAllApisDeployedToServerWithAPOC(name);
    }

    public List<String> getAllApisDeployedToServerAsList(String name) {
        return serverInfoRepo.getAllApisDeployedToServerAsList(name);
    }

}
