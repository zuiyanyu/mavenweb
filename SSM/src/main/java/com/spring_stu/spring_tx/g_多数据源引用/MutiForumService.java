package com.spring_stu.spring_tx.g_多数据源引用;

public class MutiForumService {
    //@Transactional("topic")  // 使用名为topic的事务管理器
    @TopicTransactional
    public void addTopic(String topic){}

    //@Transactional("forum")  // 使用名为forum的事务管理器
    @ForumTransactional
    public void updateForum(String forum){}
}
