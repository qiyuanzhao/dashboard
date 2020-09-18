package com.controller;

import com.alibaba.excel.EasyExcel;
import com.entity.AliEntity;
import com.repository.AlibabaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName UploadAndDownloaderController
 * @Description TODO
 * @Author qiyuanzhao
 * @Date 2020-09-18
 * @Version 1.0
 **/
@RestController
@RequestMapping("/file")
public class DownloaderController {


    private Logger logger = LoggerFactory.getLogger(DownloaderController.class);


    @Value("${downloadFilePath}")
    private String downloadFilePath;

    @Autowired
    private AlibabaRepository alibabaRepository;



    @GetMapping("/download/{taskId}")
    public ResponseEntity<?> commodityDownloader(@PathVariable("taskId") Long taskId,
                                                 HttpServletResponse resp) {

        List<AliEntity> aliEntities = alibabaRepository.findAliEntitiesByTaskId(taskId);

        HashSet<String> set = new HashSet<>();
        set.add("taskId");
        set.add("timeCreated");
        set.add("timeUpdated");
        set.add("createdBy");
        set.add("lastModifiedBy");
        set.add("id");
        set.add("status");
        set.add("version");

        String fileName = commdityDownload(aliEntities, AliEntity.class, set);

        String s = downLoad(fileName, fileName, resp);

        if (StringUtils.isEmpty(s)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(s);
    }


    private String commdityDownload(List message, Class classs, HashSet<String> columns) {

        if (!CollectionUtils.isEmpty(message)) {
            String fileName = System.currentTimeMillis() + ".xlsx";

            logger.info("获取:{}条数据 , 并开始下载文件到服务器", message.size());
            //文件输出位置
            File file = new File(downloadFilePath + fileName);
            if (CollectionUtils.isEmpty(columns)) {
                EasyExcel.write(file, classs).sheet().doWrite(message);
            } else {
                EasyExcel.write(file, classs).excludeColumnFiledNames(columns).sheet().doWrite(message);
            }
            logger.info("下载完成,文件路径:{}", downloadFilePath + fileName);

            //下载成功

            return downloadFilePath + fileName;
        }

        return null;
    }

    private String downLoad(String fileName, String taskName, HttpServletResponse resp) {

        if (StringUtils.isEmpty(fileName)) {
            return null;
        }

        File file = new File(fileName);
        DataInputStream in = null;
        OutputStream out = null;
        try {
            resp.reset();// 清空输出流

            taskName = URLEncoder.encode(taskName, "UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Content-disposition", "attachment; filename=" + fileName);// 设定输出文件头
            resp.setContentType("application/msexcel");// 定义输出类型
            //输入流：本地文件路径
            in = new DataInputStream(
                    new FileInputStream(file));
            //输出流
            out = resp.getOutputStream();
            //输出文件
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            return "下载成功";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }




}
