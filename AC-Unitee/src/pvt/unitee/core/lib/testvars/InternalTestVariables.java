package pvt.unitee.core.lib.testvars;

import java.util.Map;

import com.google.gson.JsonObject;

import arjunasdk.ddauto.interfaces.DataRecord;
import pvt.arjunapro.ddt.interfaces.DataReference;
import pvt.batteries.value.DefaultStringKeyValueContainer;
import unitee.interfaces.TestVariables;

public interface InternalTestVariables extends TestVariables {

	InternalTestObjectProperties rawObjectProps() throws Exception;

	InternalTestProperties rawTestProps() throws Exception;

	DefaultStringKeyValueContainer rawCustomProps() throws Exception;

	DefaultStringKeyValueContainer rawUtv() throws Exception;
	
	void setObjectProps(InternalTestObjectProperties props) throws Exception;

	void setTestProps(InternalTestProperties props) throws Exception;

	void setCustomProps(DefaultStringKeyValueContainer props) throws Exception;

	void setUtv(DefaultStringKeyValueContainer props) throws Exception;

	void populateDefaults() throws Exception;

	JsonObject asJsonObject() throws Exception;

	void setDataRecord(DataRecord dataRecord);

	void addDataReference(String name, DataReference dataRef);

	void addDataReferences(Map<String, DataReference> dataRefs) throws Exception;

}