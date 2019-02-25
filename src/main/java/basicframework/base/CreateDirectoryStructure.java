package basicframework.base;

import java.io.File;

public class CreateDirectoryStructure {


	public static String getReportingDirectory(String appName,String sourcefolder,String currentDateTime){

		// parentDirectory -- similar like my Application Name

		File parentDirectory= new File(sourcefolder+"/Reports/"+appName);
		if(!parentDirectory.exists())
			if(!parentDirectory.mkdir())
				throw new RuntimeException(appName+" failed to create directory under "+sourcefolder+"/Reports/"+ " location");


		//create sub directory based on current date and time
		File subDirectory= new File(parentDirectory+"/"+currentDateTime);
		if(!subDirectory.exists())
			if(!subDirectory.mkdir())
				throw new RuntimeException(currentDateTime+" failed to create directory under "+parentDirectory+" location");

		// create Html folder
		File htmlDirectory= new File(subDirectory+"/HTML_Report");
		if(!htmlDirectory.exists())
			if(!htmlDirectory.mkdir())
				throw new RuntimeException(htmlDirectory+" failed to create directory under "+subDirectory+" location");

		// create Html folder
		File screenShotDirectory= new File(htmlDirectory+"/ScreenShot");
		if(!screenShotDirectory.exists())
			if(!screenShotDirectory.mkdir())
				throw new RuntimeException(screenShotDirectory+" failed to create directory under "+htmlDirectory+" location");


		return htmlDirectory.toString()+"/";
	}

}
