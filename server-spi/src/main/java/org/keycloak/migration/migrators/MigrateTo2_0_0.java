/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.migration.migrators;

import org.keycloak.migration.ModelVersion;
import org.keycloak.models.AdminRoles;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.utils.KeycloakModelUtils;

public class MigrateTo2_0_0 {

    public static final ModelVersion VERSION = new ModelVersion("2.0.0");

    public void migrate(KeycloakSession session) {
        for (RealmModel realm : session.realms().getRealms()) {
            migrateAuthorizationServices(realm);
        }
    }

    private void migrateAuthorizationServices(RealmModel realm) {
        KeycloakModelUtils.setupAuthorizationServices(realm);

        MigrationUtils.addAdminRole(realm, AdminRoles.VIEW_AUTHORIZATION);
        MigrationUtils.addAdminRole(realm, AdminRoles.MANAGE_AUTHORIZATION);
    }

}
