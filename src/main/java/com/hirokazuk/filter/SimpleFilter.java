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
     * �A�m�e�[�V�����Ńt�B���^�̏������w�肷�邱�Ƃ͏o���Ȃ�
     * �������w�肷��ۂ�web.xml���`���Afilter-mapping���L�ڂ���K�v������
     *  <filter-mapping>
     *    <filter-name>SimpleFilter</filter-name>
     *    <url-pattern /> 
     *  </filter-mapping>
     *  ��url-pattern�^�O�͕K�{�����A���e���ȗ��\�B
     *  @see http://qiita.com/opengl-8080/items/db385b934690d6c3d13a
     *  
     *  Filter�T�u�N���X�̐�����(�p�b�P�[�W���{�N���X��)�̎�����(a��z)�炵��
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