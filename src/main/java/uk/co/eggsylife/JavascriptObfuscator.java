// Copyright 2011 eggsylife.co.uk
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package uk.co.eggsylife;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
 
 
public class JavascriptObfuscator {
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
 
		System.out.println("Obfuscating Javascript");
 
		if( args.length >= 2 ) {
 
			int argumentCount = args.length;
			String jsFile = args[0];
 
			String outputFile = args[1];
 
			String compilationLevel = "ADVANCED_OPTIMIZATIONS";
			if(argumentCount == 3 ) {
				compilationLevel  = args[2];
			}
 
 
			String outputFormat = "text";
			if(argumentCount == 4 ) {
				outputFormat  = args[3];
			}
 
			System.out.println("Processing ( " + jsFile + " )" );
			System.out.println("Compilation Level ( " + compilationLevel + " )");
			System.out.println("Output Format ( " + outputFormat + " )");
			System.out.println("Saving to ( " + outputFile + " )");
 
			StringBuffer contents = new StringBuffer();
			BufferedReader reader = null;
 
			try {
				File file = new File(jsFile);
	            		reader = new BufferedReader(new FileReader(file));
			    	String text = null;
 
			    	// Read the Javascript
			    	while ((text = reader.readLine()) != null) {
			        	contents.append(text).append(System.getProperty("line.separator"));
			    	}
 
				// Javascript Pre-Obfuscation
			    	String jsCode = contents.toString();
 
			    	ClosureCompiler compiler = new ClosureCompiler(outputFile, jsCode, compilationLevel, outputFormat);
			    	compiler.processCompilation();
 
			    	System.out.println("Obfuscation Finished");
 
			} 
			catch (Exception e) {
	            		e.printStackTrace();
	        	}
			finally {
				try {
	                		if (reader != null) {
	                			reader.close();
	                		}
	            		} 
				catch (IOException e) {
	                		e.printStackTrace();
	            		}
	        	}
 
		}
		else {
			System.err.println("No (not enough) arguments passed to Obfuscation - requires JS file and Output directory");
		}
	}
}
