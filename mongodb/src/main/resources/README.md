MongoDB事先创建test数据库：
use test：如果数据库不存在，则创建数据库，否则切换到指定数据库
db.test.insert({"name":"菜鸟教程"}) ：刚创建的数据库并不在数据库的展示列表中， 要显示它，我们需要向数据库插入一些数据