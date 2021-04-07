package com.hyd.base.security.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;

@EnableSwagger2//swagger 注解
@Configuration//配置类
public class SwaggerConfig {

    @Bean
    public Docket webapiconfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(getApiInfo())
                .select()
                //此处swagger2.X.0版本和3.0.0版本.path()使用的Predicates不同，3.0.0为java.util自带的Predicate 注意swagger版本区别
                .paths(Predicates.not(getPaths()))
                .build();
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("API 文档")
                .description("API 接口文档描述")
                .version("1.0")
                .contact(new Contact("java","http://www.ayiyaha.net","ayiyaha@yeah.net"))
                .build();
    }

    private Predicate<String> getPaths() {
        return or(
                //PathSelectors.regex("/admin/.*"),
                PathSelectors.regex("/error.*")
        );
    }
}
