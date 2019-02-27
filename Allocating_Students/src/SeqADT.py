## @file SeqADT.py
#  @author Andrew Jackson
#  @brief A module that defines an Abstract Data Type.
#  @details Contains a class SeqADT with 4 routines to define a ADT. It allows you to create abstract objects which can easily be iterated though, restart the iteration, and check to see if there are still more items in the sequence.
#  @date Feb 11, 2019


##@brief A module that defines an Abstract Data Type
# @details Contains a class SeqADT with 4 routines to define a ADT. It allows you to create abstract objects which can easily be iterated though, restart the iteration, and check to see if there are still more items in the sequence.
class SeqADT:
    ##@brief Constructs the ADT sequence and begins by pointing to the beginning of the sequence.
    # @param s is the sequence to be stored in the ADT.
    def __init__(self, s):
        self.s = s
        self.i = 0

    ##@brief Returns the indexer to the begining of the sequence and returns the first value.
    # @return Returns the first element in the sequence.
    def start(self):
        self.i = 0
        return self.s[self.i]

    ##@brief Increaces the indexer by 1 and returns the value it was pointing at before. If the old index was out of the range it raises an exception.
    # @return Returns the element in the Sequence in which the indexer was pointing before the increment.
    def next(self):
        self.i += 1
        if (self.i >= len(self.s)):
            raise StopIteration

        return self.s[self.i - 1]

    ##@brief Checks to see if the index is still in Range of the sequence.
    # @return Returns True if index is out of range and False otherwise.
    def end(self):
        if (self.i >= len(self.s) - 1):
            return True
        return False
