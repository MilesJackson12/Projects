import pytest
from StdntAllocTypes import *
from SeqADT import *
from AALst import *
from SALst import *
from Read import *
from DCapALst import *

class TestSeqADT:
    def setup_method(self):
        self.seq = SeqADT([1,2,3])
        self.seq_empty = SeqADT([])

    def teardown_method(self):
        self.seq = None
        self.seq_empty = None

    def test_seq_init(self):
        assert (self.seq.s == [1,2,3])

    def test_seq_next(self):
        with pytest.raises(StopIteration):
            assert self.seq.next() == 1
            assert self.seq.next() == 2
            assert self.seq.next() == 3
            self.seq.next()

    def test_seq_start(self):
        assert self.seq.start() == 1

    def test_seq_end(self):
        assert self.seq.end() == False
        assert self.seq_empty.end() == True


class TestDCapALst:

    def setup_method(self):
        DCapALst.init()
        DCapALst.add(DeptT.civil, 0)
        DCapALst.add(DeptT.chemical, 5)
        DCapALst.add(DeptT.electrical, 7)

    def test_DCap_init(self):
        DCapALst.init()
        assert (DCapALst.s == [])

    def test_DCap_add(self):
        with pytest.raises(KeyError):
            assert (DCapALst.s == [{'dept': DeptT.civil, 'cap': 0}, {'dept': DeptT.chemical, 'cap': 5}, {'dept': DeptT.electrical, 'cap': 7}])
            DCapALst.add(DeptT.civil, 0)

    def test_DCap_remove(self):
        with pytest.raises(KeyError):
            DCapALst.remove(DeptT.civil)
            assert (DCapALst.s == [{'dept': DeptT.chemical, 'cap': 5}, {'dept': DeptT.electrical, 'cap': 7}])
            DCapALst.remove(DeptT.civil)

    def test_DCap_elm(self):
        assert DCapALst.elm(DeptT.civil)
        assert not DCapALst.elm(DeptT.software)

    def test_DCap_capacity(self):
        with pytest.raises(KeyError):
            assert DCapALst.capacity(DeptT.chemical) == 5
            DCapALst.capacity(DeptT.software)

