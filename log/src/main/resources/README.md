Spring Boot默认日志系统
Spring Boot默认使用LogBack日志系统，如果不需要更改为其他日志系统如Log4j2等，则无需多余的配置

日志级别总共有TRACE < DEBUG < INFO < WARN < ERROR < FATAL ，且级别是逐渐提供，
如果日志级别设置为INFO，则意味TRACE和DEBUG级别的日志都看不到

特别提醒：Spring Boot 只有1.3.x和1.3.x以下版本才支持log4j的日志配置，1.3.x以上版本只支持log4j2，logback的日志配置