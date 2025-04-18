/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package opennlp.tools.util.featuregen;

import opennlp.tools.commons.Internal;

/**
 * The {@link FeatureGeneratorResourceProvider} provides access to the resources
 * available in the model. Inside the model resources are identified by a name.
 *
 * @apiNote This interface is not intended to be implemented by users.<br>
 * All implementing classes must be thread safe.
 */
@Internal
public interface FeatureGeneratorResourceProvider {

  /**
   * @param resourceIdentifier The identifier which names the resource.
   *
   * @return Retrieves the resource object for the given {@code resourceIdentifier}.
   */
  Object getResource(String resourceIdentifier);
}
