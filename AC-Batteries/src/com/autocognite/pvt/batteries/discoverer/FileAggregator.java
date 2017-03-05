/*******************************************************************************
 * Copyright 2015-16 AutoCognite Testing Research Pvt Ltd
 * 
 * Website: www.AutoCognite.com
 * Email: support [at] autocognite.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.autocognite.pvt.batteries.discoverer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.autocognite.batteries.config.RunConfig;

public class FileAggregator {
	public Logger logger = Logger.getLogger(RunConfig.getCentralLogName());
	ArrayList<DiscoveredFile> files = new ArrayList<DiscoveredFile>();
	HashMap<String, DiscoveredFile> tempMap = new HashMap<String, DiscoveredFile>();

	public void addFile(DiscoveredFile file) {
		String key = file.getAttribute(DiscoveredFileAttribute.DIRECTORY_ABSOLUTE_PATH) + "/"
				+ file.getAttribute(DiscoveredFileAttribute.FULL_NAME);
		tempMap.put(key, file);
	}

	public void freeze() {
		ArrayList<String> paths = new ArrayList<String>();
		paths.addAll(this.tempMap.keySet());
		Collections.sort(paths);
		for (String path : paths) {
			this.files.add(this.tempMap.get(path));
		}
	}

	public Iterator<DiscoveredFile> getIterator() {
		return files.iterator();
	}

	public void enumerate() {
		Iterator<DiscoveredFile> iter = getIterator();
		while (iter.hasNext()) {
			DiscoveredFile f = iter.next();
			logger.debug("-------------------------");
			logger.debug("Name:\t" + f.getAttribute(DiscoveredFileAttribute.NAME));
			logger.debug("Extension:\t" + f.getAttribute(DiscoveredFileAttribute.EXTENSION));
			logger.debug("Full Name:\t" + f.getAttribute(DiscoveredFileAttribute.FULL_NAME));
			logger.debug("Package Dot Notation:\t" + f.getAttribute(DiscoveredFileAttribute.PACKAGE_DOT_NOTATION));
			logger.debug(
					"Directory Relative Path:\t" + f.getAttribute(DiscoveredFileAttribute.DIRECTORY_RELATIVE_PATH));
			logger.debug(
					"Directory Absolute Path:\t" + f.getAttribute(DiscoveredFileAttribute.DIRECTORY_ABSOLUTE_PATH));
			logger.debug("Comma Separated Relative Path:\t"
					+ f.getAttribute(DiscoveredFileAttribute.COMMA_SEPATARED_RELATIVE_PATH));
			logger.debug("Container:\t" + f.getAttribute(DiscoveredFileAttribute.CONTAINER));
			logger.debug("Container Type:\t" + f.getAttribute(DiscoveredFileAttribute.CONTAINER_TYPE));
			logger.debug("-------------------------");
		}
	}
}