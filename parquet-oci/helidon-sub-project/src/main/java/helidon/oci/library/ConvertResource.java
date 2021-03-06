package helidon.oci.library;

import library.ConvertObject;

import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("/convert")
@RequestScoped
public class ConvertResource {

    // curl -X PUT -H "Content-Type: application/json" -d '{"filePath" : "/home/phvle/sample2.csv"}' http://localhost:8080/convert/sample2.csv

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{file}")
    public Response convertFile(@PathParam("file") String file, JsonObject json) throws Exception{
        String csvPath = json.getString("filePath");
        String parPath = System.getProperty("user.home")+ File.separator + file.substring(0, file.length() - ".csv".length()) + ".parquet";

        try {
            new ConvertObject(csvPath, parPath);
        }
        catch(Exception e) {
            return Response.status(400).build();
        }

        return Response.ok().build();
    }

}
