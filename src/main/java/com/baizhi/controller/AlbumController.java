package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.entity.Album;
import com.baizhi.entity.AlbumMessage;
import com.baizhi.service.AlbumService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/querySome")
    public AlbumMessage querySome(Integer rows, Integer page) {

        AlbumMessage am = albumService.querySome(rows, page);

        return am;
    }

    @RequestMapping("/queryOne")
    public Album queryOne(Integer id) {
        Album a = albumService.queryOne(id);
        return a;
    }

    @RequestMapping("/insertOne")
    public void insertOne(Album a, HttpSession session, MultipartFile file1) throws IOException {
        System.out.println(a);
        ServletContext ctx = session.getServletContext();
        String realPath = ctx.getRealPath("/image");
        // 目标文件
        File descFile = new File(realPath + "/" + file1.getOriginalFilename());
        // 上传
        file1.transferTo(descFile);
        a.setCoverImg("/image/" + file1.getOriginalFilename());
        albumService.insertOne(a);
    }

    @RequestMapping("out")
    public void outExport() {
        List<Album> list = albumService.queryAll();
        for (Album a : list) {
            String url = "F:\\cmfz\\cmfz_test\\src\\main\\webapp" + a.getCoverImg();
            a.setCoverImg(url);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑信息", "专辑"),
                Album.class, list);
        try {
            workbook.write(new FileOutputStream(new File("D:/easypoi.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("init")
    public void initExport() {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        //params.setSheetNum(9);
        params.setNeedSave(true);
        long start = new Date().getTime();
        List<Album> list = ExcelImportUtil.importExcel(new File(
                "D:/easypoi.xls"), Album.class, params);
        for (Album a : list) {
            System.out.println(a);
        }
    }
}
