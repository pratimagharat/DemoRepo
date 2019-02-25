package basicframework.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigfileReader {

	Properties pro;
	String sourceFolder;
	public ConfigfileReader(String sourceFolder)
	{
		this.sourceFolder=sourceFolder;
		loadPropertiesFile();
	}
	public void loadPropertiesFile() {
		try {
			File propertiesFileObj = new File(sourceFolder+"/GlobalConfig/config.properties");
			FileInputStream fls = new FileInputStream(propertiesFileObj);
			pro = new Properties();
			pro.load(fls);
			fls.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("config file not found at "+sourceFolder+"/GlobalConfig/config.properties");
		}
	}

	public String getApplicationName(){
		String applicationName= pro.getProperty("ApplicationName");
		if(applicationName !=null)
			return applicationName;
		else
			throw new RuntimeException("ApplicationName value is not specified");
	}

	public String getEnvironment(){
		String environment= pro.getProperty("Environment");
		if(environment !=null)
			return environment;
		else
			throw new RuntimeException("ApplicationName value is not specified");
	}

	public String getDevUrl(){
		String devUrl= pro.getProperty("DevUrl");
		if(devUrl !=null)
			return devUrl;
		else
			throw new RuntimeException("DevUrl value is not specified");
	}

	public String getQAUrl(){
		String QAUrl= pro.getProperty("QAUrl");
		if(QAUrl !=null)
			return QAUrl;
		else
			throw new RuntimeException("QAUrl value is not specified");
	}

	public String getUATUrl(){
		String UATUrl= pro.getProperty("UATUrl");
		if(UATUrl !=null)
			return UATUrl;
		else
			throw new RuntimeException("UATUrl value is not specified");
	}

	public String getExecutionOn(){
		String ExecutionOn= pro.getProperty("ExecutionOn");
		if(ExecutionOn !=null)
			return ExecutionOn;
		else
			throw new RuntimeException("ExecutionOn value is not specified");
	}

	public String getBrowserName(){
		String Browser= pro.getProperty("Browser");
		if(Browser !=null)
			return Browser;
		else
			throw new RuntimeException("Browser value is not specified");
	}

	public String getEmailRequired(){
		String EmailRequired= pro.getProperty("EmailRequired");
		if(EmailRequired !=null){
			if(EmailRequired.equalsIgnoreCase("true")){
				String EmailTo= pro.getProperty("EmailTo");
				if(EmailTo ==null){
					throw new RuntimeException("EmailTo value is not specified");
				}
			}
			return EmailRequired;
		}
		else
			throw new RuntimeException("EmailRequired value is not specified");
	}

	public String getEmailTo(){
		String EmailTo= pro.getProperty("EmailTo");
		if(EmailTo !=null)
			return EmailTo;
		else
			throw new RuntimeException("EmailTo value is not specified");
	}
	
	public String getInputExcelFile(){
		String InputExcelFile= pro.getProperty("InputExcelFile");
		if(InputExcelFile !=null)
			return InputExcelFile;
		else
			throw new RuntimeException("InputExcelFile value is not specified");
	}
	
	public String getUserId(){
		String userId= pro.getProperty("userId");
		if(userId !=null)
			return userId;
		else
			throw new RuntimeException("userId value is not specified");
	}

	public String getPassword(){
		String password= pro.getProperty("password");
		if(password !=null)
			return password;
		else
			throw new RuntimeException("password value is not specified");
	}

}
