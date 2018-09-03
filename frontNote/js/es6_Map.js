/*
* JavaScript的对象，本质是键值对的集合，但是传统上只用字符串当键名，为了解决这个问题，es6提供了Map数据结构。它类似于对象，也是键值对的集合，但是“键”
* 的范围不限于字符串，各种类型的值（包括对象）都可以当作键。也就是说，object结构提供了“字符串一值”的对应，Map结构提供了“值一值”的对应，是一种更完善的
* hash结构实现。如果你需要键值对的结构，Map比Object更合适。
* */
const map = new Map([['name','张三'],['title','测试']]);
console.log(map);
console.log(map.size);
console.log(map.has('name'));
console.log(map.get('name'));
/*
*多数用法同Set
*/