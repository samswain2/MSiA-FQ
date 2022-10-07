class MySorted:

    def __init__(self):
        self.merge_counter = 0

    def bubble_sorted(self, a_list, key = None, reverse = False):
        
        import time
        
        if isinstance(a_list, dict):
            a_list = list(a_list.keys()).copy()
        
        else:
            a_list = a_list.copy()

        ft = time.perf_counter()    

        swaps = 0
        compares = 0

        if not key:
            def key(x):
                return x
            key=key

        for pass_num in range(len(a_list) - 1, 0, -1):
            for i in range(pass_num):
                compares += 1
                if reverse:
                    if key(a_list[i]) < key(a_list[i + 1]):                
                        a_list[i],a_list[i + 1] = a_list[i + 1] ,a_list[i]
                        swaps += 1
                else:
                    if key(a_list[i]) > key(a_list[i + 1]):                
                        a_list[i],a_list[i + 1] = a_list[i + 1] ,a_list[i]
                        swaps += 1


        return [a_list, compares, swaps, time.perf_counter() - ft]

    def merge_sorted(self, a_list, key = None, reverse = False):
        import time
        
        if isinstance(a_list, dict):
            temp = list(a_list.keys())
            
        else:
            temp = a_list.copy()

        self.merge_counter = 0
        
        ft = time.perf_counter()
        MySorted.merge_sorted_process(self, temp, key = key)
        et = time.perf_counter() - ft

        if reverse:
            temp = temp[::-1]

        return [temp, self.merge_counter, 0, et]
    
    def merge_sorted_process(self, a_list, key = None):
        
        if key is None:
            def key(x):
                return x
            key = key

        if len(a_list) > 1:

            mid = len(a_list) // 2
            left_half = a_list[:mid]
            right_half = a_list[mid:]

            MySorted.merge_sorted_process(self, left_half, key = key)
            MySorted.merge_sorted_process(self, right_half, key = key)

            i = 0
            j = 0
            k = 0


            while i < len(left_half) and j < len(right_half):
                self.merge_counter += 1
                if key(left_half[i]) < key(right_half[j]):
                    a_list[k] = left_half[i]
                    i = i + 1
                else:
                    a_list[k] = right_half[j]
                    j = j + 1
                k = k + 1

            while i < len(left_half):
                a_list[k] = left_half[i]
                i = i + 1
                k = k + 1

            while j < len(right_half):
                a_list[k] = right_half[j]
                j = j + 1
                k = k + 1
            
            return a_list
        
        else:
            return a_list
        
    def sort_sorted(self, a_list, key = None, reverse = False):
        import time
    
        temp = a_list.copy()
        
        ft = time.perf_counter()
        temp = sorted(temp, key = key, reverse = reverse)
        et = time.perf_counter() - ft
        return [temp, 0, 0, et]