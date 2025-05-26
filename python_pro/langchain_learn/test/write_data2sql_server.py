import pyodbc
import random

# 数据库连接信息，请根据你的配置修改
server = 'rm-bp19b5ak00z11sa24po.sqlserver.rds.aliyuncs.com'
database = 'kb_test'  # 替换为你的数据库名
username = 'wuguang'  # 如果使用身份验证
password = 'ningqiang!NQ'

# 建立连接

# conn = pyodbc.connect(host=server, user=username, password=password, database=database)
conn = pyodbc.connect('DRIVER={SQL Server};SERVER='+server+';DATABASE='+database+';UID='+username+';PWD='+ password)
cursor = conn.cursor()

# 模拟城市列表
cities = [
    "北京", "上海", "广州", "深圳", "成都", "杭州", "南京", "武汉",
    "西安", "长沙", "重庆", "郑州", "苏州", "天津", "青岛", "大连"
]

# 城市等级映射（1=一线, 2=新一线, 3=二线, 4=三线）
city_level_map = {
    "北京": 1,
    "上海": 1,
    "广州": 1,
    "深圳": 1,
    "成都": 2,
    "杭州": 2,
    "南京": 2,
    "武汉": 2,
    "西安": 2,
    "长沙": 2,
    "重庆": 2,
    "郑州": 2,
    "苏州": 2,
    "天津": 3,
    "青岛": 3,
    "大连": 3
}

# 插入数据
for i in range(400):
    city = random.choice(list(city_level_map.keys()))
    city_level = city_level_map[city]
    living_score = random.randint(60, 95)
    is_recommended = random.choice([0, 1])
    price = round(random.uniform(1.5, 15.0), 2)  # 单位：万元/平方米

    cursor.execute("""
        INSERT INTO national_housing_prices (
            city_name,
            city_level,
            living_environment_score,
            is_recommended,
            price
        ) VALUES (?, ?, ?, ?, ?)
    """, (city, city_level, living_score, is_recommended, price))

# 提交事务
conn.commit()

# 关闭连接
cursor.close()
conn.close()

print("成功插入 400 条模拟房价数据。")
