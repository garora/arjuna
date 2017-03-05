package com.autocognite.pvt.unitee.reporter.lib.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.autocognite.pvt.unitee.core.lib.testvars.InternalTestVariables;
import com.autocognite.pvt.unitee.reporter.lib.reportable.ResultDeserializer;
import com.autocognite.pvt.unitee.reporter.lib.step.StepResult;
import com.autocognite.pvt.unitee.reporter.lib.step.StepResultDeserializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class TestResultDeserializer extends ResultDeserializer<TestResult> implements JsonDeserializer<TestResult> {
	private static StepResultDeserializer deserializer = new StepResultDeserializer();
	
	public TestResult process(JsonObject root){
		TestResultBuilder builder = null;
		InternalTestVariables testVars = getTestVars(root);
		TestResult outResult = null;

		try{
			builder = new TestResultBuilder();

			//Deserialize autoProps
			JsonObject resultPropsObj = root.get("resultProps").getAsJsonObject();
			TestResultProperties resultProps = new TestResultProperties();
			processJsonObjectForEnumMap(resultPropsObj, resultProps);
			outResult = builder.resultProps(resultProps).testVariables(testVars).build();
			
			JsonArray stepArray = root.get("steps").getAsJsonArray();
			Iterator<JsonElement> iter = stepArray.iterator();
			List<StepResult> stepResults = new ArrayList<StepResult>();
			while(iter.hasNext()){
				stepResults.add(deserializer.process(iter.next().getAsJsonObject()));
			}
			
			outResult.addStepResults(stepResults);
						
		} catch (Exception e){
			e.printStackTrace();
		}

		return outResult;		
	}

}