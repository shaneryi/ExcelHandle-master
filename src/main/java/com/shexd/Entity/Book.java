package com.shexd.Entity;

import com.shexd.util.ExcelField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Book
 * @Author JiaWei
 * @Date 2019/7/5 9:15
 * @Description TODO
 */

public class Book {
    private String id;

    private String name;

    private BigDecimal price;

    private Date addTime;

    private String no;

    @ExcelField(title = "id", align = 2, sort = 2)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ExcelField(title = "名字", align = 2, sort = 1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "价格", align = 2, sort = 3)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ExcelField(title = "添加时间", align = 2, sort = 4)
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @ExcelField(title = "编号", align = 2, sort = 5)
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", addTime=" + addTime +
                ", no='" + no + '\'' +
                '}';
    }
}
