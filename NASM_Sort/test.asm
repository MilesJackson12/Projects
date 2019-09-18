%include "asm_io.inc"

SECTION .data
msg1: db "Incorrect number of command line arguments",0
msg2: db "1Incorrect argument should be an integer from 2-9",0
msg3: db "2Incorrect argument should be an integer from 2-9",0
numDisks: dd 0
pegs: dd 0,0,0,0,0,0,0,0,0

L1: db "          o|o          ",0
L2: db "         oo|oo         ",0
L3: db "        ooo|ooo        ",0
L4: db "       oooo|oooo       ",0
L5: db "      ooooo|ooooo      ",0
L6: db "     oooooo|oooooo     ",0
L7: db "    ooooooo|ooooooo    ",0
L8: db "   oooooooo|oooooooo   ",0
L9: db "  ooooooooo|ooooooooo  ",0
Base: db "XXXXXXXXXXXXXXXXXXXXXXX",0
test: db "test",0
test2: db "test2",0
startSort: db "Sort Started",0
reachSort: db "Reached Sort",0
Valstore: db "Values stored off stack",0
diskNot1: db "Number of disks is not 1",0
diskMinus: db "One disks subtracted",0
strMoved: db "Index string + 1",0
recurCall: db "Recursion Called",0
recurDone: db "Recursion Ended",0 
setn1: db "Set eax to n-1",0
comparein: db "i not equal n-1",0
ooo: db "Numbers out of order",0
swapped: db "Numbers swapped",0
reachDisp: db "Reached display",0


SECTION .bss
changeMade: resd 1

SECTION .text
   global  asm_main

Sort:
    push ebp
    mov ebp, esp

    mov eax, startSort
    call print_string
    call print_nl

    mov ecx, dword[ebp+12]
    mov edx, dword[ebp+8] 

    mov eax, Valstore
    call print_string
    call print_nl
    
    cmp ecx, 1
    je sorthem_end

    mov eax, diskNot1
    call print_string
    call print_nl

    mov eax, ecx                ;push n-1
    sub eax, 1
    push eax

    mov eax, diskMinus
    call print_string
    call print_nl

    ;mov eax, dword[edx]
    ;call print_int
    ;call print_nl

    mov ebx, edx               ;push A[+4]
    add ebx, 4
    push ebx

    mov eax, strMoved
    call print_string
    call print_nl

    mov eax, recurCall
    call print_string
    call print_nl
 
    call Sort           ; call call sorthem(A+4,n-1)

    mov eax, recurDone
    call print_string
    call print_nl

    add esp, 8            ;restore stack
    
    mov ecx, [ebp+12]
    mov edx, [ebp+8]

    mov esi, 0
    mov edi, 0
    
    mov [changeMade], dword 0  
    LOOP:
	mov ecx, [ebp+12]
        mov edx, [ebp+8]

        mov eax, ecx             
        sub eax, 1                    ;move eax to n-1

push eax
        mov eax, setn1
        call print_string
        call print_nl
pop eax
        cmp eax, esi                  ;compare i = n-1
        je loop_end
 
        mov eax, reachSort
        call print_string
        call print_nl

        mov ebx, dword[edx+4+edi]
        mov eax, dword[edx+edi]

        cmp eax, ebx                  ;compare A A+4
        ja loop_end

        mov eax, ooo
        call print_string
        call print_nl

        mov eax, edx
        add eax, 4
        mov ebx, dword[edx+edi+4]
        push ebx
     
        mov ebx, dword[edx+edi]
        push ebx

        pop dword[edx+4+edi]
        pop dword[edx+edi]                 ; Swap Values
 
        mov [changeMade], dword 1

        add edi, 4
        inc esi
        mov eax, swapped
        call print_string
        call print_nl

        jmp LOOP


    loop_end:

        mov eax, reachDisp
        call print_string
        call print_nl

        cmp dword[changeMade], dword 1
        jne sorthem_end

        push dword[numDisks]
        push pegs
        Call Display
        add esp, 8

    sorthem_end:
        pop ebp
;        leave
        ret

