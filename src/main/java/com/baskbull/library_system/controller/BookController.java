package com.baskbull.library_system.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baskbull.library_system.common.lang.Result;
import com.baskbull.library_system.entity.Book;
import com.baskbull.library_system.entity.Borrow;
import com.baskbull.library_system.service.BookService;
import com.baskbull.library_system.service.BorrowService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author baskbull
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    BorrowService borrowService;

    @GetMapping("/list")
    public Result list(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                       @RequestParam(name = "pageSize", defaultValue = "8") Integer pageSize) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("bk_id");
        Page page = new Page(pageNo, pageSize);
        IPage books = bookService.page(page, queryWrapper);
        return Result.ok(books);
    }

    @GetMapping("/listByRdId")
    public Result listByRdId(@RequestParam(name = "rdId") Integer rdId,
                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "8") Integer pageSize) {
        QueryWrapper<Borrow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rd_id",rdId);
        List<Borrow> BorrowList = borrowService.list(queryWrapper);
        List<Integer> BkIdList = new ArrayList<>();
        for(Borrow borrow:BorrowList){
            BkIdList.add(borrow.getBkId());
        }
        List<Book> bookList = new ArrayList<>();
        for(Integer i:BkIdList){
            bookList.add(bookService.getById(i));
        }
        return Result.ok(bookList);
    }

    @GetMapping("/detail/{bkId}")
    public Result detail(@PathVariable(name = "bkId") Long bkId) {
        Book book = bookService.getById(bkId);
        Assert.notNull(book, "未查阅到该书");
        return Result.ok(book);
    }

    @GetMapping("/searchBkId")
    public Result searchBkId(@RequestParam(required = true) String content) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("bk_name",content);
        List<Book> bookList = bookService.list(queryWrapper);
        Assert.notNull(bookList, "未查阅到该书");
        return Result.ok(bookList);
    }

    //    @RequiresAuthentication
    @PostMapping("/add")
    public Result add(@Validated @RequestBody Book book) {
        book.setBkDateIn(LocalDateTime.now());
        book.setBkStatus("在馆");
        if (book.getBkAuthor().isEmpty()) {
            book.setBkAuthor("暂无");
        }
        if (book.getBkPress().isEmpty()) {
            book.setBkPress("暂无");
        }
        if (book.getBkCatalog().isEmpty()) {
            book.setBkCatalog("暂无");
        }
        if (book.getBkCover().isEmpty()) {
            book.setBkCover("暂无");
        }
        if (book.getBkBrief().isEmpty()) {
            book.setBkBrief("暂无");
        }
        bookService.save(book);
        return Result.ok("添加成功!");
    }

    //    @RequiresAuthentication
    @PutMapping("/edit")
    public Result edit(@Validated @RequestBody Book book) {
        bookService.updateById(book);
        return Result.ok("编辑成功!");
    }
}
