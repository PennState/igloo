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

import org.apache.directory.scim.models.ScimGroup;
import org.apache.directory.scim.models.ScimResponse;
import org.apache.directory.scim.models.ScimUser;
import org.apache.directory.scim.search.Query;

/**
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public interface ProviderService 
{

  ScimUser createUser( ScimUser user );
  void deleteUser( ScimUser user );
  ScimResponse findUsers( Query query );
  ScimUser getUser( String id );
  ScimUser mergeUser( ScimUser user );
  ScimUser replaceUser( ScimUser user );
  
  ScimGroup createGroup( ScimGroup group );
  void deleteGroup( ScimGroup group );
  ScimResponse findGroups( Query query );
  ScimGroup getGroup( String id );
  ScimGroup mergeGroup( ScimGroup group );
  ScimGroup replaceGroup( ScimGroup group );
  
}
