package com.baskbull.library_system.service.impl;

import com.baskbull.library_system.entity.Book;
import com.baskbull.library_system.mapper.BookMapper;
import com.baskbull.library_system.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baskbull
 * @since 2020-11-25
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
