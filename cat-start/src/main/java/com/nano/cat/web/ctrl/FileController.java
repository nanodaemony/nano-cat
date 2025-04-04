package com.nano.cat.web.ctrl;

import com.nano.cat.web.data.chat.ChatPredictResponse;
import com.nano.cat.web.logic.ChatLogic;
import com.nano.cat.web.logic.FileLogic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/17 19:56
 */
@Tag(name = "文件接口")
@RestController("/file")
public class FileController {

    @Autowired
    private FileLogic fileLogic;

    /**
     * 上传文件接口
     * @param file 上传的文件
     * @return 文件在COS上的URL
     */
    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return fileLogic.uploadFile(file);
    }

}

