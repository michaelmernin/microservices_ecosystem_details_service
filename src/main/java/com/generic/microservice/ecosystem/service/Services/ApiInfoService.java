package com.generic.microservice.ecosystem.service.Services;

import com.generic.microservice.ecosystem.service.Repositories.ApiInfoRepo;
import com.generic.microservice.ecosystem.service.Models.ApiInfo;
import com.generic.microservice.ecosystem.service.Nodes.ApiNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ApiInfoService {

    @Autowired
    private ApiInfoRepo apiInfoRepo;

    public ApiInfo getApiInfoByName(String name) throws Exception {
        ApiNode info = apiInfoRepo.getByName(name);
        if (info == null) {
            throw new Exception("No apis found with given name: " + name);
        }
        return new ApiInfo(info);
    }

    public ApiNode getApiNodeByName(String name) throws Exception {
        ApiNode info = apiInfoRepo.getByName(name);
        if (info == null) {
            throw new Exception("No apis found with given name: " + name);
        }
        return info;
    }

    public ApiNode getApiInfoById(int id) throws Exception {
        ApiNode info = apiInfoRepo.getById(id);
        if (info == null) {
            throw new Exception("No apis found with given id: " + id);
        }
        return info;
    }

    public Set<String> getAllServiceNames() throws Exception {
        Set<String> serviceNames = apiInfoRepo.getAllServiceNames();
        if (serviceNames == null) {
            throw new Exception("No service names found");
        }
        return serviceNames;
    }

    public List<String> getAllServersRunningApi(String name) {
        return apiInfoRepo.getAllServersRunningApi(name);
    }
}
