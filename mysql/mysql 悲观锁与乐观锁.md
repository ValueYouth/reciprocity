# 一.介绍

* 乐观锁(Optimistic Locking): 相对悲观锁而言，乐观锁假设认为数据一般情况下不会造成冲突，所以在数据进行提交更新的时候，才会正式对数据的冲突与否进行检测。如果发现冲突了，则让返回用户错误的信息，让用户决定如何去做。
* 悲观锁(Pessimistic Locking): 对数据被外界（包括本系统当前的其他事务，以及来自外部系统的事务处理）修改持保守态度。因此，在整个数据处理过程中，将数据处于锁定状态。

# 二.悲观锁实践

## 实现方式

* 大多数情况下依靠数据库的锁机制实现
* 一般使用select ... for update对所选择的数据进行加锁处理。例如：select * from customer_info where customer_name = '小明' for update
* 当数据库执行select for update时，会获取被select中的数据行的行锁。因此其他并发执行的select for update如果试图选中同一行则会发生排斥（需要等待行锁被释放），因此达到锁的效果。
* select for update获取的行锁会在当前事务结束时自动释放，因此必须在事务中使用。

## 注意事项

* 要使用悲观锁，我们必须关闭mysql数据库的自动提交属性
* 在事务中，只有SELECT ... FOR UPDATE 或LOCK IN SHARE MODE 同一笔数据时会等待其它事务结束后才执行，一般SELECT ... 则不受此影响
* Row Lock与Table Lock：MySQL InnoDB默认Row-Level Lock，所以只有「明确」地指定主键，MySQL 才会执行Row lock (只锁住被选取的数据) ，否则MySQL 将会执行Table Lock (将整个数据表单给锁住)
* FOR UPDATE 仅适用于InnoDB，且必须在事务区块(BEGIN/COMMIT)中才能生效

## 举例说明

假设有个表单products ，里面有id 跟name 二个栏位，id 是主键。

* select * from products where id = '1' for update; // 明确指定主键，并且有此数据。【Row lock】
* select * from products where id = '2' for update; // 明确指定主键，若查无数据，则没有锁
* select * from products where name = 'mouse' for update; // 无主键 【Table Lock】
* select * from products where id <> '3' for update; // 主键不明确 【Table Lock】
* select * from products where id like '4%' for update; // 主键不明确 【Table Lock】

# 三. 乐观锁实践

## 实现方式

* 数据版本（Version）。这是乐观锁最常用的一种实现方式。何谓数据版本？即为数据增加一个版本标识，一般是通过为数据库表增加一个数字类型的 “version” 字段来实现。当读取数据时，将version字段的值一同读出，数据每更新一次，对此version值加一。当我们提交更新的时候，判断数据库表对应记录的当前版本信息与第一次取出来的version值进行比对，如果数据库表当前版本号与第一次取出来的version值相等，则予以更新，否则认为是过期数据。
* 时间戳（timestamp）。在更新提交的时候检查当前数据库中数据的时间戳和自己更新前取到的时间戳进行对比，如果一致则OK，否则就是版本冲突。

## 举例说明

假设有个表单products ，里面有id、name、version 三个栏位，id 是主键。现在需要更新产品名称

* update products set name = 'mouse', version = version + 1 where id = '1' and version = #{version}; // where条件中的version为本次查询得到的