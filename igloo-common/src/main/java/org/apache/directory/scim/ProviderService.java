/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.directory.scim;

import java.util.List;

import org.apache.directory.scim.common.Group;
import org.apache.directory.scim.common.User;

import org.apache.directory.scim.search.Criteria;

/**
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public interface ProviderService 
{

  User createUser( User user );
  void deleteUser( User user );
  List<User> findUsers( Criteria criteria );
  User getUser( String id );
  User mergeUser( User user );
  User replaceUser( User user );
  
  Group createGroup( Group group );
  void deleteGroup( Group group );
  List<Group> findGroups( Criteria criteria );
  Group getGroup( String id );
  Group mergeGroup( Group group );
  Group replaceGroup( Group group );
  
}
