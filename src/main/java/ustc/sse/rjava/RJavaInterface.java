/*
 * ============================================================
 * The SSE USTC Software License
 * 
 * RJavaInterface.java
 * 2014-3-3
 * 
 * Copyright (c) 2006 China Payment and Remittance Service Co.,Ltd        
 * All rights reserved.
 * ============================================================
 */
package ustc.sse.rjava;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Log4JLogger;
import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;

/**
 * 实现功能： R与Java的接口<br />
 * <p>
 * date	    author            email		           notes<br />
 * --------	---------------------------	---------------<br />
 * 2014-3-3	 邱星            starqiu@mail.ustc.edu.cn	      新建类<br /></p>
 *
 */
public  class RJavaInterface implements RMainLoopCallbacks{
	
	private Log log = LogFactory.getLog(RJavaInterface.class);
	public Rengine re ;

	public RJavaInterface() {
		super();
		re = new Rengine(new String[] { "--vanilla" }, false, new RJavaInterface());
	}
	
	public void callRJava() {
        if (!re.waitForR()) {
        	log.error("Can not load R!");
        }
        
        /*String path = re.jriChooseFile(0);
        String cmd = "source('"+path+"')";
        String rv = re.eval(cmd).asString();
        System.out.println(rv);
        String cmd2="area(10)";
        REXP res = re.eval(cmd2);
        System.out.println(res.asDouble());
        res = re.eval("a");
        for (int b : res.asIntArray()) {
			System.out.println(b);
		}*/
        re.end();
    }

	public String rReadConsole(Rengine re, String prompt, int addToHistory) {
		System.out.print(prompt);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String s = br.readLine();
			return (s == null || s.length() == 0) ? s : s + "\n";
		} catch (Exception e) {
			System.out.println("jriReadConsole exception: " + e.getMessage());
		}
		return null;
	}

	public void rWriteConsole(Rengine re, String text, int oType) {
		System.out.print(text);
	}
	
	public void rBusy(Rengine re, int which) {
		System.out.println("rBusy(" + which + ")");
	}
	
	/* 
	 * @see org.rosuda.JRI.RMainLoopCallbacks#rChooseFile(org.rosuda.JRI.Rengine, int)
	 */
	public String rChooseFile(Rengine re, int newFile) {
		FileDialog fd = new FileDialog(new Frame(),
				(newFile == 0) ? "Select a file" : "Select a new file",
						(newFile == 0) ? FileDialog.LOAD : FileDialog.SAVE);
		fd.setVisible(true);
		String res = null;
		if (fd.getDirectory() != null)
			res = fd.getDirectory();
		if (fd.getFile() != null)
			res = (res == null) ? fd.getFile() : (res + fd.getFile());
			return res;
	}
	
	public void rShowMessage(Rengine re, String message) {
		System.out.println("rShowMessage \"" + message + "\"");
	}

	/* 
	 * @see org.rosuda.JRI.RMainLoopCallbacks#rFlushConsole(org.rosuda.JRI.Rengine)
	 */
	public void rFlushConsole(Rengine re) {
	}

	/* 
	 * @see org.rosuda.JRI.RMainLoopCallbacks#rSaveHistory(org.rosuda.JRI.Rengine, java.lang.String)
	 */
	public void rSaveHistory(Rengine re, String filename) {
	}

	/* 
	 * @see org.rosuda.JRI.RMainLoopCallbacks#rLoadHistory(org.rosuda.JRI.Rengine, java.lang.String)
	 */
	public void rLoadHistory(Rengine re, String filename) {
		
	}

}
