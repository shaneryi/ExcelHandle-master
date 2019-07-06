package com.shexd.Controller;

import com.google.common.collect.Lists;
import com.shexd.Entity.Book;
import com.shexd.Entity.User;
import com.shexd.util.DateUtils;
import com.shexd.util.ExportExcel;
import com.shexd.util.ImportExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 下载输入数据的模板
     *
     * @param response
     */
    @RequestMapping("import/template")
    public void importFileTemplate(HttpServletResponse response) {
        try {
            //定义文件名称
            String fileName = "User_Data_import_template.xlsx";
            List<User> list = Lists.newArrayList();
            new ExportExcel("User Data", User.class, 1).setDataList(list).write(response, fileName).dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载输入数据的模板
     *
     * @param response
     */
    @RequestMapping("import/templateBook")
    public void importFileTemplateBook(HttpServletResponse response) {
        try {
            //定义文件名称
            String fileName = "Book_Data_import_template.xlsx";
            List<User> list = Lists.newArrayList();
            new ExportExcel("Book Data", Book.class, 1).setDataList(list).write(response, fileName).dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入已经填好数据的Excel
     *
     * @param multipartFile
     */
    @RequestMapping(value = "importBook", method = RequestMethod.POST)
    public void importFileBook(MultipartFile multipartFile) {
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(multipartFile, 1, 0);
            List<Book> list = ei.getDataList(Book.class);
            for (Book user : list) {
                try {
                    //to do: 保存处理数据
                    //userService.save(user);
                    logger.info(user.toString());
                    successNum++;
                } catch (ConstraintViolationException ex) {
                    failureNum++;
                } catch (Exception ex) {
                    failureNum++;
                }
                System.err.println(user);
            }

            if (failureNum > 0) {
                failureMsg.insert(0, ", Failures: " + failureNum);
            }
            logger.info("Had Operation " + successNum + " Data;" + " " + "Failure " + failureNum);
        } catch (Exception e) {
            logger.error("导入失败", e);
        }
    }


    /**
     * 导入已经填好数据的Excel
     *
     * @param multipartFile
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public void importFile(MultipartFile multipartFile) {
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(multipartFile, 1, 0);
            List<User> list = ei.getDataList(User.class);
            for (User user : list) {
                try {
                    //to do: 保存处理数据
                    //userService.save(user);
                    logger.info(user.toString());
                    successNum++;
                } catch (ConstraintViolationException ex) {
                    failureNum++;
                } catch (Exception ex) {
                    failureNum++;
                }
                System.err.println(user);
            }

            if (failureNum > 0) {
                failureMsg.insert(0, ", Failures: " + failureNum);
            }
            logger.info("Had Operation " + successNum + " Data;" + " " + "Failure " + failureNum);
        } catch (Exception e) {
            logger.error("导入失败", e);
        }
    }

    /**
     * 导出Excel文件
     *
     * @param response
     */
    @RequestMapping("export")
    public void export(HttpServletResponse response) {
        try {
            String fileName = "User Data" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            List<User> users = new ArrayList<>();
            User user1 = new User();
            user1.setUserName("小明");
            user1.setNickName("猪小明");
            user1.setAge(20);
            user1.setNote("备注");
            user1.setBirth(DateUtils.parseDate("1992-10-10"));
            users.add(user1);
            User user2 = new User();
            user2.setUserName("小红");
            user2.setNickName("小小红");
            user2.setAge(18);
            user2.setBirth(DateUtils.parseDate("1998-11-09"));
            user2.setNote(null);
            users.add(user2);
            new ExportExcel("User Data", User.class, 2).setDataList(users).write(response, fileName).dispose();
        } catch (Exception e) {
        }
    }

    /**
     * 导出Excel文件
     *
     * @param response
     */
    @RequestMapping("exportTest")
    public void exportTest(HttpServletResponse response) {
        try {
            String fileName = "Book Data" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            List<Book> books = new ArrayList<>();
            Book book = new Book();
            book.setId("1");
            book.setName("大明");
            book.setPrice(new BigDecimal(100));
            book.setAddTime(new Date());
            book.setNo("123");
            Book book1 = new Book();
            book1.setId("2");
            book1.setName("小明");
            book1.setPrice(new BigDecimal(200));
            book1.setAddTime(new Date());
            book1.setNo("321");
            books.add(book);
            books.add(book1);
            new ExportExcel("Book Data", Book.class, 2).setDataList(books).write(response, fileName).dispose();
        } catch (Exception e) {
        }
    }
}
