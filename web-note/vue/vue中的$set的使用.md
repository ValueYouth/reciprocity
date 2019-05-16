##vue中的$set的使用
在我们使用vue进行来发时，如果在实例创建后添加新的属性到实例上，他不会触发视图更新。原因在于js对象传给vue实例作为data选项，vue将遍历它的属性，用object.defineProperty会将他们转换为getter/setter,而新添加的属性，没有get/set方法，因此，设置了sex值后vue并不会自动更新视图；

要处理这种情况，我们可以使用`$set()`方法既可以新增属性，又可以触发视图更新，其用法`this.$set(this.data,”key”,value’)`，例如：
    
````````
<script>
var data = {
    name: "测试",
    age: '3'
}    
var key = 'content';
new Vue({
    el:'#app',
    data: data,
    mounted() {
        this.$set(data,'sex', '男');
    }
});
</script>
`````````
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTA4MzE4OTM0MF19
-->