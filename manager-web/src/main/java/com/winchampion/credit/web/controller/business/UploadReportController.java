package com.winchampion.credit.web.controller.business;

import com.winchampion.credit.business.domain.*;
import com.winchampion.credit.business.service.ReportCountryService;
import com.winchampion.credit.business.vo.IncrementVo;
import com.winchampion.credit.business.vo.UploadResponseVo;
import com.winchampion.credit.common.interfaces.country.res.ResultVo;
import com.winchampion.credit.common.util.*;
import com.winchampion.credit.user.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/28
 * @description: 上报国家平台数据 控制类
 */
@Controller
@RequestMapping("/business/reporting")
public class UploadReportController {

    private static Logger logger = LoggerFactory.getLogger(UploadReportController.class);
    @Autowired
    private ReportCountryService reportCountryService;
    //前缀
    private String prefix = "business/reportingCountry";

    @Value("${Upload.templateName}")
    private String templateName;
    // 上传的文件路径
    @Value("${Upload.uploadPath}")
    private String uploadPath;
    /**
     * 上传上报国家平台页面
     */
    @GetMapping("/uploadReportPage")
    //@RequiresPermissions("business:homeProductEdit:view")
    public String hotProductPage(Model model) {

        return prefix + "/uploadReportingList";
    }

    /**
     * 上报上传记录
     *
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/getReportRecord")
    //@RequiresPermissions("business:homeFinEnt:view")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<UploadReportDo> customerDOList = reportCountryService.getAllReport(query);
        int total = reportCountryService.count(query);
        PageUtils pageUtils = new PageUtils(customerDOList, total);
        return pageUtils;
    }


    /**
     * 打开上传excel模态窗口页面
     *
     * @return
     */
    @GetMapping("/openUploadModel")
    public String openUploadModel() {
        return prefix + "/uploadPage";
    }

    /**
     * 下载最新版本模板
     *
     * @return 返回excel模板
     */
    @GetMapping(value = "/downloadNewModel", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object downloadModel() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("template.xlsx");
        InputStream inputStream = classPathResource.getInputStream();
        // 查询最新上传文件的路径
        UploadDo uploadDo = reportCountryService.getNewUpload();
        String path = uploadDo.getFilePath();
        String fileName = uploadDo.getVersion();
        ResponseEntity<InputStreamResource> response = null;
        try {
            //response = DownloadFileUtil.download(inputStream,templatePath, templateName);
            response = DownloadFileUtil.download(inputStream, templateName);
        } catch (Exception e) {
            logger.error("下载模板失败");
        }
        return response;
    }

