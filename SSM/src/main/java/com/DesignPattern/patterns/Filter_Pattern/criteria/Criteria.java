package com.DesignPattern.patterns.Filter_Pattern.criteria;


import com.DesignPattern.patterns.Filter_Pattern.Person;

import java.util.List;

//为标准（Criteria）创建一个接口。
public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}