package helidon.oci.library;

import library.UploadObject;

import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Path("/upload")
@RequestScoped
public class UploadResource {
    // curl -X PUT -H "Content-Type: application/json" -d '{"filePath" : "/home/phvle/yolo"}' http://localhost:8080/upload/axovcbqne66q/sample-bucket/yolo

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{namespace}/{bucket}/{object}")
    public Response uploadFile(@PathParam("namespace") String namespace,
                             @PathParam("bucket") String bucket,
                             @PathParam("object") String object, JsonObject jsonObject) throws Exception {


        String filePath = jsonObject.getString("filePath");

        try {
            UploadObject upload = new UploadObject(namespace, bucket, object, filePath);
            upload.upload();
        }
        catch(Exception e) {
            return Response.status(Integer.parseInt(e.getMessage().substring(1, 4))).build();
        }

        return Response.ok().build();
    }

}
