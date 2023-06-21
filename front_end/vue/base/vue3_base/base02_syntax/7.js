Vue.createApp({
    template: "#my-app",
    data() {
        return {
            books: [
                {
                    id: 1,
                    name: '<书本1>',
                    date: '2021-9',
                    price: 85.00,
                    count: 1
                },
                {
                    id: 2,
                    name: '<书本2>',
                    date: '2020-3',
                    price: 42.00,
                    count: 1
                },
                {
                    id: 3,
                    name: '<书本3>',
                    date: '2010-9',
                    price: 87.00,
                    count: 1
                },
                {
                    id: 4,
                    name: '<书本4>',
                    date: '2026-9',
                    price: 112.00,
                    count: 1
                }
            ]
        }
    },
    methods: {
        increment(index) {
            this.books[index].count++;
        },
        decrement(index) {
            this.books[index].count--;
        },
        removeBook(index) {
            // 从index位置移除几个，第三个参数进行替换
            this.books.splice(index, 1);
        },
        formatPrice(price) {
            return '$' + price;
        }
    },
    computed: {
        totalPrice() {
            let finalPrice = 0;
            for (let book of this.books) {
                finalPrice += book.count * book.price;
            }
            return finalPrice;
        }
    },
}
).mount("#app")