from hw2_SMS5736 import MySorted

t1 = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
t2 = ["e", "d", "c", "b", "a"]
t3 = [[1, 3] ,[5, 1], [5, 8], [5, 8], [6, 4], [3, 2, 3], [2, 2], [2, 5], [1, 4], [3, 2]]
ruben = ["a", "badh", "ab", "z"]

test40 = [90, 'a', 'z', 'c']


def key40(x):
    return sum([ord(item) for item in str(x)])

def key(x):
    return max(x)

f = MySorted()
# func = f.bubble_sorted
func = f.merge_sorted

print(func(t3, key=key))
# print(t)


print(func(ruben, key = lambda x: (len(x), x)))