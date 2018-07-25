package org.asite.automation.adoddle.p2.scripts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;

public class SimpleUploadLoadTest extends AdoddleCommonAppMethods{

	public void simpleUploadNDocuments(String docCount, String interations) throws InterruptedException, IOException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		for(int index = 0; index < Integer.parseInt(interations); index++) {
			createAndUploadDocuments(docCount);
			waitUtils.waitInterval(300);
		}
	
	}
	
	public void createAndUploadDocuments(String docCount) throws IOException, InterruptedException {
		List<String> fileList = new ArrayList<String>();
		for(int counter=0; counter < Integer.parseInt(docCount); counter++) {
			String filePath = nodeIP+resourceUtils.getTestDataFilePath()+dateUtils.getEpoch()+counter+AdoddleCommonStringPool.TXT_EXTENSION;
			fileList.add(createFile(filePath));
		}
		log.info(fileList.toString());
		simpleUploadDocuments(fileList);
	}
	
	public String createFile(String filePath) throws IOException, InterruptedException {
		sysUtils.createDirectory(filePath.substring(0, filePath.lastIndexOf("\\")));
		BufferedWriter output = null;
		try {
			File file = new File(filePath);
			output = new BufferedWriter(new FileWriter(file));
			output.write(new JavaUtils().getRandomString(1000));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (output != null)
				output.close();
		}
		return filePath;
	}

}
