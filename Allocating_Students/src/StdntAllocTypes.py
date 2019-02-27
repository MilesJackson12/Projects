## @file StdntAllocTypes.py
#  @author Andrew Jackson
#  @brief Defines abstract types.
#  @details Contains 3 classes GenT, DeptT, and SInfoT which are abstract types for gender, the departments, and student information.
#  @date Feb 11, 2019

from SeqADT import *
from enum import Enum
from typing import NamedTuple


##@brief An abstract type created using enumeration with 2 values male and female to represent the genders.
class GenT(Enum):
    male = 0
    female = 1


##@brief An abstract type created using enumeration with the 7 departments that students choose and can be allocated into.
class DeptT(Enum):
    civil = 0
    chemical = 1
    electrical = 2
    mechanical = 3
    software = 4
    materials = 5
    engphys = 6

##@brief An abstract type created using a named tuple which contains all of the students information in its proper type.
class SInfoT(NamedTuple):
    fname: str
    lname: str
    gender: GenT
    gpa: float
    choices: SeqADT
    freechoice: bool
