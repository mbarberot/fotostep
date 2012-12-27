package jsf;


import org.apache.myfaces.custom.fileupload.UploadedFile;

public class WebUploadBean {

    private UploadedFile uploadedFile;
    private String fileName;

    public void submit() {

    }


    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public String getFileName() {
        return fileName;
    }


    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
