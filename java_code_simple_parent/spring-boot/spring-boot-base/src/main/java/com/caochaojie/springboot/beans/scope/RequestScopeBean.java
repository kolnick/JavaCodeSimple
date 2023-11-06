package com.caochaojie.springboot.beans.scope;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * request作用域
 *
 * @author sunchaser admin@lilu.org.cn
 * @since JDK8 2022/3/1
 */
@Component
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestScope
public class RequestScopeBean {
}
