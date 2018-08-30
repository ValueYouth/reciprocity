// 本文源自阮一峰老师的es6入门书籍
// set本身是一个构造函数，用来生成set数据结构， 成员的值是唯一的，没有重复的值
const s = new Set();
[1,2,3,4,4,4,5,6].forEach(x => s.add(x));
for (let i of s) {
    // console.log(i);
}
// console.log('size:',s.size); // size: 6
// console.log('set:',s); // set: Set { 1, 2, 3, 4, 5, 6 }
// 去除数组重复成员的方法
// console.log('set=>array:',[...s]); // set=>array: [ 1, 2, 3, 4, 5, 6 ]
// 向set加入值的时候，不会发生类型转换，Set内部判断两个值是否不同，使用的算法叫做“same-value-zero equality”,它类似于精确等于（===），主要的区别是NAN等于自身，而精确运算符认为NAN不等于自身
let NaNSet = new Set();
let a = NaN;
let b = NaN;
NaNSet.add(a);
NaNSet.add(b);
// console.log(NaNSet); // Set { NaN }
// 两个对象总是不相等的
let objSet = new Set();
objSet.add({});
objSet.add({});
// console.log(objSet.size); // 2
// Set实例的属性和方法
/*
* set结构的实例有以下属性
* Set.prototype.constructor: 构造函数，默认是Set函数
* Set.prototype.size:返回Set实例成员的总数
* Set实例的方法分为两大类：操作方法（用于操作数据）和遍历方法（用于遍历成员）。
* add(value)：添加某个值。返回set结构本身
* delete(value)删除某个值，返回一个布尔值，表示删除是否成功
* has(value)返回一个布尔值，表示该值是否为Set的成员
* clear()清除所有成员，没有返回值
* */

s.add(1).add(NaN).add({});
s.delete({});
// s.delete(NaN);
// console.log(s.size);
// console.log(s.has(1));
// console.log(s.has(NaN));
// console.log(s.has({}));
/*
* 数组与set结构转换
* */
const arr = Array.from(s);
// console.log(arr); // [ 1, 2, 3, 4, 5, 6, {} ]
const newSet = new Set(arr);
// console.log(newSet); // Set { 1, 2, 3, 4, 5, 6, {} }
/*
* 遍历操作
* keys():返回键名的遍历器
* values()：返回键值的遍历器
* entries()：返回键值对的遍历器
* forEach()：使用回调函数遍历每个成员
* 特别指出：set的遍历顺序就是插入顺序。这个特性非常有用，比如使用Set保存一个回调函数列表，调用时就能保证按照添加顺序调用
* */
for (let item of s.keys()) {
    // console.log(item);
}
// 1
// 2
// 3
// 4
// 5
// 6
// {}
for (let item of s.values()) {
    // console.log(item);
}
// 1
// 2
// 3
// 4
// 5
// 6
// {}
for (let item of s.entries()) {
    // console.log(item);
}
// [ 1, 1 ]
// [ 2, 2 ]
// [ 3, 3 ]
// [ 4, 4 ]
// [ 5, 5 ]
// [ 6, 6 ]
// [ {}, {} ]
for (let item of s) { // Set结构默认可遍历，它的默认遍历器生成函数就是它的values方法
    // console.log(item);
}
/*
* Set结构的实例与数组一样，也拥有forEach方法，用于对每个成员执行某种操作，没有返回值
* */
// s.forEach((values, key) => console.log(key + ':' + values));
/*
* 配合数组的map,filter方法可以很容易实现并集，交集，和差集。
* */
let aSet = new Set([1,2,3]);
let bSet = new Set([4,3,3]);
// 并集
let union = new Set([...aSet, ...bSet]); // Set { 1, 2, 3, 4 }
// 交集
let intersect = new Set([...aSet].filter(x => bSet.has(x))); // Set { 3 }
// 差集
let difference = new Set([...aSet].filter(x => !bSet.has(x))); // Set { 1, 2 }
console.log(difference);
function union1(){
    let params = Array.from(arguments);
    return params.reduce((pre,cur)=>{
        return new Set([...pre,...cur]);
    })
}
// 测试
console.log(union1([123,123],[456,456],[345,345]));

function intersect1(){
    let params = Array.from(arguments);
    return params.reduce((pre,cur)=>{
        return new Set([...cur].filter(val=>'has' in pre?pre.has(val):pre.includes(val)));
    })
}
//测试
console.log(intersect1([123],[234,123],[123]));

function difference1(){
    let params = Array.from(arguments);
    return params.reduce((pre,cur)=>{
        return new Set([...pre].filter(val=>'has' in cur?!cur.has(val):!cur.includes(val)));
    })
}
//测试
console.log(difference1([123,456],[123],[23]));