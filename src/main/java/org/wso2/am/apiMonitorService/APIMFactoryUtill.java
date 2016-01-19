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

import org.wso2.am.apiMonitorService.beans.APIStatusData;
import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.api.model.API;
import org.wso2.carbon.apimgt.impl.APIManagerFactory;

import java.util.LinkedList;
import java.util.List;


public class APIMFactoryUtill {

    public String[] getAllApisForTenant(String tenatUser) throws APIManagementException {
        List<String> apiNameList = new LinkedList<>();
        List<API> apiList = APIManagerFactory.getInstance().getAPIProvider(tenatUser).getAllAPIs();
        for (int index = 0; index <= apiList.size() - 1; index++) {
            apiNameList.add(apiList.get(index).getId().getApiName());
        }
        //return apiNameList;
        String[] apiArray = new String[apiNameList.size()];
        return apiNameList.toArray(apiArray);
    }

    public APIStatusData getSpecificApiInfo(String tenatUser, String apiName) throws APIManagementException {
        APIStatusData deployStatus = new APIStatusData();
        List<API> apiList = APIManagerFactory.getInstance().getAPIProvider(tenatUser).getAllAPIs();
        for (int index = 0; index <= apiList.size() - 1; index++) {
            if (apiList.get(index).getId().getApiName().equals(apiName)) {
                deployStatus.setProviderName(apiList.get(index).getId().getProviderName());
                deployStatus.setApiName(apiList.get(index).getId().getApiName());
                deployStatus.setVersion(apiList.get(index).getId().getVersion());
                deployStatus.setIsApiExists(true);
                deployStatus.setStatus(apiList.get(index).getStatus().getStatus());
                break;
            } else {
                deployStatus.setIsApiExists(false);
                continue;
            }
        }
        return deployStatus;
    }

    public APIStatusData getSpecificApiInfoByVersion(String tenatUser, String apiName, String version) throws
                                                                                                       APIManagementException {
        APIStatusData deployStatus = new APIStatusData();
        List<API> apiList = APIManagerFactory.getInstance().getAPIProvider(tenatUser).getAllAPIs();
        for (int index = 0; index <= apiList.size() - 1; index++) {
            if (apiList.get(index).getId().getApiName().equals(apiName) && apiList.get(index).getId().getVersion()
                    .equals(version)) {
                deployStatus.setProviderName(apiList.get(index).getId().getProviderName());
                deployStatus.setApiName(apiList.get(index).getId().getApiName());
                deployStatus.setVersion(apiList.get(index).getId().getVersion());
                deployStatus.setIsApiExists(true);
                deployStatus.setStatus(apiList.get(index).getStatus().getStatus());
                break;
            } else {
                deployStatus.setIsApiExists(false);
                continue;
            }
        }
        return deployStatus;
    }
}
