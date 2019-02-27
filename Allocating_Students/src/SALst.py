## @file SALst.py
#  @author Andrew Jackson
#  @date Feb 11, 2019

from StdntAllocTypes import *
from AALst import *
from DCapALst import *

##@brief Is a class for a an object to store and perform operations on a list of all of the students.
# @details The object for the students allows you to initialize an empty list of students, add students who are not already in the object, remove students, check to see if a student is in the object, return the information of a student, sort the students, calculate the average of the students, and allocate the students into the departments. 
class SALst():

    ##@brief Initialized the object to an empty list of students.
    @staticmethod
    def init():
        SALst.s = []

    ##@brief Adds a students macid and info to the list, unless they are already in the object in which it returns a KeyError.
    # @param m is the macid of the student being added.
    # @param i is the information of the student being added.
    @staticmethod
    def add(m, i):
        for student in SALst.s:
            if student[0] == m:
                raise KeyError
        SALst.s.append((m, i))

    ##@brief Removes a student from the list unless they are not there in which it returns a KeyError.
    # @param m is the macid of the student to be removed.
    @staticmethod
    def remove(m):
        name = 1
        for student in SALst.s:
            if student[0] == m:
                name = student

        if name != 1:
            SALst.s.remove(name)
            return

        raise KeyError

    ##@brief Checks to see if a student is in the list.
    # @param m is the macid of the student in the list.
    # @return Returns True if the student is in the list otherwise returns False.
    @staticmethod
    def elm(m):
        for student in SALst.s:
            if student[0] == m:
                return True
        return False

    ##@brief Returns the info of a given student in the list, if they are not there raises a KeyError.
    # @param m is the macid of the student you want the info of.
    # @return Returns the info of the given student.
    @staticmethod
    def info(m):
        for student in SALst.s:
            if student[0] == m:
                return student[1]
        raise KeyError

    ##@brief Creates a new list of the macid's of the students who meet the input criteria.
    # @param f is a function which returns a boolean that is used as the sorting criteria.
    # @return Returns the list of the macid's of students who meet the given criteria.
    @staticmethod
    def sort(f):
        lst = []
        for student in SALst.s:
            if f(student[1]):
                lst.append(student)
        sorted_list = sorted(lst, key=lambda g: get_gpa(g[0], lst), reverse=True)
        return sorted_list
        #worked with Chris Vishnu

    ##@brief Calculates the average of all the students in a given input criteria, and raises a ValueError if no students meet that criteria.
    # @param f is a function which returns a boolean that is used as the criteria in which students are counted for the average.
    # @return Returns a float of the calculated average.
    @staticmethod
    def average(f):
        num_students = 0
        average = 0
        for student in SALst.s:
            if f(student[1]):
                average += getattr(student[1], "gpa")
                num_students += 1
        if num_students == 0:
            raise ValueError
        return average / num_students

    ##@brief Allocated students into the proper departments.
    # @details Allocates the students first by sorting all students with free choice then allocating them. It then sorts all of the students without freechoice by their gpa and allocates them in order of highest gpa as long as there is still space if not it trys the students next choices. If the student cannot be allocated anywhere it raises a RunTimeError.
    @staticmethod
    def allocate():
        AALst.init()
        free = SALst.sort(lambda t: t.freechoice and t.gpa >= 4.0)
        for m in free:
            ch = SALst.info(m[0]).choices
            AALst.add_stdnt(ch.next(), m[0])

        stdnt = SALst.sort(lambda t: not (t.freechoice) and t.gpa >= 4.0)

        for m in stdnt:
            ch = SALst.info(m[0]).choices
            alloc = False
            while(not(alloc) and not(ch.end())):
                d = ch.next()
                if AALst.num_alloc(d) < DCapALst.capacity(d):
                    print(m[0])
                    AALst.add_stdnt(d, m[0])
                    alloc = True
            if (not(alloc)):
                raise RuntimeError

##@brief Returns the gpa of the input student, if they are in the list of students.
# @param m is the macid of the student you want the gpa of.
# @param s is the object SALst with the list of all the students.
# @return Returns the gpa of the input student.
def get_gpa(m, s):
    for student in s:
        if student[0] == m:
            return getattr(student[1], "gpa")
