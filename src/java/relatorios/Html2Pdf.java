/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package relatorios;

import com.lowagie.text.DocumentException;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
/**
 *
 * @author José
 */
public class Html2Pdf {
    public static void convert(String input, OutputStream out) throws DocumentException{
        convert(new ByteArrayInputStream(input.getBytes()), out);
	}
	public static void convert(InputStream input, OutputStream out) throws DocumentException{
    	Tidy tidy = new Tidy();        	
    	Document doc = tidy.parseDOM(input, null);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(doc, null);
        renderer.layout();       
        renderer.createPDF(out);        		
	}	
        
    public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException {
       OutputStream os = new FileOutputStream("C:\\Users\\José\\Documents\\hello.pdf");;
       Html2Pdf.convert("C:\\Users\\José\\Documents\\Nova pasta\\SecSaude3\\web\\newjsf.xhtml", os);        	
       os.close();
    }
}
