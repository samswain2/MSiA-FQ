# coding: utf-8

import traceback
import sys

from hw2_SMS5736 import MySorted

# empty list
test1 = []

# pure numbers:
test20 = [1, 2, 3, 4, 5, 6, 7, 8]
test21 = [7, 5, 32, 61]
test22 = [2]
test23 = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
test24 = [5, 4, 3, 2, 1]

# pure alpha:
test30 = ['a', 'b', 'c', 'd', 'e']
test31 = ['a', 'b']
test32 = ['e', 'd', 'c', 'b', 'a']

# combining number with alpha
test40 = [90, 'a', 'z', 'c']


def key40(x):
    return sum([ord(item) for item in str(x)])


def print_error(actual, expected, desc):
    print('#' * 50)
    incorrect_str = 'testing ' + desc + ' ------- INCORRECT'
    print(incorrect_str)
    print('expected:', end=' ')
    print(expected)
    print('got', end=' ')
    print(actual)
    # exc_type,  exc_value,  exc_traceback = sys.exc_info()
    # traceback.print_exception(exc_type,  exc_value,  exc_traceback,
    # limit=2,  file=sys.stdout)
    print('#' * 50)
    print()


def print_correct(desc):
    # print('#' * 50)
    correct_str = 'testing ' + desc + ' ------- CORRECT'
    # print(correct_str)
    # print('#' * 50)
    # print("CORRECT")
    # print()


def test_correctness(func, test_itr, key, reverse, desc):
    # if the returned and the first element of the list is the same,
    # then return 1.
    result = sorted(test_itr, key=key, reverse=reverse)
    correct = 0
    try:
        test = func(test_itr, key=key, reverse=reverse)
        # print(test)
    except:
        print_error(None, result, desc)

    try:
        assert(test == result)
        correct = 1
    except:
        try:
            assert(test[0] == result)
            correct = 1
        except:
            try:
                assert(test[-1] == result)
                correct = 1
            except:
                print_error(func(test_itr, key=key, reverse=reverse),
                            result, desc)
    finally:
        return correct


def test_case(func, test_itr, desc='', key=None, reverse=False):
    correct = 0
    if key is None:
        key = lambda x: x
    correct = test_correctness(func, test_itr, key, reverse, desc)
    if correct:
        print_correct(desc)
    return correct


def runner_reverse(func, test, key=None):
    # print(func, test, key)
    return test_case(func, test,
                     desc='[' + ', '.join([str(item) for item in test]) + ']',
                     key=key,
                     reverse=False) + \
           test_case(func, test,
                     desc='reverse' + '[' +
                          ', '.join([str(item) for item in test]) +
                          ']',
                     key=key,
                     reverse=True)


def grade_test_main(func):
    return 20 \
    - runner_reverse(func, test1) \
    - runner_reverse(func, test20) \
    - runner_reverse(func, test21) \
    - runner_reverse(func, test22) \
    - runner_reverse(func, test23) \
    - runner_reverse(func, test24) \
    - runner_reverse(func, test30) \
    - runner_reverse(func, test31) \
    - runner_reverse(func, test32) \
    - runner_reverse(func, test40, key=key40)

my_sorted = MySorted()

print ('bubble incorrect cases: %d /20,  merge incorrect cases: %d /20'
       % (grade_test_main(my_sorted.bubble_sorted), grade_test_main(my_sorted.merge_sorted)))
