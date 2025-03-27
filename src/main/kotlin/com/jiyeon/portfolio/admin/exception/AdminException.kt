package com.jiyeon.portfolio.admin.exception

import org.springframework.http.HttpStatus

abstract class AdminException(
    httpStatus: HttpStatus,
    message: String) : RuntimeException(message) {
        val httpStatus: HttpStatus = httpStatus
}

class AdminBadRequestException(message: String) : AdminException(
    HttpStatus.BAD_REQUEST,
    message)

class AdminInternalServerErrorException(message: String) : AdminException(
    HttpStatus.INTERNAL_SERVER_ERROR,
    message)