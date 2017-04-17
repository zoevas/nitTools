package com.nitos.testbed.tools;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.spongycastle.openssl.PEMParser;

import android.content.Context;
import android.util.Log;

public class TestbedHttpClient {

	
    //Making http get request
	public String getTestbedData(String path) {
			HttpURLConnection con = null ;
			InputStream input = null;

			try {
				con = (HttpURLConnection) ( new URL(Constants.BASE_URL + path)).openConnection();
				con.setRequestMethod("GET");
			
				String jsonStr;
				// Let's read the response
				input = con.getInputStream();
		
				jsonStr = new java.util.Scanner(input).useDelimiter("\\A").next();
		    			
				input.close();
				con.disconnect();
				return jsonStr;
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
			finally {
			
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				con.disconnect(); 
				
			}

			return null;
				
	}
	
	
	//Making http post request
	public void setTestbedData(String path, String data) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, IOException {
 			 HttpURLConnection con = null;
			 con = (HttpURLConnection) ( new URL(Constants.BASE_URL + path)).openConnection();
			
			
			/*  SSLSocketFactory sc = null;
		  	  try {
				  sc = getSocketFactoryPEM("/sdcard/Documents/user_cert.pem");
			  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 	  }
		    
		      if (con instanceof HttpsURLConnection) {
		    	  Log.i("HttpsURLConnection","mphka");
		    	  ((HttpsURLConnection)con).setSSLSocketFactory(sc);
		      }*/
		      
		      // If you invoke the method setDoOutput(true) on the URLConnection, it will always use the POST method.
		      con.setRequestMethod("POST");
		      con.setDoInput(true);
		      con.setDoOutput(true);
		      con.setRequestProperty("Accept", "application/json");
		      con.setRequestProperty("Content-Type", "application/json");
			
	  		  Log.i("data", data);	
			
	  		  OutputStream outputStream = con.getOutputStream();
	  		  outputStream.write(data.getBytes());
	  		  outputStream.flush();
			
	  		  Log.w("RESPONSE CODE", "code " + con.getResponseCode());
			
			  //Read Error Messages			
	  		  InputStream _is;
	  		  if (con.getResponseCode() /100 == 2) {  
	  			  _is = con.getInputStream();  
	  		  } else {  
	  			  _is = con.getErrorStream();  
	
	  			  String result = getStringFromInputStream(_is);
	  			  Log.i("Error != 2xx", result);
			    
	  			  BufferedReader responseBuffer1 = new BufferedReader(new InputStreamReader((con.getErrorStream())));
				
	  			  String output1;
	  			  Log.i("TestbedHttpClient Error","Output error from Server:");
	  			  while ((output1 = responseBuffer1.readLine()) != null) {
	  				  Log.i("Output error",output1);
	  			  }
		  	  }
			
			
			if (con.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ con.getResponseCode());
			}
					
			BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((con.getInputStream())));
			
			String output;
			Log.i("TestbedHttpClient","Output from Server:");
			while ((output = responseBuffer.readLine()) != null) {
				Log.i("Output",output);
			}
		
			con.disconnect();
		

	}

	
/*	protected static SSLSocketFactory getSocketFactoryPEM(String pemPath) throws Exception {        
	    SSLContext context = SSLContext.getInstance("SSL");

	    byte[] certAndKey =  fileToArrayOfBytes(pemPath);

	    String delimiter = "-----END CERTIFICATE-----";
	    String[] tokens = new String(certAndKey).split(delimiter);

	    byte[] certBytes = tokens[0].concat(delimiter).getBytes();
	    byte[] keyBytes = tokens[1].getBytes();
	    String certValue = new String(certBytes, "UTF-8");
	    Log.i("certBytes", certValue);
	    String keyValue = new String(keyBytes, "UTF-8");
	    Log.i("keyBytes", keyValue);
	    
	    PEMParser reader;

	    reader = new PEMParser(new InputStreamReader(new ByteArrayInputStream(certBytes)));
	    Object pemObject = reader.readObject();
	  //  X509Certificate cert = (X509Certificate)pemObject;
	    X509Certificate cert = null;		       
	   if (pemObject instanceof X509Certificate) {
		  cert = (X509Certificate)pemObject;
	   }

	    reader = new PEMParser(new InputStreamReader(new ByteArrayInputStream(keyBytes)));
	    PrivateKey key = (PrivateKey)reader.readObject();        

	    KeyStore keystore = KeyStore.getInstance("JKS");
	    keystore.load(null);
	    keystore.setCertificateEntry("cert-alias", cert);
	    keystore.setKeyEntry("key-alias", key, "".toCharArray(), new Certificate[] {cert});

	    KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
	    kmf.init(keystore, "".toCharArray());

	    KeyManager[] km = kmf.getKeyManagers(); 

	    context.init(km, null, null);
	    return context.getSocketFactory();
	  }
	
	
	
	    public static byte[] fileToArrayOfBytes(String fileName)
	    {
	    	FileInputStream fileInputStream=null;
	 
	        File file = new File(fileName);
	 
	        byte[] bFile = new byte[(int) file.length()];
	 
	        try {
	        		//convert file into array of bytes
	        		fileInputStream = new FileInputStream(file);
	        		fileInputStream.read(bFile);
	        		fileInputStream.close();
	 
	        		for (int i = 0; i < bFile.length; i++) {
	        			System.out.print((char)bFile[i]);
	        		}
	 
	        		System.out.println("Done");
	        		return bFile;
	        }catch(Exception e){
	        		e.printStackTrace();
	        }
			return null;
	    }
		*/

	
	  // convert InputStream to String
		private static String getStringFromInputStream(InputStream is) {
	 
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
	 
			String line;
			try {
	 
				br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	 
			return sb.toString();
	 
		}
	
}
