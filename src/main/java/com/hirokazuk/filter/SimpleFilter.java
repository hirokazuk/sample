package com.hirokazuk.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "SimpleFilter"                  // filter-name
         , dispatcherTypes = { DispatcherType.REQUEST } // dispatcher
         , urlPatterns = { "/*" }                       // filter-mapping
         , initParams = {                               // initParams (comma separated)
            @WebInitParam(name = "message", value = "SimpleFilter") // param1
          })
public class SimpleFilter implements Filter {
    /*
     * アノテーションでフィルタの順序を指定することは出来ない
     * 順序を指定する際はweb.xmlを定義し、filter-mappingを記載する必要がある
     *  <filter-mapping>
     *    <filter-name>SimpleFilter</filter-name>
     *    <url-pattern /> 
     *  </filter-mapping>
     *  ※url-patternタグは必須だが、内容を省略可能。
     *  @see http://qiita.com/opengl-8080/items/db385b934690d6c3d13a
     *  
     *  Filterサブクラスの正準名(パッケージ名＋クラス名)の辞書順(a→z)らしい
     */
 
    protected FilterConfig _filterConfig;
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }
 
    @Override
    public void destroy() { }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse)response;
        
        process(res);
                
        chain.doFilter(request, response);
    }

    private void process(HttpServletResponse res) throws IOException {
        res.addHeader("message :", _filterConfig.getInitParameter("message"));
    }
}