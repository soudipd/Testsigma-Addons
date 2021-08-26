package com.pdfactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PdfReader {

	public static void main(String args[])throws Exception {
		PdfReader obj= new PdfReader();
		obj.ReadPDFile();
		
		
	}
	public void ReadPDFile() throws Exception {
		
		//File file=new File("C:\\Users\\soudi\\Downloads\\file-sample_150kB.pdf");
		//FileInputStream fis = new FileInputStream(file);
		//PDDocument pdfdocument = PDDocument.load(fis);
		//System.out.println(pdfdocument.getNumberOfPages());
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://file-example.com/");
		driver.findElement(By.xpath("//a[@href='http://file-example.com/en/sample-documents-files'][normalize-space()='Download']")).click();
		driver.findElement(By.xpath("//td[text()='PDF']//following-sibling::td/a")).click();
		driver.findElement(By.xpath("//td[text()='150 kB']//following-sibling::td/a")).click();
		
		String urlString=driver.getCurrentUrl();
		URL url =new URL(urlString);
		String destinationPath="C:\\Users\\soudi\\Downloads\\file-sample_150kB_1.pdf";
		FileUtils.copyURLToFile(url, new File(destinationPath));
		PDDocument pdfdocument = PDDocument.load(url.openStream());
		
		
		
		PDFTextStripper pdftext=new PDFTextStripper();
		String docuText= pdftext.getText(pdfdocument);
		System.out.println(docuText);
		
		
		pdfdocument.close();
		driver.quit();
		
		
		
		
	}
	
		
 
	}


