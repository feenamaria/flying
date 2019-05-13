/*Copyright (c) 2019-2020 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.testing_pdf.myjavaservice;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.OutputStream;
import java.security.DigestException;
import org.xhtmlrenderer.pdf.ITextRenderer; 
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import java.io.FileWriter;
import com.wavemaker.runtime.service.annotations.HideFromClient;


@ExposeToClient
public class MyJavaService {

    private static final Logger logger = LoggerFactory.getLogger(MyJavaService.class);
    @Autowired
    private SecurityService securityService;

    
    public String sampleJavaOperation(String name, HttpServletRequest request) {
        logger.debug("Starting sample operation with request url " + request.getRequestURL().toString());
        
        HttpServletRequest req;
        String urlpath_fromxml=request.getParameter("urlpath"); 
        System.out.println("hai"+urlpath_fromxml);
        
        String result = null;
        if (securityService.isAuthenticated()) {
            result = "Hello " + name + ", You are logged in as "+  securityService.getLoggedInUser().getUserName();
        } else {
            result = "Hello " + name + ", You are not authenticated yet!";
        }
        logger.debug("Returning {}", result);
        return result;
     
    }
    
    
    public String convert(String urlpath_fromxml,String urlpath_fromxml1,String urlpath_fromxml3,String urlpath_fromxml4,String urlpath_fromxml5,String urlpath_fromxml6,String urlpath_fromxml7,String urlpath_fromxml8,String urlpath_fromxml9,String urlpath_fromxml10) throws Exception
    {
        
        String urlpath="https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Shaqi_jrvej.jpg/1200px-Shaqi_jrvej.jpg";
        String data=  "<!DOCTYPE html>\n" +
           "<html>\n" +
           "<head><style> h2 { page-break-before: always;}</style></head>" +
           "<body>\n" +
           "<img src=\""+urlpath_fromxml+"\"/> \n" +
           "<img src=\""+urlpath_fromxml1+"\"/> \n" +
           "<img src=\""+urlpath_fromxml3+"\"/> \n" +
           "<img src=\""+urlpath_fromxml4+"\"/> \n" +
           "<img src=\""+urlpath_fromxml5+"\"/> \n" +
           "<img src=\""+urlpath_fromxml6+"\"/> \n" +
           "<img src=\""+urlpath_fromxml7+"\"/> \n" +
           "<img src=\""+urlpath_fromxml8+"\"/> \n" +
           "<img src=\""+urlpath_fromxml9+"\"/> \n" +
           "<img src=\""+urlpath_fromxml10+"\"/> \n" +
           "</body>\n" +
           "</html>\n";
		
        String path =System.getProperty("user.home") + "/WaveMaker/appdata/testing_pdf/uploads/"+"sample.xhtml";
        FileOutputStream out = new FileOutputStream(path);
        out.write(data.getBytes());
        out.close();
        String inputFile = System.getProperty("user.home") + "/WaveMaker/appdata/testing_pdf/uploads/"+"sample.xhtml"; 
        String url = new File(inputFile).toURI().toURL().toString(); 
        String outputFile =  System.getProperty("user.home") + "/WaveMaker/appdata/testing_pdf/uploads/"+"sample.pdf"; 
        OutputStream os = new FileOutputStream(outputFile); 
        ITextRenderer renderer = new ITextRenderer(); 
        renderer.setDocument(url);  
        renderer.layout(); 
        renderer.createPDF(os); 
        os.close();
        return null;
    }
    

}
