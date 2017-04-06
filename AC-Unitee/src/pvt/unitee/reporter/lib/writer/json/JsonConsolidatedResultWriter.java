package pvt.unitee.reporter.lib.writer.json;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.arjunapro.sysauto.batteries.FileSystemBatteries;
import com.arjunapro.sysauto.batteries.SystemBatteries;

import pvt.arjunapro.enums.ArjunaProperty;
import pvt.batteries.config.Batteries;
import pvt.batteries.filehandler.FileWriter;
import pvt.unitee.reporter.lib.DefaultObserver;

public abstract class JsonConsolidatedResultWriter<T> extends DefaultObserver<T>{
	private String reportDir = null;
	private FileWriter writer = null;
	private boolean first = true;
	
	public JsonConsolidatedResultWriter(String reportName) throws Exception{
		super();
		this.reportDir = FileSystemBatteries.getCanonicalPath(Batteries.value(ArjunaProperty.DIRECTORY_PROJECT_RUNID_REPORT_JDB).asString());
		FileUtils.forceMkdir(new File(reportDir));
		String rPath = FileSystemBatteries.getCanonicalPath(reportDir + "/" + reportName);
		writer = new FileWriter(rPath);
	}
	
	public void setUp() throws Exception{
		writer.write("{" + SystemBatteries.getLineSeparator() + "\"records\" : [" + SystemBatteries.getLineSeparator());
	}
	
	protected void update(String jsonString) throws Exception {
		if (first){
			first = false;
		} else {
			writer.write("," + SystemBatteries.getLineSeparator());
		}
		writer.write(jsonString);
	}
	
	protected String createFileID(String inStr) throws NoSuchAlgorithmException{

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inStr.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        sb.append(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date()));
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }	
 
        return sb.toString();
	}
	
	public void tearDown() throws Exception{
		writer.write(SystemBatteries.getLineSeparator() + "]" + SystemBatteries.getLineSeparator() + "}");
		writer.close();
	}
}
