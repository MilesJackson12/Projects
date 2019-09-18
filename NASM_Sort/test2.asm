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
Initial: db "Initial Configuration",0
Final_con: db "Final Configuration",0

SECTION .bss
changeMade: resd 1

SECTION .text
   global  asm_main

Sort:
    push ebp
    mov ebp, esp

    mov ecx, dword[ebp+12]
    mov edx, dword[ebp+8] 

    cmp ecx, 1
    je sorthem_end

    mov eax, ecx                ;push n-1
    sub eax, 1
    push eax

    mov ebx, edx               ;push A[+4]
    add ebx, 4
    push ebx
 
    call Sort           ; call call sorthem(A+4,n-1)

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

        cmp eax, esi                  ;compare i = n-1
        je loop_end

        mov ebx, dword[edx+4+edi]
        mov eax, dword[edx+edi]

        cmp eax, ebx                  ;compare A A+4
        ja loop_end

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

        jmp LOOP


    loop_end:

        cmp dword[changeMade], dword 1
        jne sorthem_end

        push dword[numDisks]
        push pegs
        Call Display
        add esp, 8

    sorthem_end:
        pop ebp
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

    mov [numDisks], eax                     ;store numDisks
    push eax
    mov ecx, pegs               ;eax is peg array
    push ecx
    call rconf                  ;rearanges array
    add esp, 4
    pop ebx                     ;store numDisks in ebx

Print_Array_End:

    call print_nl

    mov eax, Initial
    call print_string
    call print_nl
    
    mov ecx, pegs
    push ebx                ;push numDisks
    push ecx                ;push pegs

    call Display               ;display pegs

    pop ecx
    pop ebx

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

    mov eax, Final_con
    call print_string
    call print_nl

    push dword[numDisks]
    push pegs

    call Display

    add esp, 8

asm_main_end:
    popa
    leave                     
    ret
