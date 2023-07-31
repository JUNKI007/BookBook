package com.example.brunchStory.post.domain.event;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

//public class PostCreateEventListener {
//    @Autowired
//    private SchedulerFactoryBean schedulerFactoryBean;
//
//    @EventListener
//    public void handlePostCreatedEvent(PostCreateEvent event) {
//        JobDataMap jobDataMap = new JobDataMap();
////        jobDataMap.put("email", event.getPost().getAuthor().getEmail());
////        jobDataMap.put("title", event.getPost().getTitle());
//
//        JobDetail jobDetail = JobBuilder.newJob(EmailJob.class)
//                .usingJobData(jobDataMap)
//                .build();
//
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .startNow()
//                .build();
//
//        try {
//            Scheduler scheduler = schedulerFactoryBean.getScheduler();
//            scheduler.scheduleJob(jobDetail, trigger);
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//}
//X EmailService 완성시 보완
//X 게시글이 작성되는 것을 Trigger로해서 email을 발송하는 것을 Job으로 함.