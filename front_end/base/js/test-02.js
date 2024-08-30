const person = {
    name: 'niubi',
    age: 30,
    city: 'china'
}

for (let personKey in person) {
    console.log(`key:${personKey}; value:${person[personKey]}`)
}

console.log("========================================")

Object.keys(person).forEach(personKey => {
    console.log(`key:${personKey}; value:${person[personKey]}`)
})

console.log("========================================")

Object.entries(person).forEach(([key, value]) => {
    console.log(`key:${key}; value:${value}`)
})

console.log("========================================")

for (let key of Object.keys(person)) {
    console.log(`key:${key}; value:${person[key]}`)
}

console.log("========================================")
Object.getOwnPropertyNames(person).forEach(_ => {
    console.log(`key:${_}; value:${person[_]}`)
})


console.log("========================================")

console.log('age' in person)
console.log(person.hasOwnProperty('name'))
// console.log(person.hasOwn('name'))