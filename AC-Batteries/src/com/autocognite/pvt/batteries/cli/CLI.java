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
package com.autocognite.pvt.batteries.cli;

import java.util.Properties;

public interface CLI extends CLIOptions {

	Properties getProperties();

	void initialize() throws Exception;

	String[] getArgs() throws Exception;

	void addSingleArgSwitch(String shortName, String longName, String argName, String desc) throws Exception;

	void addTrueSwitch(String shortName, String longName, String desc) throws Exception;

	void addFalseSwitch(String shortName, String longName, String desc) throws Exception;

	void setArgs(String[] cliArgs) throws Exception;

}