Display:
    push ebp
    mov ebp, esp

    mov ecx, dword[ebp+12]	
    mov ebx, dword[ebp+8]    
    
    mov eax, 4
    mul ecx
    push eax
    pop edx

    sub eax, 4
    mov edx, eax

    mov eax, dword[ebx+edx]
    call print_int 
    call print_nl

    mov ecx, 0
    jmp Skip_First
    disLOOP:
	mov eax, edx
	sub eax, 4
	mov edx, eax
	mov eax, dword[ebx+edx]
        inc ecx
       
    Skip_First:
	 cmp ecx, [ebp+12]
        je disLOOP_end	

	cmp eax, 1
        jne P2
        mov eax, L1
        call print_string
        call print_nl
        jmp disLOOP

	P2:
        cmp eax, 2
        jne P3
        mov eax, L2
        call print_string
        call print_nl
        jmp disLOOP

	P3:
        cmp eax, 3
        jne P4
        mov eax, L3
        call print_string
        call print_nl
        jmp disLOOP

	P4:
        cmp eax, 4
        jne P5
        mov eax, L4
        call print_string
        call print_nl
        jmp disLOOP

	P5:
        cmp eax, 5
        jne P6
        mov eax, L5
        call print_string
        call print_nl
        jmp disLOOP

        P6:
	cmp eax, 6
        jne P7
        mov eax, L6
        call print_string
        call print_nl
        jmp disLOOP

	P7:
        cmp eax, 7
        jne P8
        mov eax, L7
        call print_string
        call print_nl
        jmp disLOOP

        P8:
	cmp eax, 8
        jne P9
        mov eax, L8
        call print_string
        call print_nl
        jmp disLOOP

	P9:
        cmp eax, 9
        jne disLOOP_end
        mov eax, L9
        call print_string
        call print_nl
        jmp disLOOP

    disLOOP_end:
    mov eax, Base
    call print_string
    call print_nl

    call read_char

    pop ebp
    ret

asm_main:
    enter 0,0
    pusha

    mov eax, [ebp+8]                        ;eax is num arguments
    cmp eax, 2          
    je Correct_Number_of_Arguments
    mov eax, msg1
    call print_string
    call print_nl
    jmp asm_main_end

Correct_Number_of_Arguments:
    mov ebx, dword[ebp+12]                   ;ebx is argv[]
    mov ecx, dword[ebx+4]                   ;ecx is numDisks
  
    mov eax,0                                   
    mov al, byte[ecx]
    sub eax, dword'0'                       ;eax is numDisks
   
    call print_int
    call print_nl

    cmp eax, 2                              ;check greater than or equal 2
    jge Greater_than_2
    mov eax, msg2
    call print_string
    call print_nl
    jmp asm_main_end

Greater_than_2:
    cmp eax, 9                              ;check less than or equal to 9
    jbe Correct_Argument
    mov eax, msg3
    call print_string
    call print_nl
    jmp asm_main_end

Correct_Argument:
    call print_int                          ;print numDisks
    call print_nl

    mov [numDisks], eax                     ;store numDisks
    push eax
    mov ecx, pegs               ;eax is peg array
    push ecx
    call rconf                  ;rearanges array
    add esp, 4
    pop ebx                     ;store numDisks in ebx

    call print_nl

    mov ecx, 1                  ;set ecx 1
    mov edx, 0                  ;set edx 0

    mov eax, dword[pegs]               ;prints first value in pegs 
    call print_int
    call print_nl

Print_Array:
    cmp ebx, ecx                 ;ebx has num of disks
    je Print_Array_End
    mov eax, edx
    add eax, 4
    mov edx, eax
    mov eax, [pegs+edx]
    call print_int
    call print_nl
    inc ecx
    jmp Print_Array

Print_Array_End:

    call print_nl
    mov ecx, pegs
    push ebx                ;push numDisks
    push ecx                ;push pegs

    call Display               ;display pegs

    pop ecx
    pop ebx
   
;    mov eax, ebx            ;set eax numDisks
 ;   sub eax, 1              ;set eax numDisks-1
  ;  push eax                ;push numDisks-1
   ; mov eax, dword[ecx+4]   
    ;push eax                ;push A+4

    push dword[numDisks]
    mov eax, pegs
    add eax, 4
    push eax

    call Sort

    add esp, 8              ;restore stack

    push dword[numDisks]
    push pegs

    Call Sort

    add esp, 8


asm_main_end:
    popa
    leave                     
    ret
