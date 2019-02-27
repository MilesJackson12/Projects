## @file DCapALst.py
#  @author Andrew Jackson
#  @brief Creates an object for the deparments and their capacities.
#  @details The object created for the department uses a list of dictionaries which can be initialized to empty, and allows departements to be added as dictionaries with their capcities, removed, to check to see if that department is in the object, and to check the capacity of a department in the object.


##@brief Creates an object for the deparments and their capacities.
# @details The object created for the department uses a list of dictionaries which can be initialized to empty, and allows departements to be added as dictionaries with their capcities, removed, to check to see if that department is in the object, and to check the capacity of a department in the object.
class DCapALst():
    ##@brief Initialized the object to an empty list.
    @staticmethod
    def init():
        DCapALst.s = []

    ##@brief Adds a dictionary containing a department and a capacity unless the department is already in the object in which it raises a KeyError.
    # @param d is the department of type DeptT that is to be added.
    # @param n is the capacity of the department to be added.
    @staticmethod
    def add(d, n):
        for dep in DCapALst.s:
            if dep["dept"] == d:
                raise KeyError
        DCapALst.s.append({"dept": d, "cap": n})

    ##@brief Removes the department and its capacity as long as that department is in the object else raises a KeyError.
    # @param d is the department of type DeptT that is to be removed.
    @staticmethod
    def remove(d):
        for dep in DCapALst.s:
            if dep["dept"] == d:
                DCapALst.s.remove(dep)
                return
        raise KeyError

    ##@brief Checks to see if a department is in the object.
    # @param d is the department of type DeptT that is checked to see if it is in the object.
    # @return Returns True if the departement is in the object and False if it is not.
    @staticmethod
    def elm(d):
        for dep in DCapALst.s:
            if dep["dept"] == d:
                return True
        return False

    ##@brief Returns the capcity of a given department as long as that department is in the object, else it raises a KeyError.
    # @param d is the department of type DeptT that you want the capacity of.
    # @return Returns the capcity of given department.
    @staticmethod
    def capacity(d):
        for dep in DCapALst.s:
            if dep["dept"] == d:
                return dep["cap"]
        raise KeyError
