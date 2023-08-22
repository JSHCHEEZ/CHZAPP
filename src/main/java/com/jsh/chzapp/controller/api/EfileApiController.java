package com.jsh.chzapp.controller.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsh.chzapp.dto.ResponseDTO;
import com.jsh.chzapp.service.EfileService;

@RestController
public class EfileApiController {

	@Autowired
	private EfileService efileService;
	
	@PostMapping("/api/files")
	public ResponseDTO<Integer> uploadFile(@RequestParam("files") List<MultipartFile> files) throws IOException{ //, @AuthenticationPrincipal PrincipalDetail principal
		
		efileService.createFiles(files, null);
		
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}

	@GetMapping("/image/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
		String path = "D:\\WorkSpace\\FileServer\\Image\\";
		
        Resource resource = new FileSystemResource(path + imageName);
        
        if(!resource.exists())
        	return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
        
        HttpHeaders header = new HttpHeaders();
        Path filePath = null;

        try {
        	filePath = Paths.get(path + imageName);
        	header.add("Content-Type", Files.probeContentType(filePath));
        }catch(IOException e) {
        	e.printStackTrace();
        }
        
        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }
}
