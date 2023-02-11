package com.learn.mn.controllers;

import java.io.File;

import javax.ws.rs.QueryParam;

import org.reactivestreams.Publisher;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.StreamingFileUpload;
import io.micronaut.http.server.types.files.SystemFile;
import reactor.core.publisher.Mono;

@Controller("/file")
public class FileHandlingController {
	
	private static final String UPLOAD_FOLDER = "/Users/rajeswarm/Documents/Temp/";
	
	@Post(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.TEXT_PLAIN) // 
    @SingleResult
    public Publisher<HttpResponse<String>> upload(StreamingFileUpload file) { // 
        File uploadedFile;
        try {
            uploadedFile = new File(UPLOAD_FOLDER, file.getFilename());
        } catch (Exception e) {
            return Mono.error(e);
        }
        Publisher<Boolean> uploadPublisher = file.transferTo(uploadedFile); // 

        return Mono.from(uploadPublisher)  // 
            .map(success -> {
                if (success) {
                    return HttpResponse.ok("Uploaded");
                } else {
                    return HttpResponse.<String>status(HttpStatus.CONFLICT)
                                       .body("Upload Failed");
                }
            });
    }
	
	@Get(value = "/download")
    public SystemFile download(@QueryParam("file") String fileName) { // 
        File tempFile = new File(UPLOAD_FOLDER, fileName);
        return new SystemFile(tempFile).attach(fileName);
    }

}
