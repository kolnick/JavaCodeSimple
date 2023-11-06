package com.caochaojie.springboot.beans.scope;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * application作用域
 *
 * @author sunchaser admin@lilu.org.cn
 * @since JDK8 2022/3/2
 */
@Component
//@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@ApplicationScope
public class ApplicationScopeBean {
}
