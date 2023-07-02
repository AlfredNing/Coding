module.exports = {
    module: {
        rules: [
            // {
            //     test: /\.css$/,
            //     use: [
            //         // {
            //         //     // loader: "css-loader"

            //         // }
            //         // loader 加载属性 从后面往前进行
            //         "style-loader",
            //         "css-loader",
            //         // {
            //         //     loader:"postcss-loader",
            //         //     options: {
            //         //         postcssOptions:{
            //         //             plugins :[
            //         //                 // require("autoprefixer")
            //         //             ]
            //         //         }
            //         //     }
            //         // }
            //     ]
            // },
            {
                test: /\.css$/,
                use: [
                    "style-loader",
                    "css-loader",
                    // "less-loader"
                ]
            }
        ]
    }
}