def double_number(iterable):
    for i in iterable:
        yield i + i


for i in double_number(range(1, 90000)):
    print(i)
    if i >= 30:
        break
values = (-x for x in [1, 2, 3, 4, 5, 6, 7, 8, 9])
for x in values:
    print(x)

values = (-x for x in [1, 2, 3, 4, 5])
gen_to_list = list(values)
print(gen_to_list)  # => [-1, -2, -3, -4, -5]
