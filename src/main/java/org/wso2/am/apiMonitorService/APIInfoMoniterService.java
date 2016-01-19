/*
*Copyright (c) 2005-2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/
package org.wso2.am.apiMonitorService;

import org.apache.axis2.AxisFault;
import org.wso2.am.apiMonitorService.beans.APIDeployStatus;
import org.wso2.am.apiMonitorService.beans.APIStats;
import org.wso2.am.apiMonitorService.beans.APIStatusData;
import org.wso2.am.apiMonitorService.beans.TenantStatus;
import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.api.model.APIIdentifier;
import org.wso2.carbon.apimgt.api.model.APIStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/api_detail/")
public class APIInfoMoniterService {

    APIStatusUtil apiStatusUtil;
    APIStats stats;
    APIDeployStatus apiStatus;
    APIMFactoryUtill apiInfoMoniterService;

    public APIInfoMoniterService() throws AxisFault {
        stats = new APIStats();
        apiStatus = new APIDeployStatus();
        apiInfoMoniterService = new APIMFactoryUtill();
    }

    @Path("api/{user}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Provide the  status of tenant.
     */
    public APIStats getApiListForUser(@PathParam("user") String user) throws APIManagementException {
        stats.setListOfApiNames(apiInfoMoniterService.getAllApisForTenant(user));
        stats.setDeployedApiCount(apiInfoMoniterService.getAllApisForTenant(user).length);
      return stats;
    }

    @Path("api/{user}/{apiName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Provide the  status of tenant.
     */
    public APIStatusData getApiForUser(@PathParam("user") String user, @PathParam("apiName") String apiName)
            throws
                                                                                          APIManagementException {
        return apiInfoMoniterService.getSpecificApiInfo(user,apiName);
    }


    @Path("api/{user}/{apiName}/{version}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Provide the  status of tenant.
     */
    public APIStatusData getApiForUserByVersion(@PathParam("user") String user, @PathParam("apiName") String
            apiName,
                                           @PathParam("version") String version)
            throws
            APIManagementException {
        return apiInfoMoniterService.getSpecificApiInfoByVersion(user,apiName, version);
    }
}
