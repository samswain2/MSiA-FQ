# Create new_sorted class for two sorting functions for use in place if sorted()
class MySorted:
    # Sorting function one (Bubble)
    def bubble_sorted(arr, key = None, reverse = False):
        # Create comparisons and swaps vars to count number
        # of comparisons and swaps
        comparisons = 0 
        swaps = 0
        # Occurs if key doesn't exist
        if not key:
            # Loops through array indices
            for _ in range(0, len(arr)):
                # Check if actions are taken from last loop
                try:
                    # If no actions taken break loop,
                    # no more swaps needed
                    if swaps == temp:
                        break
                # Pass if actions may be needed
                except NameError:
                    pass
                # Set temp to swaps to check for change is list
                # from last loop
                temp = swaps
                # Checks each value against the next item in array
                for i in range(0, len(arr)-1):
                    # If the current value is greater than the next value
                    # switch the two and move on to the next
                    if arr[i] > arr[i+1]:
                        arr[i], arr[i+1] = arr[i+1], arr[i]
                        # Count swap
                        swaps += 1
                    # Count comparison
                    comparisons += 1
        # Occurs if key does exist
        else:
            # Loops through arr indices
            for _ in range(0, len(arr)):
                # Check if actions are taken from last loop
                try:
                    # If no actions taken break loop,
                    # no more swaps needed
                    if swaps == temp:
                        break
                # Pass if actions may be needed
                except NameError:
                    pass
                # Set temp to swaps to check for change is list
                # from last loop
                temp = swaps
                # Checks each value against the next item in array
                for i in range(0, len(arr)-1):
                    # If the specified value in the sublist is greater than
                    # the number of the same index in the next array,
                    # switch the whole array/class values
                    if key(arr[i]) > key(arr[i+1]):
                        arr[i], arr[i+1] = arr[i+1], arr[i]
                        # Count swap
                        swaps += 1
                    # Count comparison
                    comparisons += 1
        # If reverse is set to true, return the reversed array and data
        if reverse:
            return arr[::-1]
        # Return normal sorted array and data
        return arr
    
    # Define merge sort function
    def merge_sorted(a_list, key = None, reverse = False):
        # Check if key is given
        if not key:
            # Check if length of list is longer then 1
            if len(a_list) > 1:
                # Start comparisons
                comparisons = 0
                # Get middle index
                mid = len(a_list) // 2
                # Split lst into 2 down the middle
                left_half = a_list[:mid]
                right_half = a_list[mid:]
                # Recursive
                MySorted.merge_sorted(left_half)
                MySorted.merge_sorted(right_half)

                i = 0
                j = 0
                k = 0


                while i < len(left_half) and j < len(right_half):
                    # Count comparisons
                    comparisons += 1
                    if left_half[i] < right_half[j]:
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
                # Check if list needs to be reversed 
                if reverse == True:
                    # Return sorted list, # of comparisons and swaps, and runtime
                    return a_list[::-1]
                else:
                    # Return sorted list, # of comparisons and swaps, and runtime
                    return a_list
        # Check if key is given
        else:
            # Check if length of list is longer then 1
            if len(a_list) > 1:
                # Start comparisons
                comparisons = 0
                # Get middle index
                mid = len(a_list) // 2
                # Split lst into 2 down the middle
                left_half = a_list[:mid]
                right_half = a_list[mid:]
                # Recursive
                MySorted.merge_sorted(left_half, key = key)
                MySorted.merge_sorted(right_half, key = key)

                i = 0
                j = 0
                k = 0


                while i < len(left_half) and j < len(right_half):
                    # Count comparisons
                    comparisons += 1
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
                # Check if list needs to be reversed    
                if reverse == True:
                    # Return sorted list, # of comparisons and swaps, and runtime
                    return a_list[::-1]
                else:
                    # Return sorted list, # of comparisons and swaps, and runtime
                    return a_list