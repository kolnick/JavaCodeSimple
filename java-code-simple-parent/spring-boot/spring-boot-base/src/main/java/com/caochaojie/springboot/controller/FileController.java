package com.caochaojie.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author caochaojie
 * @Date 2022/8/19
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private String savePath = "d:/tmp";

    @PostMapping
    public void upload(MultipartFile file) throws IOException {
        String path = savePath + generateFileName();
        FileUtil.writeFromStream(file.getInputStream(), path);
    }

    public static String generateFileName() {
        return UUID.randomUUID().toString();
    }

    /**
     * 上传到WEB-INF下
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/fileUpload")
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());
        // uploads文件夹位置
        String rootPath = request.getSession().getServletContext().getRealPath("WEB-INF/classes/");
        // 原始名称
        String originalFileName = file.getOriginalFilename();
        // 新文件
        File newFile = new File(rootPath + File.separator + "kdata.csv");
        // 判断目标文件所在目录是否存在
        if (!newFile.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        System.out.println(newFile);
        // 将内存中的数据写入磁盘
        file.transferTo(newFile);
        // 完整的url
        String fileUrl = "WEB-INF/classes/" + originalFileName;
        Map result = new HashMap<String, String>();
        result.put("path", fileUrl);
        return "redirect:http://123.207.34.126/baiyi/cluster.html?result=success";
    }

    @PostMapping("/upload")
    public String uploadToResource(MultipartFile file) throws SerialException {
        //file校验
        if (ObjectUtil.isEmpty(file)) {
            throw new SerialException("上传文件不能为空");
        }
        //file重命名 (a: 1.png   b:1.png)
        //原来的图片名
        String originalFilename = file.getOriginalFilename();
        // 1.png
        String ext = "." + originalFilename.split("\\.")[1];
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + ext;
        //上传图片
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() +
                "\\src\\main\\resources\\static\\images\\"; //
        String path = pre + fileName;
        try {
            file.transferTo(new File(path));
            return file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "图片上传失败";
    }


}
