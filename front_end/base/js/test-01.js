function sum() {
    var res = 0;
    for (let index in arguments) {

        res += arguments[index]
    }

    return res;
}

console.log(sum(1, 2, 3, 4))

