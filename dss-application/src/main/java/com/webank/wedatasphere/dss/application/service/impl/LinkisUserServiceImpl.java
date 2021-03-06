/*
 * Copyright 2019 WeBank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.webank.wedatasphere.dss.application.service.impl;

import com.webank.wedatasphere.dss.application.dao.DSSUserMapper;
import com.webank.wedatasphere.dss.application.dao.LinkisUserMapper;
import com.webank.wedatasphere.dss.application.entity.DSSUser;
import com.webank.wedatasphere.dss.application.entity.LinkisUser;
import com.webank.wedatasphere.dss.application.service.LinkisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by chaogefeng on 2019/11/29.
 */
@Component
public class LinkisUserServiceImpl implements LinkisUserService {

    @Autowired
    private LinkisUserMapper linkisUserMapper;
    @Autowired
    private DSSUserMapper dssUserMapper;

    @Override
    public LinkisUser getUserByName(String username) {
        return linkisUserMapper.getUserByName(username);
    }


    @Override
    public void registerLinkisAndDssUser(LinkisUser userDb) {
        // TODO: 2019/11/29 @transactional
        linkisUserMapper.registerLinkisUser(userDb);
        registerDSSUser(userDb);
    }

    @Override
    public void registerDSSUser(LinkisUser userDb) {
        DSSUser dssUser = new DSSUser();
        dssUser.setId(userDb.getId());
        dssUser.setName(userDb.getName());
        dssUser.setUsername(userDb.getUserName());
        dssUser.setFirstLogin(userDb.getFirstLogin());
        dssUserMapper.registerDSSUser(dssUser);
    }
}
