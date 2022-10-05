from hw2_SMS5736 import MySorted

t = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
x = ["e", "d", "c", "b", "a"]

test40 = [90, 'a', 'z', 'c']


def key40(x):
    return sum([ord(item) for item in str(x)])

print(MySorted.bubble_sorted(test40, key=sum(), reverse=True))