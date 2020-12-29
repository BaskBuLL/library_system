package com.baskbull.library_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baskbull.library_system.common.lang.Result;
import com.baskbull.library_system.common.lang.dto.BorrowDTO;
import com.baskbull.library_system.entity.Book;
import com.baskbull.library_system.entity.Borrow;
import com.baskbull.library_system.entity.Reader;
import com.baskbull.library_system.service.BookService;
import com.baskbull.library_system.service.BorrowService;
import com.baskbull.library_system.service.ReaderService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
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
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    BorrowService borrowService;
    @Autowired
    ReaderService readerService;
    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public Result borrowBook(@Validated @RequestBody BorrowDTO borrowDTO){
        Integer bkId = borrowDTO.getBkId();
        Integer rdId = borrowDTO.getRdId();
        Integer operatorId = borrowDTO.getOperatorId();

        QueryWrapper<Reader> queryWrapper = new QueryWrapper<Reader>();
        queryWrapper.eq("rd_id",rdId);
        Reader reader = readerService.getOne(queryWrapper);
        Integer temp = reader.getRdBorrowQty();
        reader.setRdBorrowQty(temp.intValue()+1);
        readerService.updateById(reader);

        Book book = bookService.getById(bkId);
        book.setBkStatus("借出");
        bookService.updateById(book);

        Borrow borrow = new Borrow();
        borrow.setBkId(bkId);
        borrow.setRdId(rdId);
        borrow.setIdContinueTimes(0);
        LocalDateTime now = LocalDateTime.now();
        borrow.setIdDateOut(now);
        borrow.setIdDateRetPlan(now.plusMonths(1L));
        borrow.setIsHasReturn("未还");
        borrow.setOperatorId(operatorId);
        borrow.setOperatorLend(readerService.getById(operatorId).getRdName());
        borrow.setCreateTime(now);
        borrow.setUpdateTime(now);

        borrowService.save(borrow);
        return Result.ok("借阅成功！");
    }

    /**
     * 查看借书
     * @return
     */
    @GetMapping("/operator")
    public Result operator(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                           @RequestParam(name = "pageSize", defaultValue = "8") Integer pageSize){
        QueryWrapper<Borrow> queryWrapper = new QueryWrapper();
        queryWrapper.ne("is_has_return","已还");
        Page page = new Page(pageNo,pageSize);
        IPage borrows = borrowService.page(page,queryWrapper);
        return Result.ok(borrows);
    }

    /**
     * 查看还书
     * @return
     */
    @GetMapping("/money")
    public Result money(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                           @RequestParam(name = "pageSize", defaultValue = "8") Integer pageSize){
        QueryWrapper<Borrow> queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_has_return","已还");
        Page page = new Page(pageNo,pageSize);
        IPage borrows = borrowService.page(page,queryWrapper);
        return Result.ok(borrows);
    }


    @PutMapping("/editBorrow")
    public Result editBorrow(@RequestBody Borrow borrowDetail){

        LocalDateTime now = LocalDateTime.now();
        Borrow borrow = new Borrow();
        Integer rdId = borrowDetail.getRdId();
        Integer bkId = borrowDetail.getBkId();

        QueryWrapper<Reader> queryWrapper = new QueryWrapper<Reader>();
        queryWrapper.eq("rd_id",rdId);
        Reader reader = readerService.getOne(queryWrapper);
        Integer temp = reader.getRdBorrowQty();
        reader.setRdBorrowQty(temp.intValue()-1);
        readerService.updateById(reader);

        Book book = bookService.getById(bkId);
        book.setBkStatus("在馆");
        bookService.updateById(book);

        BeanUtils.copyProperties(borrowDetail,borrow);
        borrow.setIdDateRetAct(now);
        Duration duration = Duration.between(now,borrow.getIdDateRetPlan());
        long day = 0;
        if(now.isBefore(borrow.getIdDateRetPlan())||now.isEqual(borrow.getIdDateRetPlan())){
            day= 0;
        }else if (now.isAfter(borrow.getIdDateRetPlan())){
            day = duration.toDays();
        }
        borrow.setIdOverDay(day);
        borrow.setIdOverMoney(BigDecimal.valueOf(day*0.5));
        borrow.setIsHasReturn("已还");
        borrow.setUpdateTime(now);
        borrowService.updateById(borrow);
        return Result.ok("还书成功");
    }
}
