package org.asite.automation.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

// TODO: Auto-generated Javadoc
/**
 * The Class PdfReaderUtils.
 */
public class PdfUtils {

	/**
	 * Convert pdf to text.
	 *
	 * @param pdfPath the pdf path
	 */
	public List<String> extractPDFText(String pdfPath)  {
		List<String> pdfText = new ArrayList<String>();
		
		try {

		    PdfReader reader = new PdfReader(pdfPath);
		    System.out.println("This PDF has "+reader.getNumberOfPages()+" pages.");
		    for(int page = 1; page <= reader.getNumberOfPages(); page++)
		    	pdfText.add(PdfTextExtractor.getTextFromPage(reader, page));
		    /*
		    System.out.println("Page Content:\n\n"+pdfText.toString()+"\n\n");
		    System.out.println("Is this document tampered: "+reader.isTampered());
		    System.out.println("Is this document encrypted: "+reader.isEncrypted());
		    */
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return pdfText;
	}
}
