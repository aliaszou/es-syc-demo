# es-syc-demo

___Doflamingo___

一个简单的demo用于拦截mybaties插入、更新和删除操作自动同步至elasticsearch。

这里的更新操作建议带上@Param注解，便于定位更新的数据，第一个参数demo中用的是唯一键。