## @file Read.py
#  @author Andrew Jackson
#  @date Feb 11, 2019

from StdntAllocTypes import *
from DCapALst import *
from SALst import *

##@brief Reads a file with all the students information and stores it into SALst.
# @param s is the string of the file name in which all the information is stored.
def load_stdnt_data(s):
    fle = open(s, "r")
    stdnt_data = fle.readlines()

    for student in stdnt_data:
        student = student.replace(" ", "")
        student = student.replace("[", "")
        student = student.replace("]", "")
        student = student.strip().split(",")
        print(student)
        macid = student[0]
        fname = str(student[1])
        lname = str(student[2])
        if student[3] == "male":
            gender = GenT.male
        else:
            gender = GenT.female
        gpa = float(student[4])

        choices = []
        i = 5
        while (student[i] != "True" and student[i] != "False"):
            if student[i] == "civil":
                choices.append(DeptT.civil)
            elif student[i] == "chemical":
                choices.append(DeptT.chemical)
            elif student[i] == "electrical":
                choices.append(DeptT.electrical)
            elif student[i] == "mechanical":
                choices.append(DeptT.mechanical)
            elif student[i] == "software":
                choices.append(DeptT.software)
            elif student[i] == "materials":
                choices.append(DeptT.materials)
            elif student[i] == "engphys":
                choices.append(DeptT.engphys)
            i = i + 1

        freechoice = bool(student[i])
        info = SInfoT(fname, lname, gender, gpa, SeqADT(choices), freechoice)
        SALst.add(macid, info)
    fle.close()

##@brief Reads a file with all the departments and their capacities and stores them into DCapALst.
# @param s is the string of the filename where all the department information is stored.
def load_dcap_data(s):
    DCapALst.init()

    fle = open(s, "r")
    dept_data = fle.readlines()

    for dept in dept_data:
        dept = dept.strip.split(",")
        if dept[0] == "civil":
            cur_dept = DeptT.civil
        elif dept[0] == "chemical":
            cur_dept = DeptT.chemical
        elif dept[0] == "electrical":
            cur_dept = DeptT.electrical
        elif dept[0] == "mechanical":
            cur_dept = DeptT.mechanical
        elif dept[0] == "software":
            cur_dept = DeptT.software
        elif dept[0] == "materials":
            cur_dept = DeptT.materials
        elif dept[0] == "engphys":
            cur_dept = DeptT.engphys
        cap = int(dept[1])
        DCapALst.add(cur_dept, cap)
    fle.close()
