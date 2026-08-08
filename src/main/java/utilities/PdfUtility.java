package utilities;

import java.io.File;

public class PdfUtility {
    public static boolean isPdfDownloaded() {
        String downloadPath = 
            System.getProperty("user.home") + "\\Downloads";

        File folder = new File(downloadPath);

        for(int i = 0; i < 10; i++) {
            
            File[] files = folder.listFiles();

            if(files != null) {   

                for(File file : files) {

                    if(file.getName().startsWith("swag-labs-order-")
                            && file.getName().endsWith(".pdf")) {
                        
                        return true;
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
        return false;
    }
}
