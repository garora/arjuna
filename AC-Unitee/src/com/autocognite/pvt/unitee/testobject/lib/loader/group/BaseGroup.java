package com.autocognite.pvt.unitee.testobject.lib.loader.group;

import java.util.List;

import org.apache.log4j.Logger;

import com.autocognite.batteries.config.RunConfig;
import com.autocognite.batteries.value.StringKeyValueContainer;
import com.autocognite.pvt.ArjunaInternal;
import com.autocognite.pvt.unitee.core.lib.exception.SubTestsFinishedException;
import com.autocognite.pvt.unitee.runner.lib.slots.TestSlotExecutor;
import com.autocognite.pvt.unitee.testobject.lib.loader.session.SessionNode;
import com.autocognite.pvt.unitee.testobject.lib.loader.session.SessionSubNode;

public class BaseGroup implements Group{
	private Logger logger = Logger.getLogger(RunConfig.getCentralLogName());
	private String sessionName = null;
	private String name = null;
	private TestLoader loader = null;
	private int classThreads = 1;
	List<Picker> containerPickers = null;
	private SessionSubNode sessionSubNode;
	private StringKeyValueContainer udvars = new StringKeyValueContainer();
	
	public BaseGroup(String name) throws Exception{
		this.name = name;
	}
	
	public BaseGroup(SessionSubNode node, String name) throws Exception{
		this.setSessionSubNode(node);
		this.name = name;
		this.udvars.cloneAdd(node.getUDV().items());
	}
	
	public void setGroupName(String name){
		this.name = name;
	}
	
	protected void setLoader(TestLoader loader){
		this.loader = loader;
	}

	public void load() throws Exception{
		logger.debug(String.format("%s: Loading containers", this.getName()));
		if (containerPickers != null){
			for (Picker picker: containerPickers){
				picker.setGroup(this);
			}
		}
		this.loader.load();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public TestSlotExecutor next() throws Exception {
		return loader.next();
	}

	@Override
	public int getTestMethodCount() {
		return this.loader.getTestMethodCount();
	}
	
	@Override
	public void setSessionName(String name) {
		this.sessionName = name;
	}
	
	@Override
	public String getSessionName() {
		return this.sessionName;
	}
	
	public void setClassThreadCount(int count) {
		this.classThreads = count;
	}
	
	public void setPickers(List<Picker> pickers) {
		this.containerPickers = pickers;
	}
	@Override
	public List<Picker> getPickers() {
		return this.containerPickers;
	}

	@Override
	public String getDefinitionFile() {
		return "NA";
	}

	public int getClassThreadCount() {
		return classThreads;
	}

	public SessionSubNode getSessionSubNode() {
		return sessionSubNode;
	}

	@Override
	public void setSessionSubNode(SessionSubNode sessionSubNode) {
		this.sessionSubNode = sessionSubNode;
	}

	@Override
	public StringKeyValueContainer getUDV() {
		return this.udvars;
	}

	@Override
	public SessionNode getSessionNode() {
		return this.sessionSubNode.getSessionNode();
	}

}