    /**
     * 下载指定日期上传的文件
     *
     * @param date
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/downloadNow/{date}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object downloadNow(@PathVariable("date") String date) {
        // 查询最新上传文件的路径
        UploadDo uploadDo = reportCountryService.getDateUpload(date);
        String path = uploadDo.getFilePath();
        String fileName = uploadDo.getVersion();
        ResponseEntity<InputStreamResource> response = null;
        try {
            response = DownloadFileUtil.download(path, fileName);
        } catch (Exception e) {
            logger.error("下载模板失败");
        }
        return response;
    }
    @PostMapping("/upload")
    @ResponseBody
    public R details(@RequestParam("file") MultipartFile multipartFile,
                     HttpServletRequest request) throws IOException {
        // 设置版本号
        String version = DateUtils.getSysDateAndTime(DateUtils.DATE_TIME_PATTERN_1) +
                StringUtils.getRandomNumberCode(3);
        //上传文件的位置,默认会在项目根目录找static文件夹,需手动创建,不然找到是临时路径。

        //String path = request.getSession().getServletContext().getRealPath("/uploads/" + version + "/");
        String path = uploadPath+"/uploads/" + version + "/";
        //判断该路径是否存在
        File file = new File(path);
        // 若不存在则创建该文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取上传文件的后缀名
        String fileType = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf("."));
        // 获取上传文件的名称
        //String filename = upload.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String filename = version + fileType;

        // 完成文件上传
        try {
            //multipartFile.transferTo(new File(path + multipartFile.getOriginalFilename()));
            multipartFile.transferTo(new File(path + filename));
            // 插入上传文件的记录
            // todo 把文件名存入数据库(此步骤省略)
            UploadDo uploadDo = new UploadDo();
            uploadDo.setId(IdGen.uuid());
            uploadDo.setDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            uploadDo.setFilePath(path + filename);


            uploadDo.setVersion(version);
            uploadDo.setUserId(UserUtils.getUserId());
            int result = reportCountryService.insertUploadRecord(uploadDo);
            if (result > 0) {
                logger.info("====上传文件记录成功===");
            }else{
                logger.debug("===上传文件记录失败===");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("====上传文件失败原因：==" + e.getMessage());
        }
        return R.ok("上传成功").put("filePath", path + multipartFile.getOriginalFilename()).put("version", version);

    }

    /**
     * 上传excel
     * @param request
     */
    @PostMapping("/uploadExcel")
    @ResponseBody
    public UploadResponseVo uploadExcel(UploadDo uploadDo,
                                        HttpServletRequest request)
            throws IOException {

        // 查询最新上传文件的路径
        UploadDo newUpload = reportCountryService.getNewUpload();
        String filePath = newUpload.getFilePath();
        String recordId = newUpload.getId();
        UploadResponseVo uploadResponseVo = new UploadResponseVo();

        // 读取增量的Sheet列
        //List<IncrementVo> incrementList = readExcelSheet1_2(filePath, 0,52, 0);
        List<IncrementDo> incrementList =reportCountryService.readExcel(filePath,1,1);
        // 读取全量的Sheet列
        //List<IncrementVo> quantityList = readExcelSheet1_2(filePath,  0, 31, 1);

        List<QuantityDo> quantityList = reportCountryService.readExcelSheet2(filePath,2,1);

        // 读取首页展示数据 sheet 列
        //List<IndexShowDo> indexShowList = readExcelSheet3(filePath,  0, 5, 2);
        List<IndexShowDo> indexShowList = reportCountryService.readExcelSheet3(filePath,3,1);

        // 验证首页展示数据格式是否正确
        ResultVo<IndexShowDo> indexResult = reportCountryService.checkIndexShowList(indexShowList);


        // 验证增量和全量数据格式是否正确
        ResultVo<IncrementVo> increAndQuan = reportCountryService.checkIncrementAndQuantity(incrementList,
                quantityList);
        if (indexResult.getSuccess() == true && increAndQuan.getSuccess() == true) {



                 // 插入上传记录，传入 excel增量数据，excel全量数据，excel 首页展示数据Sheet列，推送数据
                 int result = reportCountryService.insertUploadRecord(incrementList, quantityList, indexShowList,
                         recordId);
                 if (result > 0) {
                     logger.info("上传Excel文件记录成功！");
                 } else {
                     logger.debug("上传Excel文件记录失败！");
                 }


            uploadResponseVo.setIndexResult(indexResult);
            uploadResponseVo.setIncreAndQuan(increAndQuan);
            return uploadResponseVo;
        } else {
            uploadResponseVo.setIndexResult(indexResult);
            uploadResponseVo.setIncreAndQuan(increAndQuan);
            return uploadResponseVo;
        }
    }


    /**
     * 读取 上传国家Excel 中的 Sheet1 增量 和 Sheet2 全量
     *
     * @param file
     * @param fileInputStream
     * @param headerNum
     * @param lastDataRowNum
     * @param sheetIndex
     * @return
     */
    public static List<IncrementVo> readSheet1And2Excel(File file, FileInputStream fileInputStream, int headerNum,
                                                        int lastDataRowNum, int sheetIndex) {

        List<IncrementVo> list = new ArrayList<>();
        try {
            ImportExcel ei = new ImportExcel(file, fileInputStream, headerNum, lastDataRowNum, sheetIndex);
            list = ei.getDataList(IncrementVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 从数据库中读取 excel文件路径
     * @param path
     * @param headerNum
     * @param lastDataRowNum
     * @param sheetIndex
     * @return
     */
    public static List<IncrementVo> readExcelSheet1_2(String path, int headerNum, int lastDataRowNum, int sheetIndex) {


        File fileXls = new File(path);
        List<IncrementVo> list = new ArrayList<>();
        try {
            ImportExcel ei = new ImportExcel(fileXls, headerNum, lastDataRowNum, sheetIndex);
            list = ei.getDataList(IncrementVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 读取 上传国家Excel 中的 Sheet3 首页展示数据
     * 备注： 是读取前端上传的文件，而不是读取路径
     * @param file
     * @param fileInputStream
     * @param headerNum
     * @param lastDataRowNum
     * @param sheetIndex
     * @return
     */
    public static List<IndexShowDo> readSheet3Excel2(File file, FileInputStream fileInputStream, int headerNum,
                                                     int lastDataRowNum, int sheetIndex) {
        List<IndexShowDo> list = new ArrayList<>();
        try {
            ImportExcel ei = new ImportExcel(file, fileInputStream, headerNum, lastDataRowNum, sheetIndex);
            list = ei.getDataList(IndexShowDo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 从数据库中读取 excel文件路径
     * @param path
     * @param headerNum
     * @param lastDataRowNum
     * @param sheetIndex
     * @return
     */
    public static List<IndexShowDo> readExcelSheet3(String path, int headerNum, int lastDataRowNum, int sheetIndex) {


        File fileXls = new File(path);
        List<IndexShowDo> list = new ArrayList<>();
        try {
            ImportExcel ei = new ImportExcel(fileXls, headerNum, lastDataRowNum, sheetIndex);
            list = ei.getDataList(IndexShowDo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
