package com.DesignPattern.patterns.Intercepting_Filter_Pattern.filters;

import com.DesignPattern.patterns.Intercepting_Filter_Pattern.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器链（Filter Chain） - 过滤器链带有多个过滤器，并在 Target 上按 ‘照定义的顺序’执行这些过滤器。
 */
public class FilterChain {
    private List<Filter> filters = new ArrayList<Filter>();
    private Target target;

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public void execute(String request){
        for (Filter filter : filters) {
            filter.execute(request);
        }
        target.execute(request);
    }

    public void setTarget(Target target){
        this.target = target;
    }
}