class TestSALst:

    def test_SAlst_init(self):
        SALst.init()
        assert SALst.s == []

    def test_add_one_student(self):
        SALst.init()
        macid = "macid"
        info = SInfoT("firstname", "lastname", "male", 9.2, ["software", "chemical", "materials"], True)
        SALst.add(macid, info)
        assert SALst.s == [('macid', SInfoT(fname='firstname', lname='lastname', gender="male", gpa=9.2, choices=['software', 'chemical', 'materials'], freechoice=True))]

    def test_add_multiple_same_students(self):
        with pytest.raises(KeyError):
            SALst.init()
            macid = "macid"
            info = SInfoT("firstname", "lastname", "male", 9.2, ["software", "chemical", "materials"], True)
            SALst.add(macid, info)
            SALst.add(macid, info)
        
    def test_remove_standard_use(self):
        SALst.init()
        macid = "macid"
        info = SInfoT("firstname", "lastname", "male", 9.2, ["software", "chemical", "materials"], True)
        SALst.add(macid, info)
        SALst.remove("macid")
        assert SALst.s == []

    def test_remove_student_not_in_list(self):
        with pytest.raises(KeyError):
            SALst.init()
            SALst.remove("macid")

    def test_elm_is_there(self):
        SALst.init()
        macid = "macid"
        info = SInfoT("firstname", "lastname", "male", 9.2, ["software", "chemical", "materials"], True)
        SALst.add(macid, info)
        assert (SALst.elm("macid") == True)

    def test_elm_is_not_there(self):
        SALst.init()
        assert (SALst.elm("macid") == False)

    def test_info_proper(self):
        SALst.init()
        macid = "macid"
        info = SInfoT("firstname", "lastname", "male", 9.2, ["software", "chemical", "materials"], True)
        SALst.add(macid, info)
        assert (info == SALst.info("macid"))

    def test_info_not_in_list(self):
        with pytest.raises(KeyError):
            SALst.init()
            SALst.info("macid")

    def test_sort_free_choice(self):
        SALst.init()
        macid = "macid"
        info = SInfoT("firstname", "lastname", "male", 9.2, ["software", "chemical", "materials"], True)
        macid2 = "macid2"
        info2 = SInfoT("fn", "ln", "female", 5.5, ["software", "chemical", "materials"], False)
        SALst.add(macid, info)
        SALst.add(macid2, info2)
        assert (SALst.sort(lambda t: t.freechoice and t.gpa >= 4.0) == [("macid", SInfoT("firstname", "lastname", "male", 9.2, ["software", "chemical", "materials"], True))])

    def test_sort_not_free_choice(self):
        SALst.init()
        macid = "macid"
        info = SInfoT("firstname", "lastname", "male", 9.2, ["software", "chemical", "materials"], True)
        macid2 = "macid2"
        info2 = SInfoT("fn", "ln", "female", 5.5, ["software", "chemical", "materials"], False)
        SALst.add(macid, info)
        SALst.add(macid2, info2)
        assert (SALst.sort(lambda t: not t.freechoice and t.gpa >= 4.0) == [("macid2", SInfoT("fn", "ln", "female", 5.5, ["software", "chemical", "materials"], False))])

    def test_sort_notfc_order(self):
        SALst.init()
        macid = "macid"
        info = SInfoT("firstname", "lastname", "male", 4.2, ["software", "chemical", "materials"], False)
        macid2 = "macid2"
        info2 = SInfoT("fn", "ln", "female", 5.5, ["software", "chemical", "materials"], False)
        SALst.add(macid, info)
        SALst.add(macid2, info2)
        assert (SALst.sort(lambda t: not t.freechoice and t.gpa >= 4.0) == 
        [("macid2", SInfoT("fn", "ln", "female", 5.5, ["software", "chemical", "materials"], False)), ("macid", SInfoT("firstname", "lastname", "male", 4.2, ["software", "chemical", "materials"], False))])

    def test_average_male(self):
        SALst.init()
        macid = "macid"
        info = SInfoT("firstname", "lastname", "male", 4.2, ["software", "chemical", "materials"], False)
        macid2 = "macid2"
        info2 = SInfoT("fn", "ln", "female", 5.5, ["software", "chemical", "materials"], False)
        macid3 = "macid3"
        info3 = SInfoT("f", "l", "male", 10.5, ["software", "chemical", "materials"], True)
        SALst.add(macid, info)
        SALst.add(macid2, info2)
        SALst.add(macid3, info3)
        assert SALst.average(lambda x: x.gender == "male") == 7.35

    def test_average_female(self):
        SALst.init()
        macid = "macid"
        info = SInfoT("firstname", "lastname", "male", 4.2, ["software", "chemical", "materials"], False)
        macid2 = "macid2"
        info2 = SInfoT("fn", "ln", "female", 5.5, ["software", "chemical", "materials"], False)
        macid3 = "macid3"
        info3 = SInfoT("f", "l", "female", 10.5, ["software", "chemical", "materials"], True)
        SALst.add(macid, info)
        SALst.add(macid2, info2)
        SALst.add(macid3, info3)
        assert SALst.average(lambda x: x.gender == "female") == 8

    def test_average_no_students(self):
        with pytest.raises(ValueError):
            SALst.init()
            SALst.average(lambda x: x.gender == "female")

    def test_allocate_proper(self):
        SALst.init()
        macid = "macid"
        info = SInfoT("firstname", "lastname", GenT.male, 4.2, SeqADT([DeptT.civil, DeptT.chemical, DeptT.materials]), False)
        macid2 = "macid2"
        info2 = SInfoT("fn", "ln", GenT.female, 5.5, SeqADT([DeptT.software, DeptT.chemical, DeptT.materials]), False)
        macid3 = "macid3"
        info3 = SInfoT("f", "l", GenT.female, 10.5, SeqADT([DeptT.software, DeptT.chemical, DeptT.materials]), True)
        SALst.add(macid, info)
        SALst.add(macid2, info2)
        SALst.add(macid3, info3)
        DCapALst.init()
        DCapALst.add(DeptT.software, 1)
        DCapALst.add(DeptT.chemical, 1)
        DCapALst.add(DeptT.civil, 1)
        SALst.allocate()
        assert AALst.lst_alloc(DeptT.civil) == ["macid"]
        assert AALst.lst_alloc(DeptT.software) == ["macid3"]
        assert AALst.lst_alloc(DeptT.chemical) == ["macid2"]

    def test_allocate_not_enough_space(self):
        with pytest.raises(RuntimeError):
            SALst.init()
            macid = "macid"
            info = SInfoT("firstname", "lastname", GenT.male, 4.2, SeqADT([DeptT.civil, DeptT.chemical, DeptT.materials]), False)
            SALst.add(macid, info)
            DCapALst.init()
            DCapALst.add(DeptT.materials, 0)
            DCapALst.add(DeptT.chemical, 0)
            DCapALst.add(DeptT.civil, 0)
            SALst.allocate()



