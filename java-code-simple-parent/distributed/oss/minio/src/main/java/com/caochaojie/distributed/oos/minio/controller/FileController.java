package com.caochaojie.distributed.oos.minio.controller;


import com.caochaojie.distributed.oos.minio.domain.AjaxResult;
import com.caochaojie.distributed.oos.minio.util.MinioUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;

@Api(tags = "文件操作接口")
@Controller
public class FileController {
	
	@Autowired
	MinioUtil minioUtil;
	
	@ApiOperation("上传一个文件")
	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult fileupload(@RequestParam MultipartFile uploadfile, @RequestParam String bucket,
								 @RequestParam(required=false) String objectName) throws Exception {
		minioUtil.createBucket(bucket);
		if (objectName != null) {
			minioUtil.uploadFile(uploadfile.getInputStream(), bucket, objectName+"/"+uploadfile.getOriginalFilename());
		} else {
			minioUtil.uploadFile(uploadfile.getInputStream(), bucket, uploadfile.getOriginalFilename());
		}
		return AjaxResult.success();
	}
	
	@ApiOperation("列出所有的桶")
	@RequestMapping(value = "/listBuckets", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult listBuckets() throws Exception {
		return AjaxResult.success(minioUtil.listBuckets());
	}
	
	@ApiOperation("递归列出一个桶中的所有文件和目录")
	@RequestMapping(value = "/listFiles", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult listFiles(@RequestParam String bucket) throws Exception {
		return AjaxResult.success("200", minioUtil.listFiles(bucket));
	}
	
	@ApiOperation("下载一个文件")
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	@ResponseBody
	public void downloadFile(@RequestParam String bucket, @RequestParam String objectName,
			HttpServletResponse response) throws Exception {
		InputStream stream = minioUtil.download(bucket, objectName);
		ServletOutputStream output = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(objectName.substring(objectName.lastIndexOf("/") + 1), "UTF-8"));
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
		IOUtils.copy(stream, output);
	}
	
	
	@ApiOperation("删除一个文件")
	@RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult deleteFile(@RequestParam String bucket, @RequestParam String objectName) throws Exception {
		minioUtil.deleteObject(bucket, objectName);
		return AjaxResult.success();
	}
	
	@ApiOperation("删除一个桶")
	@RequestMapping(value = "/deleteBucket", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult deleteBucket(@RequestParam String bucket) throws Exception {
		minioUtil.deleteBucket(bucket);
		return AjaxResult.success();
	}	
}