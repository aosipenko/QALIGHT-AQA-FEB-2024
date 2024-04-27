package org.web.cucumber;

import io.cucumber.java.en.Given;
import org.web.page.FileBinPage;

public class FileSteps {

    public static FileBinPage fileBinPage;

    @Given("File Step")
    public void fileStep() {
        fileBinPage.loadPage();
        fileBinPage.initFileUpload();
        fileBinPage.uploadFile("D:\\java\\QALight\\FEB-2024\\src\\test\\resources\\testng-2.xml");
        System.out.println("done!");
    }
}
