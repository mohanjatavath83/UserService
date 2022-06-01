package com.userservice.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    protected static final String DEFAULT_PAGE_SIZE = "20";
    protected static final String DEFAULT_CURRENT_PAGE = "0";
}
