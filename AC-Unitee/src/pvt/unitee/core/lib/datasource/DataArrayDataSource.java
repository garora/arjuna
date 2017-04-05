package pvt.unitee.core.lib.datasource;

import java.util.Iterator;
import java.util.List;

import com.arjunapro.ddt.datarecord.DefaultDataRecord;
import com.arjunapro.ddt.datarecord.DefaultDataRecordContainer;
import com.arjunapro.ddt.exceptions.DataSourceFinishedException;
import com.arjunapro.ddt.interfaces.DataRecord;
import com.arjunapro.ddt.interfaces.DataRecordContainer;
import com.arjunapro.ddt.interfaces.DataSource;

public class DataArrayDataSource implements DataSource{
	private DataRecordContainer container = null;
	private Iterator<DataRecord> iter = null;

	public DataArrayDataSource(String[] headers, List<String[]> valuesArr) throws Exception {
		container = new DefaultDataRecordContainer();
		for (String[] rec: valuesArr){
			DefaultDataRecord record = null;
			if (headers.length == 0){
				record = new DefaultDataRecord(rec);
			} else {
				record = new DefaultDataRecord(headers, rec);
			}
			container.add(record);
		}
		this.initialize();
	}
	
	public void initialize(){
		iter = container.iterator();
	}
	
	public DataRecord next() throws DataSourceFinishedException{
		if (iter.hasNext()){
			return iter.next();
		} else {
			throw new DataSourceFinishedException("Done");
		}
	}

}
