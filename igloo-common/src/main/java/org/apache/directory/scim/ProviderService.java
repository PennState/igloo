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

import org.apache.directory.scim.exceptions.ScimException;
import org.apache.directory.scim.models.ScimExtension;
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
  
  ScimUser createUser( ScimUser user ) throws ScimException;
  void deleteUser( String id ) throws ScimException;
  ScimResponse<ScimUser> findUsers( Query query ) throws ScimException;
  ScimUser getUser( String id ) throws ScimException;
  ScimUser mergeUser( String id, ScimUser user ) throws ScimException;
  ScimUser replaceUser( String id, ScimUser user ) throws ScimException;
  
  ScimGroup createGroup( ScimGroup group ) throws ScimException;
  void deleteGroup( String id ) throws ScimException;
  ScimResponse<ScimGroup> findGroups( Query query ) throws ScimException;
  ScimGroup getGroup( String id ) throws ScimException;
  ScimGroup mergeGroup( String id, ScimGroup group ) throws ScimException;
  ScimGroup replaceGroup( String id, ScimGroup group ) throws ScimException;
  
  List<Class<? extends ScimExtension>> getExtensionClasses();

}
