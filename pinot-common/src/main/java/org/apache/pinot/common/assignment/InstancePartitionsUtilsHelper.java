/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pinot.common.assignment;

import org.apache.helix.ConfigAccessor;
import org.apache.helix.HelixManager;
import org.apache.helix.store.HelixPropertyStore;
import org.apache.helix.zookeeper.datamodel.ZNRecord;
import org.apache.pinot.spi.config.table.TableConfig;
import org.apache.pinot.spi.config.table.assignment.InstancePartitionsType;


public interface InstancePartitionsUtilsHelper {

  String getInstancePartitionsName(String tableName, String instancePartitionsType);

  InstancePartitions fetchOrComputeInstancePartitions(HelixManager helixManager, TableConfig tableConfig,
      InstancePartitionsType instancePartitionsType);

  InstancePartitions fetchInstancePartitions(HelixPropertyStore<ZNRecord> propertyStore,
      String instancePartitionsName);

  InstancePartitions computeDefaultInstancePartitions(HelixManager helixManager, TableConfig tableConfig,
      InstancePartitionsType instancePartitionsType);

  InstancePartitions computeDefaultInstancePartitionsForTag(HelixManager helixManager,
      String tableNameWithType, String instancePartitionsType, String serverTag);

  String getInstancePartitionsNameForTier(String tableName, String tierName);

  void persistInstancePartitions(HelixPropertyStore<ZNRecord> propertyStore,
      ConfigAccessor configAccessor, String helixClusterName, InstancePartitions instancePartitions);

  InstancePartitions fetchInstancePartitionsWithRename(HelixPropertyStore<ZNRecord> propertyStore,
      String instancePartitionsName, String newName);

  void removeInstancePartitions(HelixPropertyStore<ZNRecord> propertyStore,
      String instancePartitionsName);

  void removeTierInstancePartitions(HelixPropertyStore<ZNRecord> propertyStore,
      String tableNameWithType);
}
