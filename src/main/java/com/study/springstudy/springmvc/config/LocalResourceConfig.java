package com.study.springstudy.springmvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 로컬서버에 저장된 이미지를 웹 브라우저에서 불러올 수 있도록
// 로컬서버 파일경로를 웹 서버 URL 로 변경하는 설정
@Configuration
public class LocalResourceConfig implements WebMvcConfigurer {

    @Value("${file.upload.root-path}")
    private String rootPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // http://localhost:8383/local/2024/06/05/b3093b25-aec7-4454-ba2d-36248d95dc7d_free-icon-tiger-8453554.png

        /*
            ResourceLocations : 로컬에 있는 경로
            ResourceHandler : 해당 로컬 경로를 web url 로 변환

            ex) D:/xxx/dog.jpg

            local 접근 - file:D:/xxx/dog.jpg
            web 접근 - http://localhost8383/dog.jpg
         */
        registry.addResourceHandler("/local/**")
                .addResourceLocations("file:" + rootPath);
    }
}
