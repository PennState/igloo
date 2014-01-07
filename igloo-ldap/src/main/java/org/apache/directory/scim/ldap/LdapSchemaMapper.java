/*
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */
package org.apache.directory.scim.ldap;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.schema.SchemaManager;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.scim.SchemaMapper;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * TODO LdapSchemaMapper.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class LdapSchemaMapper implements SchemaMapper
{
    private SchemaManager ldapSchema;

    private static final Logger LOG = LoggerFactory.getLogger( LdapSchemaMapper.class );


    public LdapSchemaMapper( LdapConnection connection )
    {
        try
        {
            connection.loadSchema();
        }
        catch ( LdapException e )
        {
            LOG.debug( "Failed to load schema from the server", e );
            LOG.info( "Could not load schema from the LDAP server, disabling schema checks" );
        }

        ldapSchema = connection.getSchemaManager();
    }


    public void loadMappings()
    {
        InputStream in = this.getClass().getResourceAsStream( "escimo-ldap-mapping.xml" );
        loadMappings( in );
    }


    public void loadMappings( InputStream in )
    {
        if ( in == null )
        {
            throw new IllegalArgumentException( "Mapping file inputstream cannot be null" );
        }

        BufferedReader r = new BufferedReader( new InputStreamReader( in ) );

        try
        {
            StringBuilder sb = new StringBuilder();
            String s = null;
            
            while ( ( s = r.readLine() ) != null )
            {
                sb.append( s )
                  .append( "\n" );
            }
            
            Document doc = DocumentHelper.parseText( sb.toString() );
            
            // the entities element
            Element root = doc.getRootElement();
            if ( root.elements().isEmpty() )
            {
               throw new IllegalStateException( "Invalid schema mapping file" ); 
            }            
        }
        catch ( Exception e )
        {
            LOG.warn( "Failed to load the schema mappings", e );
            throw new RuntimeException( e );
        }
        finally
        {
            if( r != null )
            {
                try
                {
                    r.close();
                }
                catch( IOException e )
                {
                    LOG.warn( "Failed to close the inputstream of the schema mapping file", e );
                }
            }
        }
    }

}
