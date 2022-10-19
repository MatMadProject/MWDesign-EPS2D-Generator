package eu.xdarqus.mwdesign.models.output;

import java.io.File;
import java.net.URISyntaxException;

public class ApplicationRunningPath {

    private String suffixPath;
    public ApplicationRunningPath() {
        suffixPath = getSuffix();
    }

    private String getApplicationRunPath() throws URISyntaxException {
        return new File(ApplicationRunningPath.class.getProtectionDomain().getCodeSource().getLocation()
                .toURI()).getPath();
    }

    private String getSuffix(){
        String applicationRunPath = null;
        try {
            applicationRunPath = getApplicationRunPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return applicationRunPath == null ?
                null :
                applicationRunPath.substring(0,applicationRunPath.lastIndexOf('\\'));
    }

    public String getSuffixPath() {
        return suffixPath;
    }
}
