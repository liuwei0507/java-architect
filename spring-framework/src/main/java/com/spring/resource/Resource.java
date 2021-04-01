package com.spring.resource;

import java.io.InputStream;

/**
 * 提供对资源的访问
 */
public interface Resource {
    InputStream getResourceAsStream();
}
