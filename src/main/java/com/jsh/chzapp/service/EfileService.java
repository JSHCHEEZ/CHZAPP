package com.jsh.chzapp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jsh.chzapp.model.Efile;
import com.jsh.chzapp.model.Post;
import com.jsh.chzapp.repository.EfileRepository;


@Service
public class EfileService {
	
	@Autowired
	private EfileRepository efileRepository;
	
    private static final String UPLOAD_DIR = "D:\\WorkSpace\\FileServer\\Image\\";

    public void uploadChunk(MultipartFile fileChunk, int totalChunks, int currentChunk, MultipartFile file, Post post) throws IOException {
    	String fileName = "temp_" + currentChunk + ".part";
    	String originalFileName = file.getOriginalFilename();
        File tempFile = new File(UPLOAD_DIR + fileName);

        try (FileOutputStream fos = new FileOutputStream(tempFile, true)) {
            fos.write(fileChunk.getBytes());
        }

        // 모든 chunk 합치기 완료
        if (currentChunk == totalChunks - 1) {
        	//파일 저장
        	File combineFile = new File(UPLOAD_DIR + UUID.randomUUID().toString() + "." + originalFileName.substring(originalFileName.lastIndexOf('.')));

            try (FileOutputStream fos = new FileOutputStream(combineFile, true)) {
                for (int i = 0; i < totalChunks; i++) {
                    File chunk = new File(UPLOAD_DIR + "temp_" + i + ".part");
                    byte[] chunkData = java.nio.file.Files.readAllBytes(chunk.toPath());
                    fos.write(chunkData);
                    chunk.delete();
                }
            }
            
            //DB 저장     
            Efile efile = new Efile();
            efile.setEfileName(combineFile.getName());
            efile.setEfileOriginalName(originalFileName);
            efile.setEfileExtension(originalFileName.substring(originalFileName.lastIndexOf('.')));
            efile.setEfileContentType(file.getContentType());
            efile.setEfileSize((int)file.getSize());
            efile.setEfileUrl(UPLOAD_DIR + "\\" + combineFile.getName());
            if(post != null) {
            	efile.setPost(post);
            }
            
    		efileRepository.save(efile);
        }
    }

	
	@Transactional
	public void createFiles(List<MultipartFile> files,@RequestParam(required = false) Post post) throws IOException{
		for (MultipartFile file : files) {
            // 원본 파일 이름 가져오기
            String originalFileName = file.getOriginalFilename();
            
        	// 확장자 추출
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));

            // 새로운 파일 이름 생성 (UUID를 사용하여 중복 방지)
            String newFileName = UUID.randomUUID().toString() + fileExtension;
        	
            // 파일 저장 경로 설정 (원하는 경로로 수정)
            String uploadDirectory = "D:\\WorkSpace\\FileServer\\Image";
            
            // 파일을 저장할 디렉토리 생성
            File directory = new File(uploadDirectory);
            if (!directory.exists())
                directory.mkdirs();
            
            // 파일 저장
			file.transferTo(new File(directory, newFileName));
            
            Efile efile = new Efile();
            efile.setEfileName(newFileName);
            efile.setEfileOriginalName(originalFileName);
            efile.setEfileExtension(fileExtension);
            efile.setEfileContentType(file.getContentType());
            efile.setEfileSize((int)file.getSize());
            efile.setEfileUrl(uploadDirectory + "\\" + newFileName);
            if(post != null) {
            	efile.setPost(post);
            }
            
    		efileRepository.save(efile);
            
        }
		
	}
	
}
