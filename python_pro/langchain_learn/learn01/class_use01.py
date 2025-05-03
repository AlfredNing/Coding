# 定义一个类
import sys


class Human:
    # 类属性
    species = "Hi,species"

    def __init__(self, name):
        self.name = name
        self._age = 0

    # 成员函数
    def say(self, msg):
        print('{name}:{message}'.format(name=self.name, message=msg))

    def sing(self):
        return 'yo... yo... microphone check... one two... one two...'

    # 类函数
    @classmethod
    def get_species(cls):
        return cls.species

    # 静态函数
    @staticmethod
    def grunt():
        return "*grunt*"

    @property
    def age(self):
        return self._age

    @age.setter
    def age(self, age):
        self._age = age

    @age.deleter
    def age(self):
        del self._age


class Superhero(Human):
    # pass
    species = 'Superhuman'

    def __init__(self, name, movie=False,
                 superpowers=["super strength", "bulletproofing"]):
        # add additional class attributes:
        # 额外新增的参数
        self.fictional = True
        self.movie = movie
        # be aware of mutable default values, since defaults are shared
        self.superpowers = superpowers
        super().__init__(name)

    def sing(self):
        return 'Dun, dun, DUN!'

    def boast(self):
        for power in self.superpowers:
            print("I wield the power of {pow}!".format(pow=power))


if __name__ == '__main__':
    sup = Superhero(name="Tick")
    if isinstance(sup, Human):
        print('I am human')
    if type(sup) is Superhero:
        print('I am a superhero')
    print(Superhero.__mro__)
    print(sup.get_species())
    # i = Human('John')
# i.say('hi')
# j = Human('Joel')
# j.say('hello')
# i.say(i.get_species())
# Human.species = "H. neanderthalensis"
# i.say(i.get_species())  # => "Ian: H. neanderthalensis"
# j.say(j.get_species())
# print(Human.grunt())
# print(i.grunt())  # => TypeError: grunt() takes 0 positional arguments but 1 was given
# i.age = 42
# print(sys.version)
