## @file AALst.py
#  @author Andrew Jackson
#  @date Feb 11, 2019
from StdntAllocTypes import *


##@brief A class which creates and object which contains the departments and a list of all the macid's of the students who have been allocated into that department.
# @details The object has 4 routines, one which initializes the departments with empty lists, one to add students, one to remove them, and one to check to see how many students are allocated in a given department.
class AALst():

    ##@brief Initalized the departments in the objects as dictionaries with an empty list.
    @staticmethod
    def init():
        AALst.s = {DeptT.civil: [],
                   DeptT.chemical: [],
                   DeptT.electrical: [],
                   DeptT.mechanical: [],
                   DeptT.software: [],
                   DeptT.materials: [],
                   DeptT.engphys: []}

    ##@brief Adds a macid of a student into the list of students for the input department.
    # @param dep is the Department of type DeptT in which the student is to be allocated in.
    # @param m is the macid of the student being allocated.
    @staticmethod
    def add_stdnt(dep, m):
        for d in AALst.s.keys():
            if dep == d:
                AALst.s[d].append(m)

    ##@brief Returns the of the macid's of the students in the inpute department.
    # @param d is the Department of type DeptT in which you want the list of students.
    # @return Returns the of the macid's of the students in the inpute department.
    @staticmethod
    def lst_alloc(d):
        return AALst.s[d]

    ##@brief Returns the number of students allocated in a given department.
    # @param d is the Department of type DeptT in which you want the number of allocated students.
    # @return Returns the number of students allocated in a given department.
    @staticmethod
    def num_alloc(d):
        return len(AALst.s[d])
