本项目单纯实用内存，若需要引入Redis作为缓存，参考Redis工程

@Cacheable:查找缓存 - 有就返回 -没有就执行方法体 - 将结果缓存起来；

@CachePut:执行方法体 - 将结果缓存起来；

@CacheEvict:删除缓存；