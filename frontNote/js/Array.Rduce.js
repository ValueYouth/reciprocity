var values = [1,2,3,4,5];
const reducer = (pre, cur) => pre + cur;
// console.log(values.reduce(reducer));
// console.log(values.reduce(reducer, 5));
/*
* arr.reduce(callback[, initialValue])
* callback 执行数组中每个值的函数，包含四个参数：
*   accumulator
*   累加器累加回调的返回值；它是上一次调用回调时返回的累积值，或者initialValue
*   currentValue
*   数组正在处理的元素
*   currentIndex | 可选
*   数组中正在处理
*   array | 可选
*   调用reduce的数组
*   initialValue | 可选
*   用作第一个调用callback的第一个参数的值。如果没有提供初始值，则将使用数据中的第一个元素。在没有初始值的空数组上调用reduce将报错
*   返回值
*   函数累计处理的结果
*   !!!（注意：如果没有提供initialValue，reduce会从索引1的地方开始执行callback方法，跳过第一个索引。如果提供initialValue，从索引0开始。）
* */
// 数组求和
const sum = [0,1,2,3].reduce((pre, cur) => pre + cur, 0);
console.log(sum);
// 将二维数组转化为一维数组
const flattened = [[0, 1], [2, 3], [4, 5]].reduce((acc, cur) => acc.concat(cur), []);
console.log(flattened);
// 计算数组中每个元素出现的次数
const names = ['aa', 'bb', 'cc', 'aa'];
const countedNames = names.reduce((allNames, name) => {
    if (name in allNames) {
        allNames[name] ++;
        // console.log('name:', name);
        // console.log('allNames:', allNames);
    } else {
        // console.log('name:', name);
        // console.log('allNames:', allNames);
        allNames[name] = 1;
    }
    return allNames;
}, {});
console.log(countedNames);
// 使用扩展运算符和initialValue绑定包含在对象数组中的数组
const friends = [{
    name: 'Anna',
    books:['book1','book2','book3'],
    age: 22
},{
    name: 'Anna1',
    books:['book3','book4','book6'],
    age: 25
},{
    name: 'Anna2',
    books:['book9','book5'],
    age: 28
}];

const allbooks = friends.reduce((pre, curr) => {
    // console.log('pre:', pre);
    // console.log('curr:', curr);
    return [...pre, ...curr.books]
}, ['book0']);
// console.log(allbooks);
// 数组去重
let arr = [1,2,4,5,5,5,6,8,9,64,21,1,1,4];
let result = arr.sort().reduce((init, current) => {
    if (init.length === 0 || init[init.length - 1] !== current) {
        init.push(current);
    }
    console.log('init:', init);
    console.log('current:', current);
    return init;
},[]);
console.log(result);
