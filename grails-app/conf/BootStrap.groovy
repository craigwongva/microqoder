import javax.servlet.*
import java.util.jar.*
import java.text.*

class BootStrap {

    def init = { servletContext ->
        InputStream inputStream = servletContext.getResourceAsStream("/META-INF/MANIFEST.MF");
        Manifest manifest = new Manifest(inputStream);
        Attributes secondaryAttributes = manifest.getAttributes("Grails Application")
        String sha =  secondaryAttributes.getValue('Implementation-Build-Number')

        String ii = "curl 169.254.169.254/latest/meta-data/instance-id".execute().text
        SimpleDateFormat format=new SimpleDateFormat ("yyyy-MM-dd:HH:mm:ss.SSS");
        println "candlestick microcero $sha $ii ${format.format(new Date())}"
    }

    def destroy = {
    }
}

