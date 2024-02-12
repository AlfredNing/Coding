console.log(2 ** 3);

/**
 * 创造对象的方案- 构造函数
 */
function Person(name, age, address) {
    this.name = name;
    this.age = age;
    this.address = address;

    this.eating() = function () {
        console.log(this.name + "在跑步");
    }

    this.running() = function () {
        console.log(this.name + "在跑步~");
    }
}


